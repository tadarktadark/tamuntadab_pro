<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light" data-topbar="light" data-sidebar="light" data-sidebar-size="sm" data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title} | 타문타답</title>
<%@ include file="./shared/_logout.jsp"%>
<%@ include file="./shared/_head_css.jsp"%>
<script type="text/javascript">
	function sendAlarm(){
		var gubun = 'AT_C';//구분은 slack 참고
		var content = '내용 테스트';//보낼 내용
		var accountId = 'TMTD16';//받는 사용자 ID
		var url = 'main.do';//알림을 누르면 이동할 주소, ?로 값전달해서 보내도됨
		$.ajax({
			url:'./insertAlarm.do',
			type:'POST',
			data:{
				"gubun":gubun,
				"content":content,
				"accountId":accountId,
				"url":url
			},
			success:function(){},
			error:function(){}
		})
	}
</script>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_menu.jsp"%>
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<%@ include file="./shared/_page_title.jsp"%>
					<div>
						<button onclick="sendAlarm()">알림 전송</button>
					</div>
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp"%>
		</div>
	</div>
<%@ include file="./shared/_vender_scripts.jsp"%>
</body>
</html>