package com.Team.Vo;

import java.util.Date;

public class ShopVO {
	public ShopVO() {
	}
	
	private int sh_idx;					// 상품 번호
	private String sh_category;			// 상품 종류 	ex) 신발, 옷, 가방, ...
	private String sh_categoryDetail;	// 상품 브랜드 	ex) 나이키, 아디다스, 뉴발란스, ...
	private String sh_name;				// 상품명
	private String sh_content;			// 상품 내용
	private int sh_ea;					// 재고
	private int sh_size;				// 사이즈
	private double sh_price;			// 가격
	private double sh_salePrice;		// 할인된 가격
	private double sh_salePercent;		// 할인 퍼센트
	private int sh_like;				// 찜하기
	private int sh_hit;					// 조회수
	private int sh_report;				// 신고받은 횟수
	
	private String sh_img1;				// 상품 이미지1
	private String sh_img2;				// 상품 이미지2
	private String sh_img3;				// 상품 이미지3
	private Date sh_writeDate;			// 상품 게시일
	
	private String sh_seller;			// 판매자 명
	private String sh_title;			// 판매글 제목
	
	private String sh_priceFM;			// 가격 포멧
	private String sh_salePriceFM;		// 할인 가격 포멧
	
	
	public String getSh_salePriceFM() {
		return sh_salePriceFM;
	}
	public void setSh_salePriceFM(String sh_salePriceFM) {
		this.sh_salePriceFM = sh_salePriceFM;
	}
	public double getSh_salePrice() {
		return sh_salePrice;
	}
	public void setSh_salePrice(double sh_salePrice) {
		this.sh_salePrice = sh_salePrice;
	}
	public double getSh_salePercent() {
		return sh_salePercent;
	}
	public void setSh_salePercent(double sh_salePercent) {
		this.sh_salePercent = sh_salePercent;
	}
	public String getSh_priceFM() {
		return sh_priceFM;
	}
	public void setSh_priceFM(String sh_priceFM) {
		this.sh_priceFM = sh_priceFM;
	}
	public int getSh_idx() {
		return sh_idx;
	}
	public void setSh_idx(int sh_idx) {
		this.sh_idx = sh_idx;
	}
	public String getSh_category() {
		return sh_category;
	}
	public void setSh_category(String sh_category) {
		this.sh_category = sh_category;
	}
	public String getSh_categoryDetail() {
		return sh_categoryDetail;
	}
	public void setSh_categoryDetail(String sh_categoryDetail) {
		this.sh_categoryDetail = sh_categoryDetail;
	}
	public String getSh_name() {
		return sh_name;
	}
	public void setSh_name(String sh_name) {
		this.sh_name = sh_name;
	}
	public String getSh_content() {
		return sh_content;
	}
	public void setSh_content(String sh_content) {
		this.sh_content = sh_content;
	}
	public int getSh_ea() {
		return sh_ea;
	}
	public void setSh_ea(int sh_ea) {
		this.sh_ea = sh_ea;
	}
	public int getSh_size() {
		return sh_size;
	}
	public void setSh_size(int sh_size) {
		this.sh_size = sh_size;
	}
	public double getSh_price() {
		return sh_price;
	}
	public void setSh_price(double sh_price) {
		this.sh_price = sh_price;
	}
	public int getSh_like() {
		return sh_like;
	}
	public void setSh_like(int sh_like) {
		this.sh_like = sh_like;
	}
	public int getSh_hit() {
		return sh_hit;
	}
	public void setSh_hit(int sh_hit) {
		this.sh_hit = sh_hit;
	}
	public int getSh_report() {
		return sh_report;
	}
	public void setSh_report(int sh_report) {
		this.sh_report = sh_report;
	}
	public Date getSh_writeDate() {
		return sh_writeDate;
	}
	public String getSh_img1() {
		return sh_img1;
	}
	public void setSh_img1(String sh_img1) {
		this.sh_img1 = sh_img1;
	}
	public String getSh_img2() {
		return sh_img2;
	}
	public void setSh_img2(String sh_img2) {
		this.sh_img2 = sh_img2;
	}
	public String getSh_img3() {
		return sh_img3;
	}
	public void setSh_img3(String sh_img3) {
		this.sh_img3 = sh_img3;
	}
	public void setSh_writeDate(Date sh_writeDate) {
		this.sh_writeDate = sh_writeDate;
	}
	public String getSh_seller() {
		return sh_seller;
	}
	public void setSh_seller(String userId) {
		this.sh_seller = userId;
	}
	public String getSh_title() {
		return sh_title;
	}
	public void setSh_title(String sh_title) {
		this.sh_title = sh_title;
	}
	@Override
	public String toString() {
		return "sh_idx=" + sh_idx + ", sh_category=" + sh_category + ", sh_categoryDetail=" + sh_categoryDetail + ", sh_name=" + sh_name + ", sh_content=" + sh_content + ", sh_ea=" + sh_ea + ", sh_size=" + sh_size
				+ ", sh_price=" + sh_price + ", sh_salePrice=" + sh_salePrice + ", sh_salePercent=" + sh_salePercent
				+ ", sh_like=" + sh_like + ", sh_hit=" + sh_hit + ", sh_report=" + sh_report + ", sh_img1=" + sh_img1
				+ ", sh_img2=" + sh_img2 + ", sh_img3=" + sh_img3 + ", sh_writeDate=" + sh_writeDate + ", sh_seller="
				+ sh_seller + ", sh_title=" + sh_title + ", sh_priceFM=" + sh_priceFM + ", sh_salePriceFM="
				+ sh_salePriceFM;
	}
	
}
