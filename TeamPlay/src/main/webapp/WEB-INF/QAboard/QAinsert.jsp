<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<jsp:include page="../Layout/header.jsp"></jsp:include>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container" align="center" style="margin-top: 30px;">
	<form action="insertOKDo" method="post">
		<table class="table table-bordered table-hover">
			<tr align="center">
				<th colspan="3">상품 Q&A</th>
			</tr>
			<tr>
				<td colspan="1" style="width: 15%;">
					<label for="q_userid">아이디</label>
				</td>
				<td colspan="2">
					<input id="q_userid" type="text" name="q_userid" value = "${sessionScope.session_id }" style="width: 35%" readonly="readonly"/>
				</td>
			</tr>	
			<tr>
				<td colspan="1" style="width: 15%;">
					<label for="q_title">제목</label>
				</td>
				<td colspan="2">
					<input id="q_title" type="text" name="q_title" placeholder="제목을 입력하세요" 
						required="required" style="width: 95%"/>
				</td>
			</tr>
			<tr>
				<td>
					<label for="q_content">내용</label>
				</td>
				<td colspan="2">
					<textarea id="q_content" rows="10" cols="65" name="q_content" 
						style="resize:none; width: 95%;" placeholder="내용을 입력하세요"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="submit" value="저장하기"/>
					<input type="button" value="돌아가기" onclick="history.back()"/>
				</td>
			</tr>
		</table>
	</form>
</div>
	</body>
</html>
<jsp:include page="../Layout/footer.jsp"></jsp:include>

