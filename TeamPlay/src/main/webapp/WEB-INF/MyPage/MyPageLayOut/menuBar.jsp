<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<div class="container_fluid col-xs-12" style="
    padding-bottom: 80px;
">
	<div class="row affix-row">
	    <div class="col-sm-3 col-md-2 affix-sidebar">
			<div class="sidebar-nav">
	  <div class="navbar navbar-default" role="navigation" style="width: 225px;">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-navbar-collapse">
	      <span class="sr-only">Toggle navigation</span>
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
	      </button>
	      <span class="visible-xs navbar-brand">Sidebar menu</span>
	    </div>
	    <div class="navbar-collapse collapse sidebar-navbar-collapse" style="text-align: left; width: 242px;">
	      <ul class="nav navbar-nav" id="sidenav01">
	        <li class="active">
	          <a href="#" data-toggle="collapse" data-target="#toggleDemo0" data-parent="#sidenav01" class="collapsed">
	          <h4 style="color: white">
	          <span class="glyphicon glyphicon-user"></span>&nbsp;내 정보
	          <br>
	          <small>${sessionScope.session_id} 님<span class="caret"></span></small>
	          </h4>
	          </a>
	          <div class="collapse" id="toggleDemo0" style="height: 0px;">
	            <ul class="nav nav-list">
	              <li><a href="MyEditViewPasswordCheckDo">개인정보 변경</a></li>
	              <li><a href="#">비밀번호 변경</a></li>
	              <li><a href="#">결제수단 변경</a></li>
	              <li><a href="#">배송지 관리</a></li>
	            </ul>
	          </div>
	        </li>
	        <li>
	          <a href="#" data-toggle="collapse" data-target="#toggleDemo" data-parent="#sidenav01" class="collapsed">
	          <span class="glyphicon glyphicon-cloud"></span> 게시글 관리 <span class="caret pull-right"></span>
	          </a>
	          <div class="collapse" id="toggleDemo" style="height: 0px;">
	            <ul class="nav nav-list">
	              <li><a href="#">판매 상품</a></li>
	              <li><a href="MyListViewPage">내가 올린 리뷰</a></li>
	              <li><a href="MyQnAviewPageDo">1:1 상담</a></li>
	            </ul>
	          </div>
	        </li>
	        <li><a href="MyBaguniViewDo"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;장바구니</a></li>
	        <li><a href="MyPointSelect"><span class="glyphicon glyphicon-copyright-mark"></span> 포인트 내역 <span class="badge pull-right">${sessionScope.session_point}</span></a></li>
	        <li><a href="MyClientWithdrawalDo"><span class="glyphicon glyphicon-cog"></span> 회원 탈퇴</a></li>
	      </ul>
	      </div><!--/.nav-collapse -->
	    </div>
	  </div>
		</div>
</html>