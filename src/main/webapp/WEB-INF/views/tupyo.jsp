<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title} | 타문타답</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.0.1/chart.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>
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
function tupyoComplete(){
    
    var selectedTeacher = $("input[name='teacher']:checked").val();
    var tupyoOptionList = $("input[name='list']").val();
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
    	  
    	  
    	  console.log(response.length);
    	  console.log(response[0].tuopInstr);
    	  var tupyoOptionArray = new Array();
    	  for(let i=0;i<response.length;i++){
    		  tupyoOptionArray.push(response[i].tuopInstr)
    	  }
    	  
    	  
    	  console.log(tupyoOptionArray);
    	  var ctx = document.getElementById("myChart").getContext('2d');

    	  var myChart = new Chart(ctx, {
    	      type: 'bar',
    	      data: {
    	          labels: tupyoOptionArray,
    	          datasets: [{
    	              label: '# of Votes',
    	              data: [12, 19, 3],
    	              backgroundColor:'#FFB1C1'
    	          }]
    	      },
    	      options: {
    	          maintainAspectRatio: false, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
    	          scales: {
    	              yAxes: [{
    	                  ticks: {
    	                      beginAtZero:true
    	                  }
    	              }]
    	          }
    	      }
    	  });
      },
      error: function(error) {
    	  console.log("오류");
        console.log(error);
      }
    });
 }
</script>
</html>