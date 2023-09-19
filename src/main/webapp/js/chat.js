//window.onload = function() {
//    var rooms = document.querySelectorAll('#chatInputRoomList');
//    for (var i = 0; i < rooms.length; i++) {
//        rooms[i].addEventListener("click", function() {
//			var chatDetail = document.getElementById('chatDetail');
//			if(chatDetail){
//				chatDetail.style.display = 'none';
//				}
//            console.log("Element was clicked!");
//            var inputElement = this.querySelector('input');
//            var chroId = inputElement.value;
//            console.log(chroId); // roomId 출력
//            $.ajax({
//				url:'./chatRoom.do',
//				type:'GET',
//				data:{
//					"chroId":chroId
//				},
//				success:function(response){
//					console.log(response.chroTitle);
//					$('#chatRoomTitle').text(response.chroTitle);
//					var chatDate = new Date(response.chroDate);
//					var year = chatDate.getFullYear();
//				    var month = ("0" + (chatDate.getMonth() + 1)).slice(-2);
//				    var day = ("0" + chatDate.getDate()).slice(-2);
//				
//				    var formattedDate = year + '-' + month + '-' + day;
//					$('#chatRoomDate').text("개설일 : "+formattedDate);
//					if(chatDetail){
//						chatDetail.style.display = 'block';
//					}
//					
//				},
//				error:function(error){
//					console.log("chatRoom.do 오류");
//					console.log(error);
//				}
//			});//ajax
//        });//clickListener
//    }//for문
//}






$(document).ready(function(){
	var ws = null;
	var url = location.href;//현재 웹주소
	var checkUrl = "ws:"+url.substring(url.indexOf('//'),url.lastIndexOf('/')+1)+"wsChat.do";//웹소켓 주소
	var nickName = $('#nickname').text();
	console.log(checkUrl);
	
	$("#chatInput").focus();
	
	
	// 웹소켓 객체를 생성
	ws = new WebSocket(checkUrl);
	console.log("생성된 websocket 객체",ws);
	
	// 웹소켓 오픈 후에 send()를 통해서 서버에 Text를 전송하면 -> handlerTextMessage -> SessionMap
	ws.onopen = function(){
		console.log("웹소켓 오픈");
		ws.send("#&nick_"+nickName); // -> handlerTextMessage
	}
	
	ws.onmessage = function(event){
		var msg = event.data;
		console.log("서버로부터 전송된 메시지",msg);
		
		if(msg.startsWith("<font color=")){// 입장 퇴장
			$("#chatInput-conversation").append($("<div class='noticeText'>").append(msg+"<br>"));
			/*
				참가자 리스트 갱신 crSession
			 */
			viewList(group);
		}else if(msg.startsWith("[나]")){// 내 대화내용
			msg = msg.substring(3);
			$("#chatInput-conversation").append($("<div class='sendText'>").append($("<span class='send_img'>").text(msg))).append("<br><br>");// send_img 작업하기
		}else{
			$("#chatInput-conversation").append($("<div class='receiveText'>").append($("<span class='receive_img'>").text(msg))).append("<br><br>");// send_img 작업하기
			
		}
		$("#chatInput-conversation").scrollTop($("#chatInput-conversation")[0].scrollHeight);
	}
	
	
	ws.onclose = function(){
		alert("서버와 연결이 종료되었습니다 \n 채팅방이 삭제 됩니다");
	}
	
	$("#chatBtn").bind("click", function(){
		if($("#chatInput").val() == ""){
			alert("내용을 입력해 주세요");
		}else{
			ws.send(nickName + ":"+$("#chatInput").val());
			$("#chatInput").val("");
			$("#chatInput").focus();
		}
	});
});



function openChat(){
	var chatDetail = document.getElementById('chatDetail');
	console.log("Element was clicked!");
	var inputElement = this.querySelector('input');
	var chroId = inputElement.value;
	
	$.ajax({
		url:'./chatRoom.do',
		type:'GET',
		data:{
			"chroId":chroId
		},
		success:function(response){
			console.log(response.chroTitle);
			$('#chatRoomTitle').text(response.chroTitle);
			var chatDate = new Date(response.chroDate);
			var year = chatDate.getFullYear();
		    var month = ("0" + (chatDate.getMonth() + 1)).slice(-2);
		    var day = ("0" + chatDate.getDate()).slice(-2);
		
		    var formattedDate = year + '-' + month + '-' + day;
			$('#chatRoomDate').text("개설일 : "+formattedDate);
			if(chatDetail){
				chatDetail.style.display = 'block';
			}
			
		},
		error:function(error){
			console.log("chatRoom.do 오류");
			console.log(error);
		}
	});//ajax
	
	
	
	
	
	
}
