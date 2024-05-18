package com.tripshot.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripshot.board.dto.Board;
import com.tripshot.board.mapper.BoardMapper;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardMapper mapper;
	
	@Override
	public List<Board> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public List<Board> search(String season, String startDate, String endDate, String keyword){
		return mapper.search(season,startDate,endDate,keyword);
	}

	@Override
	public Board selectOne(Long id) {
		return mapper.selectOne(id);
	}

	@Override
	public int insertBoard(Board board) {
		return mapper.insertBoard(board);
	}

//	@Override
//	public Board selectOne(String num) {
//		Board b = mapper.selectOne(num);
//		mapper.countUp(num);
//		return b;
//	}
//
//	@Override
//	public void insert(Board b) {
//		mapper.insert(b);
//	}
//
//	@Override
//	public void modifyContent(String content, String num) {
//		mapper.modifyContent(content, num);
//	}
//
//	@Override
//	public void delete(String num) {
//		mapper.delete(num);
//	}
//
//	@Override
//	public List<Board> search(String keyword) {
//		return mapper.search(keyword);
//	}
//
//	@Override
//	public void modify(Board b) {
//		mapper.modify(b);
//	}

}
