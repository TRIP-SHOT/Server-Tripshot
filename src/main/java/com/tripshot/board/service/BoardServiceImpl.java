package com.tripshot.board.service;

import com.tripshot.board.dto.BoardResponseDto;
import com.tripshot.global.util.s3.S3Uploader;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripshot.board.dto.Board;
import com.tripshot.board.mapper.BoardMapper;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardMapper mapper;
	private final String DIR = "/album";
	private final S3Uploader s3Uploader;
	@Override
	public List<BoardResponseDto> selectAll(Long userPk) {
		List<BoardResponseDto> boards = mapper.selectAll();
		
		for(BoardResponseDto board : boards) {
			//heart_count 로직 추가
			//board_id가 heart talbe에 몇개 있는지 센다음 반환함.
			board.setHeartCount(mapper.countHeartCount(board.getId()));
			//Is_Like 로직 추가
			//userPk와 boardId가 heart table에 존재(한개만 존재가 가능하다)하는 경우 true;
			board.setIsLike(false);
			if(mapper.checkUserHeartBoard(userPk,board.getId()) > 0) {
				board.setIsLike(true);
			}
			log.info("board={}",board);
		}
		
		return boards;
	}

	@Override
	public List<BoardResponseDto> search(String season, String startDate, String endDate, String keyword){
		List<BoardResponseDto> boards = mapper.search(season, startDate, endDate, keyword);
		//isLike와 heartCount를 넣어줘야함.
		for(BoardResponseDto board: boards) {
			//isLike여부

			//heartCount여부
		}
		return boards;

	}

	@Override
	public BoardResponseDto selectOne(Long id, String userLoginId) {
		mapper.hitCountUp(id);
		return mapper.selectOne(id);
	}

	@Override
	public int insertBoard(Board board) {
		return mapper.insertBoard(board);
	}

	@Override
	public int updateBoard(Board board) {
		//기존의 imageUrl을 가져온 후 삭제한다.
		String imageKey= mapper.selectImageKey(board.getId());
		s3Uploader.deleteFile(imageKey);
		return mapper.updateBoard(board);
	}

	@Override
	public int deleteBoard(Long id) {
		String imageKey= mapper.selectImageKey(id);
		s3Uploader.deleteFile(imageKey);
		return mapper.deleteBoard(id);
	}

	@Override
	public int update(Long userId, Long boardId) {
		//TODO 존재하는 경우
		int isExist = mapper.checkUserHeartBoard(userId, boardId);
		if(isExist > 0) {
			Long id = mapper.findHeartId(userId,boardId);
			//좋아요 취소 => 해당 컬럼 삭제
			return mapper.deleteHeart(id);
		}
		//좋아요 추가 => 해당 컬럼 추가
		return mapper.insertHeart(userId,boardId);
		//return
	}

	@Override
	public List<BoardResponseDto> selectHearts(Long userId) {
		//heart테이블을 순회하면서 나온 id를 board테이블에서 가져옴
		List<BoardResponseDto> hearts = mapper.selectHearts(userId);
		for(BoardResponseDto heart : hearts){
			heart.setIsLike(true);
		}
		return hearts;
	}

}
