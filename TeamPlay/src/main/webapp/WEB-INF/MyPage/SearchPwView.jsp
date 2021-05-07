<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="SearchMyPwDo" method="post">
		<table border="1">
			<thead>
				<tr>
					<td colspan="2">아이디와 이메일을 입력해주세요</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<input type = "text" name = "id" placeholder="아이디를 입력하세요" required="required">
					</td>
				</tr>
				<tr>
					<td>
						<input type = "email" name = "email" placeholder="이메일을 입력하세요" required="required">
					</td>
				</tr>
				<tr>
					<td>
						<input type = "submit" value = "찾기">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>