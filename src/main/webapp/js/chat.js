window.onload = function() {




    function openChat(){
        var chatDetail = this.val();
        
        console.log("Element was clicked!",chatDetail);
        var inputElement = this.querySelector('input');
        var chroId = inputElement.value;
        
        // $.ajax({
        //     url:'./chatRoom.do',
        //     type:'GET',
        //     data:{
        //         "chroId":chroId
        //     },
        //     success:function(response){
        //         console.log(response.chroTitle);
        //         $('#chatRoomTitle').text(response.chroTitle);
        //         var chatDate = new Date(response.chroDate);
        //         var year = chatDate.getFullYear();
        //         var month = ("0" + (chatDate.getMonth() + 1)).slice(-2);
        //         var day = ("0" + chatDate.getDate()).slice(-2);
            
        //         var formattedDate = year + '-' + month + '-' + day;
        //         $('#chatRoomDate').text("개설일 : "+formattedDate);
        //         if(chatDetail){
        //             chatDetail.style.display = 'block';
        //         }
                
        //     },
        //     error:function(error){
        //         console.log("chatRoom.do 오류");
        //         console.log(error);
        //     }
        // });//ajax
    }

}