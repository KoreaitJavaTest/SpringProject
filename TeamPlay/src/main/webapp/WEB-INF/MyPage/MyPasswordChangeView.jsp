<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
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
                <div class="panel-title">비밀번호를 재설정해주세요!</div>
            </div>
            <div class="panel-body">
                <form id="login-form" action = "PasswordChangeDo" method = "get">
                    <div>
                        <input type="password" class="form-control" name="password" placeholder="비밀번호를 입력해주세요" required="required">
                    </div>
                    <div>
                        <input type="password" class="form-control" name="passwordRe" placeholder="비밀번호를 다시 입력해주세요" required="required">
                    </div>
                    <div>
                    	<input type = "hidden" value = "${id}" name = "id">
                        <button type="submit" class="form-control btn btn-primary">변경하기!</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../Layout/footer.jsp"></jsp:include>
	