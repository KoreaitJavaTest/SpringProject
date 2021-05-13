<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel=”stylesheet” href=”http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css“>
<html>
<style>
.indexjumbo {
/* 		background-image: url("./resources/images/test2.jpg"); */
/* 		background-repeat: round; */
		height: 480px;
		width: 1140px;
/* 		border-radius: 15px; */
	}
.ReViewRank p{
  height:83%;
  overflow: auto;
  white-space: pre-line;
}
</style>
<jsp:include page="../Layout/header.jsp"></jsp:include>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<div class="container">
	<div class="indexjumbo">
		<img alt="" src="<c:url value="/resources/images/test01.jpg"/>"style="width: 100%;height: 100%;">
	</div>
</div>


<c:set var="list" value="${shopList.list}"></c:set>
<div class="container">
    <h3 class="h3" align="center" style="margin-top: 100px;margin-bottom: 50px">
 		<span style="font-weight: bold;font-size: 20pt;">인기 상품</span>
    </h3>
    
    <div class="row">
    	<c:forEach var="vo" items="${list}">
        <div class="col-md-4 col-sm-6">
            <div class="product-grid">
                <div class="product-image">
                    <a href="ShopSelectProduct?sh_idx=${vo.sh_idx}">
                    	<c:if test="${vo.sh_img1 != null && vo.sh_img2 == null}">
	                        <img class="pic-1 pic" src="${vo.sh_img1}">
                    	</c:if>
                    	<c:if test="${vo.sh_img1 == null && vo.sh_img2 != null}">
	                        <img class="pic-1 pic" src="${vo.sh_img2}">
                    	</c:if>
                    	<c:if test="${vo.sh_img1 != null && vo.sh_img2 != null}">
                    		<img class="pic-1 pic" src="${vo.sh_img1}">
                    		<img class="pic-2 pic" src="${vo.sh_img2}">
                    	</c:if>
                    </a>
                    <ul class="social">
                        <li><a href="ShopSelectProduct?sh_idx=${vo.sh_idx}" data-tip="상품 보기"><i class="fa fa-search"></i></a></li>
                        <li><a onclick="addcart('${vo.sh_idx}')" data-tip="장바구니" id="cart"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                    
                    <span class="product-new-label" style="padding: 4px 10px; font-size: 20px; background-color: dimgrey; font-weight: bold;">${vo.sh_ranking}위</span>
                    <span class="product-discount-label">-${vo.sh_salePercent}%</span>
                </div>
                
                <ul class="rating">
                	<li>
                		<span class="glyphicon glyphicon-heart">&nbsp;${vo.sh_like}</span>
                	</li>
                </ul>
                
                
                   <div class="product-content">
                    <h3 class="title"><a href="ShopSelectProduct?sh_idx=${vo.sh_idx}">${vo.sh_name}</a></h3>
                    	<div class="price">${vo.sh_salePriceFM}원
                        	<span>${vo.sh_priceFM}원</span>
                        </div>
                    <a class="add-to-cart" onclick="addcart('${vo.sh_idx}')">장바구니 추가</a>
                </div>
            </div>
    	</div>
		</c:forEach>
	</div>
</div>

<div class="container" style="border-top: 2px solid black;margin-top: 30px;padding-top: 30px;">
<div class="panel panel-danger">
  <div class="panel-heading" style="text-align: center;"><span class="glyphicon glyphicon-fire"></span>&nbsp;이달의 리뷰게시글</div>
 <div class="panel-body">
	
<c:forEach var="vo" items="${ReViewList}">
<c:set var="imgN" value="${fn:split(vo.RE_imgNames,',')}"/>
	<div class="row">
        <div class="col-md-3" style="width: 585px;height: 237px; position: relative;" >
         	<div>
            <img class="img-fluid rounded mb-3 mb-md-0" style="width: 100%; height: 100%" src="http://localhost:8010/korea/upload/${imgN[0]}" alt="">
			<span class="label label-default" 
			style="color:#fff;background-color:#ef5777;font-size:12px;text-transform:uppercase;
			padding:2px 7px;display:block;position:absolute;top:10px;left:0"><p style="font-size: 15pt;">${vo.RE_rank}위</p></span>         	
         	
         	</div>
        </div>
        <div class="col-md-5" style="height: 195px;">
        	<div class="ReViewRank" style="height: 195px;">
		          <h1 style="font-size: 20pt;">${fn:trim(vo.RE_title)}</h1>
		          <p>
		          	${fn:trim(vo.RE_content)}
		          </p>
		          <a class="btn btn-primary" href="ReViewDetailSelect?idx=${vo.RE_idx}" style="position: relative ;top: 10px;">바로가기</a>
        	</div>
        </div>
     </div>
      <hr>
</c:forEach>
 </div>
  </div>
</div>
      <!-- /.row -->

<jsp:include page="../Layout/footer.jsp"></jsp:include>
</html>
	