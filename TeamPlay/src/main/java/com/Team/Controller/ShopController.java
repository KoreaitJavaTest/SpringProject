package com.Team.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Team.Dao.ShopDAO;
import com.Team.Service.ShopService;

@Controller
@RequestMapping("/")
public class ShopController {
	
	@Autowired
	public SqlSession sqlSession ;
	ShopService shopservice = ShopService.getInstance();
//	전체 상품 페이지
	@RequestMapping("/shop")
	public String shop(HttpServletRequest request,Model model) {
		System.out.println("HomeController => shop()");
		return "redirect:ShopAllProduct";
	}
	
	@RequestMapping("/ShopAllProduct")
	public String ShopAllProduct(HttpServletRequest request, Model model) {
		System.out.println("HomeController => ShopAllProduct()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.selectAllProduct(model, mapper);
		return "Shop/ShopAllProduct";
	}
	
	@RequestMapping("/ShopInsertProduct")
	public String ShopInsertProduct(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		System.out.println("HomeController => ShopInsertProduct()");
//		model.addAttribute("request", request);
//		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
//		shopservice.insertProduct(model, response, mapper);
		return "Shop/ShopInsertProduct";
	}
	
	@RequestMapping("/ShopInsertProductOK")
	public String ShopInsertProductOK(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		System.out.println("HomeController => ShopInsertProductOK()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.insertProduct(model, response, mapper);
		return "Shop/ShopAllProduct";
	}
//	

}

