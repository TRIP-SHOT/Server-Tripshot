package com.tripshot.map.dto;

public class AttractionDescription {

	private String content_id;
	private String homepage;
	private String overview;
	private String telname;
	
	public AttractionDescription() {}
	public AttractionDescription(String content_id, String homepage, String overview, String telname) {
		this.content_id = content_id;
		this.homepage = homepage;
		this.overview = overview;
		this.telname = telname;
	}
	public String getContent_id() {
		return content_id;
	}
	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getTelname() {
		return telname;
	}
	public void setTelname(String telname) {
		this.telname = telname;
	}
	@Override
	public String toString() {
		return "AttractionDescription [content_id=" + content_id + ", homepage=" + homepage + ", overview=" + overview
				+ ", telname=" + telname + "]";
	}
}
