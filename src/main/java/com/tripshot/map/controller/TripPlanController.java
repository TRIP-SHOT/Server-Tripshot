package com.tripshot.map.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tripshot.map.dto.AttractionInfo;
import com.tripshot.map.dto.PlanDetail;
import com.tripshot.map.service.TripPlanService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/plan")
public class TripPlanController {
	
	@Autowired
	TripPlanService service;
	
	// 맵 왼쪽에서 관광지 타이틀로 관광지들 정보 뽑아내는 방법 --> 이거 그..1, 2, 3, 4에 따라서 조금씩만 보이게 해야 함.
	@GetMapping("/search/{keyword}")
	public ResponseEntity<?> search(@PathVariable("keyword") String keyword){
		
		List<AttractionInfo> list = service.searchByKeyword(keyword);
		
		return new ResponseEntity<List<AttractionInfo>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/addPlace")
	public ResponseEntity addPlace(@RequestBody List<PlanDetail> detail, HttpSession session) {
		// 세션에 저장되어 있던 유저 아이디를 가져온다.
		String user_id = (String) session.getAttribute("user_id");
		service.addPlace(detail, user_id);
		return new ResponseEntity("add success!", HttpStatus.OK);
	}
}
