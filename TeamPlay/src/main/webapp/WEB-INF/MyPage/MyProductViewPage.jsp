<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<jsp:include page="../Layout/header.jsp"></jsp:include>
<jsp:include page="./MyPageLayOut/menuBar.jsp"></jsp:include>
<jsp:include page="./MyPageLayOut/StartMain.jsp"></jsp:include>
<c:set var="list" value="${shoplist.list}"/>

<div class="container">
	<table id="mytable" class="table table-bordred table-striped table-hover">
		<thead>
			<tr>
				<th style="width: 100px; text-align: center;">상품 번호</th>
				<th style="width: 70px; text-align: center;">조회수</th>
				<th style="width: 70px; text-align: center;">좋아요</th>
				<th style="width: 100px; text-align: center;">카테고리</th>
				<th style="width: 120px; text-align: center;">브랜드</th>
				<th style="width: 120px; text-align: center;">상품명</th>
				<th style="width: 200px; text-align: center;">작성일</th>
				<th style="width: 30px; text-align: center;">Edit</th>
				<th style="width: 30px; text-align: center;">Delete</th>
			</tr>
		</thead>
	    <tbody>
		    <c:choose>
			<c:when test="${list.size()==0}">
			
			<tr>
				<td colspan="9"><marquee>테이블에 저장된 글이 없습니다.</marquee></td>
			</tr>
			
			</c:when>
			<c:otherwise>
			<c:forEach var="vo" items="${list}">
			
			<tr style="cursor: pointer;">
				<td align="center">${vo.sh_idx}</td>
				<td align="center">${vo.sh_hit}</td>
				<td align="center">${vo.sh_like}</td>
				<td align="center">${vo.sh_category}</td>
				<td align="center">${vo.sh_categoryDetail}</td>
				<td align="center" onclick="location.href='ShopSelectProduct?sh_idx=${vo.sh_idx}'">${vo.sh_name}</td>
				<td align="center">
					<c:if test="${date.year == vo.sh_writeDate.year && date.month == vo.sh_writeDate.month && date.date == vo.sh_writeDate.date}">
						<fmt:formatDate value="${vo.sh_writeDate}" pattern="a h:mm" />
					</c:if> 
					<c:if test="${date.year != vo.sh_writeDate.year || date.month != vo.sh_writeDate.month || date.date != vo.sh_writeDate.date}">
						<fmt:formatDate value="${vo.sh_writeDate}" pattern="yyyy.MM.dd(E)" />
					</c:if>
				</td>
				<td>
			    	<p data-placement="top" data-toggle="tooltip" title="Edit">
				    	<button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" onclick="location.href='ShopUpdateProduct?sh_idx=${vo.sh_idx}'">
				    		<span class="glyphicon glyphicon-pencil"></span>
				    	</button>
			    	</p>
			    </td>
		    	<td align="center">
			    	<p data-placement="top" data-toggle="tooltip" title="Delete">
				    	<button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" onclick="location.href='ShopDeleteProductOK?sh_idx=${vo.sh_idx}&flag=mypage'" >
				    		<span class="glyphicon glyphicon-trash"></span>
				    	</button>
		    		</p>
  					</td>
			</tr>
			
			</c:forEach>
			</c:otherwise>
			</c:choose>
			
		    <tr>
				<td colspan="9" align="center">
					<c:if test="${shoplist.currentPage > 1}">
						<input class="button button1" type="button" value="맨앞" title="첫 번째 페이지로 이동" onclick="location.href='?currentPage=1'" />
					</c:if> 
					<c:if test="${shoplist.currentPage <= 1}">
						<input class="button button2" type="button" value="맨앞" disabled="disabled" title="이미 첫 번째 페이지 입니다." />
					</c:if> 
					<c:if test="${shoplist.startPage > 1 }">
						<input class="button button1" type="button" value="이전" title="이전 10페이지로 이동" onclick="location.href='?currentPage=${shoplist.currentPage - shoplist.pageSize}'" />
					</c:if> 
					<c:if test="${shoplist.startPage <= 1 }">
						<input class="button button2" type="button" value="이전" disabled="disabled" title="이미 첫 번째 10페이지 입니다." />
					</c:if>
					
					<c:forEach var="i" begin="${shoplist.startPage}" end="${shoplist.endPage}" step="1">
						<c:if test="${shoplist.currentPage == i}">
							<input class="button button2" type="button" value="${i}" disabled="disabled" />
						</c:if>
						<c:if test="${shoplist.currentPage != i}">
							<input class="button button1" type="button" value="${i}" onclick="location.href='?currentPage=${i}'" />
						</c:if>
					</c:forEach> 
					
					<c:if test="${shoplist.endPage < shoplist.totalPage}">
						<input class="button button1" type="button" value="다음" title="다음 10페이지로 이동" onclick="location.href='?currentPage=${shoplist.endPage + 1}'"/>
					</c:if> 
					<c:if test="${shoplist.endPage >= shoplist.totalPage}">
						<input class="button button2" type="button" value="다음" disabled="disabled" title="이미 마지막 10페이지 입니다." />
					</c:if> 
					<c:if test="${shoplist.currentPage < shoplist.totalPage}">
						<input class="button button1" type="button" value="맨뒤" title="마지막 페이지로 이동" onclick="location.href='?currentPage=${shoplist.totalPage}'" />
					</c:if> 
					<c:if test="${shoplist.currentPage >= shoplist.totalPage}">
						<input class="button button2" type="button" value="맨뒤" disabled="disabled" title="이미 마지막 페이지 입니다." />
					</c:if>
				</td>
			</tr>
	    </tbody>
	</table>
</div>

<jsp:include page="./MyPageLayOut/EndMain.jsp"></jsp:include>
<div>
<jsp:include page="../Layout/footer.jsp"></jsp:include>
</div>
</html>