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
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script type='text/javascript'>
function tupyoComplete(){
    
    var selectedTeacher = $("input[name='teacher']:checked").val();
    var userId = "TMTD1";
    $.ajax({
      url: './insertTupyoUser.do',
      type: 'POST',
      contentType: "application/json;charset=UTF-8",
      data: JSON.stringify({ "tuusOptionSeq" : "1",
    	  	  "tuusAccountId" : "TMTD1"}),
      success: function(response) {
    	  console.log(selectedTeacher,userId);
    	  $("#tupyoList").css('display','none');
    	  $("#tupyoResult").css('display','block');
      },
      error: function(error) {
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
		차트요
	</div>
</body>
</html>