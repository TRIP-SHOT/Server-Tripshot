package com.tripshot.map.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tripshot.map.dto.AttractionInfo;
import com.tripshot.map.service.TripInfoService;


@RestController
public class TripInfoController {
	@Autowired
	TripInfoService service;
	
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
	
	@GetMapping("/search/{location}/{category}/{word}")
	public ArrayList<AttractionInfo> search(@PathVariable("location") String location, @PathVariable("category") String category, @PathVariable("word") String word) throws SQLException {
		
		ArrayList<AttractionInfo> list = service.search(location,category,word);

		return list;
		
	}

}
