package com.tripshot.map.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tripshot.board.dto.BoardResponseDto;
import com.tripshot.global.ApiResponse;
import com.tripshot.map.dto.AttractionInfo;
import com.tripshot.map.dto.Gugun;
import com.tripshot.map.dto.Sido;
import com.tripshot.map.service.TripInfoService;


@RestController
public class TripInfoController {
	@Autowired
	TripInfoService service;
	
	@GetMapping("/sido")
	public ResponseEntity<ApiResponse<List<Sido>>> selectSidoAll() throws SQLException{
		List<Sido> list = service.selectSidoAll();
		return new ResponseEntity(new ApiResponse(HttpStatus.OK,"시도 조회 성공",list),HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<ApiResponse<List<Sido>>> selectGugunAll() throws SQLException{
		List<Gugun> list = service.selectGugunAll();
		return new ResponseEntity(new ApiResponse(HttpStatus.OK,"구군 조회 성공",list),HttpStatus.OK);
	}
	
	@GetMapping("/info")
	public List<AttractionInfo> selectAll() throws SQLException {	
		List<AttractionInfo> list = service.selectAll();
		return list;
	}
	
	@GetMapping("/read/{num}")
	public AttractionInfo read(@PathVariable("num") String no) throws SQLException {
		AttractionInfo b = service.selectOne(no);
		return b;
	}
	
	@GetMapping("/search")
	public ResponseEntity<ApiResponse<List<AttractionInfo>>> search(@RequestParam(name="searchArea", defaultValue = "1") String location, @RequestParam(name="searchContentId", defaultValue = "12") String category, @RequestParam(name = "searchKeyword", defaultValue = "1") String word, @RequestParam(name="searchGugun", defaultValue = "1") String gugun) throws SQLException {
		if(location.equals("0")) location = "1";
		if(category.equals("0")) category = "12";
		if(gugun.equals("0")) gugun="1";
		
		ArrayList<AttractionInfo> list = service.search(location,category,word, gugun);
		return new ResponseEntity(new ApiResponse(HttpStatus.OK,"검색 성공",list),HttpStatus.OK);
	}

}
