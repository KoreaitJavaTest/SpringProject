<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	int currentPage = Integer.parseInt(request.getParameter("currentPage"));
//테이블에 메인글을 삭제했으므로 컨트롤러에게 삭제한 글이 있던 1페이지 분량의 글을 얻어오는 요청을 한다.
	response.sendRedirect("list.nhn?currentPage=" + currentPage);
%>

</body>
</html>