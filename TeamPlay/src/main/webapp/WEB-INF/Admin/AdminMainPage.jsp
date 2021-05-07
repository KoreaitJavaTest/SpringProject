<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style type="text/css">
body { padding-top:20px; }
.panel-body .btn:not(.btn-block) { width:120px;margin-bottom:10px; }
</style>
</head>
<body>
<jsp:include page="./AdminLayOut/AdminNav.jsp"></jsp:include>
	<div class="col-md-10 well">
	    <div class="panel panel-primary">
	        <div class="panel-heading">
	            <h3 class="panel-title">
	                <span class="glyphicon glyphicon-bookmark"></span> 퀵 메뉴</h3>
	        </div>
	         <div class="panel-body">          
	                <!-- 메인글시작 -->
	                    <div class="row">
	                        <div class="col-xs-6 col-md-6">
	                          <a href="#" class="btn btn-danger btn-lg" role="button"><span class="glyphicon glyphicon-list-alt"></span> <br/>게시글관리</a>
	                          <a href="#" class="btn btn-warning btn-lg" role="button"><span class="glyphicon glyphicon-bookmark"></span> <br/>Bookmarks</a>
	                          <a href="#" class="btn btn-primary btn-lg" role="button"><span class="glyphicon glyphicon-signal"></span> <br/>신고관리</a>
	                          <a href="#" class="btn btn-primary btn-lg" role="button"><span class="glyphicon glyphicon-comment"></span> <br/>리뷰답변</a>
	                        </div>
	                        <div class="col-xs-1 col-md-1">
	                        </div>
	                        <div class="col-xs-5 col-md-5">
	                          <a href="#" class="btn btn-success btn-lg" role="button"><span class="glyphicon glyphicon-user"></span> <br/>유저관리</a>
	                          <a href="#" class="btn btn-info btn-lg" role="button"><span class="glyphicon glyphicon-file"></span> <br/>공지사항</a>
	                          <a href="#" class="btn btn-primary btn-lg" role="button"><span class="glyphicon glyphicon-picture"></span> <br/>Photos</a>
	                          <a href="#" class="btn btn-primary btn-lg" role="button"><span class="glyphicon glyphicon-tag"></span> <br/>Tags</a>
	                        </div>
	                    </div>
	                    <a href="http://www.jquery2dotnet.com/" class="btn btn-success btn-lg btn-block" role="button"><span class="glyphicon glyphicon-globe"></span> Home</a>
	                </div>
	                
	                <!-- 메인글 끝 -->
	                
<jsp:include page="./AdminLayOut/AdminNavEnd.jsp"></jsp:include>
</body>
</html>