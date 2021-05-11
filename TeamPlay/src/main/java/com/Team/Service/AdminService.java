package com.Team.Service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.Team.Dao.AdminDao;
import com.Team.Dao.ClientDao;
import com.Team.Dao.ReViewDAO;
import com.Team.List.AdminUserMangementList;
import com.Team.Vo.AttentionPointVO;
import com.Team.Vo.ClientVo;
import com.Team.Vo.ReViewCommentVO;
import com.Team.Vo.ReViewVO;

public class AdminService {
	private static AdminService instance = new AdminService();
	private AdminService() {}
	public static AdminService getInstance() {	return instance;}
	
	
	
	public void selectUserList(Model model, AdminDao mapper) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:AdminCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int currentPage = 1;	//현재페이지
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
		}
		int pageSize = 10;	
		int totalCount= mapper.userTotalCount();
		
		AdminUserMangementList list = ctx.getBean("AdminUserMangementList",AdminUserMangementList.class);
		list.SetAdminUserMangementList(pageSize, totalCount, currentPage);
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", list.getStartNo());
		hmap.put("endNo", list.getEndNo());
		list.setList(mapper.AdminUserSelectList(hmap));
		
		model.addAttribute("AdminUserList",list);
		
	}
	public String AdminUserDelete(Model model, AdminDao mapper, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:AdminCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String userId = request.getParameter("userId");
		
		mapper.AdminUserDelete(userId);
		return "성공적으로"+ userId+"을(를) 삭제했습니다.";
		
		
		
	}
	public void AdminUserUpdate(Model model, AdminDao mapper) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:ClientCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String updateId = request.getParameter("userId");
		String updatePw = request.getParameter("userPw");
		String updateEmail = request.getParameter("userEmail");
		String updatePh = request.getParameter("userPh");
		String updatePoint = request.getParameter("userPoint");
		int userLevel = Integer.parseInt(request.getParameter("userLevel")); 	
		System.out.println(updatePw);
		System.out.println(updateEmail);
		System.out.println(updatePh);
		System.out.println(updatePoint);
		System.out.println(userLevel);
		ClientVo updateVo = ctx.getBean("Client",ClientVo.class);
		updateVo.adminUserUpdate(userLevel, updateId, updatePw, updatePh, updateEmail, updatePoint);
		System.out.println(updateVo);
		mapper.adminUpserUpdate(updateVo);
		
		
	}
	public void AdminReViewSelectionOK(Model model, AdminDao mapper, ReViewDAO reViewmapper, ClientDao clientwmapper) {
		AbstractApplicationContext ReViewctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		AbstractApplicationContext Clientctx = new GenericXmlApplicationContext("classpath:ClientCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		String[] checkIdx = request.getParameterValues("cbox"); //선택한 체크박스 (Value= 리뷰 게시글 번호)
		String[] checkUserId = request.getParameterValues("ReViewId");	// 포인트 줄 사람
//		for (int i = 0; i < checkUserId.length; i++) {
//			System.out.println(checkIdx[i]);
//			System.out.println(checkUserId[i]);
//		}
		ReViewCommentVO vo = ReViewctx.getBean("ReViewCommentVO", ReViewCommentVO.class);
		vo.setContent("리뷰글 감사합니다 ^^ 포인트 30Point 적립 되셨습니다!");
		vo.setUserId("관리자");
		AttentionPointVO logVo = Clientctx.getBean("AttentionPointVO",AttentionPointVO.class);
		logVo.setContent("리뷰 적립");
		logVo.setPoint(30);
		for (int i = 0; i < checkIdx.length; i++) {
			vo.setRefIdx(Integer.parseInt(checkIdx[i]));	//댓글 추가할 IDX번호 저장
			logVo.setUserId(checkUserId[i]);				//포인트적립내역을 추가할 유저 ID
			reViewmapper.CommentUp(vo.getRefIdx());	// 댓글 개수 1증가
			reViewmapper.insertComment(vo);			// 댓글DB insert
			clientwmapper.insertPointLog(logVo);
			clientwmapper.depositAttentionPoint(logVo);
		}
		int user_point = clientwmapper.userPointSelect(""+session.getAttribute("session_id"));
		model.addAttribute("flag","AdminReViewSelection");
		session.setAttribute("session_point",user_point);
			
			
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
