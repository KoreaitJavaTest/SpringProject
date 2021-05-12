<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<jsp:useBean id="date" class="java.util.Date"/>
<script src='<c:url value="/resources/JS/jquery-3.6.0.min.js"/>'></script>

<script type="text/javascript">
$(function() {
	var flag = '${succees}';
	console.log(flag);
		$('li[class=active]').attr('class','');
		$('li[id=userMange]').attr('class','active');
		
		if(flag=="admincheck"){
			alert('포인트 적립이 정상적으로 처리 되었습니다');
		}
})
function boxcheck(size) {
	var cnt = 0;
	for (var i = 0; i < size; i++) {
		if($('input[name=cbox]').eq(i)[0].checked==true){
			cnt++;
			}
		}
	if(cnt==0){
		alert('체택할 리뷰 게시물을 체크해 주세요!')
 		return false;		
	}	
	return true;			
}
</script>
<jsp:include page="./AdminLayOut/AdminNav.jsp"></jsp:include>
	<div class="col-md-12 well">
	    <div class="panel panel-primary">
	        <div class="panel-heading">
	            <h3 class="panel-title">
	                <span class="glyphicon glyphicon-bookmark"></span> 리뷰 채택 (채택시 30Point씩 지급후 댓글알림)</h3>
	        </div>
	         <div class="panel-body" style="padding: 0px;"> 
<c:set var="list" value="${ReViewList.list}"/>	
<c:set var="size" value="${fn:length(ReViewList.list)}"/>    

	                <!-- 메인글시작 -->
		<div class="col-xs-12 col-md-12" style="padding: 0 0 0 0;">
	<table id="mytable" class="table table-bordred table-striped table-hover" style="margin-bottom: 0px;">
            		<tr>
	                   <th class="text-center" style="width: 5%;"><input type="checkbox" id="checkall" /></th>
	                   <th class="text-center" style="width: 10%;">글번호</th>
	                   <th class="text-center" style="width: 10%;">글쓴이</th>
	                   <th class="text-center" style="widows: 20%;">제목</th>
	                   <th class="text-center" style="width: 20%;">작성일</th>
	                   <th class="text-center" style="width: 8%;">조회수</th>
	                 </tr>
    <tbody>
	    <c:choose>
			<c:when test="${list.size()==0}">
					<tr>
						<td colspan="9"><marquee>테이블에 저장된 글이 없습니다.</marquee></td>
					</tr>
				</c:when>
				<c:otherwise>
				<form action="AdminReViewSelectionOK" method="post" onsubmit="return boxcheck('${size}')">
					<c:forEach var="ReView" items="${list}">
						<tr style="cursor: pointer;">
							<td align="center"><input type="checkbox" class="." name="cbox" value="${ReView.RE_idx}"/></td>
							<td align="center">${ReView.RE_idx} <input type="hidden" value="${ReView.RE_userId}" name="ReViewId"></td>
							<td align="center">${ReView.RE_userId}</td>
							<td align="center">${ReView.RE_title}</td>
							<td align="center">
								<c:if test="${date.year == ReView.RE_writeDate.year && date.month == ReView.RE_writeDate.month && date.date == ReView.RE_writeDate.date}">
									<fmt:formatDate value="${ReView.RE_writeDate}" pattern="a h:mm" />
								</c:if> 
								<c:if test="${date.year != ReView.RE_writeDate.year || date.month != ReView.RE_writeDate.month || date.date != ReView.RE_writeDate.date}">
									<fmt:formatDate value="${ReView.RE_writeDate}" pattern="yyyy.MM.dd(E)" />
								</c:if>
							</td>
							<td align="center">
									${ReView.RE_hit}
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
		</c:choose>
	    <tr>
			<td colspan="9" align="center">
				<c:if test="${ReViewList.currentPage > 1}">
					<input class="btn btn-default" type="button" value="맨앞" title="첫 번째 페이지로 이동" onclick="location.href='?currentPage=1'" />
				</c:if> 
				<c:if test="${ReViewList.currentPage <= 1}">
					<input class="btn btn-default" type="button" value="맨앞" disabled="disabled" title="이미 첫 번째 페이지 입니다." />
				</c:if> 
				<c:if test="${ReViewList.startPage > 1 }">
					<input class="btn btn-default" type="button" value="이전" title="이전 10페이지로 이동" onclick="location.href='?currentPage=${ReViewList.currentPage - ReViewList.pageSize}'" />
				</c:if> 
				<c:if test="${ReViewList.startPage <= 1 }">
					<input class="btn btn-default" type="button" value="이전" disabled="disabled" title="이미 첫 번째 10페이지 입니다." />
				</c:if>
				<c:forEach var="i" begin="${ReViewList.startPage}" end="${ReViewList.endPage}" step="1">
					<c:if test="${ReViewList.currentPage == i}">
						<input class="btn btn-default" type="button" value="${i}" disabled="disabled" />
					</c:if>
					<c:if test="${ReViewList.currentPage != i}">
						<input class="btn btn-default" type="button" value="${i}" onclick="location.href='?currentPage=${i}'" />
					</c:if>
				</c:forEach> 
					<c:if test="${ReViewList.endPage < ReViewList.totalPage}">
					<input class="btn btn-default" type="button" value="다음" title="다음 10페이지로 이동" onclick="location.href='?currentPage=${ReViewList.endPage + 1}'"/>
				</c:if> 
				<c:if test="${ReViewList.endPage >= ReViewList.totalPage}">
					<input class="btn btn-default" type="button" value="다음" disabled="disabled" title="이미 마지막 10페이지 입니다." />
				</c:if> 
				<c:if test="${ReViewList.currentPage < ReViewList.totalPage}">
					<input class="btn btn-default" type="button" value="맨뒤" title="마지막 페이지로 이동" onclick="location.href='?currentPage=${ReViewList.totalPage}'" />
				</c:if> 
				<c:if test="${ReViewList.currentPage >= ReViewList.totalPage}">
					<input class="btn btn-default" type="button" value="맨뒤" disabled="disabled" title="이미 마지막 페이지 입니다." />
				</c:if>
				<span style="float: right; margin-top: 2px;">
				<input type="submit" class="btn btn-sm btn-success" value="채택하기">
				</span>
				</form>
			</td>
		</tr>
    </tbody>
</table>		
</div>
	                <!-- 메인글 끝 -->
	          
<jsp:include page="./AdminModal.jsp"></jsp:include>
	                
<jsp:include page="./AdminLayOut/AdminNavEnd.jsp"></jsp:include>

</html>