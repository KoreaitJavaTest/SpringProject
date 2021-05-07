<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
		//비밀번호 체크
	   $(function() {
		      $('#re_password').keyup(function() {
		    	  var joinbtn = document.getElementById('join_go');
		    	  var ps1 = $("#password").val();
		    	  var ps2 = $("#re_password").val();
		    	  if(ps1.length > 8){	  
		    	  if(ps1 == ps2){
			    		  $("#password_result").empty();
			    		  $("#password_result").append("비밀번호가 일치합니다.");
			    		  joinbtn.disabled = false;
			    	  }else{
			    		  $("#password_result").empty();
			    		  $("#password_result").append("비밀번호가 일치하지않습니다.");
			    		  joinbtn.disabled = true;
			    	  }
		    	  }else{
		    		  $("#password_result").empty();
		    		  $("#password_result").append("8자 이상 입력해주세요.");
		    		  joinbtn.disabled = true;
		    	  }
		      });
		   });
	   $(function() {
		      $('#password').keyup(function() {
		    	  var joinbtn = document.getElementById('join_go');
		    	  var ps1 = $("#password").val();
		    	  var ps2 = $("#re_password").val();
		    	  if(ps1.length > 8){	  
		    	  if(ps1 == ps2){
			    		  $("#password_result").empty();
			    		  $("#password_result").append("비밀번호가 일치합니다.");
			    		  joinbtn.disabled = false;
			    	  }else{
			    		  $("#password_result").empty();
			    		  $("#password_result").append("비밀번호가 일치하지않습니다.");
			    		  joinbtn.disabled = true;
			    	  }
		    	  }else{
		    		  $("#password_result").empty();
		    		  $("#password_result").append("8자 이상 입력해주세요.");
		    		  joinbtn.disabled = true;
		    	  }
		      }); 
		   });
</script>
<script type="text/javascript">
function searchAddr(){
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = "";
            
            if(data.userSelectedType === 'R'){
            	addr = data.roadAddress;
            }else{
            	addr = data.jibunAddress;
            }
            
            document.getElementById('join_addr_headAndMiddle').value = addr;
        }
    }).open();
}
</script>
<jsp:include page="../Layout/header.jsp"></jsp:include>
<jsp:include page="./MyPageLayOut/menuBar.jsp"></jsp:include>
<jsp:include page="./MyPageLayOut/StartMain.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-8">
		<form action="EditResultViewDo" method = "post">
			<table class = "table table-bordered table-hover" border = "1" align = "center" style = "margin-top:10px;">
				<tr>
					<td align = "center" colspan ="2"> 
						아이디
					</td>
				</tr>
				<tr>
					<td align = "center" colspan ="2">
						<input type = "text" class="form-control" value = "${vo.client_id }" name = "id" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td align = "center" colspan ="2">
						비밀번호를 변경하실경우 입력해주세요.
					</td>
				</tr>
				<tr>
					<td align = "center" colspan ="2">
						<input type = "password" class="form-control" id = "password" name = "password" maxlength='20' placeholder="비밀번호 적어주세요">
					</td>
				</tr>
				<tr>
					<td align = "center" colspan ="2">
						비밀번호 한번더 입력해주세요.
					</td>
				</tr>
				<tr>
					<td align = "center" colspan ="2">
						<input type = "password" class="form-control" id = "re_password" maxlength='20' placeholder="비밀번호를 한번 더 적어주세요">
						<div id = "password_result"></div>
					</td>
				</tr>
				<tr>
					<td align = "center" colspan ="1">
						이메일
					</td>
					<td align = "center" colspan ="1">
						성별
					</td>
				</tr>
				<tr>
					<td align = "center" colspan ="1" style = "width : 80%;">
						<input type = "text" name = "email_head" value = "${vo.client_email }" autocomplete="off" readonly="readonly">
						<select name = "email_end" disabled="disabled" readonly>
							<option selected="selected">이메일선택</option>
							<option>naver.com</option>
							<option>gmail.com</option>
						</select>
					</td>
					<td align = "center" colspan ="1" style = "width : 20%;">
						<select name = "gender" readonly>
							<c:if test = "${vo.client_gender eq '남자' }"><option selected="selected">남자</option></c:if>
							<c:if test = "${vo.client_gender eq '여자' }"><option selected="selected">여자</option></c:if>
						</select>
					</td>
				</tr>
				<tr>
					<td align = "center" colspan ="2">
						핸드폰번호
					</td>
				</tr>
				<tr>
					<td align = "center" colspan ="2">
						<input type = "text" name = "phone" class="form-control" autocomplete="off" value = "${vo.client_phone }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td align = "center" colspan ="2">
						거주지를 선택해주세요&nbsp;&nbsp;<input type = "button" value = "거주지 검색" onclick = "searchAddr()">
					</td>
				</tr>
				<tr>
					<td align = "center" colspan ="2">
						일반주소 <input type = "text" id = "join_addr_headAndMiddle" value = "${vo.client_addr_head }" name = "addr_head" style = "width : 80%;" readonly="readonly"><br/>
						상세주소 <input type = "text" name = "addr_end" value = "${vo.client_addr_end }" autocomplete="off" style = "width : 80%;" required="required">
					</td>
				</tr>
				<tr>
					<td align = "center" colspan ="2">
						<input type = "submit" id = "join_go" value = "수정하기">
					</td>
				</tr>
			</table>
		</form>
			</div>
			<div class="col-xs-2"></div>
		</div>
</div>
<jsp:include page="./MyPageLayOut/EndMain.jsp"></jsp:include>
<jsp:include page="../Layout/footer.jsp"></jsp:include>
