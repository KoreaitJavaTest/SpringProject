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
	public String ShopInsertProduct(HttpServletRequest request, Model model) throws IOException {
		System.out.println("HomeController => ShopInsertProduct()");
//		model.addAttribute("request", request);
//		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
//		shopservice.insertProduct(model, response, mapper);
		return "Shop/ShopInsertProduct";
	}
	
	@RequestMapping("/ShopInsertProductOK")
	public String ShopInsertProductOK(HttpServletRequest request, Model model) throws IOException {
		System.out.println("HomeController => ShopInsertProductOK()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.insertProduct(model, mapper);
		return "Shop/ShopAllProduct";
	}
	
	@RequestMapping("/ShopCategoryDetail")
	public String selectCategoryDetail(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		System.out.println("HomeController => ShopCategoryDetail()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.selectCategoryDetail(model, response, mapper);
		return "Shop/ShopCategoryDetail";
	}
	
	@RequestMapping("/ShopSelectProduct")
	public String ShopSelectProduct(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		System.out.println("HomeController => ShopSelectProduct()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.ShopSelectProduct(model, response, mapper);
		return "Shop/ShopSelectProduct";
	}
	
	@RequestMapping("/likeUpdate")
	public void likeUpdate(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		System.out.println("HomeController => likeUpdate()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.likeUpdate(model, response, mapper);
	}
	
	@RequestMapping("/likeCount")
	public void likeCount(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		System.out.println("HomeController => likeCount()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.likeCount(model, response, mapper);
	}
	
//	상품 수정	
	@RequestMapping("/ShopUpdateProduct")
	public String ShopUpdateProduct(HttpServletRequest request, Model model) throws IOException {
		System.out.println("HomeController => ShopInsertProduct()");
		return "Shop/ShopUpdateProduct";
	}
	
	@RequestMapping("/ShopUpdateProductOK")
	public String ShopUpdateProduct(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		System.out.println("HomeController => ShopUpdateProduct()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.ShopUpdateProduct(model, response, mapper);
		return "Shop/ShopAllProduct";
	}
	
//	상품 삭제
	@RequestMapping("/ShopDeleteProduct")
	public String ShopDeleteProduct(HttpServletRequest request, Model model) throws IOException {
		System.out.println("HomeController => ShopInsertProduct()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.ShopSelectByIdx(model, mapper);
		return "Shop/ShopDeleteProduct";
	}
	
	@RequestMapping("/ShopDeleteProductOK")
	public String ShopDeleteProduct(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		System.out.println("HomeController => ShopDeleteProduct()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.ShopDeleteProduct(model, response, mapper);
		return "Shop/ShopAllProduct";
	}
//	

}

