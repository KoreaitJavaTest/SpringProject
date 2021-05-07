<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<jsp:include page="../Layout/header.jsp"></jsp:include>
<script type="text/javascript">
	function SearchIdByEmail(){
		window.screen.width = 1920;
		// 좌우 크기 반환 Ex) 1920
		window.screen.height = 1080;
		// 상하 크기 반환, Ex) 1080
		var popupWidth = 300;
		var popupHeight = 100;
		
		var popupX = (window.screen.width / 2) - (popupWidth / 2);
		// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음
		
		var popupY= (window.screen.height / 2) - (popupHeight / 2);
		// 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음
		window.open("SearchIdByEmailDo", "아이디찾기", "width=300, height=100, left= "+popupX+",top ="+popupY+",toolbar=no, menubar=no, scrollbars=no, resizable=yes" ); 
	}
	function SearchPwByEmail(){
		window.screen.width = 1920;
		// 좌우 크기 반환 Ex) 1920
		window.screen.height = 1080;
		// 상하 크기 반환, Ex) 1080
		var popupWidth = 300;
		var popupHeight = 100;
		
		var popupX = (window.screen.width / 2) - (popupWidth / 2);
		// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음
		
		var popupY= (window.screen.height / 2) - (popupHeight / 2);
		// 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음
		window.open("SearchPwViewDo", "아이디찾기", "width=300, height=200, left= "+popupX+",top ="+popupY+",toolbar=no, menubar=no, scrollbars=no, resizable=yes" ); 
	}
</script>
<style>
    body {
        background: #f8f8f8;
        padding: 60px 0;
    }
    
    #login-form > div {
        margin: 15px 0;
    }

</style>
<div class="container" align = "center">
    <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-success">
            <div class="panel-heading">
                <div class="panel-title"> <br/>아이디 비밀번호 찾기</div>
            </div>
            <div class="panel-body">
                <input type = "button" value = "아이디찾기" onclick = "SearchIdByEmail()">
                <input type = "button" value = "비밀번호찾기" onclick = "SearchPwByEmail()">
            </div>
        </div>
    </div>
</div>

<jsp:include page="../Layout/footer.jsp"></jsp:include>
	