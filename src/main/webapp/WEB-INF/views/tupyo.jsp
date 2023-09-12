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
<%@ include file="./shared/_head_css.jsp" %>
</head>
<body class="twocolumn-panel">
<div id="layout-wrapper">
	<div>
		<div class="container">
			<div class="card">
				<input name="list" type="hidden" value="${lists}">
				<input name="firstOption" type="hidden" value="${lists[0].tuopSeq}">
				<div class="card-header" id="selectInstrTitle">
					<b>강사님을 선택해주세요</b><br>
				</div>
				<div class="card-header" id="tupyoResultTitle" style="display: none;">
					<b>투표 결과</b><br>
				</div>
				<div class="card-body">
					<div id="tupyoList">
						<div class="list-group" id="list-group">
						    <c:forEach var="item" items="${lists}">
						    <label class="list-group-item">
						        <input class="form-check-input me-1" name="teacher" type="radio" value="${item.tuopSeq}">
						        강사 : ${item.tuopInstr} / 수업료: ${item.tuopFee}원
						    </label>
						    </c:forEach>
						</div>
						<div class="list-group" id="agree-disagree-group" style="display: none;">
						<div style="margin-bottom: 10px;">${lists[0].tuopInstr} 강사님 / 수업료 : ${lists[0].tuopFee}원</div>
						    <label class="list-group-item">
						        <input class="form-check-input me-1" name="vote" type="radio" value="A">
						        찬성
						    </label>
						    <label class="list-group-item">
						        <input class="form-check-input me-1" name="vote" type="radio" value="D">
						        반대
						    </label>
						</div>
						<div style="text-align: center;">
							<button class="btn btn-primary" onclick="tupyoComplete()" style="margin-top: 10px;">선택완료</button>
						</div>
					</div>
					<div id="tupyoResult" style="display: none;">
						<canvas id="myChart"></canvas>
					</div>
					<div style="text-align: center;">
						<button id="reTupyo" class="btn btn-primary" onclick="reTupyo()" style="display: none;margin-top: 10px;">재투표</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="./shared/_vender_scripts.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.0.1/chart.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script type='text/javascript' src="./js/tupyo.js"></script>
</body>
</html>