<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src='<c:url value="/resources/JS/jquery-3.6.0.min.js"/>'></script>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src='<c:url value="/resources/JS/AdminJS.js"/>'></script>
</head>
<style>
<style>
#mytable > th {
 text-align: center;
}
#mytable thead>tr>th, #mytable tbody>tr>th, #mytable tfoot>tr>th, #mytable thead>tr>td, #mytable tbody>tr>td, #mytable tfoot>tr>td{
	vertical-align: middle;
}
body { padding-top:20px; }
</style>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <ul class="nav nav-pills nav-stacked adminMenu">
                <li id="home" class="active"><a href="adminPage"><i class="fa fa-home fa-fw"></i>Home</a></li>
                <li id="userMange"><a href="AdminUserMangement"><i class="fa fa-list-alt fa-fw"></i>사용자관리</a></li>
                <li id="shopMange"><a href="http://www.jquery2dotnet.com"><i class="fa fa-file-o fa-fw"></i>상품관리</a></li>
                <li id="cashMange"><a href="AdminReViewSelection"><i class="fa fa-bar-chart-o fa-fw"></i>리뷰 채택</a></li>
                <li><a href="http://www.jquery2dotnet.com"><i class="fa fa-table fa-fw"></i>배송 관리</a></li>
                <li id="pointMange"><a href="http://www.jquery2dotnet.com"><i class="fa fa-tasks fa-fw"></i>포인트 관리</a></li>
                <li id="superList"><a href="http://www.jquery2dotnet.com"><i class="fa fa-calendar fa-fw"></i>공지사항 관리</a></li>
                <li><a href="http://www.jquery2dotnet.com"><i class="fa fa-book fa-fw"></i>###</a></li>
                <li><a href="http://www.jquery2dotnet.com"><i class="fa fa-pencil fa-fw"></i>###</a></li>
                <li><a href="http://www.jquery2dotnet.com"><i class="fa fa-cogs fa-fw"></i>###</a></li>
            </ul>
        </div>
	       <div class="col-md-9">
</body>
</html>