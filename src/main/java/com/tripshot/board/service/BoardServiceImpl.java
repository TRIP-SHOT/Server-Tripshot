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
//			if(checkUserHeartBoard(board.getId()) > 0) {
//				
//			}
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

}
