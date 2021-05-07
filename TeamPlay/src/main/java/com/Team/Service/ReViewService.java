package com.Team.Service;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.Team.Dao.ReViewDAO;
import com.Team.List.ReViewList;
import com.Team.Vo.ReViewVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReViewService {
	String fileAddr = "http://localhost:8009/korea/upload/";	// 여기서 포트만 수정하세요 or (server.xml 경로도 자기에맞게)
	
	private static ReViewService instance = new ReViewService();
	private ReViewService() {}
	public static ReViewService getInstance() {	return instance;}
	
	public void ReViewSelect(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - ReViewSelect() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int currentPage = 1;	//현재페이지
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			
		}
		int pageSize = 6;		//한페이지에 표시할 글의 개수
		int totalCount= mapper.selectCount();
		System.out.println("---------------------");
		
		ReViewList list = ctx.getBean("ReViewList",ReViewList.class);
		list.setDate(pageSize, totalCount, currentPage);
		list.setList(mapper.selectList(list));
		
		model.addAttribute("ReViewList",list);
	}
	
	public void ReViewDetailSelect(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - ReViewSelect() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int currentPage = 1;	//현재페이지
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			
		}
		int idx = Integer.parseInt(request.getParameter("idx"));	//선택한 게시글 글번호
		
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
	public void ReViewUpdate(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - ReViewSelect() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int currentPage = 1;	//현재페이지
		int flag=2;
		int imgCount=0;								//업로드한 이미지갯수
		int idx = 0;
		String imgNames="";
		ReViewVO vo = ctx.getBean("ReViewVO",ReViewVO.class);
		try {
			MultipartRequest mr = new MultipartRequest(request,
//					application.getRealPath("../upload"),
					"D:/upload",
					5*1024*1024,
					"UTF-8",
					new DefaultFileRenamePolicy()
					);
			
			String fileName1=mr.getParameter("fileName1");			//선택한 파일없음 일때 들어옴
			String fileName2=mr.getParameter("fileName2");
			String fileName3=mr.getParameter("fileName3");
//			http://localhost:8009/korea/upload/iconfinder_arrow_left_13150912.png
			String local =fileAddr;						// TeamProject시 맞춰야할것
			
			Enumeration<String> filenames = mr.getFileNames();				//업로드로 올라온 파일이름(들)
			idx = Integer.parseInt(mr.getParameter("idx"));
			currentPage = Integer.parseInt(mr.getParameter("currentPage"));
			while(filenames.hasMoreElements()) {							//파일있는지 묻기
				String parameter = ""+filenames.nextElement();				//다음파일
				String fileRealName = mr.getFilesystemName(parameter);		//실제저장된파일이름
				String fileName = mr.getFilesystemName(parameter);		//파일이름
				if(fileName==null) {										//null이면 계속
					continue;
				}
				imgNames+=fileRealName;										//imgNames에 실제 파일이름추가
				if(filenames.hasMoreElements()) {
					imgNames+=",";											// imgNames에 ","를 붙임 (split)하기 위함
				}
			}//while..end
			if(fileName1!=null && fileName2!=null &&fileName3!=null) {
					fileName1=fileName1.replaceAll(local, "");
					imgNames+=fileName1;
					fileName2=fileName2.replaceAll(local, ",");
					imgNames+=fileName2;
					fileName3=fileName3.replaceAll(local, ",");
					imgNames+=fileName3;
			}else if(fileName1!=null || fileName2!=null ||fileName3!=null) {
				if(fileName1!=null) {
					fileName1=fileName1.replaceAll(local, "");
					imgNames+=fileName1;
				}
				if(fileName2!=null) {
					fileName2=fileName2.replaceAll(local, ",");
					imgNames+=fileName2;
				}
				if(fileName3!=null) {
					fileName3=fileName3.replaceAll(local, ",");
					imgNames+=fileName3;
				}
			}
			System.out.println(imgNames);
			String[] imgName = null; 										//이미지 카운트를 초기화 시킬 수단
//			======================vo 저장========================


		    imgName=imgNames.split(",");
		    imgCount = imgName.length;
		    vo.setDate(idx, mr.getParameter("title"), mr.getParameter("content"),
		    		imgCount, mr.getParameter("categoryDetail"), imgNames);
		    
		    mapper.ReViewUpdate(vo);			//정보 업데이트(새로 추가or삭제 한 이미지 수정까지)
		} catch (IOException e) {
			e.printStackTrace();
		}
			vo = mapper.selectByIdx(idx);	// update 완료한 정보를 다시 불러옴.
			request.setAttribute("currentPage", currentPage);		
			request.setAttribute("vo", vo);
			request.setAttribute("flag", flag);
			
			//다시 정보창에 뿌려줄 이미지 알고리즘 코딩
			String[] fileNameList = vo.getRE_imgNames().split(",");
			for (int i = 0; i < fileNameList.length; i++) {
				String fileName =fileAddr;
				fileName+=fileNameList[i];
				request.setAttribute("fileName"+(i+1), fileName);
			}
		
		
	}
	// idx를 전달받아 데이터 한건을 가지고 오는 메소드를 호출하는 메소드
	public void selectByIdx(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - ReViewSelect() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int idx = Integer.parseInt(request.getParameter("idx"));
		int currentPage=1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}catch (Exception e) {
		}
		ReViewVO vo = ctx.getBean("ReViewVO",ReViewVO.class);
		vo = mapper.selectByIdx(idx);
		String[] fileNameList = vo.getRE_imgNames().split(",");
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < fileNameList.length; i++) {
			String fileName =fileAddr;
			fileName+=fileNameList[i];
			request.setAttribute("fileName"+(i+1), fileName);
			if(i!=fileNameList.length-1) {
			buffer.append(fileNameList[i]+","); 	// 파일1,파일2,파일3
			}else {									// 파일,
				buffer.append(fileNameList[i]);
			}
		}
		model.addAttribute("buffer", buffer);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("vo", vo);
		
	}
	public void ReViewDelete(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - ReViewDelete() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int idx = Integer.parseInt(request.getParameter("idx"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		int flag = 3 ; 	// insert : 1 
		                // update : 2
		                // delete : 3
		mapper.ReViewDelete(idx);
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("flag", flag);
	}
	
	
	
	
	
}
