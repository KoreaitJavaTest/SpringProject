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

	<!-- 원본 글을 보여주는 테이블 -->
<div class="container" align="center" style="margin-top: 30px;">	
	<table class="table table-bordered table-hover">
		<tr>
			<th colspan="5">원본 글 보기</th>
		</tr>
		<tr>
			<td width="100" align="center">글번호</td>
			<td width="360" align="center">이름</td>
			<td width="140" align="center">작성일</td>
		</tr>
		<tr>
				<td align="center">${vo.q_idx}</td>
				<td align="center">
					<c:set var="q_userid" value="${fn:replace(fn:trim(vo.q_userid), '<', '&lt;')}"/>
					<c:set var="q_userid" value="${fn:replace(q_userid, '>', '&gt;')}"/>
					${q_userid}
			</td>
			<td align="center">
				<jsp:useBean id="date" class="java.util.Date"/>
					<c:if test="${date.year == vo.q_writedate.year && date.month == vo.q_writedate.month && date.date == vo.q_writedate.date}">
						<fmt:formatDate value="${vo.q_writedate}" pattern="a h:mm"/>
					</c:if>
					<c:if test="${date.year != vo.q_writedate.year || date.month != vo.q_writedate.month || date.date != vo.q_writedate.date}">
						<fmt:formatDate value="${vo.q_writedate}" pattern="yyyy.MM.dd(E)"/>
					</c:if>
			</td>
		</tr>
		<tr>
				<td align="center">제목</td>
				<td colspan="3">
					<input type="text" name="q_title" value="${vo.q_title}" readonly="readonly" style="width: 98%"/>
				</td>
			</tr>
			<tr>
				<td align="center">내용</td>
				<td colspan="3">
					<textarea rows="10" cols="65" name="q_content" readonly="readonly" style="resize: none; width: 95%;">${vo.q_content}</textarea>
				</td>
			</tr>
	</table>
</div>
	<hr color="red" size="3"/>
	
	<!-- 답글을 입력하는 테이블 -->
	
<div class="container" align="center" style="margin-top: 30px;">
	<form action="replyInsertDo" method="post">
	
		<input type="hidden" name="q_idx" value="${vo.q_idx}"/> <!-- 질문글의 글번호 -->
		<input type="hidden" name="q_ref" value="${vo.q_ref}"/> <!-- 글 그룹 -->
		<input type="hidden" name="q_lev" value="${vo.q_lev}"/> <!-- 질문글 레벨 -->
		<input type="hidden" name="q_seq" value="${vo.q_seq}"/> <!-- 같은 글 그룹에서 글 출력 순서 -->
		<input type="hidden" name="currentPage" value="${currentPage}"/> <!-- 답글 입력 후 돌아갈 페이지 번호 --> 
	
		<table class="table table-bordered table-hover">
			<tr>
				<th colspan="5">답글 쓰기</th>
			</tr>
			<tr>
				<td width="100" align="center">이름</td>
				<td width="500">
					<input type="text" name="a_userid" value = "${sessionScope.session_id}" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="center">제목</td>
				<td>
					<input type="text" name="a_title" style="width: 95%"/>
				</td>
			</tr>
			<tr>
				<td align="center">내용</td>
				<td>
					<textarea rows="10" cols="50" name="a_content" style="resize: none; width: 95%;"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="저장하기"/>
					<input type="reset" value="다시쓰기"/>
					<input type="button" value="돌아가기" onclick="history.back()"/>
					<input type="button" value="목록보기" onclick="location.href='list.nhn?currentPage=${currentPage}'"/>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>
<jsp:include page="../Layout/footer.jsp"></jsp:include>





















