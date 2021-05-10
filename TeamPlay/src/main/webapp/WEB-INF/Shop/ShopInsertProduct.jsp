<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">

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
		
		if($('#1').val() == '' && $('#2').val() == '' ){
			alert("이미지는 최소 1장 이상입니다.");
			flag = false;
		}
		return flag;
	}

</script>



<jsp:include page="../Layout/header.jsp"></jsp:include>

<form action="ShopInsertProductOK" method="post" onsubmit="return check()" enctype="multipart/form-data">

	<div class="container" style="margin-top: 50px;">
		<table class="table table-hover table-border">
			<tr>
				<th colspan="2" style="text-align: center; font-size: 30pt; font-weight: bold;">상품 등록</th>
			</tr>
			
			<tr>
				<td width="100">제목</td>
				<td>
					<input type="text" name="title" id="input_title" style="width: 98%" placeholder="제목을 입력하세요."/>
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
					<input type="text" name="name" id="input_name" style="width: 98%" placeholder="제품명을 입력하세요."/>
				</td>
			</tr>
			
			<tr>
				<td width="100">상품 내용</td>
				<td>
					<textarea rows="10" cols="60" name="content" id="input_content" style="resize: none;" placeholder="내용을 입력하세요."></textarea>
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
					<input type="text" name="price" id="input_price" style="width: 20%" placeholder="숫자만 입력해주세요."/>
				</td>
			</tr>
			
			<tr>
				<td width="100">할인율</td>
				<td>
					<input type="text" name="salePercent" id="input_salePercent" style="width: 20%" placeholder="숫자만 입력해주세요."/> %
				</td>
			</tr>
			
		
			<tr>
				<td>
	         		&nbsp;&nbsp;이미지 업로드&nbsp;&nbsp;
	            </td>
	            <td>
	         		<div id="file">
	         			<input type="file" name="1" id="1"/>
	         			<br/><br/>
	         			<input type="file" name="2" id="2"/>
	         		</div>
	         	</td>
			</tr>
			
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="판매 등록"/>
				</td>
			</tr>
			
		</table>
	</div>
</form>

<jsp:include page="../Layout/footer.jsp"></jsp:include>