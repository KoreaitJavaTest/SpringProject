<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test = "${null == result }">
		<form action="SearchMyIdByEmailDo" method="post">
			<table border="1">
				<thead>
					<tr>
						<td colspan="2">등록된 아이디가 없습니다. 다시 입력하세요</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<input type = "email" name = "email" placeholder="이메일을 입력하세요">
						</td>
						<td>
							<input type = "submit" value = "찾기">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</c:if>
	<c:if test = "${result != null }">
		아이디 : ${result}입니다.<br/><br/>
		<div align = "center">
			<input type = "button" value = "닫기" onclick = "window.close();">
		</div>
	</c:if>
</body>
</html>