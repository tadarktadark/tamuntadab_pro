<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light" data-topbar="light" data-sidebar="light" data-sidebar-size="sm" data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>타문타답 | 우리가 만드는 커리큘럼</title>
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
	<%
  // 세션에 값이 없으면 자바스크립트 코드 실행
  if (session.getAttribute("userInfo") == null) {
%>
  <script>
    if (localStorage.getItem("autoLoginToken") != null) {
      $.ajax({
        url:'./autoLogin.do',
        data : {userAutoLoginToken: localStorage.getItem("autoLoginToken")},
        type: "POST",
        success: function(result) {
          if(result == 'true'){
            location.href='./';
          } else {
            location.href='./loginForm.do';
          }
        },
        error:function(){
          alert('잘못된 접근입니다.');
        }
      });
    }
  </script>
<%
  }
%>

</body>
</html>