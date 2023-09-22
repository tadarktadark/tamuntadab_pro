window.onload = function() {



}

function openChat(element){
    var chroId = element.getAttribute('value');
    
    console.log("Element was clicked!",chroId);
   
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
             	    
                 
                var ws = null;
				var url = location.href;//현재 웹주소
				var checkUrl = "ws:"+url.substring(url.indexOf('//'),url.lastIndexOf('/')+1)+"wsChat.do";//웹소켓 주소
				var nickName = $('#nickname').text();
				console.log(checkUrl);
				console.log(nickName);
				

				ws = new WebSocket(checkUrl);
				console.log("생성된 websocket 객체",ws);
                 
                 
                 
                
                ws.onopen = function(){
					console.log("웹소켓 오픈");
					ws.send("#&nick_"+nickName); // -> handlerTextMessage
				}
				
                 
                 
                ws.onmessage = function(event){
					var msg = event.data;
					console.log("서버로부터 전송된 메시지",msg);
					
					if(msg.startsWith("<font color=")){// 입장 퇴장
						$("#total-chat-list").append($("<li class='왜안들어가'>하이하이</li>"));
						/*
							참가자 리스트 갱신 crSession
						 */
						 console.log('입장 퇴장');
					}else if(msg.startsWith("[나]")){// 내 대화내용
						msg = msg.substring(3);
						
						$("#total-chat-list").append($("<li class='왜안들어가'>").append($("<span>").text(msg))).append("</li>");
						 console.log('대화내용');
					}else{//다른사람 입력값
						$(".total-chat-list").append($("<div class='receiveText'>").append($("<span class='receive_img'>").text(msg))).append("<br><br>");// send_img 작업하기
						 console.log('목록');
					}
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
                 
             }else{
				chatDetail.style.display = 'none';
				alert('채팅방이 없습니다');
			}
            
         },
         error:function(error){
             console.log("chatRoom.do 오류");
             console.log(error);
         }
     });//ajax
}
    
    
    
    
    
    