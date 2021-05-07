package com.Team.Service;

import java.text.DecimalFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.Team.Dao.ShopDAO;
import com.Team.List.ShopList;
import com.Team.Vo.ShopVO;

public class ShopService {
	
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
		int pageSize = 6;
		int totalCount = mapper.selectCount(mapper);

		ShopList shoplist = ctx.getBean("ShopList", ShopList.class);
		
		String category = "";
		try {
			category = request.getParameter("category"); // 이전 페이지에서 카테고리를 가져온다. ex) 신발, 상의, 하의
		} catch (Exception e) {
		}			
		
		shoplist.shopList_category(pageSize, totalCount, currentPage, category);
		shoplist.setList(mapper.selectAllProduct(shoplist));
		
//		가격에 , 찍기
		for (ShopVO vo : shoplist.getList()) {
			vo.setSh_priceFM(priceFm.format(vo.getSh_price()));
			vo.setSh_salePriceFM(priceFm.format(vo.getSh_salePrice()));
		}
		System.out.println(shoplist);
		model.addAttribute("shoplist", shoplist);
	}
}
