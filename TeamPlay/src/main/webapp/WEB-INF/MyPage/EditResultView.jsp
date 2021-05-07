<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Layout/header.jsp"></jsp:include>
<script type="text/javascript">
	window.onload = function(){
		alert("정보수정이 완료되었습니다.");
		location.href = "index.jsp";
	}
</script>

<jsp:include page="../Layout/footer.jsp"></jsp:include>
	