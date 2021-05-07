package com.Team.List;

import java.util.ArrayList;

import com.Team.Vo.ReViewCommentVO;

public class ReViewCommentList {
	ArrayList<ReViewCommentVO> list ;
	
	public ReViewCommentList() {
		// TODO Auto-generated constructor stub
	}
	

	public ReViewCommentList(ArrayList<ReViewCommentVO> list) {
		super();
		this.list = list;
	}

	public ArrayList<ReViewCommentVO> getList() {
		return list;
	}

	public void setList(ArrayList<ReViewCommentVO> list) {
		this.list = list;
	}
	
	
}
