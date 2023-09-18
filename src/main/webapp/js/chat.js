window.onload = function() {
    var rooms = document.querySelectorAll('.chatRoomList');
    for (var i = 0; i < rooms.length; i++) {
        rooms[i].addEventListener("click", function() {
			var chatDetail = document.getElementById('chatDetail');
			if(chatDetail){
				chatDetail.style.display = 'none';
				}
            console.log("Element was clicked!");
            var inputElement = this.querySelector('input');
            var chroId = inputElement.value;
            console.log(chroId); // roomId 출력
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
        });//clickListener
    }//for문
}
