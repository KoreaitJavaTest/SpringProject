package com.Team.Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import com.Team.Dao.ClientDao;
import com.Team.Vo.ClientVo;

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
		ClientVo vo = new ClientVo(id,password);
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
		
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		System.out.println(id);
		System.out.println(pw);
		ClientVo vo = new ClientVo(id, pw);
		
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
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("session_id");
		String password = request.getParameter("password");
		ClientVo vo = null;
		
		if(password.equals((String)session.getAttribute("session_password"))) {
			vo = new ClientVo(id, password);
			vo = mapper.ClientInfo(mapper, vo);
		}else {
			vo = new ClientVo(id, password);
			vo = mapper.ClientInfo(mapper, vo);
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
	
}





