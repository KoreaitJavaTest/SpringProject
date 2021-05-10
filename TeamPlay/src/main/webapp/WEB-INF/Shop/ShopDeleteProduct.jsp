<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<jsp:include page="../Layout/header.jsp"></jsp:include>

<form action="ShopDeleteProductOK" method="post" onsubmit="return check()">

	<div class="container" style="margin-top: 50px;">
		<table class="table table-hover table-border">
			<tr>
				<th colspan="2" style="text-align: center; font-size: 30pt; font-weight: bold;">상품 삭제</th>
			</tr>
			
			<tr>
				<td width="100">제목</td>
				<td>
					${vo.sh_title}
				</td>
			</tr>
			
			<tr>
				<td width="100">상품 종류</td>
				<td>
					${vo.sh_category}
				</td>
			</tr>
		
			<tr>
				<td width="100">상품 브랜드</td>
				<td>
					${vo.sh_categoryDetail}
				</td>
			</tr>
		
			<tr>
				<td width="100">상품명</td>
				<td>
					${vo.sh_name}
				</td>
			</tr>
			
			<tr>
				<td width="100">상품 내용</td>
				<td>
					${vo.sh_content}
				</td>
			</tr>
			<tr>
				<td width="100">가격</td>
				<td>
					${vo.sh_priceFM}
				</td>
			</tr>
			
			<tr>
				<td width="100">할인율</td>
				<td>
					${vo.sh_salePercent}%
				</td>
			</tr>
			
		
			<tr>
				<td>
	         		이미지
	            </td>
	            <td>
	            	<c:if test="${vo.sh_img1 != null && vo.sh_img2 == null}">
                        <img src="${vo.sh_img1}" width="300" height="300">
                   	</c:if>
                   	<c:if test="${vo.sh_img1 == null && vo.sh_img2 != null}">
                        <img src="${vo.sh_img2}" width="300" height="300">
                   	</c:if>
                   	<c:if test="${vo.sh_img1 != null && vo.sh_img2 != null}">
                   		<img src="${vo.sh_img1}" width="300" height="300">
                   		<img src="${vo.sh_img2}" width="300" height="300">
                   	</c:if>
	         	</td>
			</tr>
			
			<tr>
				<td align="center" colspan="2">
					<input type="hidden" value="${vo.sh_idx}" name="sh_idx">
					<input type="password" name="sh_password" placeholder="비밀번호를 입력해주세요." width="50%"/>
					<br/>
					<input type="submit" value="상품 삭제"/>
				</td>
			</tr>
			
		</table>
	</div>
</form>

<jsp:include page="../Layout/footer.jsp"></jsp:include>
