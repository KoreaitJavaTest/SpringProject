<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html>

<jsp:include page="../Layout/header.jsp"></jsp:include>
<c:set var="list" value="${ReViewList.list}"/>
<jsp:useBean id="date" class="java.util.Date"/>
<script type="text/javascript">
//리뷰글 검색 JS
$(function() {
	$('#ReViewSearch').click(function() {
		var searchName = $("select option:selected").val();
		var searchText = $('input[name=searchText]').val();
		if(searchText.trim().length==0){
			alert('검색어를 입력해 주세요');
		}else{
			location.href='ReViewSearch?searchName='+searchName+'&searchText='+searchText;
		}
	})
})
</script>
<style>
.searchLine{
	margin-bottom: 5px;
	margin-top: 1px;
}
.insertBtn{
	position:relative;
	left:100px;
}
.RETitle{
	color:black;
}
	
.RETitle:hover{
	color:black;
	text-decoration: none;
}
.media-carousel .carousel-control.right 
{
  right: 0px !important;
  background-image: none;
  background: none repeat scroll 0 0 #222222;
  /* border: 4px solid #FFFFFF; */
  border-radius: 23px 23px 23px 23px;
  height: 40px;
  width : 40px;
  margin-top: 30px;
}
</style>
<c:set var="list" value="${ReViewList.list}"/>

    <div class="container">
		<div class="row" style="margin-left: 20px;">
			<c:if test="${NoSearch==true}">
				<h3>검색한 글이 존재 하지 않습니다.</h3>
			</c:if>
			<c:forEach var="vo" items="${list}">
				<c:set var="imgN" value="${fn:split(vo.RE_imgNames,',')}"/>
			  <div class="col-xs-2 col-sm-6 col-md-4" style="height: 560px; width: 360px;" align="center">
			    <div class="thumbnail" style="width: 320px; height: 548px;">
			    	<a href="ReViewDetailSelect?idx=${vo.RE_idx}&currentPage=${ReViewList.currentPage}&flag=1">
				        <img src="http://localhost:8010/korea/upload/${imgN[0]}" alt="${imgN[0]}" style="width: 300px;height: 300px;">
			    	</a>
			      <div class="caption" style="height: 243px;">
			      <div>
			      	<a class="RETitle" href="ReHitUp.nhn?idx=${vo.RE_idx}&currentPage=${ReViewList.currentPage}">
				        <h4 style="margin-bottom: 5px; display: inline-block; word-break:break-all">${vo.RE_title}
				        	<c:if test="${date.year == vo.RE_writeDate.year && date.month == vo.RE_writeDate.month && date.date == vo.RE_writeDate.date}">
								<span class="label label-default">New</span>
							</c:if>
				        </h4>
			      	</a>
			      </div>
			        <hr size="1" style="margin-top: 5px;margin-bottom: 5px;"/>
			        <p style="width:100%; word-break:break-all">${vo.RE_content}</p>
			        <p style=" position: relative; top: 30%;"><a href="#" class="btn btn-primary" role="button" onclick="location.href='ReViewDetailSelect?idx=${vo.RE_idx}&currentPage=${ReViewList.currentPage}&flag=1'">리뷰보기</a> 
			       	<!-- 재훈이꺼랑 연동하기 -->
			        <a href="#" class="btn btn-default" role="button">상품보기</a></p>
			      </div>
			    </div>
			  </div>
			</c:forEach>
		</div>
		<hr size="2" align="center"/>		
		<div class="row">
			<div align="center">
				<c:if test="${ReViewList.currentPage > 1}">
					<input class="button button1" type="button" value="맨앞" title="첫 번째 페이지로 이동" onclick="location.href='?currentPage=1'"/>
				</c:if>
				<c:if test="${ReViewList.currentPage <= 1}">
					<input class="button button2" type="button" value="맨앞" disabled="disabled" title="이미 첫 번째 페이지 입니다."/>
				</c:if>
				
				<c:if test="${ReViewList.startPage > 1 }">
					<input class="button button1" type="button" value="이전" title="이전 10페이지로 이동" 
							onclick="location.href='?currentPage=${ReViewList.currentPage - ReViewList.pageSize}'"/>
				</c:if>
				<c:if test="${ReViewList.startPage <= 1 }">
					<input class="button button2" type="button" value="이전" disabled="disabled" title="이미 첫 번째 10페이지 입니다."/>			
				</c:if>
				
				<c:forEach var="i" begin="${ReViewList.startPage}" end="${ReViewList.endPage}" step="1">
					<c:if test="${ReViewList.currentPage == i}">
						<input class="button button2" type="button" value="${i}" disabled="disabled"/>
					</c:if>
					<c:if test="${ReViewList.currentPage != i}">
						<input class="button button1" type="button" value="${i}" onclick="location.href='?currentPage=${i}'"/>
					</c:if>
				</c:forEach>
				
				<c:if test="${ReViewList.endPage < ReViewList.totalPage}">
					<input class="button button1" type="button" value="다음" title="다음 10페이지로 이동" 
							onclick="location.href='?currentPage=${ReViewList.endPage + 1}'"/>
				</c:if>
				<c:if test="${ReViewList.endPage >= ReViewList.totalPage}">
					<input class="button button2" type="button" value="다음" disabled="disabled" title="이미 마지막 10페이지 입니다."/>
				</c:if>
				
				<c:if test="${ReViewList.currentPage < ReViewList.totalPage}">
					<input class="button button1" type="button" value="맨뒤" title="마지막 페이지로 이동" 
							onclick="location.href='?currentPage=${ReViewList.totalPage}'"/>
				
				</c:if>
				<c:if test="${ReViewList.currentPage >= ReViewList.totalPage}">
					<input class="button button2" type="button" value="맨뒤" disabled="disabled" title="이미 마지막 페이지 입니다."/>			
				</c:if>
			</div>
		</div>
		<hr size="2">
		<div class="row">
			<div align="center">
				<select class="form-control" style="width: 100px; display: inline-block;" name="searchName">
					<option value="제목">제목</option>
					<option value="작성자">작성자</option>
					<option value="제목작성자">제목+작성자</option>
				</select>
					<input type="text" class="form-control" style="width: 300px; display: inline-block;" name="searchText">
					<input type="button" class="btn btn-primary searchLine" value="검색하기" id="ReViewSearch">
				<div align="right">
				<c:choose>
					<c:when test="${sessionScope.session_id != null }">
						<input type="button" class="btn btn-default searchLine insertBtn" value="리뷰글 작성하기" onclick="location.href='ReViewInsert'">
					</c:when>
					<c:otherwise>
						<input type="button" class="btn btn-default searchLine insertBtn"  value="리뷰글 작성하기" onclick="alert('로그인 후 작성하실 수 있습니다!');location.href='LoginViewDo'">
					</c:otherwise>
				</c:choose>
				</div>
			</div>


		</div>
	</div>


<jsp:include page="../Layout/footer.jsp"></jsp:include>
					
	

	