<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<jsp:include page="../Layout/header.jsp"></jsp:include>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="container" style="margin-top: 50px;">
	<table class="table table-hover table-border">
		<tr>
			<th colspan="5">Q&A 게시판</th>
		</tr>
		<tr>
			<td colspan="5" align="right">
				${qaboardList.totalCount}(${qaboardList.currentPage} / ${qaboardList.totalPage})Page
			</td>
		</tr>

		<tr>
			<td width="80" align="center">글번호</td>
			<td width="670" align="center">제목</td>
			<td width="130" align="center">아이디</td>
			<td width="120" align="center">작성일</td>
		</tr>
		
		<jsp:useBean id="date" class="java.util.Date"/>
		
		<c:set var="list" value="${qaboardList.list}"/>
		<c:if test="${list.size() == 0}"></c:if>
		
		<c:if test="${list.size() != 0}">
		
		<c:forEach var="vo" items="${list}">
		<tr>
			<td align="center">${vo.q_idx}</td>
			<td>
				<c:set var="q_title" value="${fn:replace(fn:trim(vo.q_title), '<', '&lt;')}"/>
				<c:set var="q_title" value="${fn:replace(q_title, '>', '&gt;')}"/>
				<a href="contentViewDo?idx=${vo.q_idx}&currentPage=${qaboardList.currentPage}" style = "color : black;">  <!-- get 방식 -->
					${q_title}
				</a>
			</td> 

			<td align="center">
				<c:set var="q_userid" value="${fn:replace(fn:trim(vo.q_userid), '<', '&lt;')}"/>
				<c:set var="q_userid" value="${fn:replace(q_userid, '>', '&gt;')}"/>
				${q_userid}
			</td>
			<td align="center">
			
				<c:if test="${date.year == vo.q_writedate.year && date.month == vo.q_writedate.month && date.date == vo.q_writedate.date}">
					<fmt:formatDate value="${vo.q_writedate}" pattern="a h:mm"/>
				</c:if>
				<c:if test="${date.year != vo.q_writedate.year || date.month != vo.q_writedate.month || date.date != vo.q_writedate.date}">
					<fmt:formatDate value="${vo.q_writedate}" pattern="yyyy.MM.dd(E)"/>
				</c:if>
			</td>
		</tr>
		
			<c:forEach var="avo" items="${Alist}">
				<c:if test="${vo.q_idx == avo.a_ref}">
					<tr>
						<td align="center">
							<input hidden="${avo.a_idx}"/> 
						</td>
						<td>
							<c:set var="avo.a_title" value="${fn:replace(fn:trim(avo.a_title), '<', '&lt;')}"/>
							<c:set var="avo.a_title" value="${fn:replace(a_title, '>', '&gt;')}"/>
							<a href="replyViewDo?idx=${vo.q_idx}&currentPage=${qaboardList.currentPage}"> 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							${avo.a_title}
							</a>						
						</td>
						<td align="center">
							<c:set var="avo.a_userid" value="${fn:replace(fn:trim(avo.a_userid), '<', '&lt;')}"/>
							<c:set var="avo.a_userid" value="${fn:replace(a_userid, '>', '&gt;')}"/>
							${avo.a_userid}
						</td>
						<td align="center">
							<c:if test="${date.year == avo.a_writedate.year && date.month == avo.a_writedate.month && date.date == avo.a_writedate.date}">
								<fmt:formatDate value="${avo.a_writedate}" pattern="a h:mm"/>
							</c:if>
							<c:if test="${date.year != avo.a_writedate.year || date.month != avo.a_writedate.month || date.date != avo.a_writedate.date}">
								<fmt:formatDate value="${avo.a_writedate}" pattern="yyyy.MM.dd(E)"/>
							</c:if>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		
		</c:forEach>
		</c:if>
		
		<tr>
			<td colspan="5" align="center">
			
			<c:if test="${qaboardList.currentPage > 1}">
				<input class="button button1" type="button" value="맨앞" title="첫 번째 페이지로 이동" onclick="location.href='?currentPage=1'"/>
			</c:if>
			<c:if test="${qaboardList.currentPage <= 1}">
				<input class="button button2" type="button" value="맨앞" disabled="disabled" title="이미 첫 번째 페이지 입니다."/>
			</c:if>
			
			<c:if test="${qaboardList.startPage > 1 }">
				<input class="button button1" type="button" value="이전" title="이전 10페이지로 이동" 
						onclick="location.href='?currentPage=${qaboardList.currentPage - qaboardList.pageSize}'"/>
			</c:if>
			<c:if test="${qaboardList.startPage <= 1 }">
				<input class="button button2" type="button" value="이전" disabled="disabled" title="이미 첫 번째 10페이지 입니다."/>			
			</c:if>
			
			<c:forEach var="i" begin="${qaboardList.startPage}" end="${qaboardList.endPage}" step="1">
				<c:if test="${qaboardList.currentPage == i}">
					<input class="button button2" type="button" value="${i}" disabled="disabled"/>
				</c:if>
				<c:if test="${qaboardList.currentPage != i}">
					<input class="button button1" type="button" value="${i}" onclick="location.href='?currentPage=${i}'"/>
				</c:if>
			</c:forEach>
			
			<c:if test="${qaboardList.endPage < qaboardList.totalPage}">
				<input class="button button1" type="button" value="다음" title="다음 10페이지로 이동" 
						onclick="location.href='?currentPage=${qaboardList.endPage + 1}'"/>
			</c:if>
			<c:if test="${qaboardList.endPage >= qaboardList.totalPage}">
				<input class="button button2" type="button" value="다음" disabled="disabled" title="이미 마지막 10페이지 입니다."/>
			</c:if>
			
			<c:if test="${qaboardList.currentPage < qaboardList.totalPage}">
				<input class="button button1" type="button" value="맨뒤" title="마지막 페이지로 이동" 
						onclick="location.href='?currentPage=${qaboardList.totalPage}'"/>
			
			</c:if>
			<c:if test="${qaboardList.currentPage >= qaboardList.totalPage}">
				<input class="button button2" type="button" value="맨뒤" disabled="disabled" title="이미 마지막 페이지 입니다."/>			
			</c:if>
			
			</td>
		</tr>
		
		<tr>
			<td colspan="5" align="right">
				<c:choose>
					<c:when test="${sessionScope.session_id != null }">
						<input type="button" value="글쓰기" onclick="location.href='QAinsertGo'"/>
					</c:when>
					<c:otherwise>
						<input type="button" value="글쓰기" onclick="alert('로그인 후 작성하실 수 있습니다')"/>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>	
</div>
</body>
</html>
<jsp:include page="../Layout/footer.jsp"></jsp:include>



