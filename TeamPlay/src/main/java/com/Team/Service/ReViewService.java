package com.Team.Service;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.Team.Dao.ReViewDAO;
import com.Team.List.ReViewCommentList;
import com.Team.List.ReViewList;
import com.Team.Vo.ReViewCommentVO;
import com.Team.Vo.ReViewSearchVO;
import com.Team.Vo.ReViewVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReViewService {
	String fileAddr = "http://localhost:8010/korea/upload/";
	
	private static ReViewService instance = new ReViewService();
	private ReViewService() {}
	public static ReViewService getInstance() {	return instance;}
	
	public void ReViewSelect(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - ReViewSelect()");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int currentPage = 1;
		String flag="";
		try {
			flag = ""+request.getParameter("flag");
//			System.out.println(flag);
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
		if(flag.trim().equals("AdminReViewSelection")) {
			model.addAttribute("succees","admincheck");
		}
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
		
//		========================================= 이후 댓글
		String commentUpdate = ""+request.getParameter("commentUpdate");
		ReViewCommentList commentList = ctx.getBean("ReViewCommentList",ReViewCommentList.class);
		int pageSize = 6;		//한페이지에 표시할 글의 개수
		int totalCount= mapper.CommentTotalCount(idx);
		System.out.println("totalCount: "+totalCount);
		ReViewList list = ctx.getBean("ReViewList",ReViewList.class);
		list.setDate(pageSize, totalCount, currentPage);
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", list.getStartNo());
		hmap.put("endNo", list.getEndNo());
		hmap.put("idx",idx);	
		commentList.setList(mapper.selectCommentList(hmap));
		System.out.println("commentList: "+commentList.getList());
		model.addAttribute("commentList", commentList);
		if(commentUpdate.equals("update")) {
			model.addAttribute("commentUpdate", "update");
		}
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
	//리뷰게시글 추가
	public void ReViewInsert(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - ReViewDelete() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();		// 현재로그인ID를 받기위해 세션을받아온다.
		ReViewVO vo = ctx.getBean("ReViewVO",ReViewVO.class);
		String userId= ""+session.getAttribute("session_id");		//세션에 저장되있는ID호출
		System.out.println("userId="+userId);
		String imgNames="";
		int flag = 1 ; 	// insert : 1 
						// update : 2
						// delete : 3
		int imgCount=0;		//업로드한 이미지갯수
		try {
			MultipartRequest mr = new MultipartRequest(request,
//					application.getRealPath("./upload/"),
					"D:/upload",
					5*1024*1024,
					"UTF-8",
					new DefaultFileRenamePolicy()
					);
		
			Enumeration<String> filenames = mr.getFileNames();	//파일이름(들)
			while(filenames.hasMoreElements()) {				//파일있는지 묻기
				String parameter = ""+filenames.nextElement();	//다음파일
				String fileRealName = mr.getFilesystemName(parameter);	//실제저장된파일이름
				String fileName = mr.getFilesystemName(parameter);	//파일이름
				if(fileName==null) {									//null이면 계속
					continue;
				}
				imgNames+=fileRealName;							//imgNames에 실제 파일이름추가
				if(filenames.hasMoreElements()) {
					imgNames+=",";								// imgNames에 ","를 붙임 (split)하기 위함
				}
			}	
			String[] imgName = imgNames.split(",");
			imgCount = imgName.length;
			vo.setRE_imgNames(imgNames);							//넘어온 모든 파일의 처리가 끝나면 vo객체에 파일이름을 저장시킴.
			vo.setRE_userId(userId);
			vo.setRE_title(mr.getParameter("title"));
			vo.setRE_content(mr.getParameter("content"));
			vo.setRE_img(imgCount);
			vo.setRE_categoryDetail(mr.getParameter("categoryDetail"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(vo);
			mapper.ReViewInsert(vo);
			request.setAttribute("flag", flag);
			request.setAttribute("currentPage", 1);
		
		
	}
	//리뷰게시판 검색기능
	public void ReViewSearch(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - ReViewDelete() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();		// 현재로그인ID를 받기위해 세션을받아온다.
		ReViewSearchVO searchVo = ctx.getBean("ReViewSearchVO",ReViewSearchVO.class);
		searchVo.setSearchName(request.getParameter("searchName"));
		searchVo.setSearchText(request.getParameter("searchText"));
		int currentPage = 1;	//현재페이지
		int pageSize = 6;		//한페이지에 표시할 글의 개수
		int totalCount= mapper.ReViewTotalCount(searchVo);		//검색타입과 검색 내용으로 검색한 리뷰글의 총 개수
		if(totalCount==0) {
			model.addAttribute("NoSearch", true);
		}
		System.out.println("totalCount");
		ReViewList list = ctx.getBean("ReViewList",ReViewList.class);
		list.setDate(pageSize, totalCount, currentPage);
		searchVo.setStartNo(list.getStartNo());
		searchVo.setEndNo(list.getEndNo());
		list.setList(mapper.ReViewSearch(searchVo));
		
		model.addAttribute("ReViewList", list);
	}
	// 댓글작성	
	public void commentInsert(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - commentInsert() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();		// 현재로그인ID를 받기위해 세션을받아온다.
		int flag= 4;
		String userId =""+session.getAttribute("session_id");   //답글작성자
		String content = request.getParameter("content");		//답글내용
		int currentPage =1;
		try {currentPage = Integer.parseInt(request.getParameter("currentPage"));}catch(Exception e) {e.printStackTrace();}
		int refIdx = Integer.parseInt(request.getParameter("refIdx"));	//답글을추가할 글번호
		
		ReViewCommentVO vo = ctx.getBean("ReViewCommentVO",ReViewCommentVO.class);
		vo.setCommentVO(refIdx, content, userId);
		//답글이 달린 글번호에 들어가서 답글 갯수를 1올려야한다.
		mapper.CommentUp(refIdx);
		
		//vo에 초기화한 답글이 들어간 리뷰글번호,답글내용,답글  작성자를 전달
		mapper.insertComment(vo);
		model.addAttribute("flag", flag);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("idx", refIdx);
		
	}
	public void updateComment(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - updateComment() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();		// 현재로그인ID를 받기위해 세션을받아온다.
		int idx = Integer.parseInt(request.getParameter("idx"));			//댓글번호
		System.out.println("idx:"+idx);
		int Commentidx = Integer.parseInt(request.getParameter("Commentidx"));			//댓글번호
		String content =request.getParameter("content");
		
		System.out.println("수정번호: "+Commentidx);
		System.out.println("수정댓글: "+content);
		
		HashMap<String, String> hmap = new HashMap<String, String>();
		hmap.put("idx", ""+Commentidx);
		hmap.put("content", content);
		
		mapper.updateComment(hmap);			//업데이트
		model.addAttribute("idx", idx);
		model.addAttribute("commentUpdate", "update");
		
		
	}
	public String likeCheck(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - likeCheck() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();		// 현재로그인ID를 받기위해 세션을받아온다.
		String userId = request.getParameter("userId");
		int idx = Integer.parseInt(request.getParameter("idx"));
		String flag = ""+request.getParameter("checkFlag");
		// flag: check (좋아요 개수 추가 + 좋아요한사람 이름추가)
		// flag: cancle (좋아요 개수 1감소 + 좋아요한사람 이름뺴기)
		System.out.println("userId:"+userId);
		System.out.println("idx:"+idx);
		System.out.println("flag:"+flag);
		userId+=",";
		ReViewVO vo = mapper.selectByIdx(idx);
		String goodCheckUser = vo.getRE_goodCheckUser();
		System.out.println(vo);
		if(flag.equals("check")) {
			mapper.likeUp(idx);			//좋아요 1증가
			System.out.println("증가");
			goodCheckUser+= userId;	
			vo.setRE_goodCheckUser(goodCheckUser);
			mapper.checkUserUpdate(vo);	//좋아요누른사람 게시글수정
		}else if(flag.equals("cancle")) {
			mapper.likeDown(idx); 		//좋아요 개수다운
			System.out.println("감소");
			goodCheckUser= goodCheckUser.replace(userId, "");	
			vo.setRE_goodCheckUser(goodCheckUser);
			mapper.checkUserUpdate(vo);
		}
		return "succees";
		
	}
	public void deleteComment(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - deleteComment() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int CommentIdx = Integer.parseInt(request.getParameter("commentIdx"));			//댓글번호
		int idx = Integer.parseInt(request.getParameter("idx"));			//댓글을가진 게시글번호
		
		mapper.minusCommentCount(idx);
		
		mapper.deleteComment(CommentIdx);
		
		ReViewDetailSelect(model, mapper);
	}
	public void ReViewGoodKing(Model model, ReViewDAO reViewmapper) {
		System.out.println("ReViewService - deleteComment() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		ReViewList list = ctx.getBean("ReViewList",ReViewList.class);
		list.setList(reViewmapper.goodKingReView());
		for (int i = 0; i < list.getList().size(); i++) {
			list.getList().get(i).setRE_rank(i+1);
		}
		
		System.out.println(list.getList());
		model.addAttribute("ReViewList",list.getList());
		
	}

}
