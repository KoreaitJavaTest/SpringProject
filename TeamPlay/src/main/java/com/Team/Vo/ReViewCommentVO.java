package com.Team.Vo;

import java.util.Date;

public class ReViewCommentVO {
	private int idx;
	private int refIdx;
	private String content;
	private String userId;
	private Date writedate;
	private int report;

	public ReViewCommentVO(int refIdx, String content, String userId) {
		super();
		this.refIdx = refIdx;
		this.content = content;
		this.userId = userId;
	}



	public ReViewCommentVO() {
		// TODO Auto-generated constructor stub
	}













	@Override
	public String toString() {
		return "ReViewCommentVO [idx=" + idx + ", refIdx=" + refIdx + ", content=" + content + ", userId=" + userId
				+ ", writedate=" + writedate + ", report=" + report + "]";
	}





	public int getIdx() {
		return idx;
	}


	public void setIdx(int idx) {
		this.idx = idx;
	}




	public int getRefIdx() {
		return refIdx;
	}





	public void setRefIdx(int refIdx) {
		this.refIdx = refIdx;
	}





	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getWritedate() {
		return writedate;
	}


	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}


	public int getReport() {
		return report;
	}


	public void setReport(int report) {
		this.report = report;
	}





	public String getUserId() {
		return userId;
	}





	public void setUserId(String userId) {
		this.userId = userId;
	}








}
