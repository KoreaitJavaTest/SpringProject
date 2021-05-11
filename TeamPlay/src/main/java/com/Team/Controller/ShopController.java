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
import com.Team.Service.ClientService;
import com.Team.Service.ShopService;

@Controller
@RequestMapping("/")
public class ShopController {
	
	@Autowired
	public SqlSession sqlSession ;
	ShopService shopservice = ShopService.getInstance();
	
//	전체 상품 페이지 이동
	@RequestMapping("/shop")
	public String shop(HttpServletRequest request,Model model) {
		System.out.println("ShopController => shop()");
		return "redirect:ShopAllProduct";
	}
	
//	전체 상품 페이지
	@RequestMapping("/ShopAllProduct")
	public String ShopAllProduct(HttpServletRequest request, Model model) {
		System.out.println("ShopController => ShopAllProduct()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.selectAllProduct(model, mapper);
		return "Shop/ShopAllProduct";
	}
	
//	상품 등록 페이지 이동
	@RequestMapping("/ShopInsertProduct")
	public String ShopInsertProduct(HttpServletRequest request, Model model) throws IOException {
		System.out.println("ShopController => ShopInsertProduct()");
		return "Shop/ShopInsertProduct";
	}
	
//	상품 등록 
	@RequestMapping("/ShopInsertProductOK")
	public String ShopInsertProductOK(HttpServletRequest request, Model model) throws IOException {
		System.out.println("ShopController => ShopInsertProductOK()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.insertProduct(model, mapper);
		return "Shop/ShopAllProduct";
	}
	
//	카테고리 별로 페이지 이동
	@RequestMapping("/ShopCategoryDetail")
	public String selectCategoryDetail(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		System.out.println("ShopController => ShopCategoryDetail()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.selectCategoryDetail(model, response, mapper);
		return "Shop/ShopCategoryDetail";
	}
	
//	상품 상세보기
	@RequestMapping("/ShopSelectProduct")
	public String ShopSelectProduct(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		System.out.println("ShopController => ShopSelectProduct()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.ShopSelectProduct(model, response, mapper);
		return "Shop/ShopSelectProduct";
	}
	
//	좋아요 수 업데이트
	@RequestMapping("/likeUpdate")
	public void likeUpdate(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		System.out.println("ShopController => likeUpdate()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.likeUpdate(model, response, mapper);
	}
	
//	좋아요 수 카운트
	@RequestMapping("/likeCount")
	public void likeCount(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		System.out.println("ShopController => likeCount()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.likeCount(model, response, mapper);
	}
	
//	상품 수정 페이지 이동
	@RequestMapping("/ShopUpdateProduct")
	public String ShopUpdateProduct(HttpServletRequest request, Model model) throws IOException {
		System.out.println("ShopController => ShopUpdateProduct()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.ShopSelectByIdx(model, mapper);
		return "Shop/ShopUpdateProduct";
	}
	
//	상품 수정 완료
	@RequestMapping("/ShopUpdateProductOK")
	public String ShopUpdateProduct(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		System.out.println("ShopController => ShopUpdateProduct()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.ShopUpdateProduct(model, response, mapper);
		return "Shop/ShopAllProduct";
	}
	
//	상품 삭제 페이지로 이동
	@RequestMapping("/ShopDeleteProduct")
	public String ShopDeleteProduct(HttpServletRequest request, Model model) throws IOException {
		System.out.println("ShopController => ShopInsertProduct()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.ShopSelectByIdx(model, mapper);
		return "Shop/ShopDeleteProduct";
	}
	
//	상품 삭제 완료
	@RequestMapping("/ShopDeleteProductOK")
	public String ShopDeleteProduct(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		System.out.println("ShopController => ShopDeleteProduct()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.ShopDeleteProduct(model, response, mapper);
		return "Shop/ShopAllProduct";
	}
	
//	장바구니 추가
	@RequestMapping("/addcart")
	public void addcart(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		System.out.println("ShopController => addcart()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.addcart(model, response, mapper);
	}
	
//	마이페이지 상품 관리
	@RequestMapping("/MyProductViewPage")
	public String MyProductViewPage(HttpServletRequest request, Model model) throws IOException {
		System.out.println("ShopController => MyProductViewPage()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.MyProductViewPage(model, mapper);
		return "MyPage/MyProductViewPage";
	}
	
//	관리자페이지 상품관리
	@RequestMapping("/AdminProductManagement")
	public String AdminProductManagement(HttpServletRequest request, Model model) throws IOException {
		System.out.println("ShopController => AdminProductManagement()");
		model.addAttribute("request", request);
		ShopDAO mapper = sqlSession.getMapper(ShopDAO.class);
		shopservice.AdminProductManagement(model, mapper);
		return "Admin/AdminProductManagement";
	}
	


}

