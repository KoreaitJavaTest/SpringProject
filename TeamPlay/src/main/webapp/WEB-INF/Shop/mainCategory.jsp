<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="../Layout/header.jsp"></jsp:include>

<div style="float: left;">
	<header role="banner">
		<nav class="nav" role="navigation">
			<ul class="nav__list">
				<li><input id="group-1" type="checkbox" hidden /> 
				<label for="group-1"><span class="fa fa-angle-right"></span> 카테고리 </label>
					<ul class="group-list" >
					<!-- <li><a href="#">1st level item</a></li> -->
					
						<li><input id="sub-group-1" type="checkbox" hidden/> <!-- 2rd 카테고리 -->
							<label for="sub-group-1"><span class="fa fa-angle-right"></span>신발</label>
							<ul class="sub-group-list">
								<li><a href="AllProducts.nhn?category=신발">전체 신발</a></li>
								<li><a href="categoryDetail.nhn?categoryDetail=nike&category=신발">나이키</a></li>
								<li><a href="categoryDetail.nhn?categoryDetail=adidas&category=신발">아디다스</a></li>
								<li><a href="categoryDetail.nhn?categoryDetail=newbalance&category=신발">뉴발란스</a></li>
							</ul>	
						</li>
						<li><input id="sub-group-2" type="checkbox" hidden/> <!-- 2rd 카테고리 -->		
							<label for="sub-group-2"><span class="fa fa-angle-right"></span>상의</label>
							<ul class="sub-group-list">
								<li><a href="AllProducts.nhn?category=상의">전체 상의</a></li>
								<li><a href="categoryDetail.nhn?categoryDetail=nike&category=상의">나이키</a></li>
								<li><a href="categoryDetail.nhn?categoryDetail=adidas&category=상의">아디다스</a></li>
								<li><a href="categoryDetail.nhn?categoryDetail=newbalance&category=상의">뉴발란스</a></li>
							</ul>
						</li>
						<li><input id="sub-group-3" type="checkbox" hidden/> <!-- 2rd 카테고리 -->		
							<label for="sub-group-3"><span class="fa fa-angle-right"></span>하의</label>
							<ul class="sub-group-list">
								<li><a href="AllProducts.nhn?category=하의">전체 하의</a></li>
								<li><a href="categoryDetail.nhn?categoryDetail=nike&category=하의">나이키</a></li>
								<li><a href="categoryDetail.nhn?categoryDetail=adidas&category=하의">아디다스</a></li>
								<li><a href="categoryDetail.nhn?categoryDetail=newbalance&category=하의">뉴발란스</a></li>
							</ul>
						</li>
					</ul>
				</li>
			</ul>
		</nav>
	</header>
</div>