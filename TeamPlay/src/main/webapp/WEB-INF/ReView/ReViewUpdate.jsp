<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../Layout/header.jsp"></jsp:include>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
// var btnCount = 1 ;
var btnCount = ${vo.RE_img};	//업뎃단에 들어오자마자 유저가 올린 이미지 파일 개수로 초기화
var buffer = "${buffer}";
var fileTagName = buffer.split(',');
$(function() {					//업뎃단이 뜨자마자 btnCount 에 맞게 파일 태그와 , 이미지 파일 뜨게
	if(btnCount>1){
		for (var i = 2; i <= btnCount; i++) {
			$('#file').append('<div class="origin"><input type="file" class="file" id="'+i+'"  name="'+i+'"/><span>기존파일 :'+fileTagName[i-1]+'</span></div>');
			$('.clthumbnail').eq(i-1).css('display','block');
		}
	}
	$('#divfile').append('<span>기존파일:'+fileTagName[0]+'</span>')
// 	console.log('${fileName1}')
})

function imgPlus() {
	btnCount++;
	if(btnCount<4){//id를 주고  onsubmit할시 함수한번실행(file을 체크하는) $('#1,2,3') value가 null이면 return false하게 만들어주면된다.
		$('#file').append('<div><input type="file" class="file" id="'+btnCount+'"  name="'+btnCount+'"/></div>');
		$('.clthumbnail').eq(btnCount-1).css('display','block');
	}else{
		alert('최대3개');
		btnCount=3;
	}
}
function imgMinus() {
	if(btnCount==1){
		alert('더이상 삭제할 수 없습니다.')
	}else{
// 	$('input[type=file]:last-child').remove();
	$('#file>div').last().remove();
	$('input[name=fileName'+btnCount+']').remove();
	btnCount--;
	$('.clthumbnail').eq(btnCount).css('display','none');	// 주의 eq(index)
	$('.userimg').eq(btnCount).attr('src'," ");
	}
}
</script>
<style>
.clthumbnail{
	margin-left: 180px;
	margin-right: 180px; 
	height: 300px;
}
.file{
	margin: 0 0 0 0;
}
.origin{
	display: inline-block;
}
.userimg{
	margin-left: 20px;
	margin-right: 20px; 
}
thumbnail a>img, .thumbnail>img {
    margin-right: 0;
    margin-left: 0;
    width: 100%;
    height: 100%;

}

</style>
<div class="container" align="center" style="margin-top: 30px;">
	<form action="ReViewUpdateOK" method="post" enctype="multipart/form-data">
		<table class="table table-bordered table-hover">
			<tr align="center" >
				<th colspan="3">리뷰 글 작성</th>
			</tr>
			<tr>
				<td colspan="1" style="width: 15%;">
					<label for="tit">
						제목
					</label>
				</td>
				<td colspan="2">
					<input type="text" id="tit" name="title" placeholder="제목을 입력하세요" required="required" style="width: 95%" value="${vo.RE_title}">
				</td>
			</tr>
			<tr>
				<td>
					<label for="content">
						내용
					</label>
				</td>
				<td colspan="2">
					<textarea id="content" name="content" rows="15" cols="100" style="resize: none; width: 95%;" placeholder="내용을 입력하세요" >${vo.RE_content}</textarea>
				</td>
			</tr>
			<tr>
				<td>
					<span onclick="imgPlus()">&nbsp;+&nbsp;</span>
					&nbsp;&nbsp;이미지 업로드&nbsp;&nbsp;
					<span onclick="imgMinus()">&nbsp;-&nbsp;</span>
				</td>
				<td id="file">
				<div id="divfile">
					<input type="file" class="file" name="1" id="1"/>
				</div>
				</td>
				<script>
					$('#1').change(function() {
						$('input[name=fileName1]').remove()
						var getList = this.files;
						var reader = new FileReader();
						reader.readAsDataURL(getList[0]);
						reader.onload = function() {
							$('#img1').attr('src',reader.result);
							$('#img1').parent().css('display','block');
						}
					})
					$(document).on('change',"#2",function(){
						$('input[name=fileName2]').remove()
// 						console.log($('input[name=fileName2]').val())
						var getList = this.files;
						var reader = new FileReader();
						reader.readAsDataURL(getList[0]);
						reader.onload = function() {
							$('#img2').attr('src',reader.result);
							$('#img2').parent().css('display','block');
						}
					})
					$(document).on('change',"#3",function(){
						$('input[name=fileName3]').remove()						
						var getList = this.files;
						var reader = new FileReader();
						reader.readAsDataURL(getList[0]);
						reader.onload = function() {
							$('#img3').attr('src',reader.result);
							$('#img3').parent().css('display','block');
						}
					})
				</script>
				<td style="width: 100px;">
				카테고리
					<select name="categoryDetail">
						<c:if test="${vo.RE_categoryDetail eq '나이키'}"><option value="나이키" selected="selected">나이키</option></c:if>
						<option value="나이키">나이키</option>
						<c:if test="${vo.RE_categoryDetail eq '아디다스'}"><option value="아디다스" selected="selected">아디다스</option></c:if>
						<option value="아디다스">아디다스</option>
						<c:if test="${vo.RE_categoryDetail eq 'New Balance'}"><option value="New Balance" selected="selected">New Balance</option></c:if>
						<option value="New Balance">New Balance</option>
						<c:if test="${vo.RE_categoryDetail eq '나이키'}"><option value="나이키" selected="selected">나이키</option></c:if>
						<option value="Doctor Martins">Doctor Martins</option>
						<c:if test="${vo.RE_categoryDetail eq 'Doctor Martins'}"><option value="Doctor Martins" selected="selected">Doctor Martins</option></c:if>
						<option value="Doctor Martins">Doctor Martins</option>
						<c:if test="${vo.RE_categoryDetail eq 'SoDa'}"><option value="SoDa" selected="selected">SoDa</option></c:if>
						<option value="SoDa">SoDa</option>
						<c:if test="${vo.RE_categoryDetail eq '고세'}"><option value="고세" selected="selected">고세</option></c:if>
						<option value="고세">고세</option>
						<c:if test="${vo.RE_categoryDetail eq '조던'}"><option value="조던" selected="selected">조던</option></c:if>
						<option value="조던">조던</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div class='col-md-12 container'>
				      <div class="carousel media-carousel" id="media">
				        <div class="carousel-inner">
				          <div class="item  active">
				            <div class="row">
				            <div class="col-md-12">
				                <a class="thumbnail clthumbnail" style="display: block" href="" data-toggle="modal" data-target="#myModal1">
				                <img alt=""  class="userimg" id="img1"  src="${fileName1}"></a>
				                <a class="thumbnail clthumbnail" style="display: none;" href="" data-toggle="modal" data-target="#myModal2">
				                <img alt=""  class="userimg" id="img2"  src='
				                <c:if test="${fileName2==null}"></c:if>
				                <c:if test="${fileName2!=null}">${fileName2}</c:if>
				                '></a>
				                <a class="thumbnail clthumbnail" style="display: none;" href="" data-toggle="modal" data-target="#myModal3">
				                <img alt=""  class="userimg" id="img3"  src="
				                <c:if test="${fileName3==null}"></c:if>
				                <c:if test="${fileName3!=null}">${fileName3}</c:if>
				                "></a>
								<!-- Modal -->
				              </div>
				            </div>
				          </div>
        				</div>
			      	  </div>                          
			    	</div>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="submit" value="수정하기">			
					<input type="button" value="돌아가기" onclick="history.back()">			
				</td>
			</tr>
		</table>
		<input type="hidden" value="${vo.RE_idx}" name="idx">
		<input type="hidden" value="${currentPage}" name="currentPage">
		<c:if test="${fileName1!=null}">
			<input type="hidden" value="${fileName1}" name="fileName1">
		</c:if>
		<c:if test="${fileName2!=null}">
			<input type="hidden" value="${fileName2}" name="fileName2">
		</c:if>
		<c:if test="${fileName3!=null}">
			<input type="hidden" value="${fileName3}" name="fileName3">
		</c:if>
	</form>
</div>
<jsp:include page="../Layout/footer.jsp"></jsp:include>

