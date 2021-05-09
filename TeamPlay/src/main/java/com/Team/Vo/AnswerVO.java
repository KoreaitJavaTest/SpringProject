package com.Team.Vo;

import java.util.Date;

public class AnswerVO {

	private int a_idx;
	private String a_userid;
	private String a_title;
	private String a_content;
	private Date a_writedate;
	private int a_ref;
	private int a_flag;
	
	public AnswerVO() {}
	
	public void setAnswerVO(String a_userid, String a_title, String a_content,int q_idx) {
		this.a_userid = a_userid;
		this.a_title = a_title;
		this.a_content = a_content;
		this.a_ref = q_idx;
	}

	public int getA_flag() {
		return a_flag;
	}

	public void setA_flag(int a_flag) {
		this.a_flag = a_flag;
	}

	public int getA_idx() {
		return a_idx;
	}
	public void setA_idx(int a_idx) {
		this.a_idx = a_idx;
	}
	public String getA_userid() {
		return a_userid;
	}
	public void setA_userid(String a_userid) {
		this.a_userid = a_userid;
	}
	public String getA_title() {
		return a_title;
	}
	public void setA_title(String a_title) {
		this.a_title = a_title;
	}
	public String getA_content() {
		return a_content;
	}
	public void setA_content(String a_content) {
		this.a_content = a_content;
	}
	public Date getA_writedate() {
		return a_writedate;
	}
	public void setA_writedate(Date a_writedate) {
		this.a_writedate = a_writedate;
	}
	public int getA_ref() {
		return a_ref;
	}
	public void setA_ref(int a_ref) {
		this.a_ref = a_ref;
	}
	
	@Override
	public String toString() {
		return "AnswerVO [a_idx=" + a_idx + ", a_userid=" + a_userid + ", a_title=" + a_title + ", a_content="
				+ a_content + ", a_writedate=" + a_writedate + ", a_ref=" + a_ref + "]";
	}
	
	
	
}
