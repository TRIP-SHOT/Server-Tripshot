package com.tripshot.map.dto;

public class PlanDetail {
	private int detail_id;
	private int plan_id;
	private int attraction_id;
	private String memo;
	
	public PlanDetail() {}
	public PlanDetail(int detail_id, int plan_id, int attraction_id, String memo) {
		this.detail_id = detail_id;
		this.plan_id = plan_id;
		this.attraction_id = attraction_id;
		this.memo = memo;
	}
	public int getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(int detail_id) {
		this.detail_id = detail_id;
	}
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public int getAttraction_id() {
		return attraction_id;
	}
	public void setAttraction_id(int attraction_id) {
		this.attraction_id = attraction_id;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
