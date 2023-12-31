$(document).ready(function() {
    $('#chatInput').on('keypress', function(event){
        if(event.which == 13){
            $('#chatBtn').click();
        }
    });
    var ws = null;
    
    $('.chatRoomList').each(function() {
		var chroId = $(this).data('chroid');
		var chusAccountId = $('#userAccountId').val();
		var currentListItem = $(this);
		
    	 $.ajax({
            url: './countChatAlarm.do',
            type: 'POST',
            data: { "chroId": chroId,
            		"chusAccountId":chusAccountId },
            success: function(response) {
                if(response!=0){
	                var newSpan = $('<span class="badge bg-danger rounded-pill"></span>').text(response);
				    currentListItem.find('.countOfMessage').append(newSpan);
				}
            },
            error: function(error) {
                console.error(error);
            }
    	})
    
    })
    
$(window).on("beforeunload", function () {
	if(ws){
		ws.close();
	}
});






$('.chatRoomList').click(function(){
	
	var chroId = $('#selectedChatRoom').val();
	var chusAccountId = $('#userAccountId').val();

	$.ajax({
        url:'./updateUserChatCount.do',
        type:'GET',
        data:{
            "chroId":chroId,
            "chusAccountId":chusAccountId
        },
        success:function(){
		},
        error:function(){
			alert("채팅 수 저장 오류");					
		}
    })
	var chroId = $(this).data('chroid');
	var chusAccountId = $('#userAccountId').val();
	var currentListItem = $(this);
    currentListItem.find('.countOfMessage').children().remove();
	 $.ajax({
        url: './countChatAlarm.do',
        type: 'POST',
        data: { "chroId": chroId,
        		"chusAccountId":chusAccountId },
        success: function() {
        },
        error: function(error) {
            console.error(error);
        }
	})
		
	$(".chatRoomList").removeClass("active");

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
				             	    
                ws = null;
				var url = location.href;//현재 웹주소
				var checkUrl = "ws:"+url.substring(url.indexOf('//'),url.lastIndexOf('/')+1)+"wsChat.do";//웹소켓 주소
				var userAccountId = $('#userAccountId').val();
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
                 
                 
                 
                
                ws.onopen = function(){
				}
				
                 
                 
                ws.onmessage = function(event){
					var msg = event.data;
				    
				    if(msg.startsWith("[나]")){// 내 대화내용
				        var content = msg.substring(3, msg.indexOf("#&nickName_")).trim();
				        
				        var startNickNameIndex = msg.indexOf("#&nickName_") + 11;
				        var endNickNameIndex = msg.indexOf("#&profileImg_");
				        
				        if (startNickNameIndex < endNickNameIndex) {
				            var userId = msg.substring(startNickNameIndex, endNickNameIndex).trim();
				
				            var startProfileImgIndex = endNickNameIndex + 13;
				            if (startProfileImgIndex <= msg.length) {
				                var profileImg = msg.substring(startProfileImgIndex).trim();
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
					var chroId = $('#selectedChatRoom').val();
					var chroChatLog = $('#total-chat-list').html();
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
						}
					})
					}else{//다른사람 입력값
						var content = msg.substring(msg.indexOf("]")+1, msg.indexOf("#&nickName_")).trim();
				        
				        var startNickNameIndex = msg.indexOf("#&nickName_") + 11;
				        var endNickNameIndex = msg.indexOf("#&profileImg_");
				        
				        if (startNickNameIndex < endNickNameIndex) {
				            var userId = msg.substring(startNickNameIndex, endNickNameIndex).trim();
				
				            var startProfileImgIndex = endNickNameIndex + 13;
				            if (startProfileImgIndex <= msg.length) {
				                var profileImg = msg.substring(startProfileImgIndex).trim();
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
					
					var chroId = $('#selectedChatRoom').val();
					var chusAccountId = $('#userAccountId').val();
	
					$.ajax({
				        url:'./updateUserChatCount.do',
				        type:'GET',
				        data:{
				            "chroId":chroId,
				            "chusAccountId":chusAccountId
				        },
				        success:function(){
						},
				        error:function(){
						}
				    })
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
             console.log(error);
         }
     });//ajax
    })
    
    
    
    
})


    
    