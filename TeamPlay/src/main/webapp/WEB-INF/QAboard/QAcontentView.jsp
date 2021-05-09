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

<div class="container" align="center" style="margin-top: 30px;">
	<form action="updateDo" method="post"> 
		<table class="table table-bordered table-hover">
			<tr align="center">
				<th colspan="5">게시글 보기</th>
			</tr>
			<tr>
				<td width="100" align="center">글번호</td>
				<td width="360" align="center">아이디</td>
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
					<input type="text" name="q_title" value="${vo.q_title}" style="width: 98%"/>
				</td>
			</tr>
			<tr>
				<td align="center">내용</td>
				<td colspan="3">
					<textarea rows="10" cols="65" name="q_content" style="resize: none; width: 95%;">${vo.q_content}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<input type="hidden" name="q_idx" value="${vo.q_idx}"/>
					<input type="hidden" name="currentPage" value="${currentPage}"/>
					<c:if test="${sessionScope.session_id eq q_userid }">
						<input type="submit" value="수정하기"/>
					</c:if>
					<c:if test="${sessionScope.session_id eq q_userid }">
							<input type="button" value="삭제하기" onclick="location.href='deleteDo?idx=${vo.q_idx}&currentPage=${currentPage}'"/>
					</c:if>

					<input type="button" value="답변하기" onclick="location.href='replyDo?idx=${vo.q_idx}&currentPage=${currentPage}'"/>

							<c:if test = "${vo.q_flag == '0'}">
								<input type="button" value="답변하기" onclick="alert('관리자만 작성 할 수 있습니다.')"/>
							</c:if>
					<input type="button" value="돌아가기" onclick="location.href='QAboardDo?currentPage=${currentPage}'"/>  
				</td>
			</tr>
		</table>
		<c:if test = "${vo.q_flag != 0 }">
			<table class="table table-bordered table-hover">
				<tr>
					<th colspan="5">답변 내용</th>				
				</tr>	
				<tr>
					<td width="100" align="center">이름</td>
					<td width="500">
						<input type="text" name="a_userid" readonly="readonly" value="${avo.a_userid}"/>
					</td>
				</tr>
				<tr>
					<td align="center">제목</td>
					<td colspan="3">
						<input type="text" name="a_title" readonly="readonly" value="${avo.a_title}" style="width: 98%"/>
					</td>
				</tr>
				<tr>
					<td align="center">내용</td>
					<td colspan="3">
						<textarea rows="10" cols="120" name="a_content" readonly="readonly" style="resize: none;">${avo.a_content}</textarea>
					</td>
				</tr>
			</table>
		</c:if>
	</form>
</div>
</body>
</html>
<jsp:include page="../Layout/footer.jsp"></jsp:include>