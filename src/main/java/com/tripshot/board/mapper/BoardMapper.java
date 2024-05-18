package com.tripshot.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tripshot.board.dto.Board;


@Mapper
public interface BoardMapper {

	List<Board> selectAll();
	List<Board> search(String season, String startDate, String endDate, String keyword);
	Board selectOne(Long id);
	int insertBoard(Board board);
	int updateBoard(Board board);
	String selectImageKey(int id);
}
