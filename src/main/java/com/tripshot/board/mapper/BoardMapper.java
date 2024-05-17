package com.tripshot.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tripshot.board.dto.Board;


@Mapper
public interface BoardMapper {

	List<Board> selectAll();
	List<Board> search(String season, String startDate, String endDate, String keyword);

//	List<Board> search(String keyword);
//
//	Board selectOne(String num);
//
//	void countUp(String num);
//
//	void insert(Board b);
//
//	void modifyContent(String content, String num);
//
//	void modify(Board b);
//
//	void delete(String num);

}
