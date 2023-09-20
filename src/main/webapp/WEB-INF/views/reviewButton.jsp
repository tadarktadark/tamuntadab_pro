<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
<input type="hidden" id="classId" value="${classId}">
<button onclick="reivewForm()">후기 등록</button>
</div>
</body>
<script type="text/javascript">
function reivewForm(){ 
	var classId = document.getElementById("classId").value;
	var url = "./reviewWriteForm.do?classId="+classId;
	var title = "후기 등록";
	var width = 800;
    var height = 800;

    // 화면 중앙에 위치시키기 위한 좌표 계산
    var left = (screen.width/2)-(width/2);
    var top = (screen.height/2)-(height/2);

    // window.open 함수에 위치와 크기를 지정
    window.open(url, title, 'width='+width+', height='+height+', top='+top+', left='+left);
}

</script>
</html>