package com.Team.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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
import com.Team.Vo.ServerConnectionIPVO;

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
		//전체 유저의 목록을 담을 list 객체
		AdminUserMangementList list = ctx.getBean("AdminUserMangementList",AdminUserMangementList.class);
		list.SetAdminUserMangementList(pageSize, totalCount, currentPage);
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", list.getStartNo());
		hmap.put("endNo", list.getEndNo());
		list.setList(mapper.AdminUserSelectList(hmap));	//list에 전체목록저장
		
		model.addAttribute("AdminUserList",list);		//전체목록 객체 response
		
	}
	public String AdminUserDelete(Model model, AdminDao mapper, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:AdminCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String userId = request.getParameter("userId");
		
		mapper.AdminUserDelete(userId);				//유저 삭제
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
		mapper.adminUpserUpdate(updateVo);		//유저정보수정
		
		
	}
	//(장진호)리뷰체택
	public void AdminReViewSelectionOK(Model model, AdminDao mapper, ReViewDAO reViewmapper, ClientDao clientwmapper) {
		AbstractApplicationContext ReViewctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		AbstractApplicationContext Clientctx = new GenericXmlApplicationContext("classpath:ClientCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		String[] checkIdx = request.getParameterValues("cbox"); 		//선택한 체크박스 (Value= 리뷰 게시글 번호)
		String[] checkUserId = request.getParameterValues("ReViewId");	// 포인트 줄 사람
		
		ReViewCommentVO vo = ReViewctx.getBean("ReViewCommentVO", ReViewCommentVO.class);
		vo.setContent("리뷰글 감사합니다 ^^ 포인트 30Point 적립 되셨습니다!");
		vo.setUserId("관리자");
		AttentionPointVO logVo = Clientctx.getBean("AttentionPointVO",AttentionPointVO.class);
		logVo.setContent("리뷰 적립");
		logVo.setPoint(30);
		for (int i = 0; i < checkIdx.length; i++) {
			vo.setRefIdx(Integer.parseInt(checkIdx[i]));				//댓글 추가할 IDX번호 저장
			logVo.setUserId(checkUserId[i]);							//포인트적립내역을 추가할 유저 ID
			reViewmapper.CommentUp(vo.getRefIdx());						// 댓글 개수 1증가
			reViewmapper.insertComment(vo);								// 댓글DB insert
			clientwmapper.insertPointLog(logVo);
			clientwmapper.depositAttentionPoint(logVo);
		}
		int user_point = clientwmapper.userPointSelect(""+session.getAttribute("session_id"));
		model.addAttribute("flag","AdminReViewSelection");
		session.setAttribute("session_point",user_point);
	}
	//조회수  통계
	public void statistics(Model model, AdminDao mapper, ClientDao clientwmapper) {
		AbstractApplicationContext Adminctx = new GenericXmlApplicationContext("classpath:AdminCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
		Date date2 = new Date();
		Calendar cal = Calendar.getInstance(Locale.KOREA);
		cal.setTime(date2);
		cal.add(Calendar.DATE,2-cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("첫번째 요일(월) 날짜:"+sdf.format(cal.getTime()));
		String startWeekDay = sdf.format(cal.getTime()); //20210510
		//같은주 월요일
		String currentWeekDate= sdf.format(cal.getTime())+"(월)~";
		//현재는 : 20210510(월)~20210516(일)
		//다음주는 : 20210517(월)~20210523(일)
		cal.setTime(date2);
		cal.add(Calendar.DATE,8-cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("막요일 일요일 날짜: "+sdf.format(cal.getTime()));
		
		String endWeekDay = sdf.format(cal.getTime()); //20210516
		currentWeekDate+=sdf.format(cal.getTime())+"(일)";
		
		//조회한 로그의 이번주의 달
		int nowStartMonth = Integer.parseInt(startWeekDay.substring(4,6)); //5
		int nowEndMonth = Integer.parseInt(endWeekDay.substring(4,6)); //
		//조회한 로그의 이번주의 시작일
		int nowStartdate = Integer.parseInt(startWeekDay.substring(6)); //10
		int nowEnddate = Integer.parseInt(endWeekDay.substring(6)); //
		System.out.println("시작일: "+nowStartdate);
		System.out.println("끝일: "+nowEnddate);

		//최종적으로 currentWeekDate를 request로 넘겨준다.
		System.out.println(currentWeekDate);
		String nowTime = sdf.format(date2);
		//같은주 일요일
		ArrayList<ServerConnectionIPVO> enterList = mapper.selectEnterList();	//싹다 sdf 식으로 끌어 오기
		//0 = Sunday, 1 = Monday, 2 = Tuesday, 3 = Wednesday, 4 = Thursday, 5 = Friday, 6 = Saturday
		Calendar nowStartCal = Calendar.getInstance();
		nowStartCal.set(2021, nowStartMonth-1,1);
		System.out.println("nowStartDate:"+nowStartdate);
		System.out.println("요번달 끝 일 :"+nowStartCal.getActualMaximum(Calendar.DAY_OF_MONTH));
		int sun= 0 ,mon= 0,tue= 0,wed= 0,thur= 0,fri= 0,satur= 0; 
//		&&enterList.get(i).getIndate().getDate()>=nowStartdate || enterList.get(i).getIndate().getDate()<= nowEnddate
		for (int i = 0; i < enterList.size(); i++) {
			if(enterList.get(i).getIndate().getMonth()+1==nowStartMonth || enterList.get(i).getIndate().getMonth()+1==nowEndMonth) {	//5월 6월 다가져오기
				if(enterList.get(i).getIndate().getMonth()+1==nowStartMonth) {
					if(enterList.get(i).getIndate().getDate()>=nowStartdate && 
						enterList.get(i).getIndate().getDate()<=nowStartCal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
						System.out.println(enterList.get(i).getIndate());
						if(enterList.get(i).getIndate().getDay()==0) {
							sun++;
						}
						if(enterList.get(i).getIndate().getDay()==1) {
							mon++;
						}
						if(enterList.get(i).getIndate().getDay()==2) {
							tue++;
						}
						if(enterList.get(i).getIndate().getDay()==3) {
							wed++;
						}
						if(enterList.get(i).getIndate().getDay()==4) {
							thur++;
						}
						if(enterList.get(i).getIndate().getDay()==5) {
							fri++;
						}
						if(enterList.get(i).getIndate().getDay()==6) {
							satur++;
						}//if...end	
					
					}
				}
				if(nowEndMonth!=nowStartMonth&&enterList.get(i).getIndate().getMonth()+1==nowEndMonth && enterList.get(i).getIndate().getDate()<=nowEnddate) {//6월이라면
					if(enterList.get(i).getIndate().getDay()==0) {
						sun++;
					}
					if(enterList.get(i).getIndate().getDay()==1) {
						mon++;
					}
					if(enterList.get(i).getIndate().getDay()==2) {
						tue++;
					}
					if(enterList.get(i).getIndate().getDay()==3) {
						wed++;
					}
					if(enterList.get(i).getIndate().getDay()==4) {
						thur++;
					}
					if(enterList.get(i).getIndate().getDay()==5) {
						fri++;
					}
					if(enterList.get(i).getIndate().getDay()==6) {
						satur++;
					}//if...end	
				}
				
			}
		}//for..end
		model.addAttribute("mon",mon);
		model.addAttribute("tue",tue);
		model.addAttribute("wed",wed);
		model.addAttribute("thur",thur);
		model.addAttribute("fri",fri);
		model.addAttribute("satur",satur);
		model.addAttribute("sun",sun);
		model.addAttribute("currentWeekDate",currentWeekDate);
		System.out.println("오늘요일: "+thur);		
//		=========================================여기까지 요일별 IP통계
//		여기부터 회원 성비율 차트
		int ClientIsMen = clientwmapper.genderSelect("남자");
		int ClientIsGirl = clientwmapper.genderSelect("여자");
		
		model.addAttribute("ClientIsMen",ClientIsMen);
		model.addAttribute("ClientIsGirl",ClientIsGirl);
	}
	
	
//	if(enterList.get(i).getIndate().getDay()==0) {
//		sun++;
//	}
//	if(enterList.get(i).getIndate().getDay()==1) {
//		mon++;
//	}
//	if(enterList.get(i).getIndate().getDay()==2) {
//		tue++;
//	}
//	if(enterList.get(i).getIndate().getDay()==3) {
//		wed++;
//	}
//	if(enterList.get(i).getIndate().getDay()==4) {
//		thur++;
//	}
//	if(enterList.get(i).getIndate().getDay()==5) {
//		fri++;
//	}
//	if(enterList.get(i).getIndate().getDay()==6) {
//		satur++;
//	}//if...end	
	
	
	
	
	
	
}
