package com.tripshot.map.service;

import java.util.List;

import com.tripshot.map.dto.AttractionInfo;
import com.tripshot.map.dto.PlanDetail;

public interface TripPlanService {

	List<AttractionInfo> searchByKeyword(String keyword);

	void addPlace(List<PlanDetail> detail, String user_id);
	 
}
