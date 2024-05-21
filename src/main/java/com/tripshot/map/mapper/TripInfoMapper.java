package com.tripshot.map.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tripshot.map.dto.AttractionInfo;
import com.tripshot.map.dto.Gugun;
import com.tripshot.map.dto.Sido;


@Mapper
public interface TripInfoMapper {
	ArrayList<AttractionInfo> selectAll() throws SQLException;//모든 글정보
	AttractionInfo selectOne(String num) throws SQLException;//해당 번호의 글 조회
	ArrayList<AttractionInfo> search(String location, String category, String word, String gugun) throws SQLException;
	List<Sido> selectSidoAll();
	List<Gugun> selectGugunAll();

}
