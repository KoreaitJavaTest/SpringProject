<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
		$.ajax({
			type:"POST",
			url:"./GoogleIdCheck",
			data:{
				userId:profile.getId(),
				Name:profile.getName(),
				Email:profile.getEmail(),
				checkFlag:'Google'
			},
			success: function(meg){
				if(meg==-1){
					alert('회원가입 페이지로 이동합니다.');
					location.href='JoinViewDo?userEmail='+profile.getEmail()+'&checkFlag=google';
				}else{
					location.href='LoginDo?id='+profile.getEmail()+'&checkFlag=google';
				}
			},error: function(meg){
				alert('에러')
				
			}
		});
		var auth2 = gapi.auth2.getAuthInstance();
		auth2.signOut().then(function () {
		      console.log('User signed out.');
		    });
	}
</script>
<jsp:include page="../Layout/header.jsp"></jsp:include>
<style>
    body {
        background: #f8f8f8;
        padding: 60px 0;
    }
    
    #login-form > div {
        margin: 15px 0;
    }

</style>
<div class="container" align = "center">
    <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-success">
            <div class="panel-heading">
                <div class="panel-title">환영합니다! <br/>아이디와 비밀번호를 입력해주세요!</div>
            </div>
            <div class="panel-body">
                <form id="login-form" action = "LoginDo" method = "post">
                    <div>
                        <input type="text" class="form-control" name="id" placeholder="Username" autofocus>
                    </div>
                    <div>
                        <input type="password" class="form-control" name="password" placeholder="Password">
                    </div>
                    <div>
                        <button type="submit" class="form-control btn btn-primary">로그인</button>
                    </div>
                    <div>
                    	 <a href="#" class="btn btn-lg btn-primary btn-block">Facebook</a>	
                    	 <div class="g-signin2" data-onsuccess="onSignIn"></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../Layout/footer.jsp"></jsp:include>
	