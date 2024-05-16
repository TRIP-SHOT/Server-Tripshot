package com.tripshot.map.dto;

public class Sido {

	private int sido_code;
	private String sido_name;
	public Sido() {};
	public Sido(String gugun_name, int sido_code) {
		this.sido_name = gugun_name;
		this.sido_code = sido_code;
	}
	public String getGugun_name() {
		return sido_name;
	}
	public int getSido_code() {
		return sido_code;
	}
	public void setGugun_name(String gugun_name) {
		this.sido_name = gugun_name;
	}
	public void setSido_code(int sido_code) {
		this.sido_code = sido_code;
	}
	@Override
	public String toString() {
		return "Gugun [gugun_name=" + sido_name + ", sido_code=" + sido_code + "]";
	}
}
