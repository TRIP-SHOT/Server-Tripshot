package com.tripshot.board.controller;

import com.tripshot.board.dto.BoardResponseDto;
import com.tripshot.board.dto.WriteBoardRequestDto;
import com.tripshot.board.dto.WriteBoardResponseDto;
import com.tripshot.global.util.s3.S3Uploader;
import com.tripshot.user.model.CustomUserDetails;
import com.tripshot.user.service.UserService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tripshot.board.dto.Board;
import com.tripshot.board.service.BoardService;
import com.tripshot.global.ApiResponse;

@CrossOrigin("*")
@RequestMapping("/boards")
@RestController
@Slf4j
@RequiredArgsConstructor
public class BoardController {

	private final BoardService service;
	private final UserService userService;
	private final S3Uploader s3Uploader;
	private final String DIR = "album";

	/**
	 * 조회 및 검색
	 * 
	 * @param season    계절(봄,여름,가을,겨울)
	 * @param startDate 필터시작일
	 * @param endDate   종료일
	 * @param keyword   검색어
	 * @return 게시글 목록
	 */
	@GetMapping
	public ResponseEntity<ApiResponse<List<BoardResponseDto>>> list(
			@RequestParam(value = "season", required = false) String season,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "keyword", required = false) String keyword) {

		// SecurityContextHolder에서 Authentication 객체를 가져옵니다.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Long userPk = 0L;
		if (authentication != null && authentication.isAuthenticated()
				&& !(authentication instanceof AnonymousAuthenticationToken)) {
			// Authentication 객체에서 Principal을 가져옵니다.
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			// 인증된 사용자 정보를 활용하는 로직 추가
			// 예: userDetails.getUsername(), userDetails.getAuthorities() 등
			userPk = userDetails.getUser().getId();
		}

		List<BoardResponseDto> response = null;

		if (season != null || startDate != null || endDate != null || keyword != null) {// 검색 조건이 있는 경우
			System.out.println("조건있다.");
			response = service.search(season, startDate, endDate, keyword);
		} else { // 검색 기준이 없는 경우
			System.out.println("조건없다.");

			response = service.selectAll(userPk);
			log.info("response={}", response);
		}
		return new ResponseEntity(new ApiResponse(HttpStatus.OK, "게시글 목록 조회 성공", response), HttpStatus.OK);
	}

	/**
	 * 게시글 상세조회
	 * 
	 * @param id board(게시글)의 고유한 id
	 * @return 해당하는 고유한 게시글
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<BoardResponseDto>> detail(@PathVariable("id") Long id) {
		BoardResponseDto response = service.selectOne(id, null);
		return new ResponseEntity(new ApiResponse(HttpStatus.OK, "게시글 상세조회 조회 성공", response), HttpStatus.OK);
	}

	/**
	 * 게시글 작성
	 * 
	 * @param request 게시글 작성에 필요한 값들
	 * @return 고유id
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<?>> writeBoard(@ModelAttribute WriteBoardRequestDto request) throws IOException {
		// request의 image는 multipartFile, 이를 s3에 업로드하여 url을 생성한뒤 게시글등록 과정에 사용함.
		Board board = request.toBoard();
		String[] keyAndUrl = s3Uploader.upload(request.getImage(), DIR);
		board.setImageKey(keyAndUrl[0]);
		board.setImage(keyAndUrl[1]);

		service.insertBoard(board);
		WriteBoardResponseDto response = new WriteBoardResponseDto(board.getId());
		return new ResponseEntity(new ApiResponse(HttpStatus.OK, "게시글 생성 성공", response), HttpStatus.OK);
	}

	/**
	 * 게시글 수정
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@PutMapping
	public ResponseEntity<ApiResponse<?>> updateBoard(@ModelAttribute WriteBoardRequestDto request) throws IOException {
		Board board = request.toBoard();
		String[] keyAndUrl = s3Uploader.upload(request.getImage(), DIR);
		board.setImageKey(keyAndUrl[0]);
		board.setImage(keyAndUrl[1]);
		service.updateBoard(board);
		// service - 게시글 id를 통해서 해당 내용을 모두 엎어쓰기한다.
		return new ResponseEntity(new ApiResponse(HttpStatus.OK, "게시글 수정 성공", "게시글 수정을 성공했습니다."), HttpStatus.OK);
	}

	/**
	 * 게시글 삭제
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping
	public ResponseEntity<ApiResponse<?>> deleteBoard(@RequestParam("id") Long id) {
		service.deleteBoard(id);
		return new ResponseEntity(new ApiResponse(HttpStatus.OK, "게시글 삭제 성공", "게시글 삭제 성공하였습니다."), HttpStatus.OK);
	}

	@PostMapping("/heart")
	public ResponseEntity<ApiResponse<?>> addHeart(@RequestParam("id") Long boardId){
		//사용자의 고유id와 boardId를 찾는다.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Long userPk = 0L;
		if (authentication != null && authentication.isAuthenticated()
				&& !(authentication instanceof AnonymousAuthenticationToken)) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			userPk = userDetails.getUser().getId();
		}
		
		int result = service.update(userPk,boardId);
		return new ResponseEntity(new ApiResponse (HttpStatus.OK, "좋아요 요청 성공!", "좋아요 요청 성공!"), HttpStatus.OK);
	}

}
