var shouldConfirmUnload = true;

$(document).ready(function(){
	
	$(window).on("beforeunload", function (e) {
			console.log("왜!!!!!");
	    if (shouldConfirmUnload) {
	        return;
	    } else {
			console.log("진짜 왜?!");
		}
	});
	
	// 1. 옵저버 감시 대상 설정
	const target = document.getElementsByClassName("ck-content")[0]
	
	// 2. 옵저버 콜백 생성
	const callback = (mutationList, observer) => {
		if(mutationList[0].removedNodes[0]){			
			const node = mutationList[0].removedNodes[0].childNodes[0]
		  	if(node.localName == "img"){
				$.ajax({
					type:"post",
					url:"./commynityRemoveImage.do",
					data: "saveName="+node.currentSrc.substring(node.currentSrc.lastIndexOf("/")+1),
					error: function(){
						alert("잘못된 요청입니다.");
					}
				});
			}
		}
	};
	
	// 3. 옵저버 인스턴스 생성
	observer = new MutationObserver(callback); // 타겟에 변화가 일어나면 콜백함수를 실행하게 된다.
	
	// 4. DOM의 어떤 부분을 감시할지를 옵션 설정
	const config = { 
	    childList: true, // 자식노드 추가/제거 감지
	};
	
	// 5. 감지 시작
	observer.observe(target, config);
	
});

$(document).on("change", "#class", function(e){
	if($(e.target).val()=='none'){
		$("#class-selected").remove();
		$(".choices").css("display","block");
	} else {
		$.ajax({
			type:"post",
			url:"./getJilmunClassList.do",
			data:{
				"clasId":$(e.target).val()
			},
			success: function(data){
				$("#formSubject").val(data.code);
				$("#class-selected").remove();
				$(".choices").css("display","none");
				
				var options = [];
				var subjects = [];
				for(i=0; i<data.title.length;i++){
					options.push({"option":data.title[i]});
					subjects.push({"no":i,"subject":data.title[i]});
				}
					
				var selSource = $("#select-template").html();
				var selTemplate = Handlebars.compile(selSource);
				var selData = {
					"options":options,
					"subjects":subjects
				}
				var selItem = selTemplate(selData);
				
				$("#write-top").append(selItem);
			},
			error: function(){
				alert("잘못된 요청입니다.");
			}
		});
	}
})

$(document).on("click","#write-btn",function(e){
	e.preventDefault()
	
	// 에디터 내용 가져오기
	const data = editor.getData();
	// hidden form 요소에 할당
	$("#ckeditor").text(data);
	// 6. 감지 중지
	observer.disconnect();
	
	
	if(($("#title").length>0 && $("#title").val()=='') ||
		($("#ckeditor").length>0 && $("#ckeditor").val()=='') ||
		($("#viewGroup").length>0 && $("#viewGroup").val()=='N') ||
		($("#downloadGroup").length>0 && $("#downloadGroup").val()=='N') ||
		($("#subjectCode").length>0 && $("#subjectCode").val()=='')){
		$("#sa-basic").click();
	} else {
		shouldConfirmUnload = false;
//		$(window).off("beforeunload");
		$("form")[0].submit();
	}
});

$(document).on("click","#sa-basic",function(){
	Swal.fire({
	    title: '모든 값을 입력해주세요',
	    buttonsStyling: false,
	    customClass: {
	        confirmButton: 'btn btn-primary w-xs mt-2',
	    },
	    showCloseButton: true
    })
});