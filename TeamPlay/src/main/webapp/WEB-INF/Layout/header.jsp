<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="google-signin-client_id" content="986137111067-6blmfih4njnp4vsstfibtuv3t012lg8t.apps.googleusercontent.com">
<title>부트스트랩 웹 페이지</title>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.css"/>
<link rel="shortcut icon" href="<c:url value="/resources/images/favicon.ico"/>">
<link rel="icon" href="./images/me.png">
<link rel="stylesheet" href='<c:url value="/resources/css/mainPage.css"/>'>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.4/Chart.bundle.min.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script type="text/javascript">
function signOut() {
	  function signOut() {
		    var auth2 = gapi.auth2.getAuthInstance();
		    auth2.signOut().then(function () {
		      console.log('User signed out.');
		    });
		  }
}
function homeSignHidden(googleUser) {
	  var profile = googleUser.getBasicProfile();
}
</script>
</head>
<body style="margin-top: 0px;">
	<div style = "background-color: black;">
	</div>
	<nav class="navbar navbar-default">
		<div class="container-fluid" id="headerNav">
			<div class="navbar-header"> 
				<button type="button" id="togglebar" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
			<div style="cursor: pointer;" class="jumbotron" onclick = "location.href = 'index'">
				<h1 class="text-center">LUXMEA</h1>
				<p class="text-center">LUXMEA MAKES YOU SHINE</p>
			</div>
			<div class="collapse navbar-collapse navheigt" id="bs-example-navbar-collapse-1" style="padding-left: 80px;">
				<ul class="nav navbar-nav" style="background: black;" >
					<li class="nav-item">
						<a  class="nav-link jinho" href="ShopAllProduct">전체상품</a>
					</li>
					<li class="nav-item">
						<a  class="nav-link jinho" href="ShopInsertProduct">상품판매등록(임시)</a>
					</li>
					<li class="nav-item">
						<a class="nav-link jinho" href="ReViewBoard">리뷰게시판</a>
					</li>
					<li class="nav-item">
						<a class="nav-link jinho" href="QAboardDo">묻고답하기</a>
					</li>
					<li class="nav-item">
						<a class="nav-link jinho" href="index5.html">공지사항</a>
					</li>
					<li class="nav-item">
						<c:if test = "${sessionScope.session_id eq null }">
							<a class="nav-link" onclick="alert('로그인후 사용가능 합니다.');location.href='LoginViewDo'">마이페이지</a>
						</c:if>
						<c:if test = "${sessionScope.session_id != null }">
							<a class="nav-link" href="MyPageViewDo">마이페이지</a>
						</c:if>
					</li>
			<li>
				<span class="nav navbar-nav navbar-right" style="margin-left: 10px;">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle loginMenu"  data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
							접속하기<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<c:if test = "${sessionScope.session_id eq null }">
								<li><a  href="LoginViewDo" onclick="signOut()">로그인</a></li>
								<li><a href="SearchMyIdPwDo">아이디,비밀번호 찾기</a></li>
								<li><a href="JoinViewDo">회원가입</a></li>
							</c:if>
							<c:if test = "${sessionScope.session_id != null }">
								<li><div align="center">${sessionScope.session_id}님<br/>point: ${sessionScope.session_point}점</div></li>
								<li><div style="cursor: pointer;" align="center" onclick="signOut()">로그아웃</div></li>
								<input type="hidden" class="g-signin2" data-onsuccess="homeSignHidden">
								<c:if test="${sessionScope.session_level==1}">
									<li><div style="cursor: pointer;" align="center" onclick="location.href='adminPage'">관리자페이지</div></li>
								</c:if>
							</c:if>
						</ul>
					</li>
				</span>
			</li>
			</ul>
	</nav>