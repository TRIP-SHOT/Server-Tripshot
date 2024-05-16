package com.tripshot.map.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripshot.map.dto.AttractionInfo;
import com.tripshot.map.mapper.TripInfoMapper;


@Service
public class TripInfoServiceImpl implements TripInfoService {
	@Autowired
	TripInfoMapper mapper;
	
	@Override
	public ArrayList<AttractionInfo> selectAll() throws SQLException {
		ArrayList<AttractionInfo> list = mapper.selectAll();//java mapper의 메소드 호출
		return list;
	}

	@Override
	public AttractionInfo selectOne(String num) throws SQLException {
		return mapper.selectOne(num);//SELECT
	}

	@Override
	public ArrayList<AttractionInfo> search(String location, String category, String word) throws SQLException {
		Map<String, String> map = new HashMap<>();
		map.put("location", location);
		map.put("category", category);
		map.put("word", word);
		return mapper.search(map);
	}
	
}
