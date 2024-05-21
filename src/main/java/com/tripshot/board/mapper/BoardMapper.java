package com.tripshot.board.mapper;

import com.tripshot.board.dto.BoardResponseDto;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tripshot.board.dto.Board;


@Mapper
public interface BoardMapper {

	List<BoardResponseDto> selectAll(String userLoginId);
	List<BoardResponseDto> search(String season, String startDate, String endDate, String keyword, String userLoginId);
	BoardResponseDto selectOne(Long id, String userLoginId);
	int insertBoard(Board board);
	int updateBoard(Board board);
	String selectImageKey(Long id);
	int deleteBoard(Long id);
	int hitCountUp(Long id);
	Long findUserIdByUsername(String username);
}
