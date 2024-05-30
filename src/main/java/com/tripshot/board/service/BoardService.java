package com.tripshot.board.service;

import com.tripshot.board.dto.BoardResponseDto;
import java.util.List;

import com.tripshot.board.dto.Board;


public interface BoardService {

	List<BoardResponseDto> selectAll(Long userPk);
	List<BoardResponseDto> search(String season, String startDate, String endDate, String keyword);
	BoardResponseDto selectOne(Long id, String userLoginId);
	int insertBoard(Board board);
	int updateBoard(Board board);
	int deleteBoard(Long id);
	int update(Long userId, Long boardId);
	List<BoardResponseDto> selectHearts(Long userId);
	Boolean checkBoardWriter(Long userId, Long id);
}
