<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="../Layout/header.jsp"></jsp:include>
<jsp:useBean id="date" class="java.util.Date"/>
<jsp:include page="./MyPageLayOut/menuBar.jsp"></jsp:include>
<jsp:include page="./MyPageLayOut/StartMain.jsp"></jsp:include>

<c:forEach var="list" items="${list}">
	${list}
</c:forEach>

<jsp:include page="./MyPageLayOut/EndMain.jsp"></jsp:include>
<jsp:include page="../Layout/footer.jsp"></jsp:include>
</html>