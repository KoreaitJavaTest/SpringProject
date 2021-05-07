<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
.dots {
  width: 3.5em;
  display: flex;
  flex-flow: row nowrap;
  align-items: center;
  justify-content: space-between;
}

.dots div {
  
  width: 0.8em;
  height: 0.8em;
  border-radius: 50%;
  background-color: #fc2f70;
  animation: fade 0.8s ease-in-out alternate infinite;
}

.dots div:nth-of-type(1) {
  animation-delay: -0.4s;
}

.dots div:nth-of-type(2) {
  animation-delay: -0.2s;
}

@keyframes fade {
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
}
</style>
<script type="text/javascript">
	$(function() {
	   // 아이디 중복 체크
	      $('#user_input_id').keyup(function() {
		      var client_id = $("#user_input_id").val();
		      var idcheck = "사용 가능한 아이디입니다.";
		      var realid = "";
		      if(client_id.length > 8 && client_id.length < 16){
		         $.ajax({
		        	  type:'POST',
		        	  url: './IdoverlapcheckLogicDo',
		        	  data:{ client_id : $("#user_input_id").val()},
		            success: function(data) {
		               $("#id_overlap_result").empty();
		               $("#id_overlap_result").append(data);
		               realid = data;
		               yourealwantthisid(realid,idcheck);
		            }, 
		            error: function(xhr, status) {
		            	alert(xhr + " : " + status)
		            }
		         }); 
		         return false;
		      }else{
		    	  $("#id_overlap_result").empty();
		    	  $("#id_overlap_result").append("8자 이상이여야 합니다.");
		      }
	      }); 
	});
	function yourealwantthisid(realid ,idcheck){
		   var joinbtn = document.getElementById('join_go');
		   var pscheckreal = document.getElementById('password_result').innerText;  
		   var pscheck = "비밀번호가 일치합니다.";
		   if(realid == idcheck){
			   if(pscheckreal == pscheck){
				   joinbtn.disabled = false;
			   }
		   }else{
			   joinbtn.disabled = true;
		   }
	}
	
		//비밀번호 체크
	   $(function() {
		      $('#re_password').keyup(function() {
		    	  var joinbtn = document.getElementById('join_go');
		    	  var idcheckreal = document.getElementById('id_overlap_result').innerText;
		    	  var idcheck = "사용 가능한 아이디입니다.";
		    	  var ps1 = $("#password").val();
		    	  var ps2 = $("#re_password").val();
		    	  if(ps1.length > 8){	  
		    	  if(ps1 == ps2){
		    		  $("#password_result").empty();
		    		  $("#password_result").append("비밀번호가 일치합니다.");
		    		  if(idcheckreal == idcheck){
		    			  joinbtn.disabled = false;
		    		  }
		    	  }else{
		    		  $("#password_result").empty();
		    		  $("#password_result").append("비밀번호가 일치하지않습니다.");
		    		  joinbtn.disabled = true;
		    	  }
		    	  }else{
		    		  $("#password_result").empty();
		    		  $("#password_result").append("8자 이상 입력해주세요.");
		    	  }
		      return false;
		      }); //formAction e
		   });
	   $(function() {
		      $('#password').keyup(function() {
		    	  var joinbtn = document.getElementById('join_go');
		    	  var idcheckreal = document.getElementById('id_overlap_result').innerText;
		    	  var idcheck = "사용 가능한 아이디입니다.";
		    	  var ps1 = $("#password").val();
		    	  var ps2 = $("#re_password").val();
		    	  if(ps1.length > 8){	  
			    	  if(ps1 == ps2){
			    		  $("#password_result").empty();
			    		  $("#password_result").append("비밀번호가 일치합니다.");
			    		  if(idcheckreal == idcheck){
			    			  joinbtn.disabled = false;
			    		  }
			    	  }else{
			    		  $("#password_result").empty();
			    		  $("#password_result").append("비밀번호가 일치하지않습니다.");
			    		  joinbtn.disabled = true;
			    	  }
			    	  }else{
			    		  $("#password_result").empty();
			    		  $("#password_result").append("8자 이상 입력해주세요.");
			    	  }
		      return false;
		      }); //formAction e
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
<html>
	<form action="JoinResultViewDo" method = "post">
		<table class = "table table-bordered table-hover" border = "1" align = "center" style = "width : 30%; margin-top:10px;">
			<tr>
				<td align = "center" colspan ="2"> 
					아이디
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					<input type = "text" class="form-control" id = "user_input_id" name = "id" placeholder="이름을 적어주세요" maxlength='16' autocomplete="off" required="required">
					<div id = "id_overlap_result"></div>
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					비밀번호
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					<input type = "password" class="form-control" id = "password" name = "password" maxlength='20' placeholder="비밀번호 적어주세요" required="required">
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					비밀번호 한번더
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					<input type = "password" class="form-control" id = "re_password" maxlength='20' placeholder="비밀번호를 한번 더 적어주세요" required="required">
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
					<input type = "text" name = "email_head" placeholder="이메일을 적어주세요" autocomplete="off" required="required">@
					<select name = "email_end">
						<option selected="selected">이메일선택</option>
						<option>naver.com</option>
						<option>gmail.com</option>
					</select>
				</td>
				<td align = "center" colspan ="1" style = "width : 20%;">
					<select name = "gender">
						<option selected="selected">성별선택</option>
						<option>남자</option>
						<option>여자</option>
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
					<input type = "text" name = "phone" class="form-control" autocomplete="off" placeholder="핸드폰번호 적어주세요" required="required">
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					거주지를 선택해주세요&nbsp;&nbsp;<input type = "button" value = "거주지 검색" onclick = "searchAddr()">
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					일반주소 <input type = "text" id = "join_addr_headAndMiddle" name = "addr_head" style = "width : 80%;" readonly="readonly"><br/>
					상세주소 <input type = "text" name = "addr_end" autocomplete="off" style = "width : 80%;">
				</td>
			</tr>
			<tr>
				<td align = "center" colspan ="2">
					<input type = "submit" id = "join_go" disabled="" data-target = "#modal" data-toggle = "modal" value = "회원가입!">
						<div class = "row">
						<div class = "modal" id = "modal" tabindex = "1">
							<div class = "modal-dialog">
								<div class = "modal-content">
									<div class = "modal-header" align = "center">
									  회원가입중입니다.
									</div>
									<div class ="modal-body" align = "center">
										<div class="dots" align = "center">
										  <div align = "center"></div>
										  <div align = "center"></div>
										  <div align = "center"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form>
</html>

<jsp:include page="../Layout/footer.jsp"></jsp:include>
