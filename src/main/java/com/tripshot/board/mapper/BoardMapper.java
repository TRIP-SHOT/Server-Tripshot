package com.tripshot.board.mapper;

import com.tripshot.board.dto.BoardResponseDto;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tripshot.board.dto.Board;


@Mapper
public interface BoardMapper {

	List<BoardResponseDto> selectAll();
	List<BoardResponseDto> search(String season, String startDate, String endDate, String keyword);
	BoardResponseDto selectOne(Long id);
	int insertBoard(Board board);
	int updateBoard(Board board);
	String selectImageKey(Long id);
	int deleteBoard(Long id);
	int hitCountUp(Long id);
	//isLike여부
	int checkUserHeartBoard(Long userPk, Long boardId);
	//좋아요 개수
	Long countHeartCount(Long BoardId);
	int deleteHeart(Long id);
	int insertHeart(Long userId, Long boardId);
	Long findHeartId(Long userId, Long boardId);
	List<BoardResponseDto> selectHearts(Long userId);
	int checkBoardWriter(Long userId, Long boardId);
}
