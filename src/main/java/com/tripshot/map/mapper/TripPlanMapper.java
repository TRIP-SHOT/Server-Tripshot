package com.tripshot.map.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tripshot.map.dto.AttractionInfo;
import com.tripshot.map.dto.PlanDetail;


@Mapper
public interface TripPlanMapper {

	List<AttractionInfo> searchByKeyword(String keyword);

	void addPlan(String user_id);
	
	int getPlan(String user_id);

	void addPlanDetail(PlanDetail planDetail);
		
}
