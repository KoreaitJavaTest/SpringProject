<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<style>
.indexjumbo {
/* 		background-image: url("./resources/images/test2.jpg"); */
/* 		background-repeat: round; */
		height: 480px;
		width: 1140px;
/* 		border-radius: 15px; */
	}
</style>
<jsp:include page="../Layout/header.jsp"></jsp:include>
<div class="container">
	<div class="indexjumbo">
		<img alt="" src="<c:url value="/resources/images/test01.jpg"/>"style="width: 100%;height: 100%;">
	</div>
</div>
<jsp:include page="../Layout/footer.jsp"></jsp:include>
</html>
	