<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />

<script type="text/javascript">
function addcart(idx) {
	if(${userId == null}) {
		alert('장바구니는 로그인 후 이용해주세요');
	}else {
		$.ajax({
			url: "./addcart",
			type: "POST",
	        data: {
	       		sh_idx: idx
	        },
	        success: function (data) {
	     		alert('장바구니 추가 완료!')
	        },
	        error: function() {
	        	console.log(idx);
	        }
		})
	}
}

</script>
<!-- 브랜드 별로 상품을 보여주는 페이지 -->
<jsp:include page="/WEB-INF/Shop/mainCategory.jsp"></jsp:include>
<jsp:useBean id="date" class="java.util.Date"/>

<c:set var="list" value="${shopList.list}"/>

<div class="container">

	<h3 class="h3">
		<%String categoryDetail = request.getParameter("categoryDetail");%>
	 	<span style="font-weight: bold;font-size: 25pt;"><%=categoryDetail%></span>
    </h3>
    
    <div class="row">
    	<c:forEach var="vo" items="${list}">
	        <div class="col-md-3 col-sm-6">
	            <div class="product-grid">
	                <div class="product-image">
	                    <a href="ShopSelectProduct?sh_idx=${vo.sh_idx}">
		                    <c:if test="${vo.sh_img1 != null && vo.sh_img2 == null}">
		                        <img class="pic-1" src="${vo.sh_img1}">
	                    	</c:if>
	                    	<c:if test="${vo.sh_img1 == null && vo.sh_img2 != null}">
		                        <img class="pic-1" src="${vo.sh_img2}">
	                    	</c:if>
	                    	<c:if test="${vo.sh_img1 != null && vo.sh_img2 != null}">
	                    		<img class="pic-1" src="${vo.sh_img1}">
	                    		<img class="pic-2" src="${vo.sh_img2}">
	                    	</c:if>
	                    </a>
	                    <ul class="social">
	                        <li><a href="ShopSelectProduct?sh_idx=${vo.sh_idx}" data-tip="바로가기"><i class="fa fa-search"></i></a></li>
	                        <li><a onclick="addcart(${vo.sh_idx})" data-tip="장바구니"><i class="fa fa-shopping-cart"></i></a></li>
	                    </ul>
	                    
	                    <!-- 게시일이 오늘 날짜면 new 표기 -->
	                    <c:if test="${date.year == vo.sh_writeDate.year && date.month == vo.sh_writeDate.month && date.date == vo.sh_writeDate.date}">
		                    <span class="product-new-label">New</span>
	                    </c:if>
	                    
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
	                    <a class="add-to-cart" onclick="addcart(${vo.sh_idx})">장바구니 추가</a>
	                </div>
	            </div>
	    	</div>
		</c:forEach>
	</div>
</div>

<jsp:include page="../Layout/footer.jsp"></jsp:include>