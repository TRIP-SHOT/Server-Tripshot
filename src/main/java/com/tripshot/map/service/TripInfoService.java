package com.tripshot.map.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tripshot.map.dto.AttractionInfo;
import com.tripshot.map.dto.Gugun;
import com.tripshot.map.dto.Sido;



public interface TripInfoService {
	ArrayList<AttractionInfo> selectAll() throws SQLException;//모든 글정보
	AttractionInfo selectOne(String num) throws SQLException;//해당 번호의 글 조회
	ArrayList<AttractionInfo> search(String location, String category, String word, String gugun) throws SQLException;
	List<Sido> selectSidoAll();
	List<Gugun> selectGugunAll();
}
