<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	window.onload = function(){
		// 글삽입 : 1 
		// 글수정 : 2
		// 글삭제 : 3
		// 댓글삽입 : 4
		var flag = ${flag}
		var currentPage = ${currentPage}
		if(flag==1){
			alert("성공적으로 글을 작성했습니다.");
		}else if(flag==2){
			alert("성공적으로 글을 수정했습니다.");
		}else if(flag==3){
			alert("성공적으로 글을 삭제했습니다.");
		}else if(flag==4){
			alert('댓글을 추가 하였습니다.');
			location.href = "ReViewDetailSelect?currentPage="+currentPage+"&idx=${idx}";
			return;
		}
		location.href = "ReViewBoardSelect?currentPage="+currentPage;
	}
</script>
</head>
<body>

</body>
</html>