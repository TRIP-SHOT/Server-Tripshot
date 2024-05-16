package com.tripshot.map.dto;

public class Plan {
	private int plan_id;
	private int user_id;
	private String plan_date;
	
	public Plan() {}
	public Plan(int plan_id, int user_id, String plan_date) {
		this.plan_id = plan_id;
		this.user_id = user_id;
		this.plan_date = plan_date;
	}
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getPlan_date() {
		return plan_date;
	}
	public void setPlan_date(String plan_date) {
		this.plan_date = plan_date;
	}
	
}
