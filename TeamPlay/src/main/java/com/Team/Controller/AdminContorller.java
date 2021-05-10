package com.Team.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import com.Team.Dao.AdminDao;
import com.Team.Dao.ClientDao;
import com.Team.Dao.ReViewDAO;
import com.Team.Service.AdminService;
import com.Team.Service.ClientService;
import com.Team.Service.ReViewService;
//@RequestMapping("ReViewBoard")	//리뷰 게시판이동
//public String ReViewBoard(HttpServletRequest request,Model model) {
//	System.out.println("ReViewBoard()");
//	return "redirect:ReViewBoardSelect";
//}
//
//@RequestMapping("ReViewBoardSelect")	// 리뷰게시판출력-->리뷰게시판이동
//public String ReViewBoardSelect(HttpServletRequest request,Model model) {
//	System.out.println("ReViewBoardSelect()");
//	model.addAttribute("request", request);
//	ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
//	ReViewService.getInstance().ReViewSelect(model,mapper);
//	return "ReView/ReViewBoard";
//}
//@RequestMapping("ReViewDetailSelect")	// 리뷰게시판출력-->리뷰게시판이동
//public String ReViewDetailSelect(HttpServletRequest request,Model model) {
//	System.out.println("ReViewDetailSelect()");
//	model.addAttribute("request", request);
//	ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
//	ReViewService.getInstance().ReViewDetailSelect(model,mapper);
//	return "ReView/ReViewPostDetail";
//}
//@RequestMapping("ReViewUpdate")	// 리뷰게시판출력-->리뷰게시판이동
//public String ReViewUpdate(HttpServletRequest request,Model model) {
//	System.out.println("ReViewDetailSelect()");
//	model.addAttribute("request", request);
//	ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
//	ReViewService.getInstance().selectByIdx(model,mapper);
////	ReViewService.getInstance().ReViewUpdate(model,mapper);
//	return "ReView/ReViewUpdate";
//}
//@RequestMapping("ReViewUpdateOK")	// 리뷰게시판출력-->리뷰게시판이동
//public String ReViewUpdateOK(HttpServletRequest request,Model model) {
//	System.out.println("ReViewUpdateOK()");
//	model.addAttribute("request", request);
//	ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
////	ReViewService.getInstance().selectByIdx(model,mapper);
//	ReViewService.getInstance().ReViewUpdate(model,mapper);
//	return "ReView/ReViewBoardListOk";
//}
//@RequestMapping("ReViewDeleteOK")	// 리뷰게시판출력-->리뷰게시판이동
//public String ReViewDeleteOK(HttpServletRequest request,Model model) {
//	System.out.println("ReViewDeleteOK()");
//	model.addAttribute("request", request);
//	return "ReView/ReViewDeleteOK";
//}
//@RequestMapping("ReViewDelete")	// 리뷰게시판출력-->리뷰게시판이동
//public String ReViewDelete(HttpServletRequest request,Model model) {
//	System.out.println("ReViewDeleteOK()");
//	model.addAttribute("request", request);
//	ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
//	ReViewService.getInstance().ReViewDelete(model,mapper);
//	return "ReView/ReViewDeleteOK";
//}
import com.google.gson.Gson;

@Controller
public class AdminContorller {

	@Autowired
	public SqlSession sqlSession;

	//관리자 페이지 이동
	@RequestMapping("adminPage")
	public String home(Model model) {
		return "Admin/AdminMainPage";
	}
	//유저관리 페이지 이동
	@RequestMapping("AdminUserMangement")
	public String AdminUserMangement(Model model,HttpServletRequest request) {
		model.addAttribute("request", request);
		AdminDao mapper = sqlSession.getMapper(AdminDao.class);
		AdminService.getInstance().selectUserList(model,mapper);
		return "Admin/AdminUserMangement";
	}
	//유저관리페이지 -> 유저삭제 (AJAX)
	@ResponseBody
	@RequestMapping(value="AdminUserDelete",produces="application/json;charset=utf8")
	public String AdminUserDelete(Model model,HttpServletRequest request , HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Gson gson = new Gson();
		model.addAttribute("request", request);
		AdminDao mapper = sqlSession.getMapper(AdminDao.class);
		String result = gson.toJson(AdminService.getInstance().AdminUserDelete(model,mapper,response));
		return result.toString();
	}


}
