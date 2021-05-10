package com.Team.Controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Team.Dao.QAboardDao;
import com.Team.Dao.ReViewDAO;
import com.Team.Service.QAservice;
import com.Team.Service.ReViewService;


@Controller
@RequestMapping(value = "/")
public class QAboardController {
	@Autowired
	public SqlSession sqlSession;


	@RequestMapping("QAboardDo")	// 리뷰게시판출력-->리뷰게시판이동
	public String QAboardDo(HttpServletRequest request,Model model) {
		System.out.println("QAboardDo()");
		model.addAttribute("request", request);
		QAboardDao mapper = sqlSession.getMapper(QAboardDao.class);
		
		QAservice.getInstance().QAselectList(model,mapper);
		return "QAboard/QAlist";
	}
	
	@RequestMapping("QAinsertGo")
	public String QAinsertGo(HttpServletRequest request,Model model) {
		System.out.println("QAinsertGo()");
		return "QAboard/QAinsert";
	}
	
	@RequestMapping("insertOKDo")
	public String insertOKDo(HttpServletRequest request,Model model) {
		System.out.println("insertOKDo()");
		model.addAttribute("request", request);
		QAboardDao mapper = sqlSession.getMapper(QAboardDao.class);
		
		QAservice.getInstance().insert(model,mapper);
		
		return "redirect:QAboardDo";
	}
	
	@RequestMapping("contentViewDo")
	public String contentViewDo(HttpServletRequest request,Model model) {
		System.out.println("contentViewDo()");
		model.addAttribute("request", request);
		QAboardDao mapper = sqlSession.getMapper(QAboardDao.class);
		
		QAservice.getInstance().contentViewDo(model,mapper);
		
		return "QAboard/QAcontentView";
	}
	
	@RequestMapping("updateDo")
	public String updateDo(HttpServletRequest request,Model model) {
		System.out.println("updateDo()");
		model.addAttribute("request", request);
		QAboardDao mapper = sqlSession.getMapper(QAboardDao.class);
		
		QAservice.getInstance().updateDo(model,mapper);
		
		return "redirect:QAboardDo";
	}
	
	@RequestMapping("deleteDo")
	public String deleteDo(HttpServletRequest request,Model model) {
		System.out.println("deleteDo()");
		model.addAttribute("request", request);
		QAboardDao mapper = sqlSession.getMapper(QAboardDao.class);
		
		QAservice.getInstance().deleteDo(model,mapper);
		
		return "redirect:QAboardDo";
	}
	
	@RequestMapping("replyDo")
	public String replyDo(HttpServletRequest request,Model model) {
		System.out.println("replyDo()");
		model.addAttribute("request", request);
		QAboardDao mapper = sqlSession.getMapper(QAboardDao.class);
		QAservice.getInstance().QAselectByIdx(model,mapper);
		
		return "QAboard/QAreply";
	}
	
	@RequestMapping("replyInsertDo")
	public String replyInsertDo(HttpServletRequest request,Model model) {
		System.out.println("replyInsertDo()");
		model.addAttribute("request", request);
		QAboardDao mapper = sqlSession.getMapper(QAboardDao.class);
		QAservice.getInstance().replyInsertDo(model,mapper);
		
		return "redirect:QAboardDo";
	}
	
	@RequestMapping("replyViewDo")
	public String replyViewDo(HttpServletRequest request,Model model) {
		System.out.println("replyViewDo()");
		model.addAttribute("request", request);
		QAboardDao mapper = sqlSession.getMapper(QAboardDao.class);
		QAservice.getInstance().replyViewDo(model,mapper);
		
		return "QAboard/QAreplyView";
	}
	
	@RequestMapping("replyUpdateDo")
	public String replyUpdateDo(HttpServletRequest request,Model model) {
		System.out.println("replyUpdateDo()");
		model.addAttribute("request", request);
		QAboardDao mapper = sqlSession.getMapper(QAboardDao.class);
		QAservice.getInstance().replyUpdateDo(model,mapper);
		
		return "redirect:QAboardDo";
	}
	
	@RequestMapping("AnsDeleteDo")
	public String AnsDeleteDo(HttpServletRequest request,Model model) {
		System.out.println("AnsDeleteDo()");
		model.addAttribute("request", request);
		QAboardDao mapper = sqlSession.getMapper(QAboardDao.class);
		QAservice.getInstance().AnsDeleteDo(model,mapper);
		
		return "redirect:QAboardDo";
	}
	
}



