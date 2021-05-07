<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Layout/header.jsp"></jsp:include>
<jsp:include page="./MyPageLayOut/menuBar.jsp"></jsp:include>
<jsp:include page="./MyPageLayOut/StartMain.jsp"></jsp:include>

<style>

    
    #login-form > div {
        margin: 15px 0;
    }

</style>
<div class="container" align = "center" style="margin-bottom: 60px; margin-top: 35px;">
    <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-success">
            <div class="panel-heading">
                <div class="panel-title">회원수정하기전 <br/>비밀번호를 입력해주세요!</div>
            </div>
            <div class="panel-body">
                <form id="login-form" action = "ClientEditViewDo" method = "post">
                    <div>
                        <input type="password" class="form-control" name="password" placeholder="Password" required="required">
                    </div>
                    <div>
                        <button type="submit" class="form-control btn btn-primary">수정하기</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="./MyPageLayOut/EndMain.jsp"></jsp:include>
<jsp:include page="../Layout/footer.jsp"></jsp:include>
	