package com.tripshot.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/board")
@RestController
public class BoardController {
	
	BoardService service;
	
	@Autowired
	public BoardController(BoardService service) {
		this.service = service;
	}

	@GetMapping("/list")
	public ResponseEntity<ApiResponse<List<Board>>> list(){
		List<Board> list = service.selectAll();
		return new ResponseEntity(new ApiResponse(HttpStatus.OK, "게시글 조회 성공", list), HttpStatus.OK); 
	}
	
	@GetMapping("/search")
	public ResponseEntity<ApiResponse<List<Board>>> search(@RequestParam("keyword") String keyword){
		List<Board> list = service.search(keyword);
		return new ResponseEntity(new ApiResponse(HttpStatus.OK, "게시글 키워드 검색 성공", list), HttpStatus.OK); 
	}
	
	@GetMapping("/list/{num}")
	public  ResponseEntity<ApiResponse<Board>> detail(@PathVariable String num){
		Board b = service.selectOne(num);
		return new ResponseEntity(new ApiResponse(HttpStatus.OK, "게시글 상세조회 성공", b), HttpStatus.OK); 

	}
	
	@PostMapping("/insert")
	public ResponseEntity insert(@RequestBody Board b) {
		System.out.println(b+"in insert");
		service.insert(b);
		return new ResponseEntity(new ApiResponse(HttpStatus.OK, "게시글 작성 성공", b), HttpStatus.OK); 
	}
	
	@PutMapping("/modifyContent")
	public ResponseEntity modifyContent(@RequestBody Board b) {
		System.out.println("inupdate"+ b);
		service.modify(b);
		return new ResponseEntity(new ApiResponse(HttpStatus.OK, "게시글 수정 성공", b), HttpStatus.OK); 

	}
	
//	@PutMapping("/modifyContent/{num}")
//	public ResponseEntity modifyContent(@RequestBody Board b, @PathVariable String num) {
//		System.out.println("inupdate"+ b +" "+num);
//		service.modifyContent(b.getContent(), num);
//		return new ResponseEntity(new ApiResponse(HttpStatus.OK, "게시글 수정 성공", b), HttpStatus.OK); 
//
//	}
//	
	@DeleteMapping("/delete/{num}")
	public ResponseEntity delete(@PathVariable String num) {
		service.delete(num);
		return new ResponseEntity(new ApiResponse(HttpStatus.OK, "게시글 삭제 성공", null), HttpStatus.OK); 

	}
}
