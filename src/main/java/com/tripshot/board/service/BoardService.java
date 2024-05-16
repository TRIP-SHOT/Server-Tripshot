package com.tripshot.board.service;

import java.util.List;

import com.tripshot.board.dto.Board;


public interface BoardService {

	List<Board> selectAll();

	Board selectOne(String num);

	void insert(Board b);

	void modifyContent(String content, String num);
	void modify(Board b);

	void delete(String num);

	List<Board> search(String keyword);
	
}
