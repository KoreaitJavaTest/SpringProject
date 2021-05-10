<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">

function addcart(idx) {
	
//	상품의 idx로 session key와 value를 설정한다.
	if(idx == sessionStorage.getItem("session_cart_" + idx)){
		alert('이미 같은 상품을 담았습니다.')
	} else {
		sessionStorage.setItem("session_cart_" + idx, idx)
		alert(idx + '번 상품을 장바구니에 담았습니다.')
	}
}


</script>
<!-- 전체 상품을 보여주는 페이지 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<jsp:include page="../Shop/mainCategory.jsp"></jsp:include>
<jsp:useBean id="date" class="java.util.Date"/>

<c:set var="list" value="${shopList.list}"/>
<div class="container">
    <h3 class="h3">
    	<% String category = request.getParameter("category");%>
    	<c:choose>
	    	<c:when test="<%=category != null%>">
		 		<span style="font-weight: bold;font-size: 25pt;"><%=category%></span>
		 	</c:when>
		 	<c:otherwise>
		 		<span style="font-weight: bold;font-size: 25pt;">전체 상품</span>
		 	</c:otherwise>
	 	</c:choose>
    </h3>
    
    <div class="row">
    	<c:forEach var="vo" items="${list}">
        <div class="col-md-3 col-sm-6">
            <div class="product-grid">
                <div class="product-image">
                    <a href="increment.nhn?sh_idx=${vo.sh_idx}">
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
                        <li><a href="increment.nhn?sh_idx=${vo.sh_idx}" data-tip="상품 보기"><i class="fa fa-search"></i></a></li>
                        <li><a onclick="addcart(${vo.sh_idx})" data-tip="장바구니" id="cart"><i class="fa fa-shopping-cart"></i></a></li>
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
                    <h3 class="title"><a href="increment.nhn?sh_idx=${vo.sh_idx}">${vo.sh_name}</a></h3>
                    	<div class="price">${vo.sh_salePriceFM}원
                        	<span>${vo.sh_priceFM}원</span>
                        </div>
                    <a class="add-to-cart" onclick="addcart(${vo.sh_idx})">장바구니 추가</a>
                </div>
            </div>
    	</div>
		</c:forEach>
		
		<div>
			<table class="table">
				<tr>
					<th style="text-align: center">
						<c:if test="${shopList.currentPage > 1}">
							<input class="button button1" type="button" value="맨앞" title="첫 번째 페이지로 이동" onclick="location.href='?currentPage=1'"/>
						</c:if>
						<c:if test="${shopList.currentPage <= 1}">
							<input class="button button2" type="button" value="맨앞" disabled="disabled" title="이미 첫 번째 페이지 입니다."/>
						</c:if>
						
						<!-- 10페이지 앞으로 -->
						<c:if test="${shopList.startPage > 1 }">
							<input class="button button1" type="button" value="이전" title="이전 페이지로 이동" 
									onclick="location.href='?currentPage=${shopList.startPage - 1}'"/>
						</c:if>
						<c:if test="${shopList.startPage <= 1 }">
							<input class="button button2" type="button" value="이전" disabled="disabled" title="이미 첫 번째 10페이지 입니다."/>			
						</c:if>
						
						<!-- 페이지 이동 -->
						<c:forEach var="i" begin="${shopList.startPage}" end="${shopList.endPage}" step="1">
							<c:if test="${shopList.currentPage == i}">
								<input class="button button2" type="button" value="${i}" disabled="disabled"/>
							</c:if>
							<c:if test="${shopList.currentPage != i}">
								<input class="button button1" type="button" value="${i}" onclick="location.href='?currentPage=${i}'"/>
							</c:if>
						</c:forEach>
						
						<!-- 10페이지 뒤로 -->
						<c:if test="${shopList.endPage < shopList.totalPage}">
							<input class="button button1" type="button" value="다음" title="다음 페이지로 이동" 
									onclick="location.href='?currentPage=${shopList.endPage + 1}'"/>
						</c:if>
						<c:if test="${shopList.endPage >= shopList.totalPage}">
							<input class="button button2" type="button" value="다음" disabled="disabled" title="이미 마지막 10페이지 입니다."/>
						</c:if>
						
						<!-- 마지막으로 -->
						<c:if test="${shopList.currentPage < shopList.totalPage}">
							<input class="button button1" type="button" value="맨뒤" title="마지막 페이지로 이동" 
									onclick="location.href='?currentPage=${shopList.totalPage}'"/>
						
						</c:if>
						<c:if test="${shopList.currentPage >= shopList.totalPage}">
							<input class="button button2" type="button" value="맨뒤" disabled="disabled" title="이미 마지막 페이지 입니다."/>			
						</c:if>
					</th>
				</tr>
			</table>
		</div>
	</div>
</div>




<jsp:include page="../Layout/footer.jsp"></jsp:include>