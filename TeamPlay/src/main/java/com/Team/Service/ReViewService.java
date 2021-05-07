package com.Team.Service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.Team.Dao.ReViewDAO;
import com.Team.List.ReViewList;
import com.Team.Vo.ReViewVO;

public class ReViewService {
	String fileAddr = "http://localhost:8009/korea/upload/";
	
	private static ReViewService instance = new ReViewService();
	private ReViewService() {}
	public static ReViewService getInstance() {	return instance;}
	
	public void ReViewSelect(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - ReViewSelect()");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			
		}
		int pageSize = 6;
		int totalCount= mapper.selectCount();
		System.out.println("---------------------");
		
		ReViewList list = ctx.getBean("ReViewList",ReViewList.class);
		list.setDate(pageSize, totalCount, currentPage);
		list.setList(mapper.selectList(list));
		
		model.addAttribute("ReViewList",list);
	}
	
	public void ReViewDetailSelect(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - ReViewSelect()");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			
		}
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		ReViewVO vo = ctx.getBean("ReViewVO",ReViewVO.class);
		vo = mapper.selectByIdx(idx);
		
		String[] fileNameList = vo.getRE_imgNames().split(",");
		for (int i = 0; i < fileNameList.length; i++) {
			String fileName =fileAddr;
			fileName+=fileNameList[i];
			request.setAttribute("fileName"+(i+1), fileName);
			System.out.println(fileName);
		}
		model.addAttribute("vo",vo);
		model.addAttribute("currentPage",currentPage);
		
	}
	
	
}
