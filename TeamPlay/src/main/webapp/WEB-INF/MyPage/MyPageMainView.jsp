<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../Layout/header.jsp"></jsp:include>
<style>
  .affix-row .affix-sidebar{
    background-color: #f8f8f8;
    height: 100%;
    overflow: hidden;
  }
    .affix-content .container .page-header{
    margin-top: 0;
  }
  .sidebar-nav{
        position:fixed; 
        width:100%;
  }
  .affix-sidebar{
    padding-right:0; 
    font-size:small;
    padding-left: 0;
  }  
  .affix-row, .affix-container, .affix-content{
    height: 100%;
    margin-left: 0;
    margin-right: 0;    
  } 
  .affix-content{
    background-color:white; 
  } 
  .sidebar-nav .navbar .navbar-collapse {
    padding: 0;
    max-height: none;
  }
  .sidebar-nav .navbar{
    border-radius:0; 
    margin-bottom:0; 
    border:0;
  }
  .sidebar-nav .navbar ul {
    float: none;
    display: block;
  }
  .sidebar-nav .navbar li {
    float: none;
    display: block;
  }
  .sidebar-nav .navbar li a {
    padding-top: 12px;
    padding-bottom: 12px;
  }  
}

@media (min-width: 769px){
  .affix-content .container {
    width: 600px;
  }
    .affix-content .container .page-header{
    margin-top: 0;
  }  
}

@media (min-width: 992px){
  .affix-content .container {
  width: 900px;
  }
    .affix-content .container .page-header{
    margin-top: 0;
  }
}

@media (min-width: 1220px){
  .affix-row{
    overflow: hidden;
  }

  .affix-content{
    overflow: auto;
  }

  .affix-content .container {
    width: 1000px;
  }

  .affix-content .container .page-header{
    margin-top: 0;
  }
  .affix-content{
    padding-right: 30px;
    padding-left: 30px;
  }  
  .affix-title{
    border-bottom: 1px solid #ecf0f1; 
    padding-bottom:10px;
  }
  .navbar-nav {
    margin: 0;
  }
  .navbar-collapse{
    padding: 0;
  }
  .sidebar-nav .navbar li a:hover {
    background-color: #428bca;
    color: white;
  }
  .sidebar-nav .navbar li a > .caret {
    margin-top: 8px;
  }  
}
@import url(http://fonts.googleapis.com/css?family=Lato:400,700);
.profile
{
    font-family: 'Lato', 'sans-serif';
    }
.profile 
{
/*    height: 321px;
    width: 265px;*/
margin-top: 2px;
padding:1px;
    display: inline-block;
    }
.divider 
{
    border-top:1px solid rgba(0,0,0,0.1);
    }
.emphasis 
{
    border-top: 1px solid transparent;
    }

.emphasis h2
{
    margin-bottom:0;
    }
span.tags 
{
    background: #1abc9c;
    border-radius: 2px;
    color: #f5f5f5;
    font-weight: bold;
    padding: 2px 4px;
    }
.profile strong,span,div{
    text-transform: initial;
}


</style>
<script type="text/javascript">
function AttentionCheck() {
	$.ajax({
		type:"POST",
		url:"./depositPoint.nhn",
		data:{
			userId:"${sessionScope.session_id}"
		},
		success: function(meg){
			alert(meg);
			
		},error: function(){
			alert("failed!");
		}
	});
	location.reload();
}


</script>
<jsp:include page="./MyPageLayOut/menuBar.jsp"></jsp:include>
<!--  메뉴바는 include로 , 메뉴바,메인글테두리 css는 header에 저장되어있음 -->
<!--  메인글 시작 입니다 여기서부터 -->

<jsp:include page="./MyPageLayOut/StartMain.jsp"></jsp:include>
                <!-- 메인표시글 시작 -->
			            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="border-radius: 16px;">
			                <div class="well profile col-lg-12 col-md-12 col-sm-12 col-xs-12" style="position: relative; left: 400px;">
			                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
			                        <figure>
			                             <img src="./htmll.jpg" alt="tq" class="img-circle" style="width:75px;" id="user-img">
			                        </figure>
			                        <h5 style="text-align:center;"><strong id="user-name">${sessionScope.session_id} 님</strong></h5>
			                        <p style="text-align:center;font-size: smaller;" id="user-frid">${sessionScope.session_gender}</p>
			                        <p style="text-align:center;font-size: smaller;overflow-wrap: break-word;" id="user-email">${sessionScope.session_email}</p>
			                        <p style="text-align:center;font-size: smaller;"><strong>
			                 	       	<c:if test="${sessionScope.session_level==0}">관리자</c:if>		                      
			                        	<c:if test="${sessionScope.session_level==1}">판매자</c:if>		                      
			                        	<c:if test="${sessionScope.session_level==2}">손님</c:if>		                      
			                        	</strong><span class="tags" id="user-status">
			                        	<c:if test="${sessionScope.session_level==0}">ADMIN</c:if>		                      
			                        	<c:if test="${sessionScope.session_level==1}">SELLER</c:if>		                      
			                        	<c:if test="${sessionScope.session_level==2}">CUSTOMER</c:if>	
			                        	</span></p>
			                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 divider text-center" style="margin-top: 3px;"></div>
			                        <p style="text-align:center;font-size: smaller;"><strong>Point</strong></p>
			                        <p style="text-align:center;font-size: smaller;" id="user-role">${sessionScope.session_point}점</p>
			                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 divider text-center"></div>
			                            <div class="col-lg-12 left" style="text-align:center;overflow-wrap: break-word;">
			                               <input type="button" class="btn btn-info" value="출석체크" onclick="AttentionCheck()">
			                            </div>
			                      </div>
			                    </div>
			            </div>
			            <!-- 메인표시글 끝 -->
<jsp:include page="./MyPageLayOut/EndMain.jsp"></jsp:include>



<jsp:include page="../Layout/footer.jsp"></jsp:include>