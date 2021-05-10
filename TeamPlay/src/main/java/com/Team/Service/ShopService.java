package com.Team.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.support.DaoSupport;
import org.springframework.ui.Model;

import com.Team.Dao.ShopDAO;
import com.Team.List.ShopList;
import com.Team.Vo.ShopVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ShopService {
	
	String imgNames = "http://localhost:9090/korea/upload/";// img 파일 이름
	
	private static ShopService instance = new ShopService();
	private ShopService() {}
	public static ShopService getInstance() {return instance;}
	DecimalFormat priceFm = new DecimalFormat("#,##0");
	
//	전체 상품 보기
	public void selectAllProduct(Model model, ShopDAO mapper) {
		DecimalFormat priceFm = new DecimalFormat("#,##0");
		System.out.println("service => selectAllProduct() 메서드 들어옴");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:shopCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) {
		}
		int pageSize = 8;
		int totalCount = mapper.selectCount(mapper);

		ShopList shopList = ctx.getBean("ShopList", ShopList.class);
		
		String category = "";
		try {
			category = request.getParameter("category"); // 이전 페이지에서 카테고리를 가져온다. ex) 신발, 상의, 하의
		} catch (Exception e) {
		}			
		
		shopList.shopList_category(pageSize, totalCount, currentPage, category);
		shopList.setList(mapper.selectAllProduct(shopList));
		
//		가격에 , 찍기
		for (ShopVO vo : shopList.getList()) {
			vo.setSh_priceFM(priceFm.format(vo.getSh_price()));
			vo.setSh_salePriceFM(priceFm.format(vo.getSh_salePrice()));
		}
		System.out.println(shopList);
		model.addAttribute("shopList", shopList);
	}
	
	public void insertProduct(Model model, HttpServletResponse response, ShopDAO mapper) throws IOException {
		System.out.println("service => insertProduct()");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:shopCTX.xml");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
//		ServletContext application = request.getServletContext();
		ShopVO vo = ctx.getBean("ShopVO", ShopVO.class);
		String userId = (String) session.getAttribute("session_id");
		
		try {
			MultipartRequest mr = new MultipartRequest(
					request,
//				application.getRealPath("./upload/"),
					"C:/upload/",
					5 * 1024 * 1024,
					"UTF-8",
					new DefaultFileRenamePolicy()
				);
			System.out.println("======================여기===========================");
//			업로드할 파일이름 여러개를 받는다.
				Enumeration<String> fileNames = mr.getFileNames();
//			hasMoreElements() : Enumeration 인터페이스 객체에 다음에 읽어들일 데이터가 있으면 true, 없으면 false를 리턴시킨다.

				int imgCount = 0;
				
//			이미지파일 이름 넣는 반복문
				while (fileNames.hasMoreElements()) {
					imgCount++;
					String parameter = (String) fileNames.nextElement();
//				String fileName = mr.getOriginalFileName(parameter);
					String fileRealName = mr.getFilesystemName(parameter);
					
					if (fileRealName == null) {
						continue;
					}
					imgNames += fileRealName;	// img 파일 이름
					
					if(imgCount == 1) {
						vo.setSh_img1(imgNames);
					} else if(imgCount == 2) {
						vo.setSh_img2(imgNames);
					}
				}
				
				String title = mr.getParameter("title");
				String name = mr.getParameter("name");
				String category = mr.getParameter("category");
				String categoryDetail = mr.getParameter("categoryDetail");
				String content = mr.getParameter("content");
//				int size = Integer.parseInt(mr.getParameter("size"));
				int price = Integer.parseInt(mr.getParameter("price"));
				double salePercent = Double.parseDouble(mr.getParameter("salePercent"));
				double salePrice = price - (price*(salePercent/100));
				vo.setSh_title(title);
				vo.setSh_name(name);
				vo.setSh_category(category);
				vo.setSh_categoryDetail(categoryDetail);
				vo.setSh_content(content);
//				vo.setSh_size(size);
				vo.setSh_price(price);
				vo.setSh_salePercent(salePercent);
				vo.setSh_salePrice(salePrice);
				vo.setSh_seller(userId);
				
		} catch (Exception e) {
			System.out.println("파일업로드 에러");
			e.printStackTrace();
		}
			
			


			
			System.out.println(vo);
			if(vo.getSh_img1() != null && vo.getSh_img2() != null) {
				mapper.shopInsertProduct(vo);
				System.out.println("shopInsertProduct");
			} else if(vo.getSh_img1() != null && vo.getSh_img2() == null) {
				mapper.shopInsertProduct1(vo);
				System.out.println("shopInsertProduct1");
			} else if(vo.getSh_img1() == null && vo.getSh_img2() != null) {
				mapper.shopInsertProduct2(vo);
				System.out.println("shopInsertProduct2");
			} else {
				System.out.println("상품 등록 실패");
			}
	}
	
}
