<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light" data-topbar="light" data-sidebar="light" data-sidebar-size="sm" data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>타문타답 | 우리가 만드는 커리큘럼</title>
<link href="./assets/libs/sweetalert2/sweetalert2.min.css" rel="stylesheet" type="text/css" />
<%@ include file="./shared/_head_css.jsp" %>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp" %>
		<%@ include file="./shared/_sidebar.jsp" %>
	
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp" %>
		</div>
	</div>	
	<%@ include file="./shared/_vender_scripts.jsp" %>
	<script src="./assets/libs/sweetalert2/sweetalert2.min.js"></script>
	<%
  // 세션에 값이 없으면 자바스크립트 코드 실행
  if (session.getAttribute("userInfo") == null) {
	%>
  <script src="./js/autoLogin.js"></script>
    <%
  }else if((session.getAttribute("userInfo") != null) ){
  %>
 	 <script src="./js/jeongjiCheck.js"></script>
  <%
  }
%>
</body>
</html>