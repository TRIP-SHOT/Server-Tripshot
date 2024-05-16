package com.tripshot.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripshot.board.dto.Board;
import com.tripshot.board.mapper.BoardMapper;


@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper mapper;
	
	@Override
	public List<Board> selectAll() {
		List<Board> list = mapper.selectAll();
		return list;
	}

	@Override
	public Board selectOne(String num) {
		Board b = mapper.selectOne(num);
		mapper.countUp(num);
		return b;
	}

	@Override
	public void insert(Board b) {
		mapper.insert(b);
	}

	@Override
	public void modifyContent(String content, String num) {
		mapper.modifyContent(content, num);
	}

	@Override
	public void delete(String num) {
		mapper.delete(num);
	}

	@Override
	public List<Board> search(String keyword) {
		return mapper.search(keyword);
	}

	@Override
	public void modify(Board b) {
		mapper.modify(b);
	}

}