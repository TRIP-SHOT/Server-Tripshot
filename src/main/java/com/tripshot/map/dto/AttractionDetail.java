package com.tripshot.map.dto;

public class AttractionDetail {

	private String content_id;
	private String cat1;
	private String cat2;
	private String cat3;
	private String created_time;
	private String modified_time;
	private String booktour;
	public AttractionDetail() {}
	public AttractionDetail(String content_id, String cat1, String cat2, String cat3, String created_time,
			String modified_time, String booktour) {
		this.content_id = content_id;
		this.cat1 = cat1;
		this.cat2 = cat2;
		this.cat3 = cat3;
		this.created_time = created_time;
		this.modified_time = modified_time;
		this.booktour = booktour;
	}
	public String getContent_id() {
		return content_id;
	}
	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}
	public String getCat1() {
		return cat1;
	}
	public void setCat1(String cat1) {
		this.cat1 = cat1;
	}
	public String getCat2() {
		return cat2;
	}
	public void setCat2(String cat2) {
		this.cat2 = cat2;
	}
	public String getCat3() {
		return cat3;
	}
	public void setCat3(String cat3) {
		this.cat3 = cat3;
	}
	public String getCreated_time() {
		return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public String getModified_time() {
		return modified_time;
	}
	public void setModified_time(String modified_time) {
		this.modified_time = modified_time;
	}
	public String getBooktour() {
		return booktour;
	}
	public void setBooktour(String booktour) {
		this.booktour = booktour;
	}
	@Override
	public String toString() {
		return "AttractionDetail [content_id=" + content_id + ", cat1=" + cat1 + ", cat2=" + cat2 + ", cat3=" + cat3
				+ ", created_time=" + created_time + ", modified_time=" + modified_time + ", booktour=" + booktour
				+ "]";
	}
}
