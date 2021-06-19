	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="./AdminLayOut/AdminNav.jsp"></jsp:include>
<div class="col-md-12 well">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">
                <span class="glyphicon glyphicon-bookmark"></span> 이번주 웹페이지 방문횟수</h3>
        </div>
        <div class="panel-body" style="padding: 0px;"> 
        	<div class="container">
<canvas id="myChart" width="1000" height="600">
</canvas>
			</div>
		</div>
	</div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">
                <span class="glyphicon glyphicon-bookmark"></span> 회원별 남녀 성비</h3>
        </div>
        <div class="panel-body" style="padding: 0px;"> 
<div class="container">
<canvas id="genderChart" width="1000" height="600">
</canvas>
			</div>
		</div>
	</div>	

<script>
var ctx = document.getElementById("myChart");
var myChart = new Chart(ctx, {
    type: 'bar',
    data: { 
        labels: ["월", "화", "수", "목", "금", "토","일"],
        datasets: [{
            label: '${currentWeekDate}',
            data: [${mon}, ${tue},${wed},${thur}, ${fri}, ${satur},${sun}],
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
    options: {scales: {yAxes: [{ticks: {beginAtZero:true,
    									max:50	
    } }]}} //데이터 값을 0부터 시작
});
var ctx2 = document.getElementById("genderChart");
var genderChart = new Chart(ctx2, {
    type: 'pie',
    data: { 
        labels: ["남","여"],
        datasets: [{
            label: '${currentWeekDate}',
            data: [${ClientIsMen},${ClientIsGirl}],
            backgroundColor: [
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 99, 132, 0.2)'
            ],
            borderColor: [
                'rgba(54, 162, 235, 1)',
                'rgba(255,99,132,1)'
            ],
        }]
    },
});
</script>
        
        </div>

<jsp:include page="./AdminModal.jsp"></jsp:include>
<jsp:include page="./AdminLayOut/AdminNavEnd.jsp"></jsp:include>