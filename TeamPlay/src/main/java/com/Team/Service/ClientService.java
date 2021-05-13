package com.Team.Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Team.Dao.ClientDao;
import com.Team.Dao.ReViewDAO;
import com.Team.Dao.ShopDAO;
import com.Team.List.QAboardList;
import com.Team.List.ReViewList;
import com.Team.Vo.AttentionPointVO;
import com.Team.Vo.ClientVo;
import com.Team.Vo.IpVo;
import com.Team.Vo.ShopVO;

import util.Gmail;
import util.SHA256;


public class ClientService {
	private static ClientService instance = new ClientService();
	private ClientService() {}
	public static ClientService getInstance() {	return instance;}
	
	public void join(Model model, HttpServletResponse response, ClientDao mapper) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:ClientCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String addr_head = request.getParameter("addr_head");
		String addr_end = request.getParameter("addr_end");
		String email_head = request.getParameter("email_head");
		String email_end = request.getParameter("email_end");
		String email = email_head + "@" + email_end;
		String phone = request.getParameter("phone");
		
		if(gender.equals("성별선택") || email_end.equals("이메일선택")){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('입력이 안된 사항이 있습니다');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		
		ClientVo vo = (ClientVo) ctx.getBean("Client");
		vo.setClientVo(id, password, gender, addr_head, addr_end, phone, email, SHA256.getSHA256(email));

		int result = mapper.join(vo);
		
		if(result == -1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('회원가입에 문제가 생겼습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}	
		
		String host = "http://localhost:9090/Controller/";
		String to = mapper.getClientEmail(vo.getClient_id());
		String from = "gygus7345@naver.com";
		String subject = "이메일인증입니다.";
		String content = "다음 링크에 접속하여 이메일 인증을 진행하세요" + "<a href ='"+host+"JoinEmailResultViewDo?code=" + new SHA256().getSHA256(to) + "'>이메일 인증하기</a>";
		
		Properties p = new Properties();
		p.put("mail.smtp.user",from);
		p.put("mail.smtp.host","smtp.googlemail.com");
		p.put("mail.smtp.port","465");
		p.put("mail.smtp.starttls.enable","true");
		p.put("mail.smtp.auth","true");
		p.put("mail.smtp.debug","true");
		p.put("mail.smtp.socketFactory.port","465");
		p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback","false");
		
		try{
			
			Authenticator auth = new Gmail();
			Session ses = Session.getInstance(p,auth);
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses);
			msg.setSubject(subject);
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO,toAddr);
			msg.setContent(content,"text/html;charset=UTF-8");
			Transport.send(msg);
			
		}catch(Exception e){
			e.printStackTrace();
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('오류가 발생했습니다..');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		} 
		
	}
	
	public void emailCheckAction(Model model, HttpServletResponse response, ClientDao mapper) throws UnsupportedEncodingException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:ClientCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String code = request.getParameter("code");
		mapper.emailCheckAction(code);
		
	}
	public void IdoverlapcheckLogic(Model model, HttpServletResponse response,ClientDao mapper) throws IOException {
		System.out.println("일로 들어옴?");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		response.setContentType("text/html; charset=utf-8");
		String client_id = request.getParameter("client_id");
		System.out.println(client_id);
		
		response.getWriter().write(getJSON(client_id,mapper));
		
	}
	private String getJSON(String client_id, ClientDao mapper) {
		int daoresult = -1;
		StringBuffer result = new StringBuffer("");
		
		if(client_id.equals("")) {
			result.append("아이디를 입력해주세요");
		}else {
			daoresult =	 mapper.idoverlapcheck(client_id);
			if(daoresult == 0) {
				result.append("사용 가능한 아이디입니다.");
			}
			if(daoresult == 1) {
				result.append("이미 사용중인 아이디입니다.");
			}
		}
		
		return result.toString();
	}
	//  --------------------- 회원가입 ---------------------------- // 
	
	public void Login(Model model, HttpServletResponse response, ClientDao mapper) throws IOException {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:ClientCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		ClientVo vo = (ClientVo) ctx.getBean("Client");
		vo.setClientVo(id, password);
		
		vo = mapper.login(vo);	
		if(null == vo) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('아이디 혹은 비밀번호가 틀립니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		if(vo.getClient_emailcheck().equals("false")) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이메일인증을 한 후 로그인을 시도해주세요.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}else {
		HttpSession session = request.getSession();
		session.setAttribute("session_level", vo.getClient_level()); 
		session.setAttribute("session_id", vo.getClient_id()); 
		session.setAttribute("session_password", vo.getClient_password()); 
		session.setAttribute("session_gender", vo.getClient_gender()); 
		session.setAttribute("session_addr_head", vo.getClient_addr_head()); 
		session.setAttribute("session_addr_end", vo.getClient_addr_end()); 
		session.setAttribute("session_phone", vo.getClient_phone()); 
		session.setAttribute("session_email", vo.getClient_email()); 
		session.setAttribute("session_point", vo.getClient_point()); 
		}
		
	}
	public void LogoutViewDo(Model model, HttpServletResponse response) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		session.removeAttribute("session_level");
		session.removeAttribute("session_id");
		session.removeAttribute("session_password");
		session.removeAttribute("session_gender");
		session.removeAttribute("session_addr_head");
		session.removeAttribute("session_addr_end");
		session.removeAttribute("session_phone");
		session.removeAttribute("session_email");
		session.removeAttribute("session_point");
		session.invalidate();
		
	}
	
	public void SearchMyIdByEmailDo(Model model, ClientDao mapper) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String result =  mapper.SearchMyIdByEmailDo(email);
		
		request.setAttribute("result", result);		
	}
	
	public void SearchMyPwDo(Model model, ClientDao mapper,HttpServletResponse response) throws IOException {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		ClientVo vo = mapper.checkidandemail(id);
		
		if(email.equals(vo.getClient_email())) {
			
			String host = "http://localhost:9090/Controller/";
			String to = mapper.getClientEmail(vo.getClient_id());
			String from = "gygus7345@naver.com";
			String subject = "비밀번호를 변경합니다..";
			String content = "다음 링크에 접속하여 비밀번호를 변경하세요" + "<a href ='"+host+"MyPasswordChangeDo?id=" + id + "'>비밀번호 변경하기.</a>";
			
			Properties p = new Properties();
			p.put("mail.smtp.user",from);
			p.put("mail.smtp.host","smtp.googlemail.com");
			p.put("mail.smtp.port","465");
			p.put("mail.smtp.starttls.enable","true");
			p.put("mail.smtp.auth","true");
			p.put("mail.smtp.debug","true");
			p.put("mail.smtp.socketFactory.port","465");
			p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.socketFactory.fallback","false");
			
			try{
				
				Authenticator auth = new Gmail();
				Session ses = Session.getInstance(p,auth);
				ses.setDebug(true);
				MimeMessage msg = new MimeMessage(ses);
				msg.setSubject(subject);
				Address fromAddr = new InternetAddress(from);
				msg.setFrom(fromAddr);
				Address toAddr = new InternetAddress(to);
				msg.addRecipient(Message.RecipientType.TO,toAddr);
				msg.setContent(content,"text/html;charset=UTF-8");
				Transport.send(msg);
				
			}catch(Exception e){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('오류가 발생했습니다..');");
				script.println("history.back();");
				script.println("</script>");
				script.close();
				return;
			} 
			
		}else {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('아이디 혹은 이메일이 잘못되었습니다.<br/>다시입력해주세요');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
	}
	public void MyPasswordChangeDo(Model model, ClientDao mapper) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:ClientCTX.xml");

		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		System.out.println(id);
		System.out.println(pw);
		ClientVo vo = (ClientVo) ctx.getBean("Client");
		vo.setClientVo(id, pw);
		
		mapper.ChangePassword(vo);
	}
	public void MyClientWithdrawalDo(Model model, ClientDao mapper) throws UnsupportedEncodingException {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("session_id");
		mapper.deleteId(id);

		session.removeAttribute("session_level");
		session.removeAttribute("session_id");
		session.removeAttribute("session_password");
		session.removeAttribute("session_gender");
		session.removeAttribute("session_addr_head");
		session.removeAttribute("session_addr_end");
		session.removeAttribute("session_phone");
		session.removeAttribute("session_email");
		session.removeAttribute("session_point");
		session.invalidate();	
	}
	public void ClientEditViewDo(Model model, ClientDao mapper, HttpServletResponse response) throws IOException {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:ClientCTX.xml");
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("session_id");
		String password = request.getParameter("password");
		ClientVo vo = (ClientVo) ctx.getBean("Client");
		
		if(password.equals((String)session.getAttribute("session_password"))) {
			vo.setClientVo(id, password);
			vo = mapper.ClientInfo(vo);
		}else {
			vo.setClientVo(id, password);
			vo = mapper.ClientInfo(vo);
			if(null == vo) {
				PrintWriter script;
				script = response.getWriter();
				script.println("<script>");
				script.println("alert('비밀번호가 일치하지 않습니다.');");
				script.println("history.back();");
				script.println("</script>");
				script.close();
				return;
			}
		}
		request.setAttribute("vo", vo);
		
	}
	public void EditResultViewDo(Model model, ClientDao mapper, HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ClientVo vo = null;
		
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("session_id");
		String before_password = (String)session.getAttribute("session_password");
		String before_addr_head = (String)session.getAttribute("session_addr_head");
		String before_addr_end = (String)session.getAttribute("session_addr_end");
		
		String password = request.getParameter("password");
		String addr_head = request.getParameter("addr_head");
		String addr_end = request.getParameter("addr_end");
		if(password.equals("") || password.equals(before_password)) {
			if(addr_head.equals(before_addr_head)) {
				if(addr_end.equals(before_addr_end)) {
					PrintWriter script;
					try {
						script = response.getWriter();
						script.println("<script>");
						script.println("alert('변경된 내용이없어 메인페이지로 돌아갑니다.');");
						script.println("location.href = 'views/index';");
						script.println("</script>");
						script.close();
						return;
					} catch (IOException e) {e.printStackTrace();}
				}else {
					System.out.println("비밀번호는 변경하지않지만 주소만 변경");
					vo = new ClientVo();
					vo.setClient_password(before_password);
					vo.setClient_addr_head(addr_head);
					vo.setClient_addr_end(addr_end);
					vo.setClient_id(id);
					
					mapper.ClientUpdate(vo);
					
					session.setAttribute("session_addr_head", addr_head);
					session.setAttribute("session_addr_end", addr_end);
				}
			}else {
				System.out.println("비밀번호는 변경하지않지만 주소만 변경");
				vo = new ClientVo();
				vo.setClient_password(before_password);
				vo.setClient_addr_head(addr_head);
				vo.setClient_addr_end(addr_end);
				vo.setClient_id(id);
				
				mapper.ClientUpdate(vo);
				
				session.setAttribute("session_addr_head", addr_head);
				session.setAttribute("session_addr_end", addr_end);
			}
		}else {
			System.out.println("비밀번호 주소 둘다변경");
			vo = new ClientVo();
			vo.setClient_password(password);
			vo.setClient_addr_head(addr_head);
			vo.setClient_addr_end(addr_end);
			vo.setClient_id(id);
			mapper.ClientUpdate(vo);
			
			session.setAttribute("session_password", password);
			session.setAttribute("session_addr_head", addr_head);
			session.setAttribute("session_addr_end", addr_end);
		}
	}
	public int AttentionCheck(Model model, ClientDao mapper, HttpServletResponse response) throws IOException {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:ClientCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		int check = 0;
		int attentionPoint = 50;
		String content = "출석체크 포인트";
		String userId = request.getParameter("userId");
		System.out.println(userId);
		ArrayList<AttentionPointVO> PointList = mapper.selectPoint(userId);
		System.out.println("PointList: "+PointList);
		Date date = new Date(System.currentTimeMillis());
		AttentionPointVO logVo = ctx.getBean("AttentionPointVO",AttentionPointVO.class);
		logVo.SetAttentionPointVO(userId, attentionPoint, content);
		if(PointList.size()==0) {
			mapper.insertPointLog(logVo);
			mapper.depositAttentionPoint(logVo);
			System.out.println("test");
//			response.getWriter().write("출석포인트 50Point 적립 되었습니다!");
//			System.out.println("사이즈가 0일떄");
		}else { 
			System.out.println("사이즈"+PointList.size());
			for (int i = 0; i < PointList.size(); i++) {	
//				System.out.println("적립날짜 순서 :"+PointList.);
				System.out.println("반복횟수:"+i);
				if(PointList.get(i).getDepositDate().getMonth()+1!=date.getMonth()+1 && PointList.get(i).getDepositDate().getDate()!=date.getDate()
					|| PointList.get(i).getDepositDate().getMonth()+1==date.getMonth()+1 && PointList.get(i).getDepositDate().getDate()!=date.getDate()) {
					check++;
				}
			}
			if(check==PointList.size()) { //포인트내역추가 , 유저 포인트 증가
				mapper.insertPointLog(logVo);
				System.out.println("insertPointLog 종료");
				mapper.depositAttentionPoint(logVo);
				System.out.println("depositAttentionPoint 종료");
				
//				response.getWriter().write("출석포인트 50Point 적립 되었습니다!");
				int user_point = mapper.userPointSelect(userId);
				session.setAttribute("session_point", user_point);//다시 세션에저장해줘야함
				return 1;
			}else {
//				response.getWriter().write("오늘 이미 출석포인트를 적립 받으셨습니다!");
				return -1;
			}
				
		
			
		}
		return 1;
	}
	
	public void MyQnAviewPageDo(Model model, ClientDao mapper, HttpServletResponse response) throws IOException {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:QAboardCTX.xml");
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) { }
		
		int pagesize = 10;
		
		String id = (String)session.getAttribute("session_id");
		
		int totalcount = mapper.qnaTotalCount(id);
		System.out.println("토탈카운트 : "+totalcount);
		
		QAboardList qaBoardList = (QAboardList) ctx.getBean("qaboaddlist");
		qaBoardList.setQAboardList(pagesize, totalcount, currentPage, id);
		
		System.out.println("id: "+id);
//		1페이지 분량의 글 목록을 얻어와서 mvcboardList의 ArrayList에 넣어준다.
		qaBoardList.setList(mapper.QAselectList(qaBoardList));

		request.setAttribute("qaList", qaBoardList);		
	}
	public void reviewSelect(Model model, ClientDao mapper, ReViewDAO reViewmapper, HttpServletResponse response) throws UnsupportedEncodingException {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) { }
		
		int pagesize = 10;
		String id = (String)session.getAttribute("session_id");
		int totalCount = mapper.reviewListCount(id);
		ReViewList reViewList = ctx.getBean("ReViewList",ReViewList.class);
		reViewList.SetReViewList(pagesize, totalCount, currentPage, id);
		reViewList.setList(mapper.selectreviewList(reViewList));
		
		model.addAttribute("reViewList", reViewList);
		System.out.println("list:" +reViewList.getList());
		
	}
	public void SelectMyPointDeposit(Model model, ClientDao clientmapper, HttpServletResponse response) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		String userId=""+session.getAttribute("session_id");
		ArrayList<AttentionPointVO> list = clientmapper.SelectMyPointDeposit(userId);
		model.addAttribute("list", list);
	}
	public void MyBaguniViewDo(Model model, ShopDAO mapper, HttpServletResponse response) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		ArrayList<ShopVO> baguni = new ArrayList<ShopVO>();
		String goods = "";
		int money = 0;
		//총액
		
		//상품테이블에서 전부 카운트를 한 값이 들어가겠죠>
		for(int i = 0; i < 50; i++) {
			String idx = (String) session.getAttribute("sh_idx_" + i);
			if(null != idx) {
				System.out.println("널이아니라능");
				int go = Integer.parseInt(idx);
				ShopVO vo = mapper.selectProduct(go);
				goods += vo.getSh_name() + ",";
				money += vo.getSh_price();
				
				baguni.add(vo);
			}
		}
		
		
		System.out.println("상품목록 : " + goods);
		System.out.println("바구니 : " + baguni);
		
		request.setAttribute("money", money);
		request.setAttribute("list", baguni);
		request.setAttribute("goods", goods);
	}
	
	public void UserIpCheck(Model model, ClientDao mapper) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:ClientCTX.xml");
		
		String ip = null;
	    ip = request.getRemoteAddr();
	    ArrayList<IpVo> vo = new ArrayList<IpVo>();
	    
	    vo = mapper.checkip(ip);
	    
	    Date today = new Date();
	    
	    SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
	    int wjflrktpdyt = 0;
	    
	    try {
	    	for(int i = 0; i < vo.size(); i++) {
			    if(date.format(today).equals(date.format(vo.get(i).getIndate()))){
			    	System.out.println("저리가세욧");
			    	wjflrktpdyt++;
			    }
	    	}
	    	
	    	if(wjflrktpdyt == 1) {
	    		System.out.println("저리가시라구욧!");
	    	}else {
	    		mapper.insertUserIp(ip);
	    	}
	    	
		} catch (Exception e) {
			System.out.println("뭐야없잖아!");
			mapper.insertUserIp(ip);
		}

	}
	
}












