package com.Team.Vo;

import java.util.Date;

public class QAboardVo {

	private int q_idx;
	private String q_title;
	private String q_content;
	private String q_userid;
	private int q_seq;
	private int q_lev;
	private int q_ref;
	private Date q_writedate;
	private String Q_flag;

	public QAboardVo() {}
	
	public void setQAboardVo(String q_userid, String q_title, String q_content) {
		this.q_userid = q_userid;
		this.q_title = q_title;
		this.q_content = q_content;
	}

	public int getQ_idx() {
		return q_idx;
	}

	public void setQ_idx(int q_idx) {
		this.q_idx = q_idx;
	}

	public String getQ_title() {
		return q_title;
	}

	public void setQ_title(String q_title) {
		this.q_title = q_title;
	}

	public String getQ_content() {
		return q_content;
	}

	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}

	public String getQ_userid() {
		return q_userid;
	}

	public void setQ_userid(String q_userid) {
		this.q_userid = q_userid;
	}

	public int getQ_seq() {
		return q_seq;
	}

	public void setQ_seq(int q_seq) {
		this.q_seq = q_seq;
	}

	public int getQ_lev() {
		return q_lev;
	}

	public void setQ_lev(int q_lev) {
		this.q_lev = q_lev;
	}

	public int getQ_ref() {
		return q_ref;
	}

	public void setQ_ref(int q_ref) {
		this.q_ref = q_ref;
	}

	public Date getQ_writedate() {
		return q_writedate;
	}

	public void setQ_writedate(Date q_writedate) {
		this.q_writedate = q_writedate;
	}

	public String getQ_flag() {
		return Q_flag;
	}

	public void setQ_flag(String q_flag) {
		Q_flag = q_flag;
	}

	
	
}
