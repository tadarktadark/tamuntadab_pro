<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<%@ include file="./shared/_head_css.jsp" %>
</head>
<body class="twocolumn-panel">
<div id="layout-wrapper">
	<div>
		<div class="container">
		<input name="clasId" type="hidden" value="${clasId}">
		<input name="hasTupyo" type="hidden" value="${hasTupyo}">
		<c:choose>
		<c:when test="${hasTupyo eq 'true'}">
			<div class="card">
				<input name="accountId" type="hidden" value="${userInfo.userAccountId}">
				<input name="list" type="hidden" value="${lists}">
				<input name="agreeTupyoOptionSeq" type="hidden" value="${lists[0].tuopSeq}">
				<input name="tupyoSeq" type="hidden" value="${vo.tupySeq}">
				<input name="tupyoClassId" type="hidden" value="${vo.tupyClasId}">
				<input name="tupyoStatus" type="hidden" value="${vo.tupyStatus}">
				<div class="card-header" id="selectInstrTitle">
					<b>강사님을 선택해주세요</b><span style="float: right;">종료일자 :<fmt:formatDate value="${vo.tupyEnddate}" pattern="yyyy-MM-dd"/> &nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-danger btn-icon" onclick="closeWindow()"><i class="ri-close-fill"></i></button></span><br>
				</div>
				<c:choose>
					<c:when test="${vo.tupyStatus eq 'P'}">
						<div class="card-header" id="tupyoResultTitle" style="display: none;">
							<b>투표 결과</b><span style="float: right;">종료일자 : <fmt:formatDate value="${vo.tupyEnddate}" pattern="yyyy-MM-dd"/> &nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-danger btn-icon" onclick="closeWindow()"><i class="ri-close-fill"></i></button></span><br>
						</div>
					</c:when>
					<c:otherwise>
						<div class="card-header" id="tupyoResultTitle" style="display: none;">
							<b>투표 결과</b><span style="float: right;">종료됨 &nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-danger btn-icon" onclick="closeWindow()"><i class="ri-close-fill"></i></button></span><br>
						</div>
					</c:otherwise>
				</c:choose>
				<div class="card-body">
					<div id="tupyoList">
						<div class="list-group" id="list-group">
						    <c:forEach var="i" begin="0" end="${fn:length(lists) - 1}">
						    <label class="list-group-item">
						        <input class="form-check-input me-1" name="teacher" type="radio" value="${lists[i].tuopSeq}">
						        강사 : ${instrNicknameList[i]} / 수업료: ${lists[i].tuopFee}원
						    </label>
						    </c:forEach>
						</div>
						<div class="list-group" id="agree-disagree-group" style="display: none;">
						<div style="margin-bottom: 10px;">${instrNicknameList[0]} 강사님 / 수업료 : ${lists[0].tuopFee}원</div>
						    <label class="list-group-item">
						        <input class="form-check-input me-1" name="vote" type="radio" value="A">
						        찬성
						    </label>
						    <label class="list-group-item">
						        <input class="form-check-input me-1" name="vote" type="radio" value="D">
						        반대
						    </label>
						</div>
					</div>
					<div id="tupyoResult" style="display: none;">
						<canvas id="myChart"></canvas>
					</div>
					<div style="text-align: center;">
						<button id="selectComplete" class="btn btn-primary" onclick="tupyoComplete()" style="margin-top: 10px;">선택완료</button>
						<button id="reTupyo" class="btn btn-primary" onclick="reTupyo()" style="display: none;margin-top: 10px;">재투표</button>
						<c:if test="${isMaster eq 'true'}">
						<button id="finishTupyo" class="btn btn-danger" onclick="finishTupyo()" style="margin-top: 10px;">투표 종료</button>
						</c:if>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div id="masterBtn" class="card">
				<c:if test="${isMaster eq 'true'}">
				<div class="row mb-3" style="text-align: center;">
					<div class="col-lg-3">
					</div>
				     <div class="col-lg-3" style="margin-top: 15px;">
				         <label for="dateInput" class="form-label"><b>종료 일자</b></label>
				         <button style="float: right;" class="btn btn-danger btn-icon" onclick="closeWindow()"><i class="ri-close-fill"></i></button>
				     </div>
				     <div class="col-lg-3" style="padding-top: 5px;">
				         <input type="date" min="" class="form-control flatpickr" id="dateInput" placeholder="날짜를 선택해주세요.">
				     </div>
				     <div style="margin-top: 10px;">
						<button id="makeTupyo" class="btn btn-primary" onclick="makeTupyo()">투표 생성하기</button>
				     </div>
				 </div>
				</c:if>
				<c:if test="${isMaster eq 'false'}">
				<button class="btn btn-danger btn-icon" onclick="closeWindow()"><i class="ri-close-fill"></i></button>
				</c:if>
			</div>
		</c:otherwise>
		</c:choose>
		</div>
	</div>
</div>
<%@ include file="./shared/_vender_scripts.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.0.1/chart.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script type='text/javascript' src="./js/tupyo.js"></script>
</body>
</html>