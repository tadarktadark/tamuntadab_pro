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
</head>
<body>
<div id="layout-wrapper">
	<div id="tupyoList">
		<b>강사님을 선택해주세요</b>${vo}<br>
		<input name="list" type="hidden" value="${lists}">
		<c:forEach var="item" items="${lists}">
			<input type="radio" name="teacher" value="${item.tuopSeq}">강사 : ${item.tuopInstr} / 수업료: ${item.tuopFee}원<br>
		</c:forEach>
		<input type="button" value="선택완료" onclick="tupyoComplete()">
	</div>
	<div id="tupyoResult" style="display: none;">
		<canvas id="myChart"></canvas>
	</div>
	<div>
		<input id="reTupyo" type="button" value="재투표" onclick="reTupyo()" style="display: none;">
	</div>
</div>
<%@ include file="./shared/_vender_scripts.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.0.1/chart.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script type='text/javascript' src="./js/tupyo.js"></script>
</body>
</html>