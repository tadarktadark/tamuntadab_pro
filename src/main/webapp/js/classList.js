//페이지 로드 되자마자 
$(function() {
	var page = 1;
	ajaxPaging(page);
});

function ajaxPaging(page) {
	$.ajax({
		url: "./classListLoad.do",
		type: "get",
		async: true,
		data: {
			"page": page
		},
		dataType: "JSON",
		success: function(resp) {
			console.log(resp.pVo);      // 페이지 정보에 접근
			console.log(resp.classList); // 클래스 리스트에 접근

			var normalClass = $("#normalClass");
			var classList = resp.classList; // 서버에서 받은 클래스 리스트
			var pVo = resp.pVo;
			normalClass.empty();
			normalClass.append('<h6 class="text-muted text-uppercase mb-3">클래스 목록</h6>');
			html = "";
			console.log(classList.length);
			for (var i = 0; i < classList.length; i++) {

				var userNickname = classList[i].userVo[0].userNickname;
				var truncatedNickname = userNickname.length > 8 ? userNickname.substring(0, 8) + '...' : userNickname;
					
				var location = classList[i].clasLocation;
				var truncatedlocation = location.length > 12 ? location.substring(0, 10) + '...' : location;
				
				html = '';
				html += '<div class="mb-2">';
				html += '	<div class="message-list mb-0 p-1">';
				html += ' 		<div class="list">';
				html += '			<div class="col-mail col-mail-1">';
				html += '				<div class="d-flex title align-items-center">';
				html += '				<img src="assets/images/users/avatar-1.jpg" class="avatar-sm rounded-circle" alt="">';
				html += '					<div class="flex-1 ms-2 ps-1 mt-1">';
				html += '						<h5 class="fs-15 mb-0"><a class="text-body" data-toggle="tooltip" title="' + userNickname + '">' + truncatedNickname + '</a></h5>';
				html += '						<a class="text-muted text-uppercase fs-13 mt-1" data-toggle="tooltip" title="' + location + '">' + truncatedlocation + '</a>';
				html += '					</div>';
				html += '				</div>';
				html += '				<span class="star-toggle bx bx-star"  style="padding-left:30px;"></span>';
				html += '			</div>';
				html += '<div class="col-mail col-mail-2">';
				if (classList[i].clasSeongbyeolJehan == "MONLY") {
					html += '<span class="bg-danger badge me-2">남자만</span>';
				} else if (classList[i].clasSeongbyeolJehan == "MONLY") {
					html += '<span class="bg-danger badge me-2">여자만</span>';
				}
				if (classList[i].clasNaiJehan != null) {
					html += '<span class="bg-info badge me-2">' + classList[i].clasNaiJehan(/:/g, "-") + '</span>';
				}
				html += '<a href="classDetail.do?clasId='+classList[i].clasId+'" class="subject text-body">';
				html += classList[i].clasTitle + " - ";
				html += '<span class="teaser text-muted fw-normal">' + classList[i].subjVo[0].subjTitle + '</span>';
				html += '</a>';
				
				html += '<div class="date"><i class=" ri-calendar-2-line me-2 fs-15 align-middle"></i>' + dateChange(classList[i].clasRegdate) + '</div>';
				html += '</div>';
				html += '</div>';
				html += '</div>';
				html += '</div>';          						                        
				normalClass.append(html);						                    
			}
			var html = '';
			html += '   <div class="row align-items-center mb-4 gy-3">';
			html += '		<div class="col-7">';
			html += '      		Showing '+(pVo.page*pVo.countList-(pVo.countList-1))+' - '+pVo.page*pVo.countList+' of '+pVo.totalCount+'';
			html += '   	</div>';
			html += '       <div class="col-sm-auto ms-auto">';
			html += '           <nav aria-label="...">';
			html += '               <ul class="pagination mb-0">';
			
			if(pVo.page>1){
				html += '	                    	<li class="page-item">';
				html += '	                            <a class="page-link" href="javascript:ajaxPaging(1);">first</a>';
				html += '	                        </li>';
			} else{
				html += '	                        <li class="page-item disabled">';
				html += '	                            <span class="page-link">first</span>';
				html += '	                        </li>';       
			}
			
			if(pVo.page - pVo.countPage>0){
				html += '			          		<li class="page-item">';
				html += '                              	<a class="page-link" href="javascript:ajaxPaging('+(pVo.startPage-pVo.countPage)+')">Previous</a>';
				html += '                           </li>';
			}else{
				html += '							<li class="page-item disabled">';
				html += '                              	<span class="page-link">Previous</span>';
				html += '                           </li>';
			}
			for(let i=pVo.startPage; i<=pVo.endPage;i++){
				if(pVo.page==i){
					html += '				                <li class="page-item active">';
					html += '				                    <span class="page-link">'+i+'</span>';
					html += '				                </li>';
				} else{
					html += '				                <li class="page-item">';
					html += '				                    <a class="page-link" href="javascript:ajaxPaging('+i+');">'+i+'</a>';
					html += '				                </li>';
				}
			}
			if(pVo.totalPage > (pVo.page+pVo.countPage)){
				html += '					        <li class="page-item">';
				html += '					            <a class="page-link" href="javascript:ajaxPaging('+(pVo.startPage+pVo.countPage)+');">NEXT</a>';
				html += '					        </li>';
			}else{
				html += '					        <li class="page-item disabled">';
				html += '					            <span class="page-link">NEXT</span>';
				html += '					        </li>';
			}
			if(pVo.page==pVo.totalPage){
				html += '	                       <li class="page-item disabled">';
				html += '	                            <span class="page-link">End</span>';
				html += '	                        </li> ';
			}else{
				html += '	                    	 <li class="page-item">';
				html += '	                            <a class="page-link" href="javascript:ajaxPaging('+pVo.totalPage+');">End</a>';
				html += '	                        </li>';
			}
			html += '               </ul>                                                                                                                                                        ';
			html += '             </nav>                                                                                                                                                         ';
			html += '             </div>                                                                                                                                                         ';
			html += '             </div>                                                                                                                                                         ';
			html += '             </div>                                                                                                                                                         ';
			html += '             </div>                                                        ';
			normalClass.append(html);	
		},                                                        
		error: function(){                                     
			alert("통신에 실패하였습니다");
			}
		});

}

function dateChange(dateString) {
    console.log("Received dateString: ", dateString);  // 받은 날짜 문자열 로깅

    const isoFormattedDateString = dateString.replace(' ', 'T') + 'Z';
    console.log("ISO Formatted dateString: ", isoFormattedDateString);  // ISO 형식의 날짜 문자열 로깅

    // 서버에서 받은 날짜-시간 정보를 Date 객체로 변환
    const targetDate = new Date(isoFormattedDateString);
    console.log("Converted Date: ", targetDate);  // 변환된 Date 객체 로깅

    // 현재 날짜와 시간 정보
    const now = new Date();

    // 년, 월, 일만 비교하기 위해 시간 정보를 제거
    now.setHours(0, 0, 0, 0);
    targetDate.setHours(0, 0, 0, 0);

    const oneWeekInMilliseconds = 7 * 24 * 60 * 60 * 1000;

    let output;
    
    if (now.getTime() === targetDate.getTime()) {
        output = "오늘";
    } else if (now.getTime() - targetDate.getTime() < oneWeekInMilliseconds) {
        output = "일주일 내";
    } else {
        const formattedDate = [
            targetDate.getFullYear().toString().slice(2),
            ("0" + (targetDate.getMonth() + 1)).slice(-2),
            ("0" + targetDate.getDate()).slice(-2),
        ].join(".");
        output = formattedDate;
    }

    console.log("Output: ", output);  // 최종 출력값 로깅
    return output;
}

$(function () {
  $('[data-toggle="tooltip"]').tooltip();
});


