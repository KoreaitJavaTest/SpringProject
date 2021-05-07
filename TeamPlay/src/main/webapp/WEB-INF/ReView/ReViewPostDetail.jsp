<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>

<jsp:include page="../Layout/header.jsp"></jsp:include>
<style>
.setImg{
	height: 200px;	
}

.media-carousel 
{
  margin-bottom: 0;
  padding: 0 40px 0px 40px;
  margin-top: 10px;
}
/* Previous button  */
.media-carousel .carousel-control.left 
{
  left: -12px;
  background-image: none;
  background: none repeat scroll 0 0 #222222;
  border: 4px solid #FFFFFF;
  border-radius: 23px 23px 23px 23px;
  height: 40px;
  width : 40px;
  margin-top: 30px
}
/* Next button  */
.media-carousel .carousel-control.right 
{
  right: -12px !important;
  background-image: none;
  background: none repeat scroll 0 0 #222222;
  border: 4px solid #FFFFFF;
  border-radius: 23px 23px 23px 23px;
  height: 40px;
  width : 40px;
  margin-top: 30px
}
/* Changes the position of the indicators */
.media-carousel .carousel-indicators 
{
  right: 50%;
  top: auto;
  bottom: 0px;
  margin-right: -19px;
}
/* Changes the colour of the indicators */
.media-carousel .carousel-indicators li 
{
  background: #c0c0c0;
}
.media-carousel .carousel-indicators .active 
{
  background: #333333;
}
.media-carousel img
{
  width: 250px;
  height: 150px
}
.like{
	width: 25px;height: 22px;
}
</style>
<script type="text/javascript">

var voIdx = ${vo.RE_idx}	//해당게시글의 번호
var CoIdx = 0;				//삭제하기 버튼누른후 초기화될 댓글번호

$(document).ready(function() {
	  $('#media').carousel({
	    pause: true,
	    interval: false,
	  });
$('.dropdown-toggle').dropdown();

// $('commentUpdate').click(function(this) {
// 	console.log(this.children().eq(0).val());
// })
	});
function commentCheck() {
	var session_id = '${sessionScope.session_id}';
	var context = $('#context').val();
	
// 	console.log(context.trim().length);
	if(session_id==''){
		alert('로그인 후 작성해주세요.');
		location.href='LoginView.nhn'
		return false;
	}else if(context.trim().length==0){
		alert('댓글을 작성해 주세요.');
		return false;
	}
	return true;

}
function commentUpdateForm(obj) {
	var idx =  obj.children[0].value;
	var userId = obj.children[1].value;
	var content = obj.children[2].value;
	if(userId.trim()=='${sessionScope.session_id}'){
		$('#'+idx+'').empty();
		$('#'+idx+'').append("<td colspan=3><input type='text' name='content2' value='"+content+"' style='width:1100px;margin-top: 4px;'/>"+
								"</td>");
		$('#'+idx+'').append("<td>"+
							"<input type='button' class='btn btndefault test' value='수정하기' onclick='test("+idx+")'></td>");

	}else{
		alert('댓글 작성자가 아닙니다!');
	}
}

function test(idx) {
	var Commentidx = idx;		//댓글번호
	var content = $('input[name=content2]').val()
	var PageIdx = ${vo.RE_idx};
	location.href = 'updateComment.nhn?Commentidx='+Commentidx+'&content='+content+'&idx='+PageIdx;
	
}
function idxCommit(idx) {			//idx : 댓글 번호 idx 
	CoIdx =idx;						//댓글 삭제하기 누름동시에 버튼초기화
}
function commentDelete() {
	console.log("삭제할 댓글 글번호 : "+CoIdx);
	console.log("댓글을 가진 게시글번호 : "+voIdx);
	location.href = 'commentDelete.nhn?idx='+voIdx+'&commentIdx='+CoIdx;
}//AJAX로 하자!

function like(flag){
	var flag=flag;
// 	console.log(flag);
	$.ajax({
		type:"POST",
		url:"./likeCheck.nhn",
		data:{
			userId:"${sessionScope.session_id}",
			idx:"${vo.RE_idx}",
			checkFlag:flag
		},
		success: function(meg){
			alert('t')
			location.reload();
		},error: function(){
			alert("failed!");
		}
	});
}

</script>
<jsp:include page="/WEB-INF/ReView/ReViewModal.jsp"></jsp:include>
<c:set var="goodCheckUsers" value="${fn:split(vo.RE_goodCheckUser,',')}"/>
<div class="container" style="margin-top: 50px;">
	<table align="center" class="table table-hover">
		<thead>
			<tr>
				<th>상세보기</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td width="10%">제목</td>
				<td>${vo.RE_title} Test:${goodCheckUsers[0]}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${vo.RE_userId}</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td><fmt:formatDate value="${vo.RE_writeDate}" pattern="yyyy.MM.dd(E)"/></td>
			</tr>
			<tr>
				<td colspan="2" height="200">
				<!-- 상품이 보여질 테이블 행 -->
				<div class='col-md-12 container'>
				      <div class="carousel media-carousel" id="media">
				        <div class="carousel-inner">
				          <div class="item  active">
				            <div class="row">
				            <c:if test="${vo.RE_img==1}">
				              <div class="col-md-2">
				              </div>          
				              <div class="col-md-8">
				                <a class="thumbnail" href="" data-toggle="modal" data-target="#myModal1"><img alt="" style="height: 200px;" src="${fileName1}"></a>
								<!-- Modal -->
				              </div>          
				              <div class="col-md-2">
				              </div>          
				    		</c:if>
				            <c:if test="${vo.RE_img==2}">
				              <div class="col-md-6">
				                <a class="thumbnail" style="" href="" data-toggle="modal" data-target="#myModal2_1"><img alt="" style="height: 200px;" class="setImg" src="${fileName1}"></a>
				                <!-- Modal -->
				              </div>          
				              <div class="col-md-6">
				                <a class="thumbnail" style="" href="" data-toggle="modal" data-target="#myModal2_2"><img alt="" style="height: 200px;" class="setImg" src="${fileName2}"></a>
				                <!-- Modal -->
				              </div> 
				              
				                       
				    		</c:if>
				            <c:if test="${vo.RE_img==3}">
				              <div class="col-md-4">
				                <a class="thumbnail" style="" href="" data-toggle="modal" data-target="#myModal3_1"><img alt="" style="height: 200px;" class="setImg" src="${fileName1}"></a>
				                <!-- Modal -->
				              </div>          
				              <div class="col-md-4">
				                <a class="thumbnail" style="" href="" data-toggle="modal" data-target="#myModal3_2"><img alt="" style="height: 200px;" class="setImg" src="${fileName2}"></a>
				                <!-- Modal -->
				              </div>          
				              <div class="col-md-4">
				                <a class="thumbnail" style="" href="" data-toggle="modal" data-target="#myModal3_3"><img alt="" style="height: 200px;" class="setImg" src="${fileName3}"></a>
				                <!-- Modal -->
				              </div>          
				    		</c:if>  
				    		      
				            </div>
				          </div>
        				</div>
			      </div>                          
			    </div>
				<!-- td의끝 -->				
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>${vo.RE_content}</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" class="btn btn-default" value="목록보기" onclick="location.href='ReViewBoard.nhn?currentPage=${currentPage}'"/>
					<div class="dropdown" style="float: right;">
						<a class="dropdown-toggle" data-toggle="dropdown" data-target="#"><span class="glyphicon glyphicon-option-horizontal"></span></a>
						<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
							 <li role="presentation">
							 <c:choose>
							 	<c:when test="${sessionScope.session_id eq vo.RE_userId }">
							 	<a href="ReView/ReViewUpdate?idx=${vo.RE_idx}&currentPage=${currentPage}" role="menuitem" tabindex="-1">
							 		수정하기
							 	</a>
							 	</c:when>
							 	<c:otherwise>
<%-- 							 		<a href="ReView/ReViewUpdate?idx=${vo.RE_idx}&currentPage=${currentPage}" role="menuitem" tabindex="-1"> --%>
								 	<a href="#" role="menuitem" tabindex="-1" onclick="alert('작성자 아이디가 아닙니다!');location.href='LoginView.nhn'">
								 		수정하기
								 	</a>
							 	</c:otherwise>
							 </c:choose>
							 </li>
							 <li role="presentation">
								 <c:choose>
							 	<c:when test="${sessionScope.session_id eq vo.RE_userId }">
							 	<a href="ReView/ReViewDeleteOK?idx=${vo.RE_idx}&currentPage=${currentPage}&flag=detail" role="menuitem" tabindex="-1">
							 		삭제하기
							 	</a>
							 	</c:when>
							 	<c:when test="${sessionScope.session_id eq null }">
							 	<a href="ReView/ReViewDeleteOK?idx=${vo.RE_idx}&currentPage=${currentPage}&flag=detail" role="menuitem" tabindex="-1">
							 		삭제하기
							 	</a>
							 	</c:when>
							 	<c:otherwise>
<%-- 								 	<a href="ReViewDeleteOK?idx=${vo.RE_idx}&currentPage=${currentPage}&flag=detail" role="menuitem" tabindex="-1"> --%>
								 	<a href="#" role="menuitem" tabindex="-1" onclick="alert('작성자 아이디가 아닙니다!')location.href='LoginView.nhn'">
								 		삭제하기
								 	</a>
							 	</c:otherwise>
							 </c:choose>
							 </li>
						</ul>
					</div>
				</td>				
			</tr>
			<tr>
				
				<td colspan="2">
<!-- 				http://localhost:8009/korea/upload/img05.jpg -->
					<div style="display: inline-block; float: left;">
						<!-- 비 로그인일시 -->
						<c:if test="${sessionScope.session_id eq null }">
							<a onclick="alert('로그인후 이용해주세요.');location.href='LoginView.nhn'">
								<img class="like" src="http://localhost:8009/korea/upload/nolike.png" alt="좋아요안눌럿을때"/>
							</a>
						</c:if>
<!-- 						로그인됫을시 -->
						<c:if test="${sessionScope.session_id != null }">
								<c:choose>
									<c:when test="${fn:contains(vo.RE_goodCheckUser,sessionScope.session_id)}">
										<img class="like" src="http://localhost:8009/korea/upload/like.png" alt="좋아요눌럿을때" onclick="like('cancle')">
									</c:when>					
									<c:otherwise>
										<img class="like" src="http://localhost:8009/korea/upload/nolike.png" alt="좋아요안눌럿을때" onclick="like('check')">						
									</c:otherwise>
								</c:choose>
						</c:if>
					</div>
					<div  align="right" style="cursor: pointer; display: inline-block; float: right" onclick="location.href='ReViewReport.nhn?idx=${vo.RE_idx}&currentPage=${currentPage}'"><span class="glyphicon glyphicon-bullhorn"></span>신고하기</div>
				</td>
			</tr>				
		</tbody>
	</table>
</div>

<jsp:useBean id="date" class="java.util.Date"/>


<div class="container" style="margin-top: 3px;">
<form action="ReViewComment.nhn" method="get" onsubmit="return commentCheck()">
	<span>comment</span>
	   <div class="input-group">
	      <input type="text" class="form-control" placeholder="Search for..." id="context" name="content">
	      <input type="hidden" value="${vo.RE_idx}" name="refIdx">
	      <input type="hidden" value="${currentPage}" name="currentPage">
	      <span class="input-group-btn">
	        <button class="btn btn-default" type="submit" id="commentBtn">작성하기</button>
	      </span>
	    </div><!-- /input-group -->
</form>
	    
	    
	 <div class="row" style="margin-top: 5px;">
		<table class="table" style="margin-left: 5px;">
<!-- 			 foreach 구간 -->
		<c:forEach var="comment" items="${commentList.list}">
			<tr id="${comment.idx}">
				<td width="100" align="center">${comment.userId}</td>
				<td width="900">${comment.content}</td>	
				<td align="right">
					<c:if test="${date.year == comment.writedate.year && date.month == comment.writedate.month && date.date == comment.writedate.date}">
						<fmt:formatDate value="${comment.writedate}" pattern="a h:mm"/>
					</c:if>
					<c:if test="${date.year != comment.writedate.year || date.month != comment.writedate.month || date.date != comment.writedate.date}">
						<fmt:formatDate value="${comment.writedate}" pattern="yyyy.MM.dd(E)"/>
					</c:if>
				
				</td>
				<td>
					<div class="dropdown" style="float: right;">
						<a class="dropdown-toggle" style="color: black; cursor: pointer;" data-toggle="dropdown" data-target="#"><span class="glyphicon glyphicon-triangle-bottom" style="float: right;"></span></a>
						<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
							 <li role="presentation">
							 	<a role="menuitem" tabindex="-1" class="commentUpdate">
							 		<span class="commentUpdate" onclick="commentUpdateForm(this)">
							 			수정하기
							 			<input type="hidden" name="idx" value="${comment.idx}">
							 			<input type="hidden" name="userId" value="${comment.userId}">
							 			<input type="hidden" name="content" value="${comment.content}">
							 		</span>
							 	</a>
							 </li>
							 <li role="presentation">
							 	<c:if test="${sessionScope.session_id==comment.userId}">
								 	<a href="#" role="menuitem" onclick="idxCommit(${comment.idx})" tabindex="-1"  data-toggle="modal" data-target="#CommentDel">
								 		삭제하기
								 	</a>
							 	</c:if>
							 	<c:if test="${sessionScope.session_id!=comment.userId && sessionScope.session_id!=null}">
							 		<c:if test="${sessionScope.session_id==null}">
								 	<a href="#" role="menuitem" tabindex="-1" onclick="alert('해당 댓글 작성자가 아닙니다!')">
								 		삭제하기
								 	</a>
								 	</c:if>
							 	</c:if>
							 	<c:if test="${sessionScope.session_id==null}">
								 	<a href="#" role="menuitem" tabindex="-1" onclick="alert('로그인후 이용해주세요.');location.href='LoginView.nhn'">
								 		삭제하기
								 	</a>
							 	</c:if>
							 </li>
						</ul>
					</div>
				</td>	
			</tr>
		</c:forEach>
<!-- 				foreach..end 구간			 -->
		</table>
	 </div>
</div>
<div class="modal fade" id="CommentDel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">댓글 삭제</h4>
      </div>
      <div class="modal-body">
			해당댓글을 정말삭제 하시겠습니까?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" data-toggle="modal" data-target="#CommentDelOK"  >예</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">아니오</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="CommentDelOK" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">삭제완료!</h4>
      </div>
      <div class="modal-body">
			댓글 삭제를 완료 하였습니다
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="commentDelete()">확인</button>
      </div>
    </div>
  </div>
</div>

<jsp:include page="../Layout/footer.jsp"></jsp:include>