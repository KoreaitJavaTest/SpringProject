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
	//(장진호)포트번호를 자신의 포트에 맞게 설정하세요!
	
	private static ReViewService instance = new ReViewService();
	private ReViewService() {}
	public static ReViewService getInstance() {	return instance;}
	
	//(장진호)리뷰게시판 조회 메소드
	public void ReViewSelect(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - ReViewSelect()");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		ReViewList list = ctx.getBean("ReViewList",ReViewList.class);		//리뷰글을 받을 ReViewList 클래스의 bean
		int currentPage = 1;							//현재페이지
		String flag="";									//다양한 attribute를 돌려주기위한 flag 변수
		try {
			flag = ""+request.getParameter("flag");		//null처리
			currentPage = Integer.parseInt(request.getParameter("currentPage"));	//null처리
		} catch (Exception e) {e.printStackTrace();}
		
		int pageSize = 6;								//한페이지당 데이터 노출 개수
		int totalCount= mapper.selectCount();			//리뷰글 총 개수
		
		list.setDate(pageSize, totalCount, currentPage);	//페이징 작업할 알고리즘 초기화
		list.setList(mapper.selectList(list));				//리뷰글 담기
		model.addAttribute("ReViewList",list);
		
		if(flag.trim().equals("AdminReViewSelection")) {	
			model.addAttribute("succees","admincheck");		//다양한 방법으로 attribute
		}
	}//ReViewSelect...end
	
	//(장진호)리뷰글 상세보기 메소드
	public void ReViewDetailSelect(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - ReViewSelect()");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		ReViewVO vo = ctx.getBean("ReViewVO",ReViewVO.class);		//상세보기할 데이터 1건을 담을 VO클래스 Bean
		
		int currentPage = 1;										//돌아가기 버튼 클릭후 돌아갈 리뷰게시판 페이지
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));	//numberFormatexception 처리
		} catch (Exception e) {e.printStackTrace();}
		int idx = Integer.parseInt(request.getParameter("idx"));	//선택한 게시글 글번호
		
		vo = mapper.selectByIdx(idx);								//DB에서 선택 게시글  글번호의 정보를 vo에 초기화
		String[] fileNameList = vo.getRE_imgNames().split(",");		//게시글에서 저장한 파일이름을 배열에 초기화
		for (int i = 0; i < fileNameList.length; i++) {
			String fileName =fileAddr;
			fileName+=fileNameList[i];
			request.setAttribute("fileName"+(i+1), fileName);		//fileName1~3까지 response
		}
		model.addAttribute("vo",vo);								//정보 response
		model.addAttribute("currentPage",currentPage);				//현재 페이지 response
		
//		밑줄부터는 리뷰상세보기에서 댓글의 역할을 맡는다.============================
		String commentUpdate = ""+request.getParameter("commentUpdate");	//댓글수정 -> 리뷰글보기로 넘어올 수도 있다.
		ReViewCommentList commentList = ctx.getBean("ReViewCommentList",ReViewCommentList.class); //댓글리스트
		int pageSize = 6;											//한페이지에 표시할 댓글의 개수
		int totalCount= mapper.CommentTotalCount(idx);				//해당 게시글에 달린 댓글의 총개수
		
		ReViewList list = ctx.getBean("ReViewList",ReViewList.class);	//댓글칸 전용 페이징
		list.setDate(pageSize, totalCount, currentPage);
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", list.getStartNo());
		hmap.put("endNo", list.getEndNo());
		hmap.put("idx",idx);	
		commentList.setList(mapper.selectCommentList(hmap));
		
		model.addAttribute("commentList", commentList); 			//댓글리스트 response
		if(commentUpdate.equals("update")) {						//만약 댓글이 update됬다면 해당정보를 response
			model.addAttribute("commentUpdate", "update");
		}
	}
	//(장진호)리뷰글 업데이트
	public void ReViewUpdate(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - ReViewUpdate() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int currentPage = 1;						//원래 게시글이 있던 페이지
		int flag=2;
		int imgCount=0;								//업로드한 이미지갯수
		int idx = 0;								//해당게시글글번호
		String imgNames="";							//해당 게시글이 재업로드한 이미지 파일 이름의 변수
		ReViewVO vo = ctx.getBean("ReViewVO",ReViewVO.class);	//해당게시글을 수정하기위한 vo객체
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
			String local =fileAddr;									// localhost:포트번호 수정해주자!
			
			idx = Integer.parseInt(mr.getParameter("idx"));
			currentPage = Integer.parseInt(mr.getParameter("currentPage"));
			
			//파일업로드 코딩
			Enumeration<String> filenames = mr.getFileNames();				//업로드로 올라온 파일이름(들)
			while(filenames.hasMoreElements()) {							//파일있는지 묻기
				String parameter = ""+filenames.nextElement();				//다음파일
				String fileRealName = mr.getFilesystemName(parameter);		//실제저장된파일이름
				String fileName = mr.getFilesystemName(parameter);			//파일이름
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
			//if....end
			String[] imgName = null; 										//이미지 카운트를 초기화 시킬 수단
			
			
//			======================vo 저장========================
		    imgName=imgNames.split(",");
		    imgCount = imgName.length;
		    vo.setDate(idx, mr.getParameter("title"), mr.getParameter("content"),
		    		imgCount, mr.getParameter("categoryDetail"), imgNames);
		    
		    mapper.ReViewUpdate(vo);										//정보 업데이트(새로 추가or삭제 한 이미지 수정까지)
		} catch (IOException e) {
			e.printStackTrace();
		}
			vo = mapper.selectByIdx(idx);									// update 완료한 정보를 다시 불러옴.
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
	// (장진호)idx를 전달받아 데이터 한건을 가지고 오는 메소드를 호출하는 메소드
	// 리뷰글정보  -> 업데이트 페이지로  넘겨준다.
	public void selectByIdx(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - ReViewSelect() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		ReViewVO vo = ctx.getBean("ReViewVO",ReViewVO.class);		//정보 1건을 담은 vo객체
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		int currentPage=1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}catch (Exception e) {
		}
		
		vo = mapper.selectByIdx(idx);								//해당IDX 정보한건을 담는다.
		String[] fileNameList = vo.getRE_imgNames().split(",");		//기존에 업로드한 파일의 이름들을 배열화
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < fileNameList.length; i++) {
			String fileName =fileAddr;
			fileName+=fileNameList[i];
			request.setAttribute("fileName"+(i+1), fileName);
			if(i!=fileNameList.length-1) {
			buffer.append(fileNameList[i]+","); 					// 파일1,파일2,파일3
			}else {													// 파일,
				buffer.append(fileNameList[i]);
			}
		}
		model.addAttribute("buffer", buffer);						//파일이름들 Stringbuffer로 response
		model.addAttribute("currentPage", currentPage);				//원래있던게시물의 현재페이지 response
		model.addAttribute("vo", vo);								//게시글정보 response
	}
	//(장진호)리뷰게시글 삭제 메소드
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
		
		mapper.ReViewDelete(idx);	//idx게시글 삭제
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("flag", flag);	//flag response 하여 메세지창띄우기위함
	}
	//(장진호)리뷰게시글 추가
	public void ReViewInsert(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - ReViewDelete() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();		// 현재로그인ID를 받기위해 세션을받아온다.
		ReViewVO vo = ctx.getBean("ReViewVO",ReViewVO.class);		//게시글작성 정보를 vo에 담고 DB에 저장하기 위함
		String userId= ""+session.getAttribute("session_id");		//게시글작성한 작성자
		String imgNames="";											//해당게시글의 이미지이름을 담는 변수
		int flag = 1 ; 												// insert : 1 
																	// update : 2
																	// delete : 3
		int imgCount=0;												//업로드한 이미지갯수
		try {
			MultipartRequest mr = new MultipartRequest(request,
//					application.getRealPath("./upload/"),
					"D:/upload",
					5*1024*1024,
					"UTF-8",
					new DefaultFileRenamePolicy()
					);
		
			Enumeration<String> filenames = mr.getFileNames();		//파일이름(들)
			while(filenames.hasMoreElements()) {					//파일있는지 묻기
				String parameter = ""+filenames.nextElement();		//다음파일
				String fileRealName = mr.getFilesystemName(parameter);//실제저장된파일이름
				String fileName = mr.getFilesystemName(parameter);		//파일이름
				if(fileName==null) {									//null이면 계속
					continue;
				}
				imgNames+=fileRealName;								//imgNames에 실제 파일이름추가
				if(filenames.hasMoreElements()) {
					imgNames+=",";									// imgNames에 ","를 붙임 (split)하기 위함
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
			request.setAttribute("flag", flag);						//메세지창을 띄우기 위한 flag response
			request.setAttribute("currentPage", 1);					//해당게시글 페이지
		
		
	}
	//(장진호)리뷰게시판 검색기능
	public void ReViewSearch(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - ReViewDelete() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();				// 현재로그인ID를 받기위해 세션을받아온다.
		ReViewSearchVO searchVo = ctx.getBean("ReViewSearchVO",ReViewSearchVO.class);
		searchVo.setSearchName(request.getParameter("searchName"));
		searchVo.setSearchText(request.getParameter("searchText"));
		int currentPage = 1;									//현재페이지
		int pageSize = 6;										//한페이지에 표시할 글의 개수
		int totalCount= mapper.ReViewTotalCount(searchVo);		//검색타입과 검색 내용으로 검색한 리뷰글의 총 개수
		if(totalCount==0) {										//검색내용이 없으면 실행할 조건문
			model.addAttribute("NoSearch", true);
		}
		ReViewList list = ctx.getBean("ReViewList",ReViewList.class);	//검색내용을 담을 ReViewList객체의 list
		list.setDate(pageSize, totalCount, currentPage);				//검색도 페이징작업을해야한다.
		searchVo.setStartNo(list.getStartNo());
		searchVo.setEndNo(list.getEndNo());
		list.setList(mapper.ReViewSearch(searchVo));					//검색내용 ArrayList로 초기화
		model.addAttribute("ReViewList", list);							//페이징+검색내용을 담은ReViewList객체 response
	}
	// (장진호)댓글작성	
	public void commentInsert(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - commentInsert() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();				// 현재로그인ID를 받기위해 세션을받아온다.
		int flag= 4;											// 메세지창 띄우기 위함
		String userId =""+session.getAttribute("session_id");   //답글작성자
		String content = request.getParameter("content");		//답글내용
		int currentPage =1;										//댓글작성게시물의 현재페이지
		try {currentPage = Integer.parseInt(request.getParameter("currentPage"));}catch(Exception e) {e.printStackTrace();}
		int refIdx = Integer.parseInt(request.getParameter("refIdx"));	//답글을추가할 글번호
		
		ReViewCommentVO vo = ctx.getBean("ReViewCommentVO",ReViewCommentVO.class);	//답글1개의 정보를 저장하는 객체
		vo.setCommentVO(refIdx, content, userId);									//정보저장
		
		mapper.CommentUp(refIdx); 													//답글이 달린 게시물의 총 댓글갯수 +1 증가
		
		mapper.insertComment(vo);													//답글내용 DB에 저장
		model.addAttribute("flag", flag);											
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("idx", refIdx);												
		
	}
	//(장진호) 댓글 수정
	public void updateComment(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - updateComment() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();							// 현재로그인ID를 받기위해 세션을받아온다.
		int idx = Integer.parseInt(request.getParameter("idx"));			//게시글번호
		System.out.println("idx:"+idx);
		int Commentidx = Integer.parseInt(request.getParameter("Commentidx"));	//댓글번호
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
	//(장진호) 리뷰게시글 좋아요 기능(AJAX 메소드)
	public String likeCheck(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - likeCheck() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();			// 현재로그인ID를 받기위해 세션을받아온다.
		String userId = request.getParameter("userId");
		int idx = Integer.parseInt(request.getParameter("idx"));
		String flag = ""+request.getParameter("checkFlag");	//jsp에서 좋아요체크(해제)를 만들어놓았고 거기에 따른 flag가 넘어온다.
															// flag: check (좋아요 개수 추가 + 좋아요한사람 이름추가)
															// flag: cancle (좋아요 개수 1감소 + 좋아요한사람 이름뺴기)
		userId+=",";
		ReViewVO vo = mapper.selectByIdx(idx);				//해당글번호의 리뷰게시글의 정보를 저장
		String goodCheckUser = vo.getRE_goodCheckUser();	//해당글의 좋아요를 누른 유저목록들 (,)
		if(flag.equals("check")) {
			mapper.likeUp(idx);								//좋아요 1증가
			goodCheckUser+= userId;	
			vo.setRE_goodCheckUser(goodCheckUser);
			mapper.checkUserUpdate(vo);						//좋아요누른사람 게시글수정
		}else if(flag.equals("cancle")) {
			mapper.likeDown(idx); 							//좋아요 개수다운
			goodCheckUser= goodCheckUser.replace(userId, "");//좋아요 누른사람목록에서 해당 ID 삭제
			vo.setRE_goodCheckUser(goodCheckUser);			 //적용
			mapper.checkUserUpdate(vo);						 //적용
		}
		return "succees";									//AJAX 조건문에 해당하는 문자열 리턴
	}
	//(장진호) 댓글 삭제
	public void deleteComment(Model model, ReViewDAO mapper) {
		System.out.println("ReViewService - deleteComment() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int CommentIdx = Integer.parseInt(request.getParameter("commentIdx"));			//댓글번호
		int idx = Integer.parseInt(request.getParameter("idx"));						//댓글을가진 게시글번호
		
		mapper.minusCommentCount(idx);													//해당게시글 댓글개수 -1
		mapper.deleteComment(CommentIdx);												//해당게시글 댓글 삭제
		ReViewDetailSelect(model, mapper);
	}
	//(장진호) 메인페이지 좋아요 순위별 3개 노출 메소드
	public void ReViewGoodKing(Model model, ReViewDAO reViewmapper) {
		System.out.println("ReViewService - deleteComment() 메소드 실행");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		ReViewList list = ctx.getBean("ReViewList",ReViewList.class);
		list.setList(reViewmapper.goodKingReView());			//좋아요 순위 내림차순 3개까지 를 담아온다.
		for (int i = 0; i < list.getList().size(); i++) {
			list.getList().get(i).setRE_rank(i+1);				//랭킹순위 붙여주기 ->jsp에서 사용
		}
		
		model.addAttribute("ReViewList",list.getList());		//리스트뿌려주기

		
	}

}
