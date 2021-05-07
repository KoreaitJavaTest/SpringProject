<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Layout/header.jsp"></jsp:include>

<script type="text/javascript">
	window.onload = function(){
		alert("${sessionScope.session_id }님 환영합니다");
		location.href = "index";
	}
</script>

<jsp:include page="../Layout/footer.jsp"></jsp:include>
	