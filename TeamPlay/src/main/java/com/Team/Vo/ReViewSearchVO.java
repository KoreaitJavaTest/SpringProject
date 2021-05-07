package com.Team.Vo;

public class ReViewSearchVO {
	private String searchName;
	private String searchText;
	private int startNo;
	private int endNo;
	
	
	
	
	@Override
	public String toString() {
		return "ReViewSearchVO [searchName=" + searchName + ", searchText=" + searchText + ", startNo=" + startNo
				+ ", endNo=" + endNo + ", getSearchName()=" + getSearchName() + ", getSearchText()=" + getSearchText()
				+ ", getStartNo()=" + getStartNo() + ", getEndNo()=" + getEndNo() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public ReViewSearchVO(String searchName, String searchText, int startNo, int endNo) {
		super();
		this.searchName = searchName;
		this.searchText = searchText;
		this.startNo = startNo;
		this.endNo = endNo;
	}
	
	public ReViewSearchVO(String searchName, String searchText) {
		super();
		this.searchName = searchName;
		this.searchText = searchText;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public int getStartNo() {
		return startNo;
	}
	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}
	public int getEndNo() {
		return endNo;
	}
	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}
	
	
}
