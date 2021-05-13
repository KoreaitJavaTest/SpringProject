<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel=”stylesheet” href=”http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css“>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
<html>
<style>
.indexjumbo {
/* 		background-image: url("./resources/images/test2.jpg"); */
/* 		background-repeat: round; */
		height: 480px;
		width: 1140px;
/* 		border-radius: 15px; */
	}
.ReViewRank p{
  height:83%;
  overflow: auto;
  white-space: pre-line;
}
</style>
<jsp:include page="../Layout/header.jsp"></jsp:include>
<div class="container">
<canvas id="myChart" width="384" height="210"></canvas>
<script>var ctx = document.getElementById("myChart");
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ["월", "화", "수", "목", "금", "토","일"],
        datasets: [{
            label: 'IP별 쇼핑몰 방문횟수',
            data: [12, 19, 3, 5, 2, 3,7],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {scales: {yAxes: [{ticks: {beginAtZero:true} }]}} //데이터 값을 0부터 시작
});
</script>
</div>
<div class="container">
	<div class="indexjumbo">
		<img alt="" src="<c:url value="/resources/images/test01.jpg"/>"style="width: 100%;height: 100%;">
	</div>
</div>
<div class="container" style="border-top: 2px solid black;margin-top: 30px;padding-top: 30px;">
<div class="panel panel-danger">
  <div class="panel-heading" style="text-align: center;"><span class="glyphicon glyphicon-fire"></span>&nbsp;이달의 리뷰게시글</div>
 <div class="panel-body">
	
<c:forEach var="vo" items="${ReViewList}">
<c:set var="imgN" value="${fn:split(vo.RE_imgNames,',')}"/>
	<div class="row">
        <div class="col-md-3" style="width: 585px;height: 237px; position: relative;" >
         	<div>
            <img class="img-fluid rounded mb-3 mb-md-0" style="width: 100%; height: 100%" src="http://localhost:8010/korea/upload/${imgN[0]}" alt="">
			<span class="label label-default" 
			style="color:#fff;background-color:#ef5777;font-size:12px;text-transform:uppercase;
			padding:2px 7px;display:block;position:absolute;top:10px;left:0"><p style="font-size: 15pt;">${vo.RE_rank}위</p></span>         	
         	
         	</div>
        </div>
        <div class="col-md-5" style="height: 195px;">
        	<div class="ReViewRank" style="height: 195px;">
		          <h1 style="font-size: 20pt;">${fn:trim(vo.RE_title)}</h1>
		          <p>
		          	${fn:trim(vo.RE_content)}
		          </p>
		          <a class="btn btn-primary" href="ReViewDetailSelect?idx=${vo.RE_idx}" style="position: relative ;top: 10px;">바로가기</a>
        	</div>
        </div>
     </div>
      <hr>
</c:forEach>
 </div>
  </div>
</div>
      <!-- /.row -->

<jsp:include page="../Layout/footer.jsp"></jsp:include>
</html>
	