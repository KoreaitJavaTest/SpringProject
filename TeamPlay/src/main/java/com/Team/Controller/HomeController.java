package com.Team.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import com.Team.Dao.ClientDao;
import com.Team.Dao.ReViewDAO;
import com.Team.Service.ClientService;
import com.Team.Service.ReViewService;
import com.google.gson.Gson;


@Controller
@RequestMapping(value="/")
public class HomeController {

	@Autowired
	public SqlSession sqlSession;

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
	@RequestMapping("ReViewInsert")	// 리뷰게시판출력-->리뷰게시판이동
	public String ReViewInsert(HttpServletRequest request,Model model) {
		System.out.println("ReViewInsert()");
		return "ReView/ReViewInsert";
	}
	@RequestMapping("ReViewInsertOK")	// 리뷰게시판출력-->리뷰게시판이동
	public String ReViewInsertOK(HttpServletRequest request,Model model) {
		System.out.println("ReViewInsertOK()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().ReViewInsert(model,mapper);
		return "redirect:ReViewBoard";
	}
	@RequestMapping("ReViewSearch")	// 리뷰게시판출력-->리뷰게시판이동
	public String ReViewSearch(HttpServletRequest request,Model model) {
		System.out.println("ReViewSearch()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().ReViewSearch(model,mapper);
		return "ReView/ReViewBoard";
	}
	//댓글작성
	@RequestMapping("ReViewComment")	// 리뷰게시판출력-->리뷰게시판이동
	public String ReViewComment(HttpServletRequest request,Model model) {
		System.out.println("ReViewComment()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().commentInsert(model,mapper);
		return "ReView/ReViewBoardListOk";
	}
	//댓글수정
	@RequestMapping("updateComment")	// 리뷰게시판출력-->리뷰게시판이동
	public String updateComment(HttpServletRequest request,Model model) {
		System.out.println("ReViewComment()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().updateComment(model,mapper);
		return "redirect:ReViewDetailSelect";
	}
	//댓글삭제
	@RequestMapping("deleteComment")	
	public String deleteComment(HttpServletRequest request,Model model) {
		System.out.println("deleteComment()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().deleteComment(model,mapper);
		return "ReView/ReViewPostDetail";
	}
	
	//댓글삭제
//	@RequestMapping("updateComment")	// 리뷰게시판출력-->리뷰게시판이동
//	public String updateComment(HttpServletRequest request,Model model) {
//		System.out.println("ReViewComment()");
//		model.addAttribute("request", request);
//		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
//		ReViewService.getInstance().updateComment(model,mapper);
//		return "redirect:ReViewDetailSelect";
//	}
	
	
	//리뷰 좋아요
	@ResponseBody
	@RequestMapping("likeCheck")	
	public String likeCheck(HttpServletRequest request,Model model) {
		System.out.println("likeCheck()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
//		ReViewService.getInstance().likeCheck(model,mapper);
		Gson gson = new Gson();
		HashMap<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("succees","통신성공");
		String jsonString = gson.toJson(hmap);
		ReViewService.getInstance().likeCheck(model,mapper);
		return jsonString;
		
	}

}
