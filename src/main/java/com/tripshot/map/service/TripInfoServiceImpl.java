package com.tripshot.map.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripshot.map.dto.AttractionInfo;
import com.tripshot.map.dto.Gugun;
import com.tripshot.map.dto.Sido;
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
	public ArrayList<AttractionInfo> search(String location, String category, String word, String gugun) throws SQLException {
		return mapper.search(location, category, word, gugun);
	}

	@Override
	public List<Sido> selectSidoAll() {
		return mapper.selectSidoAll();
	}

	@Override
	public List<Gugun> selectGugunAll() {
		return mapper.selectGugunAll();
	}
	
}
