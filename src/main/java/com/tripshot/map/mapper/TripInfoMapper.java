package com.tripshot.map.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tripshot.map.dto.AttractionInfo;


@Mapper
public interface TripInfoMapper {
	ArrayList<AttractionInfo> selectAll() throws SQLException;//모든 글정보
	AttractionInfo selectOne(String num) throws SQLException;//해당 번호의 글 조회
	ArrayList<AttractionInfo> search(Map<String, String> map) throws SQLException;
}
