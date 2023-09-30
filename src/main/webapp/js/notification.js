$(document).ready(function() {
	
	var userId =  $('#userId').val();
	console.log(userId);
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
				var gubun = content.substring(0,3);
				if(gubun=='AT_C'){//클래스 확정 여부, 강사 매칭 여부
					var image = "mdi-lead-pencil";
				}else if(gubun=='AT_P'){//결제 상태
					var image = "bx bx-credit-card";
				}else if(gubun=='AT_R'){//게시글 답글
					var image = "mdi-message-reply-text-outline";
				}else if(gubun=='AT_I'){//경력 인증 상태
					var image = "mdi-check-decagram-outline";	
				}else if(gubun=='AT_S'){// 강의료 정산
					var image = "star";
				}else{
					var image = " bx bx-cable-car";
				}
				var alarId = vo.alarId;
				var status = vo.alarChecked;
				var link = vo.alarReplySeq;
				var time = vo.alarEventdate;
				
				if(status==='N'){
					var htmlContent = `
				    <div class="text-reset notification-item d-block dropdown-item position-relative unread-message">
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
				                    <input class="form-check-input" type="checkbox" value="" id="all-notification-check03">
				                    <label class="form-check-label" for="all-notification-check03"></label>
				                </div>
				            </div>
				        </div>
				    </div>`;	
				}else{
					var htmlContent = `
				    <div class="text-reset notification-item d-block dropdown-item position-relative unread-message">
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
				                    <input class="form-check-input" type="checkbox" value="" id="all-notification-check03">
				                    <label class="form-check-label" for="all-notification-check03"></label>
				                </div>
				            </div>
				        </div>
				    </div>`;
				}

				$('.simplebar-content').eq(0).append(htmlContent);
				
			})
		},
        error:function(error){
			console.log(error);
		}
	})
	
	
	
})