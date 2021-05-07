package com.Team.Vo;

import java.util.Date;

public class ReViewVO {

	private int RE_idx ;
	private String RE_title;
	private String RE_content;
	private String RE_userId;
	private int RE_good;
	private int RE_bad;
	private int RE_hit;
	private int RE_report;
	private Date RE_writeDate;
	private int RE_img;
	private String RE_categoryDetail;
	private String RE_imgNames;
	private int RE_commentCount;
	private String RE_goodCheckUser;

	

	public void setDate(int rE_idx, String rE_title, String rE_content, int rE_img, String rE_categoryDetail,
			String rE_imgNames) {
		RE_idx = rE_idx;
		RE_title = rE_title;
		RE_content = rE_content;
		RE_img = rE_img;
		RE_categoryDetail = rE_categoryDetail;
		RE_imgNames = rE_imgNames;
	}


	@Override
	public String toString() {
		return "ReViewVO [RE_idx=" + RE_idx + ", RE_title=" + RE_title + ", RE_content=" + RE_content + ", RE_userId="
				+ RE_userId + ", RE_good=" + RE_good + ", RE_bad=" + RE_bad + ", RE_hit=" + RE_hit + ", RE_report="
				+ RE_report + ", RE_writeDate=" + RE_writeDate + ", RE_img=" + RE_img + ", RE_categoryDetail="
				+ RE_categoryDetail + ", RE_imgNames=" + RE_imgNames + ", RE_commentCount=" + RE_commentCount + "]";
	}


	public String getRE_categoryDetail() {
		return RE_categoryDetail;
	}


	public void setRE_categoryDetail(String rE_categoryDetail) {
		RE_categoryDetail = rE_categoryDetail;
	}


	public String getRE_goodCheckUser() {
		return RE_goodCheckUser;
	}


	public void setRE_goodCheckUser(String rE_goodCheckUser) {
		RE_goodCheckUser = rE_goodCheckUser;
	}


	public int getRE_idx() {
		return RE_idx;
	}
	public void setRE_idx(int rE_idx) {
		RE_idx = rE_idx;
	}
	public String getRE_title() {
		return RE_title;
	}
	public void setRE_title(String rE_title) {
		RE_title = rE_title;
	}
	public String getRE_content() {
		return RE_content;
	}
	public void setRE_content(String rE_content) {
		RE_content = rE_content;
	}
	public String getRE_userId() {
		return RE_userId;
	}
	public void setRE_userId(String rE_userId) {
		RE_userId = rE_userId;
	}
	public int getRE_good() {
		return RE_good;
	}
	public void setRE_good(int rE_good) {
		RE_good = rE_good;
	}
	public int getRE_bad() {
		return RE_bad;
	}
	public void setRE_bad(int rE_bad) {
		RE_bad = rE_bad;
	}
	public int getRE_hit() {
		return RE_hit;
	}
	public void setRE_hit(int rE_hit) {
		RE_hit = rE_hit;
	}
	public int getRE_report() {
		return RE_report;
	}
	public void setRE_report(int rE_report) {
		RE_report = rE_report;
	}
	public Date getRE_writeDate() {
		return RE_writeDate;
	}
	public void setRE_writeDate(Date rE_writeDate) {
		RE_writeDate = rE_writeDate;
	}
	public int getRE_img() {
		return RE_img;
	}
	public void setRE_img(int rE_img) {
		RE_img = rE_img;
	}


	public String getRE_imgNames() {
		return RE_imgNames;
	}


	public void setRE_imgNames(String rE_imgNames) {
		RE_imgNames = rE_imgNames;
	}


	public int getRE_commentCount() {
		return RE_commentCount;
	}


	public void setRE_commentCount(int rE_commentCount) {
		RE_commentCount = rE_commentCount;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} 
