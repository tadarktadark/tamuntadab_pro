$(document).ready(function() {
    $('#chatInput').on('keypress', function(event){
        if(event.which == 13){
            $('#chatBtn').click();
        }
    });
    var ws = null;
    
$(window).on("beforeunload", function () {
	if(ws){
		ws.close();
	}
});
$('.chatRoomList').click(function(){
	
	$(".chatRoomList").removeClass("active");

    // 클릭된 li 요소에 'active' 클래스 추가
    $(this).addClass("active");

    var chroId = $(this).attr('value');
    if(ws){
		ws.close();
	}
	$("#total-chat-list").children().remove();
	 
	 
	 
     $.ajax({
         url:'./chatRoom.do',
         type:'GET',
         data:{
             "chroId":chroId
         },
         success:function(response){
			 $('#selectedChatRoom').val(response.chroId); 
             $('#chatRoomTitle').text(response.chroTitle);
             var chatDate = new Date(response.chroDate);
             var year = chatDate.getFullYear();
             var month = ("0" + (chatDate.getMonth() + 1)).slice(-2);
             var day = ("0" + chatDate.getDate()).slice(-2);
       
             var formattedDate = year + '-' + month + '-' + day;
             $('#chatRoomDate').text("개설일 : "+formattedDate);
			 
             if(chatDetail){
                chatDetail.style.display = 'block';
                $("#chat-message-list").scrollTop($("#chat-message-list")[0].scrollHeight);
                console.log($('#chat-conversation').scrollTop());
                console.log($('#chat-conversation')[0].scrollHeight);
				             	    
                ws = null;
				var url = location.href;//현재 웹주소
				var checkUrl = "ws:"+url.substring(url.indexOf('//'),url.lastIndexOf('/')+1)+"wsChat.do";//웹소켓 주소
				var userAccountId = $('#userAccountId').val();
				console.log(checkUrl);
				console.log(userAccountId);
				$('#total-chat-list').html(response.chroChatLog);
				
			    
			    var sessionUserName =  $('#nickname').text();
				
			    
			    $("li").each(function() {
			        var userName = $(this).find("h5.conversation-name").text().trim();
			        if (userName === sessionUserName) {
			            $(this).addClass("right");
			        }
			    });
				
				var chatContainer = $('#chat-conversation').get(0);
				var simpleBar = new SimpleBar(chatContainer);
				var scrollElement = simpleBar.getScrollElement();
				scrollElement.scrollTop = scrollElement.scrollHeight;
				
				ws = new WebSocket(checkUrl);
				console.log("생성된 websocket 객체",ws);
                 
                 
                 
                
                ws.onopen = function(){
					console.log("웹소켓 오픈");
				}
				
                 
                 
                ws.onmessage = function(event){
					var msg = event.data;
				    console.log("서버로부터 전송된 메시지", msg);
				    
				    if(msg.startsWith("[나]")){// 내 대화내용
				        var content = msg.substring(3, msg.indexOf("#&nickName_")).trim();
				        console.log(content);
				        
				        var startNickNameIndex = msg.indexOf("#&nickName_") + 11;
				        var endNickNameIndex = msg.indexOf("#&profileImg_");
				        
				        if (startNickNameIndex < endNickNameIndex) {
				            var userId = msg.substring(startNickNameIndex, endNickNameIndex).trim();
				            console.log(userId);
				
				            var startProfileImgIndex = endNickNameIndex + 13;
				            if (startProfileImgIndex <= msg.length) {
				                var profileImg = msg.substring(startProfileImgIndex).trim();
				                console.log(profileImg);
				            } else {
				                console.error('프로필 이미지 정보 파싱 에러');
				            }
				        } else {
				            console.error('닉네임 정보 파싱 에러');
				        }
						
						var now = new Date();

						var year = now.getFullYear();
						var month = (now.getMonth() + 1).toString().padStart(2, '0');
						var date = now.getDate().toString().padStart(2, '0');
						
						// 요일을 한글로 표시하기 위한 배열
						var dayNames = ["(일)", "(월)", "(화)", "(수)", "(목)", "(금)", "(토)"];
						var dayOfWeek = dayNames[now.getDay()];
						
						
						var formattedDate = year + "-" + month + "-" + date + dayOfWeek;
						
						console.log(formattedDate);

						
						var newChatMessage = $("<li>").append(
					    $("<div>").addClass("conversation-list").append(
					        $("<div>").addClass("d-flex").append(
								$("<img>").attr("src",profileImg).addClass("rounded-circle avatar-sm"),
					            $("<div>").addClass("flex-1 me-3").append(
					                $("<div>").addClass("d-flex justify-content-between").append(
					                    // 이름
					                    $("<h5>").addClass("fs-16 conversation-name align-middle").text(userId)
					                ),
					                $("<div>").addClass("ctext-wrap").append(
					                    // 보낸 메시지
					                    $("<div>").addClass("ctext-wrap-content").append(
					                        $("<p>").addClass('mb-0 text-start').html(content),
					                        // 보낸 시간
					                        $("<span>").addClass("time fw-normal text-muted ms-0 ms-md-4").text(formattedDate)
					                   )
					               )
					           )
					       )
					   )
					);
					$("#total-chat-list").append(newChatMessage);
					var sessionUserName =  $('#nickname').text();
				
			    
				    $("li").each(function() {
				        var userName = $(this).find("h5.conversation-name").text().trim();
				        if (userName === sessionUserName) {
				            $(this).addClass("right");
				        }
				    });
					scrollElement.scrollTop = scrollElement.scrollHeight;
					console.log($('#selectedChatRoom').val());
					var chroId = $('#selectedChatRoom').val();
					console.log(chroId);
					var chroChatLog = $('#total-chat-list').html();
					console.log(chroChatLog);
					//채팅 목록 저장하는거 넣기
					$.ajax({
				         url:'./updateChatLog.do',
				         type:'POST',
				         data:{
				             "chroId":chroId,
				             "chroChatLog":chroChatLog
         				},
         				success:function(){
						},
         				error:function(){
							alert("저장하는 과정에서 오류가 발생했습니다");
						}
					})
					}else{//다른사람 입력값
						console.log("입장 {}",msg);
						var content = msg.substring(msg.indexOf("]")+1, msg.indexOf("#&nickName_")).trim();
				        console.log(content);
				        
				        var startNickNameIndex = msg.indexOf("#&nickName_") + 11;
				        var endNickNameIndex = msg.indexOf("#&profileImg_");
				        
				        if (startNickNameIndex < endNickNameIndex) {
				            var userId = msg.substring(startNickNameIndex, endNickNameIndex).trim();
				            console.log(userId);
				
				            var startProfileImgIndex = endNickNameIndex + 13;
				            if (startProfileImgIndex <= msg.length) {
				                var profileImg = msg.substring(startProfileImgIndex).trim();
				                console.log(profileImg);
				            } else {
				                console.error('프로필 이미지 정보 파싱 에러');
				            }
				        } else {
				            console.error('닉네임 정보 파싱 에러');
				        }
						
						var now = new Date();

						var year = now.getFullYear();
						var month = (now.getMonth() + 1).toString().padStart(2, '0');
						var date = now.getDate().toString().padStart(2, '0');
						
						// 요일을 한글로 표시하기 위한 배열
						var dayNames = ["(일)", "(월)", "(화)", "(수)", "(목)", "(금)", "(토)"];
						var dayOfWeek = dayNames[now.getDay()];
						
						
						var formattedDate = year + "-" + month + "-" + date + dayOfWeek;
						
						console.log(formattedDate);

						
						var newChatMessage = $("<li>").append(
					    $("<div>").addClass("conversation-list").append(
					        $("<div>").addClass("d-flex").append(
					            $("<img>").attr("src",profileImg).addClass("rounded-circle avatar-sm"),
					            $("<div>").addClass("flex-1 me-3").append(
					                $("<div>").addClass("d-flex justify-content-between").append(
					                    // 이름
					                    $("<h5>").addClass("fs-16 conversation-name align-middle").text(userId)
					                ),
					                $("<div>").addClass("ctext-wrap").append(
					                    // 보낸 메시지
					                    $("<div>").addClass("ctext-wrap-content").append(
					                        $("<p>").addClass('mb-0 text-start').html(content),
					                        // 보낸 시간
					                        $("<span>").addClass("time fw-normal text-muted ms-0 ms-md-4").text(formattedDate)
					                   )
					               )
					           )
					       )
					   )
					);
					$("#total-chat-list").append(newChatMessage);
					var sessionUserName =  $('#nickname').text();
				
			    
				    $("li").each(function() {
				        var userName = $(this).find("h5.conversation-name").text().trim();
				        if (userName === sessionUserName) {
				            $(this).addClass("right");
				        }
				    });
					scrollElement.scrollTop = scrollElement.scrollHeight;
					}
				}
                
                ws.onclose = function(){
					alert("서버와 연결이 종료되었습니다");
				}
				
				  
                $("#chatBtn").unbind().bind("click", function(){
					if($("#chatInput").val() == ""){
						alert("내용을 입력해 주세요");
					}else{
						var nickName = $('#nickname').html();
						var profileImg = $('#profileImg').val();
						ws.send(userAccountId + ":"+$("#chatInput").val()+"#&nickName_"+nickName+"#&profileImg_"+profileImg);
		                var element = document.querySelector('.chat-message-list');
		    			element.scrollTop = element.scrollHeight; 
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
    })
    
    
    
    
})


    
    