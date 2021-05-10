package com.Team.Service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.Team.Dao.AdminDao;
import com.Team.List.AdminUserMangementList;

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
	
	
	
	
	
	
	
	
	
}
