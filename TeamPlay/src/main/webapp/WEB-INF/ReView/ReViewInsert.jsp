<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
var btnCount = 1 ;
function imgPlus() {
	btnCount++;
	if(btnCount<4){//id를 주고  onsubmit할시 함수한번실행(file을 체크하는) $('#1,2,3') value가 null이면 return false하게 만들어주면된다.
		$('#file').append('<input type="file" id="'+btnCount+'" name="'+btnCount+'" onchange="setThumbnail(event,'+btnCount+')"/>');	
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
	$('input[name='+btnCount+']').remove();
	btnCount--;
	$('.clthumbnail > img').eq(btnCount).parent().css('display','none');
	$('.clthumbnail > img').eq(btnCount).attr('src',"");
	}
}
function test(){	//Test
	   var test = document.getElementsByName("1")[0].value;
	   if(test == ""){
	      alert("사진을 등록해주세요");
	      return false;
	   }
	   if(test != ""){
	      return true;
	   }
	}

</script>
<jsp:include page="../Layout/header.jsp"></jsp:include>
<div class="container" align="center" style="margin-top: 30px;">
	<form action="ReViewInsertOK" method="post" enctype="multipart/form-data">
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
					<input type="text" id="tit" name="title" placeholder="제목을 입력하세요" required="required" style="width: 95%">
				</td>
			</tr>
			<tr>
				<td>
					<label for="content">
						내용
					</label>
				</td>
				<td colspan="2">
					<textarea id="content" name="content" rows="15" cols="100" style="resize: none; width: 95%;" placeholder="내용을 입력하세요" ></textarea>
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
						<input type="file" name="1"  id="1" onchange="setThumbnail(event,1)">
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
				<td>
				카테고리
					<select name="categoryDetail">
						<option value="나이키">나이키</option>
						<option value="아디다스">아디다스</option>
						<option value="New Balance">New Balance</option>
						<option value="Doctor Martins">Doctor Martins</option>
						<option value="SoDa">SoDa</option>
						<option value="TenD">TenD</option>
						<option value="고세">고세</option>
						<option value="조던">조던</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="3" >
					<div class='col-md-12 container'>
				      <div class="carousel media-carousel" id="media">
				        <div class="carousel-inner">
				          <div class="item  active">
				            <div class="row">
				            <div class="col-md-12">
				                <a class="thumbnail clthumbnail" style="display: none;" href="" data-toggle="modal" data-target="#myModal1">
				                <img alt=""  class="userimg" id="img1"  src=""></a>
				                <a class="thumbnail clthumbnail" style="display: none;" href="" data-toggle="modal" data-target="#myModal2">
				                <img alt=""  class="userimg" id="img2"  src=''></a>
				                <a class="thumbnail clthumbnail" style="display: none;" href="" data-toggle="modal" data-target="#myModal3">
				                <img alt=""  class="userimg" id="img3"  src=""></a>
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
					<input type="submit" value="저장하기">			
					<input type="button" value="돌아가기">			
				</td>
			</tr>
		</table>
	</form>

</div>
<jsp:include page="../Layout/footer.jsp"></jsp:include>

