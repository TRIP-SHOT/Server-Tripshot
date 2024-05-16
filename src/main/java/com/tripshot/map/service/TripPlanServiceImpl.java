package com.tripshot.map.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tripshot.map.dto.AttractionInfo;
import com.tripshot.map.dto.PlanDetail;
import com.tripshot.map.mapper.TripPlanMapper;

@Service
public class TripPlanServiceImpl implements TripPlanService {

	@Autowired
	TripPlanMapper mapper;
	
	@Override
	public List<AttractionInfo> searchByKeyword(String keyword) {
		
		List<AttractionInfo> list = mapper.searchByKeyword(keyword);
		return list;
	}

	@Override
	@Transactional
	public void addPlace(List<PlanDetail> detail, String user_id) {
		// plan테이블에 레코드를 하나 추가하고(user정보) 그때의 plan_id를 가져와야 함.
		mapper.addPlan(user_id);  // plan테이블에 하나 추가
		int plan_id = mapper.getPlan(user_id);  // plan_id를 가져옴.
		for(int i=0;i<detail.size();i++) {
			detail.get(i).setPlan_id(plan_id);
			mapper.addPlanDetail(detail.get(i));
		}
	}

}
