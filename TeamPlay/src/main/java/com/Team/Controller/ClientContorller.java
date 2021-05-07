package com.Team.Controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import com.Team.Dao.ClientDao;
import com.Team.Dao.ReViewDAO;
import com.Team.Service.ClientService;
import com.Team.Service.ReViewService;


@Controller
public class ClientContorller {

	@Autowired
	public SqlSession sqlSession;

	@RequestMapping(value = "/")
	public String homehome(Model model) {
		return "views/index";

	}
	
	@RequestMapping("/index")
	public String home(Model model) {
		return "views/index";

	}
	
	// 회원가입 페이지 이동
	@RequestMapping("/JoinViewDo")
	public String JoinViewDo(HttpServletRequest request, Model model) {
		System.out.println("JoinViewDo()");
		return "ClientJoin/JoinView";
	}

	// 회원가입 결과 페이지
	@RequestMapping("/JoinResultViewDo")
	public String JoinResultViewDo(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		System.out.println("JoinViewDo()");
		ClientDao mapper = sqlSession.getMapper(ClientDao.class);
		model.addAttribute("request", request);
		ClientService.getInstance().join(model,response, mapper);

		return "ClientJoin/JoinResultView";
	}

	@RequestMapping("/JoinEmailResultViewDo")
	public String JoinEmailResultViewDo(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		System.out.println("JoinEmailResultViewDo()");
		ClientDao mapper = sqlSession.getMapper(ClientDao.class);
		model.addAttribute("request", request);
		ClientService.getInstance().emailCheckAction(model,response ,mapper);

		return "ClientJoin/JoinEmailResultView";
	}

	@ResponseBody
	@RequestMapping("/IdoverlapcheckLogicDo")
	public void IdoverlapcheckLogicDo(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		System.out.println("IdoverlapcheckLogicDo()");
		ClientDao mapper = sqlSession.getMapper(ClientDao.class);
		model.addAttribute("request", request);
		String client_id = request.getParameter("client_id");
		System.out.println(client_id);

		response.getWriter().write(getJSON(client_id, mapper));

	}

	private String getJSON(String client_id, ClientDao mapper) {
		int daoresult = -1;
		StringBuffer result = new StringBuffer("");
		System.out.println("제이슨함수");

		if (client_id.equals("")) {
			result.append("아이디를 입력해주세요");
		} else {
			daoresult = mapper.idoverlapcheck(client_id);
			if (daoresult == 0) {
				result.append("사용 가능한 아이디입니다.");
			}
			if (daoresult == 1) {
				result.append("이미 사용중인 아이디입니다.");
			}
		}

		return result.toString();
	}
	
	@RequestMapping("/LoginViewDo")
	public String LoginViewDo(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		System.out.println("LoginViewDo()");
		return "Login/LoginView";
	}
	
	@RequestMapping("/LoginDo")
	public String LoginDo(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		ClientDao mapper = sqlSession.getMapper(ClientDao.class);
		model.addAttribute("request", request);
		ClientService.getInstance().Login(model,response,mapper);

		return "Login/LoginResultView";
	}

	@RequestMapping("/LogoutViewDo")
	public String LogoutViewDo(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		System.out.println("LogoutViewDo()");
		model.addAttribute("request", request);
		ClientService.getInstance().LogoutViewDo(model,response);

		return "Login/LogoutView";
	}
	
	@RequestMapping("/SearchMyIdPwDo")
	public String SearchMyIdPwDo(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		System.out.println("SearchMyIdPwDo()");

		return "MyPage/SearchMyIdPw";
	}
	
	@RequestMapping("/SearchIdByEmailDo")
	public String SearchIdByEmailDo(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		System.out.println("SearchIdByEmailDo()");

		return "MyPage/SearchIdByEmail";
	}
	
	
	@RequestMapping("/SearchPwViewDo")
	public String SearchPwViewDo(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		System.out.println("SearchPwViewDo()");

		return "MyPage/SearchPwView";
	}
	
	@RequestMapping("/SearchMyIdByEmailDo")
	public String SearchMyIdByEmailDo(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		System.out.println("SearchMyIdByEmailDo()");
		ClientDao mapper = sqlSession.getMapper(ClientDao.class);
		model.addAttribute("request", request);
		ClientService.getInstance().SearchMyIdByEmailDo(model,mapper);

		return "MyPage/SearchMyIdByEmailResult";
	}
	
	@RequestMapping("/SearchMyPwDo")
	public String SearchMyPwDo(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		System.out.println("SearchMyPwDo()");
		ClientDao mapper = sqlSession.getMapper(ClientDao.class);
		model.addAttribute("request", request);
		ClientService.getInstance().SearchMyPwDo(model,mapper,response);

		return "MyPage/SearchMyPwResult";
	}
	
	@RequestMapping("/MyPasswordChangeDo")
	public String MyPasswordChangeDo(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		System.out.println("MyPasswordChangeDo()");
		String id = request.getParameter("id");
		request.setAttribute("id",id);
		return "MyPage/MyPasswordChangeView";
	}
	
	@RequestMapping("/PasswordChangeDo")
	public String PasswordChangeDo(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		System.out.println("PasswordChangeDo()");
		ClientDao mapper = sqlSession.getMapper(ClientDao.class);
		model.addAttribute("request", request);
		ClientService.getInstance().MyPasswordChangeDo(model,mapper);
		return "MyPage/PasswordChangeResult";
	}
	
	@RequestMapping("/MyPageViewDo")
	public String MyPageViewDo(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		System.out.println("MyPageViewDo()");
		
		return "MyPage/MyPageMainView";
	}
	
	@RequestMapping("/MyClientWithdrawalDo")
	public String MyClientWithdrawalDo(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		System.out.println("MyClientWithdrawalDo()");
		ClientDao mapper = sqlSession.getMapper(ClientDao.class);
		model.addAttribute("request", request);
		ClientService.getInstance().MyClientWithdrawalDo(model,mapper);
		return "MyPage/MyClientWithdrawal";
	}
	
	@RequestMapping("/MyEditViewPasswordCheckDo")
	public String MyEditViewPasswordCheckDo(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		System.out.println("MyEditViewPasswordCheckDo()");
		return "MyPage/ClientMyEditPasswordView";
	}
	
	@RequestMapping("/ClientEditViewDo")
	public String ClientEditViewDo(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		System.out.println("ClientEditViewDo()");
		ClientDao mapper = sqlSession.getMapper(ClientDao.class);
		model.addAttribute("request", request);
		ClientService.getInstance().ClientEditViewDo(model,mapper,response);
		return "MyPage/ClientEditView";
	}

	@RequestMapping("/EditResultViewDo")
	public String EditResultViewDo(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		System.out.println("EditResultViewDo()");
		ClientDao mapper = sqlSession.getMapper(ClientDao.class);
		model.addAttribute("request", request);
		ClientService.getInstance().EditResultViewDo(model,mapper,response);
		return "MyPage/EditResultViewDo";
	}

}
