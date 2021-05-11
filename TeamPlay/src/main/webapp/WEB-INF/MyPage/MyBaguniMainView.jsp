<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<jsp:include page="../Layout/header.jsp"></jsp:include>
<jsp:useBean id="date" class="java.util.Date" />
<jsp:include page="./MyPageLayOut/menuBar.jsp"></jsp:include>
<jsp:include page="./MyPageLayOut/StartMain.jsp"></jsp:include>
<div class="container">
	<table id="mytable"
		class="table table-bordred table-striped table-hover">
		<tr>
			<th colspan="5">장바구니</th>
		</tr>
		<tr>
			<td align="center">상품이미지</td>
			<td align="center">상품명</td>
			<td align="center">사이즈</td>
			<td align="center">가격</td>
		</tr>
			<c:if test = "${list == null }">
			<tr>
				<td colspan="4"><marquee>장바구니에 아무것도 없어용.</marquee></td>
			</tr>
			</c:if>
			<c:if test = "${list != null }">
				<c:forEach var="list" items="${list}">
				<tr>
					<td align = "center">
						<img src="http://localhost:9090/korea/upload/${list.sh_img1 }">
					</td>
					<td align = "center">
						${list.sh_name }
					</td>
					<td align = "center">
						<c:if test ="${list.sh_category eq '신발' }">
							<select>
								<option>230</option>
								<option>240</option>
								<option>250</option>
								<option>260</option>
								<option>270</option>
								<option>280</option>
							</select>
						</c:if>
					</td>
					<td align = "center">
						${list.sh_price}원
					</td>
				</tr>
				</c:forEach>
			</c:if>
			<tr>
				<td colspan="5">
					<div id ="money" style = "float: right;">총액 : ${money }</div>
				</td>
			</tr>
		<tr>
			<td colspan="5">
				<input type="button"  style = "float: right;" value="결제하기" id = "naverPayBtn">
			</td>
		</tr>
		
	</table>
	<script src="https://nsp.pay.naver.com/sdk/js/naverpay.min.js"></script>
	<script>
	    var oPay = Naver.Pay.create({
	          "mode" : "production", // development or production
	          "clientId": "u86j4ripEt8LRfPGzQ8" // clientId
	    });
	
	    //직접 만드신 네이버페이 결제버튼에 click Event를 할당하세요
	    var elNaverPayBtn = document.getElementById("naverPayBtn");
	
	    elNaverPayBtn.addEventListener("click", function() {
	        oPay.open({
	          "merchantUserKey": "test",
	          "merchantPayKey": "test",
	          "productName": "뀨잉상품",
	          "totalPayAmount": "${money}",
	          "taxScopeAmount": "1000",
	          "taxExScopeAmount": "0",
	          "returnUrl": "사용자 결제 완료 후 결제 결과를 받을 URL"
	        });
	    });
	
	</script>
	</div>
		<jsp:include page="./MyPageLayOut/EndMain.jsp"></jsp:include>
	<div>
		<jsp:include page="../Layout/footer.jsp"></jsp:include>
	</div>

</html>