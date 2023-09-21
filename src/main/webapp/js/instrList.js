var page = 2; // 초기 페이지 번호 설정


$(document).ready(function() {
	 $('#scrollToTopButton').click(function() {
    	    $('#moreList').animate({ scrollTop: 0 }, 'slow');
    	});
    	
$('#moreList').scroll(function() {
   if ($('#moreList').scrollTop() + $('#moreList').innerHeight() >= this.scrollHeight) {
       if ($("#orderSelect").is(':visible')) {
       	if(hasMoreList){
			var order = $('#orderSelect').val();
			console.log("Scrolled API 실행 order: ", order);
            loadMoreList(order);
         }
       }
   }
});

$('#orderSelect').change(function() {
	listPage = 1; // 페이지 번호 초기화
	hasMoreList = true; // 리뷰 로딩 가능 상태로 설정
	
	$('#moreList').empty(); // 기존 리뷰 내용 제거
	
	var order = $(this).val();
	
	loadMoreList(order); 
});
	
	var page = 0;
	
         function loadMoreData() {
	        page++; // 페이지 번호 증가
	        $('form').trigger('submit'); // form 제출 이벤트 발생시킴
	    }
	    
	    $('#more').click(function(e) { 
	        e.preventDefault();
	        loadMoreData(); 
	    });
	
	//검색할 때 엘라스틱 작동
	$('form').on('submit', function (event) {
        if (event.originalEvent) { // 폼 제출에 의해 이벤트가 발생한 경우, 페이지번호 초기화
	        page = 0;
	    }
	    
        event.preventDefault();
        
        
        var formDataArray = $("#searchForm").serializeArray();
        var formData = {};
        
        // 과목 수집
	    var subjects = [];
	    $('#selectedSubjects div').each(function() {
	        subjects.push($(this).attr('data-value'));
	    });
	    
	    if(subjects.length > 0){
	        formData['subjects'] = subjects;
	    }
        
        formDataArray.forEach(function (item) {
            if(item.name === 'feeLt' || item.name === 'feeGt'){
            	formData['fee'] = formData['fee'] || {};
                formData['fee'][item.name === 'feeLt' ? 'lt' : 'gt'] = parseInt(item.value);
            } else if (item.value && item.name !== "subjects") {
		        	formData[item.name] = item.value;
            }
        });
        
        formData['page'] = page;
        
        console.log("보내는 값: ",JSON.stringify(formData));

        $.ajax({
        	url: "./instrSearch.do",
            method: "post",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function (data) {
            	var $results = $(".output-area");
            	  
				$("#orderSelect").hide();
				if(data.length > 7){
	        		$("#more").show();
				}
            	    /*$results.empty();*/ // 이전 검색 결과를 지움

            	    if (data.length === 0 && page ==0) {
            	        $results.html("<p>검색 조건에 해당하는 강사를 찾을 수 없습니다.</p>");
            	    } else {
            	        console.log("엘라스틱 서치 성공!")
						console.log(data);
			            	var html = '';
			                var lists = data;
			                for (var i = 0; i < lists.length; i++) {
			                    var instr = lists[i];
			            		console.log("instr:::::",instr);
			            html +="	<div class='col-xxl-3 col-md-6'>                                                                                                   ";
						html +="	<div class='card team-box' ";
						html +=`onclick='location.href="./instrDetail.do?seq=${instr.inpr_seq}&loginId=${userinfo}"'>`;
						html +="		<div class='card-body p-4'>                                                                                                    ";
						html +="			<div class='row mb-3'>                                                                                                     ";
						html +="				<div class='col'>                                                                                                      ";
						html +="					<div class='flex-shrink-0 me-2'>   ";
						if (instr.inpr_cert == 'S') {
							html +="						<span title='경력이 인증된 강사' data-bs-toggle='tooltip' data-bs-placement='top'                                                                                                          ";
							html +="							class='badge bg-success-subtle text-success member-designation me-2'>                                      ";
							html +="							<i class=' bx bxs-user-check' style='font-size: 20px;'></i>                                                ";
							html +="						</span>                                                                                                        ";
						}
						html +="					</div>                                                                                                             ";
						html +="				</div>                                                                                                                 ";
		                html +="                                                                                                                                       ";
						html +="				<div class='col-auto text-end dropdown'>                                                                               ";
						html +="<span>&nbsp;</span>";
						 if (instr.ingi == 'HOT') {
		                        html += "<span class='badge bg-danger-subtle text-danger member-designation me-2' style='font-size: 15px;'>HOT</span>";
		                    }
						 if (instr.review_count > 0) {
		                        html += "<span class='badge bg-info-subtle text-info member-designation me-2' style='font-size: 15px;'>후기있음</span>";
		                    }
						html +="				</div>                                                                                                                 ";
						html +="			</div>                                                                                                                     ";
						html +="			<div class='text-center mb-3'>                                                                                             ";
						html +="				<div class='avatar-lg mx-auto'>                                                                                        ";
						var userinfo = instr.login_info;
						html += `<img src="${userinfo ? instr.user_profile_file : './assets/images/users/user-dummy-img.jpg'}" alt='' `;
						html += `data-bs-toggle="${!userinfo? 'tooltip' : ''}" title="${!userinfo? '로그인 후 볼 수 있습니다.' : ''}"`
						html += `class='member-img img-fluid d-block rounded-circle'>`;
						html +="				</div>                                                                                                                 ";
						html +="			</div>                                                                                                                     ";
						html +="			<div class='text-center'>                                                                                                  ";
						html += `<h5 class='fs-16 mb-1'>${instr.nickname}</h5>`;
						html += `<span class="text-muted fs-13 mt-1 text-truncate">만 ${instr.user_age} 세</span>`;
						
						html +="				<div class='row'>                                                                                                      ";
						html +="					<div class='col-6'>                                                                                                ";
						html +="						<div class='mt-3'>                                                                                             ";
						html +="							<p class='mb-0 text-muted'>전문분야</p>                                                                    ";
						html +="							<h5 class='mt-1 fs-16 mb-0 text-truncate'>                      ";
						
						var subjectsMajorTitleList = JSON.parse(instr.subjects_major_title);
						if (Array.isArray(subjectsMajorTitleList)) {
						    subjectsMajorTitleList = subjectsMajorTitleList.map(item => item.trim());
						    html += subjectsMajorTitleList[0];
						}
						
						html +="							</h5>                       ";
						html +="						</div>                                                                                                         ";
						html +="					</div>                                                                                                             ";
						html +="					<div class='col-6'>                                                                                                ";
						html +="						<div class='mt-3'>                                                                                             ";
						html +="							<p class='mb-0 text-muted'>최소 수업료(만원)</p>                                                                        ";
						html +=`<h5 class="mt-1 fs-16 mb-0 text-truncate">${instr.fee == 0 ? '미등록':instr.fee}</h5>`;
						html +="						</div>                                                                                                         ";
						html +="					</div>                                                                                                             ";
						html +="				</div>                                                                                                                 ";
						html +="			</div>                                                                                                                     ";
						html +="		</div>                                                                                                                         ";
						html +="		<div class='card-footer pt-3 border-top px-4'>                                                                                 ";
						html +="			<p class='mb-1 text-muted text-truncate'>                                                                                  ";
						html +="				<i class='mdi mdi-book'></i>전문분야:                                                                                  ";
						  for(var j=0; j<subjectsMajorTitleList.length; j++){
							    if(j != subjectsMajorTitleList.length - 1){
							        subjectsMajorTitleList[j] += ', '; 
							    }
							}
						html += subjectsMajorTitleList.join("");
						html +="			</p>                                                                                                                       ";
						html +="			<p class='mb-1 text-muted text-truncate'>                                                                                  ";
						html +="				<i class='mdi mdi-book'></i>가능한 과목:                                                                               ";
						var subjectsTitleList = JSON.parse(instr.subjects_title);
						
						if (Array.isArray(subjectsTitleList)) {
						    for(var k=0; k<subjectsTitleList.length; k++){
						        // 이미 배열의 각 요소는 문자열입니다.
						        if(k != subjectsTitleList.length - 1){
						            subjectsTitleList[k] += ', '; 
						        }
						    }
						}
						html += subjectsTitleList.join("");
						
						html +="			</p>                                                                                                                       ";
						html +="		</div>                                                                                                                         ";
						html +="	</div>                                                                                                                             ";
						html +="</div>                                                                                                                                 ";
			                }	
					    if (event.originalEvent) { // 폼 제출에 의해 이벤트가 발생한 경우, 출력 영역 초기화
				            $results.html(html);
				            $results.animate({ scrollTop: 0 }, 'slow');
				        } else {
				            $results.append(html);
				        }
				        
				        if (data.length < 8) {
						    $("#more").hide();
						} else {
						    $("#more").show();
						}
						
						$(function () {
						    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
						    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
						        return new bootstrap.Tooltip(tooltipTriggerEl, { delay: { "show": 100, "hide": 100 } })
						    })
						})
			       }
	            },
            error: function(){
            	alert("오류");
            }
        });
	});
	
});

var listPage = 2;
var hasMoreList = true;
var isAjaxProcessing = false;

function loadMoreList(order){
    if (!hasMoreList || isAjaxProcessing) return; // 더 이상 로드할 리뷰가 없으면 종료
	isAjaxProcessing = true;
	console.log("loadMoreList 보내는 값: ", order, listPage);
    $.ajax({
        url: './instrMoreList.do',
        method: 'GET',
        data: { start: (listPage - 1) * 8 + 1,
                end: listPage * 8,
                order: order }, 
        success: function(data) {
			var lists = data.lists;
			
			lists.forEach(function(subject){
				subject.subjectsTitle = JSON.parse(subject.subjectsTitle);
				subject.subjectsMajorTitle = JSON.parse(subject.subjectsMajorTitle);
			});
	
            var source = $("#instrList-template").html();
            var template = Handlebars.compile(source);
            
            var html = template(lists); 
            
            $('#moreList').append(html); // 새로운 내용 추가
            
            hasMoreList = data.hasMore; // 다음 페이지의 존재 여부 업데이트
			if (hasMoreList){
				listPage++; // 페이지 번호 증가
				console.log("증가하는 다음 페이지 :",listPage);
			}
			isAjaxProcessing = false;
			 $(function () {
				    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
				    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
				        return new bootstrap.Tooltip(tooltipTriggerEl, { delay: { "show": 100, "hide": 100 } })
				    })
				})	
            
        },
        error: function(err) {
           console.error(err);
           isAjaxProcessing = false;
       }
   });
}
	