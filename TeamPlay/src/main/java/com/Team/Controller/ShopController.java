package com.Team.Controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Team.Dao.ShopDAO;
import com.Team.Service.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopController {
	
	@Autowired
	public SqlSession sqlSession ;
	
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
		ShopService.getInstance().selectAllProduct(model, mapper);
		
		return "Shop/ShopAllProduct";
	}
}
