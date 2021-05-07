<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<jsp:useBean id="date" class="java.util.Date"/>
<jsp:include page="./AdminLayOut/AdminNav.jsp"></jsp:include>
	<div class="col-md-12 well">
	    <div class="panel panel-primary">
	        <div class="panel-heading">
	            <h3 class="panel-title">
	                <span class="glyphicon glyphicon-bookmark"></span> 유저설정</h3>
	        </div>
	         <div class="panel-body" style="padding: 0px;"> 
<c:set var="list" value="${AdminUserList.list}"/>	                

	                <!-- 메인글시작 -->
		<div class="col-xs-12 col-md-12" style="padding: 0 0 0 0;">
	<table id="mytable" class="table table-bordred table-striped table-hover" style="margin-bottom: 0px;">
            		<tr>
	                   <th class="text-center" style="width: 5%;"><input type="checkbox" id="checkall" /></th>
	                   <th class="text-center" style="width: 10%;">아이디</th>
	                   <th class="text-center" style="width: 10%;">성별</th>
	                   <th class="text-center" style="widows: 20%;">이메일</th>
	                   <th class="text-center" style="width: 15%;">잔여 포인트</th>
	                   <th class="text-center" style="width: 20%;">가입일자</th>
	                   <th class="text-center" style="width: 8%;">권한</th>
	                   <th class="text-center" style="width: 5%;">Edit</th>   
	                   <th class="text-center" style="width: 5%;">Delete</th>
	                 </tr>
    <tbody>
	    <c:choose>
			<c:when test="${list.size()==0}">
					<tr>
						<td colspan="9"><marquee>테이블에 저장된 글이 없습니다.</marquee></td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="id" items="${list}">
						<tr style="cursor: pointer;">
							<td><input type="checkbox" class="checkthis" /></td>
							<td align="center">${id.client_id}</td>
							<td align="center">${id.client_gender}</td>
							<td align="center">${id.client_email}</td>
							<td align="center">
								${id.client_point}
							</td>
							<td align="center">
								<c:if test="${date.year == id.client_rdate.year && date.month == id.client_rdate.month && date.date == id.client_rdate.date}">
									<fmt:formatDate value="${id.client_rdate}" pattern="a h:mm" />
								</c:if> 
								<c:if test="${date.year != id.client_rdate.year || date.month != id.client_rdate.month || date.date != id.client_rdate.date}">
									<fmt:formatDate value="${id.client_rdate}" pattern="yyyy.MM.dd(E)" />
								</c:if>
							</td>
							<td align="center">
							<select id="level" name="level">
								<option value="0" <c:if test="${id.client_level==0}">selected</c:if> >소비자</option>
								<option value="2" <c:if test="${id.client_level==2}">selected</c:if> >판매자</option>
								<option value="1" <c:if test="${id.client_level==1}">selected</c:if> >관리자</option>
							</select>
							</td>
							<td align="center">
						    	<p data-placement="top" data-toggle="tooltip" title="Edit">
							    	<button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" onclick="">
							    		<span class="glyphicon glyphicon-pencil"></span>
							    	</button>
						    	</p>
					    	</td>
					    	<td align="center">
						    	<p data-placement="top" data-toggle="tooltip" title="Delete">
							    	<button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" onclick="" >
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
				<c:if test="${AdminUserList.currentPage > 1}">
					<input class="btn btn-default" type="button" value="맨앞" title="첫 번째 페이지로 이동" onclick="location.href='?currentPage=1'" />
				</c:if> 
				<c:if test="${AdminUserList.currentPage <= 1}">
					<input class="btn btn-default" type="button" value="맨앞" disabled="disabled" title="이미 첫 번째 페이지 입니다." />
				</c:if> 
				<c:if test="${AdminUserList.startPage > 1 }">
					<input class="btn btn-default" type="button" value="이전" title="이전 10페이지로 이동" onclick="location.href='?currentPage=${AdminUserList.currentPage - AdminUserList.pageSize}'" />
				</c:if> 
				<c:if test="${AdminUserList.startPage <= 1 }">
					<input class="btn btn-default" type="button" value="이전" disabled="disabled" title="이미 첫 번째 10페이지 입니다." />
				</c:if>
				<c:forEach var="i" begin="${AdminUserList.startPage}" end="${AdminUserList.endPage}" step="1">
					<c:if test="${AdminUserList.currentPage == i}">
						<input class="btn btn-default" type="button" value="${i}" disabled="disabled" />
					</c:if>
					<c:if test="${AdminUserList.currentPage != i}">
						<input class="btn btn-default" type="button" value="${i}" onclick="location.href='?currentPage=${i}'" />
					</c:if>
				</c:forEach> 
					<c:if test="${AdminUserList.endPage < AdminUserList.totalPage}">
					<input class="btn btn-default" type="button" value="다음" title="다음 10페이지로 이동" onclick="location.href='?currentPage=${AdminUserList.endPage + 1}'"/>
				</c:if> 
				<c:if test="${AdminUserList.endPage >= AdminUserList.totalPage}">
					<input class="btn btn-default" type="button" value="다음" disabled="disabled" title="이미 마지막 10페이지 입니다." />
				</c:if> 
				<c:if test="${AdminUserList.currentPage < AdminUserList.totalPage}">
					<input class="btn btn-default" type="button" value="맨뒤" title="마지막 페이지로 이동" onclick="location.href='?currentPage=${AdminUserList.totalPage}'" />
				</c:if> 
				<c:if test="${AdminUserList.currentPage >= AdminUserList.totalPage}">
					<input class="btn btn-default" type="button" value="맨뒤" disabled="disabled" title="이미 마지막 페이지 입니다." />
				</c:if>
			</td>
		</tr>
    </tbody>
</table>		
	                
	
	</div>
	                <!-- 메인글 끝 -->
	                
<jsp:include page="./AdminLayOut/AdminNavEnd.jsp"></jsp:include>

</html>