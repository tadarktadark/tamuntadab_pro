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
</head>
<body>
${vo}
	<div id="tupyoList">
		<b>강사님을 선택해주세요</b>
		<c:forEach var="item" items="${lists}">
			<input type="radio" name="teacher" value="${item.tuopInstr}">${item.tuopInstr}<br>
		</c:forEach>
		<input type="button" value="선택완료" onclick="tupyoComplete()">
	</div>
</body>
</html>