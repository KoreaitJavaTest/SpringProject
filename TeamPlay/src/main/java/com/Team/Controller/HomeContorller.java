package com.Team.Controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Team.Dao.ReViewDAO;
import com.Team.Service.ReViewService;


@Controller
@RequestMapping(value = "/")
public class HomeContorller {
	@Autowired
	public SqlSession sqlSession;


//	ReView======================================================================================================================
//	05-06 진호추가 
	@RequestMapping("ReViewBoard")	//리뷰 게시판이동
	public String ReViewBoard(HttpServletRequest request,Model model) {
		System.out.println("ReViewBoard()");
		return "redirect:ReViewBoardSelect";
	}
	
	@RequestMapping("ReViewBoardSelect")	// 리뷰게시판출력-->리뷰게시판이동
	public String ReViewBoardSelect(HttpServletRequest request,Model model) {
		System.out.println("ReViewBoardSelect()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().ReViewSelect(model,mapper);
		return "ReView/ReViewBoard";
	}
	@RequestMapping("ReViewDetailSelect")	// 리뷰게시판출력-->리뷰게시판이동
	public String ReViewDetailSelect(HttpServletRequest request,Model model) {
		System.out.println("ReViewDetailSelect()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().ReViewDetailSelect(model,mapper);
		return "ReView/ReViewPostDetail";
	}
	@RequestMapping("ReViewUpdate")	// 리뷰게시판출력-->리뷰게시판이동
	public String ReViewUpdate(HttpServletRequest request,Model model) {
		System.out.println("ReViewDetailSelect()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().selectByIdx(model,mapper);
//		ReViewService.getInstance().ReViewUpdate(model,mapper);
		return "ReView/ReViewUpdate";
	}
	@RequestMapping("ReViewUpdateOK")	// 리뷰게시판출력-->리뷰게시판이동
	public String ReViewUpdateOK(HttpServletRequest request,Model model) {
		System.out.println("ReViewUpdateOK()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
//		ReViewService.getInstance().selectByIdx(model,mapper);
		ReViewService.getInstance().ReViewUpdate(model,mapper);
		return "ReView/ReViewBoardListOk";
	}
	@RequestMapping("ReViewDeleteOK")	// 리뷰게시판출력-->리뷰게시판이동
	public String ReViewDeleteOK(HttpServletRequest request,Model model) {
		System.out.println("ReViewDeleteOK()");
		model.addAttribute("request", request);
		return "ReView/ReViewDeleteOK";
	}
	@RequestMapping("ReViewDelete")	// 리뷰게시판출력-->리뷰게시판이동
	public String ReViewDelete(HttpServletRequest request,Model model) {
		System.out.println("ReViewDeleteOK()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().ReViewDelete(model,mapper);
		return "ReView/ReViewDeleteOK";
	}
//	ReView======================================================================================================================
}
