<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../Layout/header.jsp"></jsp:include>
<jsp:useBean id="date" class="java.util.Date"/>
<jsp:include page="./MyPageLayOut/menuBar.jsp"></jsp:include>
<jsp:include page="./MyPageLayOut/StartMain.jsp"></jsp:include>
<div class="container">
	<a href="#" class="btn btn-primary active" role="button">적립내역</a>
	<a href="#" class="btn btn-primary" role="button">사용내역</a>
	<table class="table table-striped  table-bordered" style="margin-top: 30px;">
		<thead>
			<tr>
				<th colspan="4" style="text-align: center;">포인트 적립 내역</th>
			</tr>
		</thead>
		<tbody>
			<tr align="center">
				<td>날짜</td>
				<td>구분</td>
				<td>내역</td>
				<td>포인트</td>
			</tr>
			 <c:choose>
				<c:when test="${list.size()==0}">
						<tr>
							<td colspan="5"><marquee>테이블에 저장된 글이 없습니다.</marquee></td>
						</tr>
					</c:when>
					<c:otherwise>
					<c:forEach var="data" items="${list}">
							<tr align="center">								
								<td>
									<fmt:formatDate value="${data.depositDate}" pattern="MM.dd(E)"/>							
								</td>
								<td>
									${data.content}
								</td>
								<td>
									${data.content}
								</td>
								<td>
									+${data.point}
								</td>
							</tr>
					</c:forEach>
					</c:otherwise>
			</c:choose>
		</tbody>
	</table>



</div>
<jsp:include page="./MyPageLayOut/EndMain.jsp"></jsp:include>
<jsp:include page="../Layout/footer.jsp"></jsp:include>
</html>