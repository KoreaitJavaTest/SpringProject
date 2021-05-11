<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
function addcart() {
	if(${userId == null}) {
		alert('장바구니는 로그인 후 이용해주세요');
	}else {
		$.ajax({
			url: "./addcart",
			type: "POST",
	        data: {
	       		sh_idx: "${vo.sh_idx}"
	        },
	        success: function (data) {
	        	alert(data);
	        },
	        error: function() {
	        }
		})
	}
}

function NoLogin() {
	alert('좋아요는 로그인 후 가능합니다.');
	location.href='LoginViewDo';
}

	// 추천버튼 클릭시(추천 추가 또는 추천 제거)
	function likeUpdate() {
		$.ajax({
			url: "./likeUpdate",
			type: "POST",
            data: {
                like_idx: "${vo.sh_idx}",
                like_id: "${userId}"
            },
            success: function (data) {
            	console.log(data)
            	if(data == 0){
	            	$('.likeCount').attr('class', 'likeCount glyphicon glyphicon-heart');
            	}else{
	            	$('.likeCount').attr('class', 'likeCount glyphicon glyphicon-heart-empty');
            		
            	}
		        likeCount();
		        
            },
            error: function() {
            	console.log("통신 실패")
            }
		})
	}
	// 게시글 추천수
    function likeCount() {
		$.ajax({
			url: "./likeCount",
			method: "POST",
            data: {
            	like_idx: "${vo.sh_idx}"
            },
            success: function (count) {
            	console.log(count);
            	$(".likeCount").html("&nbsp;"+count);
            },
            error: function() {
            }
		})
    };

</script>

<!-- 선택한 상품을 보여주는 페이지 -->
<jsp:include page="/WEB-INF/Shop/mainCategory.jsp"></jsp:include>
	<div class="container">
	
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
				
					<div class="panel-heading">
						<h3 class="panel-title">
							<span>${vo.sh_title}</span>
						</h3>
					</div>
					<div class="panel-heading">
						<h3 class="panel-title" align="right">
							<span onclick="location.href='ShopAllProduct?category=신발'" style="cursor: pointer;">${vo.sh_category} </span>
							>>
							<span onclick="location.href='ShopCategoryDetail?categoryDetail=${vo.sh_categoryDetail}&category=신발'" style="cursor: pointer;">${vo.sh_categoryDetail}</span>
						</h3>
					</div>
				
					<div class="panel-body">
						<div class="media">
							<div class="media-left" style="width: 20%">
								<c:if test="${vo.sh_img1 != null && vo.sh_img2 == null}">
			                        <img class="pic" src="${vo.sh_img1}">
		                    	</c:if>
		                    	<c:if test="${vo.sh_img1 == null && vo.sh_img2 != null}">
			                        <img class="pic" src="${vo.sh_img2}">
		                    	</c:if>
		                    	<c:if test="${vo.sh_img1 != null && vo.sh_img2 != null}">
		                    		<img class="pic" src="${vo.sh_img1}">
		                    		<img class="pic" src="${vo.sh_img2}">
		                    	</c:if>
							</div>
							<div class="media-body">
								<h4 class="media-heading" style="font-size: 20pt;font-weight: bold"><strong>${vo.sh_name}</strong></h4>
								<br/>
								<h2>
									<c:set var="sh_content" value="${fn:replace(fn:trim(vo.sh_content), '<', '&lt;')}"/>
									<c:set var="sh_content" value="${fn:replace(sh_content, '>', '&gt;')}"/>
									<c:set var="sh_content" value="${fn:replace(sh_content, enter, '<br/>')}"/>
									${sh_content}
								</h2>
							</div>
						</div>
					</div>
					
					<table class="table">
						<thead>
							<tr>
								<td align="center" style="font-weight: bold; font-size: 13pt; text-align: left;">
									판매가 <span> ${vo.sh_salePriceFM} 원</span>
									<c:if test="${vo.sh_salePercent != null}">
										<br/><br/>
										<span style="color: graytext;">
											<span style="text-decoration:line-through;"> ${vo.sh_priceFM} 원</span>
											 &nbsp;&nbsp;-${vo.sh_salePercent}% 할인
										</span>
									</c:if>
								</td>
							</tr>
							
							<tr>
								<td colspan="2" align="center">
									<a class="btn btn-default" href="#">바로 구매</a>
								</td>
								<td colspan="2" align="right">
									<a class="btn btn-default" onclick="addcart()">장바구니</a>
									&nbsp;
									<c:if test="${userId != null }">
										<c:if test="${likeCheck == 0}">
											<span class="likeCount glyphicon glyphicon-heart-empty" onclick="likeUpdate()" style="cursor: pointer;">&nbsp;${likeCount}</span>
										</c:if>
										<c:if test="${likeCheck == 1}">
											<span class="likeCount glyphicon glyphicon-heart" onclick="likeUpdate()" style="cursor: pointer;">&nbsp;${likeCount}</span>
										</c:if>
									</c:if>
									<c:if test="${userId == null }">
											<span class="likeCount glyphicon glyphicon-heart-empty" onclick="NoLogin()" style="cursor: pointer;">&nbsp;${likeCount}</span>
									</c:if>
								</td>
								
							</tr>
						</thead>
						<tbody>
							<tr>
								<td align="right" colspan="4">
									사이즈
									<select>
										<option>230</option>
										<option>240</option>
										<option>250</option>
										<option>260</option>
										<option>270</option>
										<option>280</option>
									</select>
									
									&nbsp;&nbsp;&nbsp;&nbsp;
									수량 <input type="text"/>
								</td>
							</tr>
							<tr>
								<td colspan="4" style="text-align: right; font-weight: bold;">평균 배송일 2 ~ 3일 </td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<a class="btn btn-default" href="#">상품 리뷰 보러가기</a>
								</td>
								<td colspan="2" align="center">
									<a class="btn btn-default" href="ShopAllProduct?currentPage=${currentPage}">목록으로</a>
								</td>
							</tr>
							
							<!-- 상품 등록 id와 로그인 id가 같으면 수정, 삭제버튼이 나온다. -->
							<%String userId = session.getAttribute("session_id") + "";%>
							<c:set var="userId" value="<%=userId%>"/>
							<c:if test="${vo.sh_seller eq userId}">
								<tr>
									<td colspan="4" align="center">
										<a class="btn btn-default" href="ShopUpdateProduct?sh_idx=${vo.sh_idx}&sh_img1=${vo.sh_img1}&sh_img2=${vo.sh_img2}">수정</a>
										<a class="btn btn-default" href="ShopDeleteProduct?sh_idx=${vo.sh_idx}">삭제</a>
									</td>
								</tr>
							</c:if>
							
						</tbody>
					</table>
				
				</div>
			</div>
		</div>
			
	</div>

<jsp:include page="../Layout/footer.jsp"></jsp:include>