/**
	좋아요 기능
 */
$(document).on("click",".like-do",function(e){
	if($(".dropdown-header")[0]!=undefined){
		e.stopPropagation();
		$.ajax({
			type:"post",
			url:"./communityLike.do",
			data:{
				"type":$(e.target).attr("src").split("_")[1].split(".")[0],
				"id": $(e.target).attr("id")
			},
			success: function(data){
				const src = e.target.getAttribute("src");
				const newSrc = src.substring(0, src.indexOf("_") + 1) + data.type + ".png";
				$(e.target).attr('src', newSrc);
				$("#lickCount").text(data.count);
			},
			error: function(){
				alert("잘못된 요청입니다.");
			}
		});	
	} else {
		e.stopPropagation();
		$("#sa-warning").click();
	}
})

/**
	목록 좋아요 기능
 */
$(document).on("click",".list-like-do",function(e){
	if($(".dropdown-header")[0]!=undefined){
		e.stopPropagation();
		$.ajax({
			type:"post",
			url:"./communityLike.do",
			data:{
				"type":$(e.target).attr("src").split("_")[1].split(".")[0],
				"id": $(e.target).attr("id")
			},
			success: function(data){
				const src = e.target.getAttribute("src");
				const newSrc = src.substring(0, src.indexOf("_") + 1) + data.type + ".png";
				$(e.target).attr('src', newSrc);
				$(e.target).closest("div").next().find("span").text(data.count);
			},
			error: function(){
				alert("잘못된 요청입니다.");
			}
		});	
	} else {
		e.stopPropagation();
		$("#sa-warning").click();
	}
})

/**
	로그인 경고창
 */
$(document).on("click","#sa-warning",function(){
	Swal.fire({
	    title: "로그인시 이용할 수 있습니다.",
	    text: "로그인 하시겠습니까?",
	    icon: "warning",
	    showCancelButton: true,
	    customClass: {
	        confirmButton: 'btn btn-primary w-xs mt-2 me-2',
	        cancelButton: 'btn btn-danger w-xs mt-2',
	    },
	    confirmButtonText: "로그인",
	    cancelButtonText: "취소",
	    buttonsStyling: false,
	    showCloseButton: true
	}).then(function (result) {
	    if (result.value) {
	        location.href="./loginForm.do";
	    }
	});
});

/**
	상세보기 이동
 */
$(document).on("click",".view-detail",function(e){
	location.href="./communityDetails.do?id="+$(e.target).closest('.view-detail').attr("id");
});

/**
	유효값 경고창
 */
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

/**
	페이지 이동시 페이지 목록 변경
 */
function pageChange(startPage, endPage, page, totalPage){
	const pageList = []
	const preId = (startPage-5)<1?1:(startPage-5);
	const postId = (startPage+5)>totalPage?totalPage:(startPage+5);
	if(startPage!=1){		
		pageList.push({"id":1,"value":"<i id='1' class='mdi mdi-page-first'></i>"});
		pageList.push({"id":preId,"value":"<i id='"+preId+"' class='ri-arrow-left-s-line'></i>"});
	}
	for(i=startPage;i<endPage+1;i++){
		pageList.push({"id":i,"value":i,"state":i==page?"active":""});	
	}
	if(endPage!=totalPage){
		pageList.push({"id":postId,"value":"<i id='"+postId+"' class='ri-arrow-right-s-line'></i>"});
		pageList.push({"id":totalPage,"value":"<i id='"+totalPage+"' class='mdi mdi-page-last'></i>"});
	}
	
	var pageSource = $("#page-list-template").html();
	var pageTemplate = Handlebars.compile(pageSource);
	
	// Handlebars.js 템플릿 컴파일 시, htmlOrText 헬퍼 함수 등록
	Handlebars.registerHelper('htmlOrText', function(value) {
	    // value가 HTML 태그를 포함하는지 여부 확인
	    const containsHtmlTags = /<[a-z][\s\S]*>/i.test(value);
	
	    // value가 HTML 태그를 포함하면서 안전한 방법으로 출력하기 위해 {{{}}} 사용
	    if (containsHtmlTags) {
	        return new Handlebars.SafeString(value);
	    }
	
	    // value가 일반 문자열인 경우 {{}} 사용하여 출력
	    return value;
	});
	
	$(".pagination").children().remove();
	
	var pageData = {
		"page":pageList
	}
	
	var pageItem = pageTemplate(pageData);
	$(".pagination").append(pageItem);
}

