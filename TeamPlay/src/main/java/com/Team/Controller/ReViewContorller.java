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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import com.Team.Dao.ClientDao;
import com.Team.Dao.ReViewDAO;
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
public class ReViewContorller {

	@Autowired
	public SqlSession sqlSession;

	
	//(장진호)리뷰 게시판이동
	@RequestMapping("ReViewBoard")
	public String ReViewBoard(HttpServletRequest request,Model model) {
		System.out.println("ReViewBoard()");
		return "redirect:ReViewBoardSelect";
	}
	//(장진호)리뷰 게시판에 출력할 데이터를 리턴하는 메소드
	@RequestMapping("ReViewBoardSelect")
	public String ReViewBoardSelect(HttpServletRequest request,Model model) {
		System.out.println("ReViewBoardSelect()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().ReViewSelect(model,mapper);
		return "ReView/ReViewBoard";
	}
	//(장진호)리뷰글 상세보기를 출력
	@RequestMapping("ReViewDetailSelect")	
	public String ReViewDetailSelect(HttpServletRequest request,Model model) {
		System.out.println("ReViewDetailSelect()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().ReViewDetailSelect(model,mapper);
		return "ReView/ReViewPostDetail";
	}
	//(장진호)리뷰글 정보를 가지고 수정페이지로 이동
	@RequestMapping("ReViewUpdate")	
	public String ReViewUpdate(HttpServletRequest request,Model model) {
		System.out.println("ReViewDetailSelect()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().selectByIdx(model,mapper);
		return "ReView/ReViewUpdate";
	}
	//(장진호)리뷰글 수정페이지 -> 리뷰글 업데이트
	@RequestMapping("ReViewUpdateOK")
	public String ReViewUpdateOK(HttpServletRequest request,Model model) {
		System.out.println("ReViewUpdateOK()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().ReViewUpdate(model,mapper);
		return "ReView/ReViewBoardListOk";
	}
	//(장진호)리뷰글 삭제페이지이동
	@RequestMapping("ReViewDeleteOK")
	public String ReViewDeleteOK(HttpServletRequest request,Model model) {
		System.out.println("ReViewDeleteOK()");
		model.addAttribute("request", request);
		return "ReView/ReViewDeleteOK";
	}
	//(장진호)리뷰글 삭제
	@RequestMapping("ReViewDelete")
	public String ReViewDelete(HttpServletRequest request,Model model) {
		System.out.println("ReViewDeleteOK()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().ReViewDelete(model,mapper);
		return "ReView/ReViewDeleteOK";
	}
	//(장진호)리뷰글 추가페이지 이동
	@RequestMapping("ReViewInsert")
	public String ReViewInsert(HttpServletRequest request,Model model) {
		System.out.println("ReViewInsert()");
		return "ReView/ReViewInsert";
	}
	//(장진호)리뷰글 추가 기능
	@RequestMapping("ReViewInsertOK")
	public String ReViewInsertOK(HttpServletRequest request,Model model) {
		System.out.println("ReViewInsertOK()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().ReViewInsert(model,mapper);
		return "redirect:ReViewBoard";
	}
	//(장진호)리뷰글 검색기능
	@RequestMapping("ReViewSearch")
	public String ReViewSearch(HttpServletRequest request,Model model) {
		System.out.println("ReViewSearch()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().ReViewSearch(model,mapper);
		return "ReView/ReViewBoard";
	}
	//(장진호) 리뷰글에서 댓글 추가
	@RequestMapping("ReViewComment")
	public String ReViewComment(HttpServletRequest request,Model model) {
		System.out.println("ReViewComment()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().commentInsert(model,mapper);
		return "ReView/ReViewBoardListOk";
	}
	//(장진호) 리뷰게시글 댓글 수정
	@RequestMapping("updateComment")
	public String updateComment(HttpServletRequest request,Model model) {
		System.out.println("ReViewComment()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().updateComment(model,mapper);
		return "redirect:ReViewDetailSelect";
	}
	//(장진호) 리뷰 게시글 댓글 삭제
	@RequestMapping("deleteComment")	
	public String deleteComment(HttpServletRequest request,Model model) {
		System.out.println("deleteComment()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		ReViewService.getInstance().deleteComment(model,mapper);
		return "ReView/ReViewPostDetail";
	}

	//리뷰게시글 좋아요 기능 AJAX
	@ResponseBody
	@RequestMapping("likeCheck")	
	public String likeCheck(HttpServletRequest request,Model model) {
		System.out.println("likeCheck()");
		model.addAttribute("request", request);
		ReViewDAO mapper = sqlSession.getMapper(ReViewDAO.class);
		Gson gson = new Gson();
		HashMap<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("succees","통신성공");
		String jsonString = gson.toJson(hmap);
		ReViewService.getInstance().likeCheck(model,mapper);
		return jsonString;
	}

}
