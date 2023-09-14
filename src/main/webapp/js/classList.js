function ajaxPaging() {

	$.ajax({
		url: "./classListLoad.do",
		type: "get",
		async: true,
		data: {
			"page": page,
			"shopNo": shopNo
		},
		dataType: "JSON",
		success: function(resp) {
			console.log(typeof resp, resp);
			//클래스를 출력할 요소 선택
			var normalClass = $("#normalClass");
			// 기존 내용 삭제
			normalClass.empty();

			for (var i = 1; i <= 20; i++) {
				var classItem = $('<div class="mb-2"></div>');

				var messageList = $('<div class="message-list mb-0 p-1"></div>');
				var list = $('<div class="list"></div>');

				var colMail1 = $('<div class="col-mail col-mail-1"></div>');
				var title = $('<div class="d-flex title align-items-center"></div>');
				title.append($('<img src="assets/images/users/avatar-1.jpg" class="avatar-sm rounded-circle" alt="">'));

				var flexContainer = $('<div class="flex-1 ms-2 ps-1 mt-1"></div>');
				flexContainer.append($('<h5 class="fs-15 mb-0"><a href="" class="text-body">클래스장 닉네임</a></h5>'));
				flexContainer.append($('<a href="" class="text-muted text-uppercase fs-13 mt-1">대전광역시 동구 중앙동</a>'));

				title.append(flexContainer);

				colMail1.append(title);
				colMail1.append($('<span class="star-toggle bx bx-star"></span>'));

				var colMail2 = $('<div class="col-mail col-mail-2"></div>');
				var subjectLink = $('<a href="classDetail.do" class="subject text-body">클래스 제목<span></span><span></span><span></span></a>')
				subjectLink.find('span:eq(0)').addClass('teaser text-muted fw-normal').text('과목 명 / 과목 명 / 과목 명');
				subjectLink.append('<span></span><span></span>'); // 남자만, 20~40 등의 badge 추가

				colMail2.append(subjectLink);
				colMail2.append('<i class= "bx bx-link-alt me - 2 fs - 15 align - middle "></i > 오늘');

				list.append(colMail1);
				list.append(colMail2);
				messageList.append(list);
				classItem.append(messageList);
				normalClass.append(classItem);
			}
		},
		error: function() {
			alert("통신에 실패하였습니다");
		}
	});
}