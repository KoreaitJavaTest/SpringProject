<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

//	submit 버튼이 눌렸을 경우 필수요건을 체크하는 메서드
	function check() {
		var flag = true;
		if(isNaN($('#input_price').val())) {
			alert('가격은 숫자로만 입력이 가능합니다.');
			$('#input_price').val("");
			$('#input_price').focus();
			flag = false;
		}
		if(isNaN($('#input_salePercent').val())) {
			alert('할인율은 숫자로만 입력이 가능합니다.');
			$('#input_salePercent').val("");
			$('#input_salePercent').focus();
			flag = false;
		}
		if($('#1').val() == '' && $('#2').val() == ''){
			alert("이미지는 최소 1장 이상입니다.");
			flag = false;
		}
		return flag;
	}
	
//	수정 전 이미지가 비어있을 경우에 파일이 변하는 것을 감지하는 메서드
	$(function(){		
		$('#1').on('change', function(){
			readURL(1, this);
		});
		
		$('#2').on('change', function(){
			readURL(2, this);
		});
	});
	
// 	파일이 변하면 실행하는 메서드
	function readURL(i, input) {
		var filename = input.files[0].name;
		var ext = filename.substr(filename.length-3,filename.length);
		// 파일 확장명 확인
		if(ext.toLowerCase() != "jpg" && ext.toLowerCase() != "gif" &&ext.toLowerCase() != "png") {
			alert("이미지 파일이 아닙니다. 다시 선택해주세요")
			input.value = "";
		} else{
			// 파일을 읽을 수 있는 객체
			var reader = new FileReader();
			
			// 선택한 파일 읽기
			reader.readAsDataURL(input.files[0]);
			
			// 파일을 전부 읽으면
			reader.onload = function() {
				if(i == 1){
					$('#imgDiv1').empty();			// 원래 있던 이미지를 지운다.
					$('#deleteButton1').remove();	// 파일 선택을 연속으로 할 경우 삭제버튼을 하나 삭제한다.
					$('#imgDiv1').append('<img id="img_1" class="pic" width="300px" height="300px">');	
					$('#buttonDiv1').append('<input id="deleteButton1" type="button" value="삭제하기" onclick="deleteImg(1)"/>');
					$('#img_1').attr('src', reader.result);	// 읽어들인 이미지url을 얻어와서 img_2에 src에 넣어준다.
				} else if(i == 2){
					$('#imgDiv2').empty();			// 원래 있던 이미지를 지운다.
					$('#deleteButton2').remove();	// 파일 선택을 연속으로 할 경우 삭제버튼을 하나 삭제한다.
					$('#imgDiv2').append('<img id="img_2" class="pic" width="300px" height="300px">');	
					$('#buttonDiv2').append('<input id="deleteButton2" type="button" value="삭제하기" onclick="deleteImg(2)"/>');
					$('#img_2').attr('src', reader.result);	// 읽어들인 이미지url을 얻어와서 img_2에 src에 넣어준다.
				}
			}
		}
	}
	
// 	이미지가 삭제됐을 때 실행되는 메서드
	function deleteImg(i) {
		if(i == 1) {
// 			alert('1번 이미지');
			$('#imgDiv1').empty();
			$('#buttonDiv1').empty();
// 			$('#h_1').remove();
			$('#imgDiv1').append('<img id="img_1" class="pic" width="300px" height="300px">');	
			$('#buttonDiv1').append('<input id="1" name="1" type="file"/>');
			
// 			삭제 버튼이 눌리고 난 후 이미지가 변하는 것을 감지
			$('#1').on('change', function(){
				readURL(1, this);
			});
			
		} else if(i == 2) {
// 			alert('2번 이미지');
			$('#imgDiv2').empty();
			$('#buttonDiv2').empty();
// 			$('#h_2').remove();
			$('#imgDiv2').append('<img id="img_2" class="pic" width="300px" height="300px">');	
			$('#buttonDiv2').append('<input id="2" name="2" type="file"/>');
			
// 			삭제 버튼이 눌리고 난 후 이미지가 변하는 것을 감지
			$('#2').on('change', function(){
				readURL(2, this);
			});
		}
	}

</script>

<jsp:include page="../Layout/header.jsp"></jsp:include>

<form action="ShopUpdateProductOK" method="post" onsubmit="return check()" enctype="multipart/form-data">

	<div class="container" style="margin-top: 50px; margin-left: 37%;">
		<table class="table table-hover table-border" style="width: 700px;">
			<tr>
				<th colspan="2" style="text-align: center; font-size: 30pt; font-weight: bold;">상품 수정</th>
			</tr>
			
			<tr>
				<td width="100">제목</td>
				<td>
					<input type="text" name="title" id="input_title" style="width:30%" value="${vo.sh_title}"/>
				</td>
			</tr>
			
			<tr>
				<td width="100">상품 종류</td>
				<td>
					<select name="category" id="input_category">
						<option value="신발">신발</option>
						<option value="상의">상의</option>
						<option value="하의">하의</option>
					</select>
				</td>
			</tr>
		
			<tr>
				<td width="100">상품 브랜드</td>
				<td>
					<select name="categoryDetail" id="input_categoryDetail" >
						<option value="nike">나이키</option>
						<option value="adidas">아디다스</option>
						<option value="newbalance">뉴발란스</option>
					</select>
				</td>
			</tr>
		
			<tr>
				<td width="100">상품명</td>
				<td>
					<input type="text" name="name" id="input_name" value="${vo.sh_name}" style="width: 30%"/>
				</td>
			</tr>
			
			<tr>
				<td width="100">상품 내용</td>
				<td>
					<textarea rows="10" cols="60" name="content" id="input_content" style="resize: none;">${vo.sh_content}</textarea>
				</td>
			</tr>
	
<!-- 			<tr> -->
<!-- 				<td width="100">사이즈</td> -->
<!-- 				<td> -->
<!-- 					<select name="size" id="input_size"> -->
<!-- 						<option value="230">230</option> -->
<!-- 						<option value="240">240</option> -->
<!-- 						<option value="250">250</option> -->
<!-- 						<option value="260">260</option> -->
<!-- 						<option value="270">270</option> -->
<!-- 						<option value="280">280</option> -->
<!-- 					</select> -->
<!-- 				</td> -->
<!-- 			</tr> -->
		
			<tr>
				<td width="100">가격</td>
				<td>
					<input type="text" name="price" id="input_price" style="width: 20%" value="${vo.sh_price}"/>
				</td>
			</tr>
			
			<tr>
				<td width="100">할인율</td>
				<td>
					<input type="text" name="salePercent" id="input_salePercent" style="width: 20%" value="${vo.sh_salePercent}"/> %
				</td>
			</tr>
			</table>
			<table class="table table-hover table-border" style="width: 700px">
				<tr>
		            <td style="width: 50%">
		            	<div style="float: left;">
			            	<div id="imgDiv1" style="float: left;">
			            	
			            		<c:choose>
			            			<c:when test="${vo.sh_img1 == null}">
	                					<img id="img_1" class="pic" width="300px" height="300px">
	                				</c:when>
	                				<c:otherwise>
	                					<img id="img_1" class="pic" src="${vo.sh_img1}" width="300px" height="300px">
	                				</c:otherwise>
	                			</c:choose>
	                			
		                		<input id="originalImg1" name="originalImg1" type="hidden" value="${vo.sh_img1}"/>
		                	</div>
		                	<div id="buttonDiv1">
		                		<c:choose>
			            			<c:when test="${vo.sh_img1 == null}">
	                					<input id="1" name="1" type="file"/>
	                				</c:when>
	                				<c:otherwise>
	                					<input id="deleteButton1" type="button" value="삭제하기" onclick="deleteImg(1)"/>
	                				</c:otherwise>
	                			</c:choose>
		                	</div>
		            	</div>
		         	</td>
		            <td style="width: 50%">
		            	<div style="float: rignt;">
		                	<div id="imgDiv2" style="float: left;">
		                		<c:choose>
			            			<c:when test="${vo.sh_img2 == null}">
	                					<img id="img_2" class="pic" width="300px" height="300px">
	                				</c:when>
	                				<c:otherwise>
	                					<img id="img_2" class="pic" src="${vo.sh_img2}" width="300px" height="300px">
	                				</c:otherwise>
	                			</c:choose>
		                		<input id="originalImg2" name="originalImg2" type="hidden" value="${vo.sh_img2}"/>
		                	</div>
		                	<div id="buttonDiv2">
		                		<c:choose>
			            			<c:when test="${vo.sh_img2 == null}">
	                					<input id="2" name="2" type="file"/>
	                				</c:when>
	                				<c:otherwise>
			                			<input id="deleteButton2" type="button" value="삭제하기" onclick="deleteImg(2)"/>
	                				</c:otherwise>
	                			</c:choose>
			                	
		                	</div>
	                	</div>
		         	</td>
				</tr>
			<tr>
				<td align="center" colspan="3">
					<input type="submit" value="상품 수정"/>
				</td>
			</tr>
			
		</table>
	</div>
	<input type="hidden" value="${vo.sh_idx}" name="idx"/>
</form>

<jsp:include page="../Layout/footer.jsp"></jsp:include>

















