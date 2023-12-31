$(document).ready(function() {
	
	var userId =  $('#userId').val();
	$.ajax({
		url: './notificationList.do',
        type: 'POST',
        data: { "alarAccountId": userId},
        success: function(response) {
			if(response.length!=0){
				$('.empty-notification-elem').remove();
			}
			response.forEach(function(vo) {
				var content = vo.alarContent;
				var alarId = vo.alarId;
				var gubun = alarId.substring(0,4);
				if(gubun=='AT_C'){//클래스 확정 여부, 강사 매칭 여부
					var image = "bx bx-pencil";
				}else if(gubun=='AT_P'){//결제 상태
					var image = "bx bx-credit-card";
				}else if(gubun=='AT_R'){//게시글 답글
					var image = "bx bx-comment-detail";
				}else if(gubun=='AT_I'){//경력 인증 상태
					var image = "bx bx-star";	
				}else if(gubun=='AT_S'){// 강의료 정산
					var image = "bx bx-money-withdraw";
				}else{
					var image = "bx bx-question-mark";
				}
				var status = vo.alarChecked;
				var link = vo.alarReplySeq;
				link = link.replace('&','%26');
				var time = vo.alarEventdate;
				
				if(status==='N'){
					var htmlContent = `
				    <div class="text-reset notification-item d-block dropdown-item position-relative unread-message" value="${alarId}">
				        <div class="d-flex">
				            <div class="avatar-xs me-3 flex-shrink-0">
					            <span class="active-badge position-absolute start-20 translate-middle p-1 bg-danger rounded-circle">
	                                <span class="visually-hidden">New alerts</span>
	                            </span>
				                <span class="avatar-title bg-secondary-subtle  text-primary rounded-circle fs-16">
				                    <i class="${image}"></i>
				                </span>
				            </div>
				            <div class="flex-grow-1">
				                <a id="alarmLink" href="./notificationLink.do?link=${link}&alarId=${alarId}" class="stretched-link">
				                    <h6 class="mt-0 mb-2 fs-14 lh-base">${content}
				                    </h6>
				                </a>
				                <p class="mb-0 fs-11 fw-medium text-uppercase text-muted">
				                    <span><i class="mdi mdi-clock-outline"></i> ${time}</span>
				                </p>
				            </div>
				            <div class="px-2 fs-15">
				                <div class="form-check notification-check">
				                    <input name="form-check-input" class="form-check-input" type="checkbox" id="all-notification-check03">
				                    <label class="form-check-label" for="all-notification-check03"></label>
				                </div>
				            </div>
				        </div>
				    </div>`;	
				}else{
					var htmlContent = `
				    <div class="text-reset notification-item d-block dropdown-item position-relative" value="${alarId}">
				        <div class="d-flex">
				            <div class="avatar-xs me-3 flex-shrink-0">
				                <span class="avatar-title bg-secondary-subtle  text-primary rounded-circle fs-16">
				                    <i class="${image}"></i>
				                </span>
				            </div>
				            <div class="flex-grow-1">
				                <a id="alarmLink" href="./notificationLink.do?link=${link}&alarId=${alarId}" class="stretched-link">
				                    <h6 class="mt-0 mb-2 fs-14 lh-base">${content}
				                    </h6>
				                </a>
				                <p class="mb-0 fs-11 fw-medium text-uppercase text-muted">
				                    <span><i class="mdi mdi-clock-outline"></i> ${time}</span>
				                </p>
				            </div>
				            <div class="px-2 fs-15">
				                <div class="form-check notification-check">
				                    <input name="form-check-input" class="form-check-input" type="checkbox" id="all-notification-check03">
				                    <label class="form-check-label" for="all-notification-check03"></label>
				                </div>
				            </div>
				        </div>
				    </div>`;
				}
				$('.simplebar-content').eq(0).append(htmlContent);
			})
			var count = $('.simplebar-content').eq(0).find('.unread-message').length;
			$('.notification-unread').text(count);
			
			
			var aLLCount = $('.simplebar-content').eq(0).find('.notification-item').length;
			$('.notification-badge').text(aLLCount);
			
			
			if (document.getElementsByClassName("notification-check")) {
            function emptyNotification() {
                if (document.querySelectorAll(".notification-item").length > 0) {
                    document.querySelectorAll(".notification-title").forEach(function (item) {
                        item.style.display = "block";
                    });
                } else {
                    document.querySelectorAll(".notification-title").forEach(function (item) {
                        item.style.display = "none";
                    });
                    var emptyNotificationElem = document.querySelector("#notificationItemsTabContent .empty-notification-elem")
                    if (!emptyNotificationElem) {
                        document.getElementById("notificationItemsTabContent").innerHTML += '<div class="empty-notification-elem text-center px-4">\
						<div class="mt-3 avatar-md mx-auto">\
							<div class="avatar-title bg-info-subtle  text-info fs-24 rounded-circle">\
							<i class="bi bi-bell "></i>\
							</div>\
						</div>\
						<div class="pb-3 mt-2">\
							<h6 class="fs-16 fw-semibold lh-base">알림이 없습니다 </h6>\
						</div>\
					</div>'
                    }
                }
            }
            emptyNotification();
			Array.from(document.querySelectorAll(".notification-check input")).forEach(function (element) {
                element.addEventListener("change", function (el) {
                    el.target.closest(".notification-item").classList.toggle("active");
                    var checkedCount = document.querySelectorAll('.notification-check input:checked').length;
                    if (el.target.closest(".notification-item").classList.contains("active")) {
                        (checkedCount > 0) ? document.getElementById("notification-actions").style.display = 'block' : document.getElementById("notification-actions").style.display = 'none';
                    } else {
                        (checkedCount > 0) ? document.getElementById("notification-actions").style.display = 'block' : document.getElementById("notification-actions").style.display = 'none';
                    }
                    document.getElementById("select-content").innerHTML = checkedCount
                });
                var notificationDropdown = document.getElementById('notificationDropdown')
                if (notificationDropdown) {
                    notificationDropdown.addEventListener('hide.bs.dropdown', function (event) {
                        element.checked = false;
                        document.querySelectorAll('.notification-item').forEach(function (item) {
                            item.classList.remove("active");
                        })
                        emptyNotification();
                        document.getElementById('notification-actions').style.display = '';
                    });
                }
            });
            var removeItem = document.getElementById('removeNotificationModal');
            if (removeItem) {
                removeItem.addEventListener('show.bs.modal', function (event) {
                    document.getElementById("delete-notification").addEventListener("click", function () {
                        Array.from(document.querySelectorAll(".notification-item")).forEach(function (element) {
                            if (element.classList.contains("active")) {
                                element.remove();
                            }
                            Array.from(document.querySelectorAll(".notification-badge")).forEach(function (item) {
                                item.innerHTML = document.querySelectorAll('.notification-check input').length;
                            })
                            Array.from(document.querySelectorAll(".notification-unread")).forEach(function (item) {
                                item.innerHTML = document.querySelectorAll('.notification-item.unread-message').length;
                            })
                        });
                        emptyNotification();
                        document.getElementById("NotificationModalbtn-close").click();
                    })
                })
            }
           }
		},
        error:function(error){
			console.log(error);
		}
	})
	
	
	$('#delete-notification').on('click', function() {
		var checkedValues = [];
		var checkList = $('.notification-item.active');
		checkList.each(function(){
			checkedValues.push($(this).attr('value'));	
		})
		
		$.ajax({
			url:'./delAlarm.do',
			type: 'POST',
	        data: { "checkedValues": checkedValues},
	        traditional: true,
	        success: function() {
				
			},
	        error:function(){
		
			}
		})
	});
	
	
	
	
})