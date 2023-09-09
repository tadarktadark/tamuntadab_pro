<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>투표</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.0.1/chart.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script type='text/javascript'>
function tupyoComplete(){
    
    var selectedTeacher = $("input[name='teacher']:checked").val();
    var userId = "TMTD1";
    $.ajax({
      url: './insertTupyoUser.do',
      type: 'POST',
      contentType: "application/json;charset=UTF-8",
      data: JSON.stringify({ "tuusOptionSeq" : selectedTeacher,
    	  	  "tuusAccountId" : userId}),
      success: function(response) {
    	  console.log(selectedTeacher,userId);
    	  $("#tupyoList").css('display','none');
    	  $("#tupyoResult").css('display','block');
      },
      error: function(error) {
    	  console.log(selectedTeacher,userId);
    	  console.log("오류");
        console.log(error);
      }
    });
 }
</script>
</head>
<body>
	<div id="tupyoList">
		<b>강사님을 선택해주세요</b>${vo}<br>
		<c:forEach var="item" items="${lists}">
			<input type="radio" name="teacher" value="${item.tuopSeq}">강사 : ${item.tuopInstr} / 수업료: ${item.tuopFee}원<br>
		</c:forEach>
		<input type="button" value="선택완료" onclick="tupyoComplete()">
	</div>
	<div id="tupyoResult" style="display: none;">
		<canvas id="myChart"></canvas>
	</div>
</body>
<script type='text/javascript'>
// 우선 컨텍스트를 가져옵니다. 
var ctx = document.getElementById("myChart").getContext('2d');
/*
- Chart를 생성하면서, 
- ctx를 첫번째 argument로 넘겨주고, 
- 두번째 argument로 그림을 그릴때 필요한 요소들을 모두 넘겨줍니다. 
*/
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
        datasets: [{
            label: '# of Votes',
            data: [12, 19, 3, 5, 2, 3],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        maintainAspectRatio: true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});
</script>
</html>