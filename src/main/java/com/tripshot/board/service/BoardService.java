package com.tripshot.board.service;

import com.tripshot.board.dto.BoardResponseDto;
import java.util.List;

import com.tripshot.board.dto.Board;


public interface BoardService {

	List<BoardResponseDto> selectAll(String userLoginId);
	List<BoardResponseDto> search(String season, String startDate, String endDate, String keyword, String userLoginId);
	BoardResponseDto selectOne(Long id, String userLoginId);
	int insertBoard(Board board);
	int updateBoard(Board board);
	int deleteBoard(Long id);

//	Board selectOne(String num);
//
//	void insert(Board b);
//
//	void modifyContent(String content, String num);
//	void modify(Board b);
//
//	void delete(String num);
//
//	List<Board> search(String keyword);
	
}
