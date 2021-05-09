package com.Team.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.Team.Dao.QAboardDao;
import com.Team.List.QAboardList;
import com.Team.Vo.AnswerVO;
import com.Team.Vo.QAboardVo;

public class QAservice {
	private static QAservice instance = new QAservice();
	private QAservice() {}
	public static QAservice getInstance() {	return instance;}
	
	
	public void QAselectList(Model model, QAboardDao mapper) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:QAboardCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		System.out.println("QAboardService 클래스의 QAselectList() 메소드");
		
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {	}
		
		int pageSize = 10;
		int totalCount = mapper.QAselectCount(mapper);
		System.out.println(totalCount);
		
		QAboardList qaboardList = new QAboardList(pageSize, totalCount, currentPage);
		
		ArrayList<AnswerVO> Alist = new ArrayList<AnswerVO>();
		Alist = mapper.selectAlist();
//		System.out.println(Alist);
		request.setAttribute("Alist", Alist);
		
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", qaboardList.getStartNo());
		hmap.put("endNo", qaboardList.getEndNo());
		qaboardList.setList(mapper.QAselectList(hmap));

		request.setAttribute("qaboardList", qaboardList);
		
	}
	public void insert(Model model, QAboardDao mapper) {
		System.out.println("QAboardService 클래스의 insert() 메소드");
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:QAboardCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String q_userid = request.getParameter("q_userid");
		String q_title = request.getParameter("q_title");
		String q_content = request.getParameter("q_content");
		
		QAboardVo vo = (QAboardVo) ctx.getBean("qaboardvo");
		vo.setQAboardVo(q_userid, q_title, q_content);
		
		mapper.insert(vo);
	}
	public void contentViewDo(Model model, QAboardDao mapper) {
		System.out.println("QAboardService 클래스의 QAselectByIdx() 메소드");
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:QAboardCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
//		request 객체로 넘어오는 얻어올 글번호와 작업 후 돌아갈 페이지 번호를 받는다.
		int q_idx = Integer.parseInt(request.getParameter("idx"));
		System.out.println(q_idx);
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		QAboardVo vo = (QAboardVo) ctx.getBean("qaboardvo");
		vo = mapper.QAselectByIdx(q_idx);
		
		AnswerVO avo = (AnswerVO) ctx.getBean("answervo");
		avo = mapper.AnswerByIdx(q_idx);
		
//		브라우저에 표시할 글이 저장된 객체, 작업 후 돌아갈 페이지 번호, 줄바꿈에 사용할 이스케이프 시퀀스 request 영역에 저장한다.
		request.setAttribute("vo", vo);
		request.setAttribute("avo", avo);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("enter", "\r\n");
	}
	public void updateDo(Model model, QAboardDao mapper) {
		
		System.out.println("QAboardService 클래스의 update() 메소드");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:QAboardCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int q_idx = Integer.parseInt(request.getParameter("q_idx"));
		String q_title = request.getParameter("q_title");
		String q_content = request.getParameter("q_content");
		
		QAboardVo vo = (QAboardVo) ctx.getBean("qaboardvo");

		vo.setQ_idx(q_idx);
		vo.setQ_title(q_title);
		vo.setQ_content(q_content);
		
		mapper.update(vo);
	}
	
	public void deleteDo(Model model, QAboardDao mapper) {
		System.out.println("QAboardService 클래스의 delete() 메소드");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		mapper.delete(idx);
	}
	public void QAselectByIdx(Model model, QAboardDao mapper) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		int q_idx = Integer.parseInt(request.getParameter("idx"));
		System.out.println(q_idx);
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		
		QAboardVo vo = mapper.QAselectByIdx(q_idx);
		System.out.println(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("enter", "\r\n");
	}
	public void replyInsertDo(Model model, QAboardDao mapper) {
		System.out.println("QAboardService 클래스의 replyInsert() 메소드");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:QAboardCTX.xml");

		int q_idx = Integer.parseInt(request.getParameter("q_idx"));
		System.out.println(q_idx);
		String a_userid = request.getParameter("a_userid");
		System.out.println(a_userid);
		String a_title = request.getParameter("a_title");
		System.out.println(a_title);
		String a_content = request.getParameter("a_content");
		System.out.println(a_content);
		
		AnswerVO vo = (AnswerVO) ctx.getBean("answervo");
		vo.setAnswerVO(a_userid, a_title, a_content, q_idx);
		System.out.println(vo);
		
		mapper.ansReply(vo);
		AnswerVO voref = mapper.selectAnswarbyref(q_idx);
		mapper.editqaflag(voref);
	}
	public void replyViewDo(Model model, QAboardDao mapper) {
		
		System.out.println("QAboardService 클래스의 QAselectByIdx() 메소드");
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:QAboardCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int q_idx = Integer.parseInt(request.getParameter("idx"));
		System.out.println(q_idx);
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
				
		QAboardVo vo = mapper.QAselectByIdx(q_idx);
		System.out.println(vo);
		AnswerVO avo = mapper.AnswerByIdx(q_idx);
		System.out.println(avo);
		
//		브라우저에 표시할 글이 저장된 객체, 작업 후 돌아갈 페이지 번호, 줄바꿈에 사용할 이스케이프 시퀀스 request 영역에 저장한다.
		request.setAttribute("vo", vo);
		request.setAttribute("avo", avo);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("enter", "\r\n");
	}
	public void replyUpdateDo(Model model, QAboardDao mapper) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:QAboardCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int q_idx = Integer.parseInt(request.getParameter("q_idx"));
		System.out.println(q_idx);
		String a_userid = request.getParameter("a_userid");
		System.out.println(a_userid);
		String a_title = request.getParameter("a_title");
		System.out.println(a_title);
		String a_content = request.getParameter("a_content");
		System.out.println(a_content);
		
		AnswerVO vo = (AnswerVO) ctx.getBean("answervo");
		vo.setAnswerVO(a_userid, a_title, a_content, q_idx);
		System.out.println(vo);
		mapper.Aupdate(vo);
	}
	public void AnsDeleteDo(Model model, QAboardDao mapper) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:QAboardCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int a_idx = Integer.parseInt(request.getParameter("a_idx"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		request.setAttribute("currentPage", currentPage);
		
		AnswerVO vo = mapper.selectAnswar(a_idx);
		vo.setA_flag(0);
		
		mapper.ansdelete(a_idx);
	}
	
	
	
}
