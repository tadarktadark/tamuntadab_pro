//페이지 로드 되자마자 1페이지 로드
$(function() {
	ajaxPaging(1, 1);
});

document.querySelector('.email-search').addEventListener('submit', function(e) {
    e.preventDefault(); // 기본 검색 폼 제출 방지
    const searchTerm = document.getElementById('subjects').value.trim(); // 공백 제거
    
    // 검색어가 공백인지 확인
    if (searchTerm === "") {
        swal("경고", "검색어를 입력해주세요.", "warning");
        return;
    }

    // 검색어가 두 글자 이하인지 확인
    if (searchTerm.length < 2) {
        swal("경고", "검색어는 두 글자 이상 입력해주세요.", "warning");
        return;
    }

    // 검증이 끝난 후에 searchPaging 함수 호출
    searchPaging(1, searchTerm);
});


function ajaxPaging(page, category) {
	$.ajax({
		url: "./classListLoad.do",
		type: "get",
		async: true,
		data: {
			"page": page,
			"category": category
		},
		dataType: "JSON",
		success: function(resp) {
			var normalClass = $("#normalClass");
			var classList = resp.classList; // 서버에서 받은 클래스 리스트
			var pVo = resp.pVo;
			var chamyeoClass = resp.chamyeoClass;
			normalClass.empty();
			normalClass.append('<h6 class="text-muted text-uppercase mb-3">클래스 목록</h6>');
			html = "";
			for (var i = 0; i < classList.length; i++) {

				var userNickname = classList[i].userVo[0].userNickname;
				var truncatedNickname = userNickname.length > 8 ? userNickname.substring(0, 8) + '...' : userNickname;
					
				var location = classList[i].clasLocation;
				var truncatedlocation = location.length > 12 ? location.substring(0, 10) + '...' : location;
				
				if (classList[i] && classList[i].userVo) {
					// chamyeoClass[i].userVo 가 존재할 때만 실행되는 코드
					var userProfileFile = classList[i].userVo[0].userProfileFile;
				} else {
					// chamyeoClass[i].userVo 가 존재하지 않을 때 실행되는 코드
					var userProfileFile = './image/profile.png';
				}

				html = '';
				html += '<div class="mb-2" style="padding-bottom:20px;">';
				html += '	<div class="message-list mb-0 p-1">';
				html += ' 		<div class="list">';
				html += '			<div class="col-mail col-mail-1">';
				html += '				<div class="d-flex title align-items-center">';
				html += '				<img src="'+userProfileFile+'" alt="" class="rounded-circle avatar-sm">';
				html += '					<div class="flex-1 ms-2 ps-1 mt-1">';
				html += '						<h5 class="fs-15 mb-0"><a class="text-body" data-toggle="tooltip" title="' + userNickname + '">' + truncatedNickname + '</a></h5>';
				html += '						<a class="text-muted text-uppercase fs-13 mt-1" data-toggle="tooltip" title="' + location + '">' + truncatedlocation + '</a>';
				html += '					</div>';
				html += '				</div>';
				html += '				<span class="bg-success badge me-2" style="margin-top:20px;margin-left: 32px;">'+classList[i].clasStatus+'</span>';
				html += '			</div>';
				html += '<div class="col-mail col-mail-2">';
				html += '<a href="classDetail.do?clasId='+classList[i].clasId+'" class="subject text-body" data-toggle="tooltip" title="' + classList[i].clasTitle + '-' + classList[i].subjVo[0].subjTitle+'">';
				if (classList[i].clasSeongbyeolJehan == "MONLY") {
					html += '<span class="bg-danger badge me-2">남자만</span>';
				} else if (classList[i].clasSeongbyeolJehan == "FONLY") {
					html += '<span class="bg-danger badge me-2">여자만</span>';
				}
				if (classList[i].clasNaiJehan != null) {
				    html += '<span class="bg-info badge me-2">' + classList[i].clasNaiJehan.replace(/:/g, "-") + '</span>';
				}
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
				html += '	                            <a class="page-link" href="javascript:ajaxPaging(1,'+category+');">first</a>';
				html += '	                        </li>';
			} else{
				html += '	                        <li class="page-item disabled">';
				html += '	                            <span class="page-link">first</span>';
				html += '	                        </li>';       
			}
			
			if(pVo.page - pVo.countPage>0){
				html += '			          		<li class="page-item">';
				html += '                              	<a class="page-link" href="javascript:ajaxPaging('+(pVo.startPage-pVo.countPage)+','+category+')">Previous</a>';
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
					html += '				                    <a class="page-link" href="javascript:ajaxPaging('+i+','+category+');">'+i+'</a>';
					html += '				                </li>';
				}
			}
			if(pVo.totalPage > (pVo.page+pVo.countPage)){
				html += '					        <li class="page-item">';
				html += '					            <a class="page-link" href="javascript:ajaxPaging('+(pVo.startPage+pVo.countPage)+','+category+');">NEXT</a>';
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
				html += '	                            <a class="page-link" href="javascript:ajaxPaging('+pVo.totalPage+','+category+');">End</a>';
				html += '	                        </li>';
			}
			html += '               </ul>                                                                                                                                                        ';
			html += '             </nav>                                                                                                                                                         ';
			html += '             </div>                                                                                                                                                         ';
			html += '             </div>                                                                                                                                                         ';
			html += '             </div>                                                                                                                                                         ';
			html += '             </div>                                                        ';
			normalClass.append(html);
			
			for (let i = 1; i <= 6; i++) {
				$(`#category${i}`).attr('class', 'border-bottom');
			  }
			  $(`#category${category}`).attr('class', 'active bg-primary-subtle');

			if(resp.chamyeoClass != null){
			var chamyeoClassTag = $("#chamyeoClassTag");
			chamyeoClassTag.empty();
			html = "";
				for(var i = 0; i < chamyeoClass.length; i++){
					var userNickname = chamyeoClass[i].userVo[0].userNickname;
					var truncatedNickname = userNickname.length > 8 ? userNickname.substring(0, 8) + '...' : userNickname;
						
					var location = chamyeoClass[i].clasLocation;
					var truncatedlocation = location.length > 12 ? location.substring(0, 10) + '...' : location;
					
					if (chamyeoClass[i] && chamyeoClass[i].userVo) {
					// chamyeoClass[i].userVo 가 존재할 때만 실행되는 코드
					var userProfileFile = chamyeoClass[i].userVo[0].userProfileFile;
					} else {
						// chamyeoClass[i].userVo 가 존재하지 않을 때 실행되는 코드
						var userProfileFile = "./image/profile.png";
					}
				
					var html = '';
					html += '<div class="mb-2" style="padding-bottom:20px;">';
					html += '	<div class="message-list mb-0 p-1">';
					html += ' 		<div class="list">';
					html += '			<div class="col-mail col-mail-1">';
					html += '				<div class="d-flex title align-items-center">';
					html += '				<img src="'+userProfileFile+'" alt="" class="rounded-circle avatar-sm">';
					html += '					<div class="flex-1 ms-2 ps-1 mt-1">';
					html += '						<h5 class="fs-15 mb-0"><a class="text-body" data-toggle="tooltip" title="' + userNickname + '">' + truncatedNickname + '</a></h5>';
					html += '						<a class="text-muted text-uppercase fs-13 mt-1" data-toggle="tooltip" title="' + location + '">' + truncatedlocation + '</a>';
					html += '					</div>';
					html += '				</div>';
					html += '				<span class="bg-success badge me-2" style="margin-top:20px;margin-left: 32px;">'+chamyeoClass[i].clasStatus+'</span>';
					html += '			</div>';
					html += '<div class="col-mail col-mail-2">';
					html += '<a href="classDetail.do?clasId='+chamyeoClass[i].clasId+'" class="subject text-body">';
					html += '<span class="bg-primary badge me-2">참여중</span>';
					if (chamyeoClass[i].clasSeongbyeolJehan == "MONLY") {
						html += '<span class="bg-danger badge me-2">남자만</span>';
					} else if (chamyeoClass[i].clasSeongbyeolJehan == "FONLY") {
						html += '<span class="bg-danger badge me-2">여자만</span>';
					}
					if (chamyeoClass[i].clasNaiJehan != null) {
						html += '<span class="bg-info badge me-2">' + chamyeoClass[i].clasNaiJehan.replace(/:/g, "-") + '</span>';
					}
					html += chamyeoClass[i].clasTitle + " - ";
					html += '<span class="teaser text-muted fw-normal">' + chamyeoClass[i].subjVo[0].subjTitle + '</span>';
					html += '</a>';
					
					html += '<div class="date"><i class=" ri-calendar-2-line me-2 fs-15 align-middle"></i>' + dateChange(chamyeoClass[i].clasRegdate) + '</div>';
					html += '</div>';
					html += '</div>';
					html += '</div>';
					html += '</div>';          						                        
					chamyeoClassTag.append(html);
				}
			
			}else{
				var chamyeoClassTag = $("#chamyeoClassTag");
				chamyeoClassTag.empty();
				chamyeoClassTag.append('<h6 class="text-muted text-uppercase mb-3">참여 중인 클래스가 없습니다</h6>');
			}

			
			},                                                        
			error: function(){                                     
				alert("통신에 실패하였습니다");
				}
		});

}



function searchPaging(page, subject) {
	$.ajax({
		url: "./searchListLoad.do",
		type: "get",
		async: true,
		data: {
			"page": page,
			"subject": subject
		},
		dataType: "JSON",
		success: function(resp) {
			var normalClass = $("#normalClass");
			var classList = resp.classList; // 서버에서 받은 클래스 리스트
			var pVo = resp.pVo;
			var chamyeoClass = resp.chamyeoClass;
			normalClass.empty();
			normalClass.append('<h6 class="text-muted text-uppercase mb-3">클래스 목록</h6>');
			html = "";
			for (var i = 0; i < classList.length; i++) {
				var userNickname = classList[i].userVo[0].userNickname;
				var truncatedNickname = userNickname.length > 8 ? userNickname.substring(0, 8) + '...' : userNickname;
					
				var location = classList[i].clasLocation;
				var truncatedlocation = location.length > 12 ? location.substring(0, 10) + '...' : location;
				
				if (classList[i] && classList[i].userVo) {
					// chamyeoClass[i].userVo 가 존재할 때만 실행되는 코드
					var userProfileFile = classList[i].userVo[0].userProfileFile;
				} else {
					// chamyeoClass[i].userVo 가 존재하지 않을 때 실행되는 코드
					var userProfileFile = './image/profile.png';
				}

				html = '';
				html += '<div class="mb-2" style="padding-bottom:20px;">';
				html += '	<div class="message-list mb-0 p-1">';
				html += ' 		<div class="list">';
				html += '			<div class="col-mail col-mail-1">';
				html += '				<div class="d-flex title align-items-center">';
				html += '				<img src="'+userProfileFile+'" alt="" class="rounded-circle avatar-sm">';
				html += '					<div class="flex-1 ms-2 ps-1 mt-1">';
				html += '						<h5 class="fs-15 mb-0"><a class="text-body" data-toggle="tooltip" title="' + userNickname + '">' + truncatedNickname + '</a></h5>';
				html += '						<a class="text-muted text-uppercase fs-13 mt-1" data-toggle="tooltip" title="' + location + '">' + truncatedlocation + '</a>';
				html += '					</div>';
				html += '				</div>';
				html += '				<span class="bg-success badge me-2" style="margin-top:20px;margin-left: 32px;">'+classList[i].clasStatus+'</span>';
				html += '			</div>';
				html += '<div class="col-mail col-mail-2">';
				html += '<a href="classDetail.do?clasId='+classList[i].clasId+'" class="subject text-body">';
				if (classList[i].clasSeongbyeolJehan == "MONLY") {
					html += '<span class="bg-danger badge me-2">남자만</span>';
				} else if (classList[i].clasSeongbyeolJehan == "FONLY") {
					html += '<span class="bg-danger badge me-2">여자만</span>';
				}
				if (classList[i].clasNaiJehan != null) {
				    html += '<span class="bg-info badge me-2">' + classList[i].clasNaiJehan.replace(/:/g, "-") + '</span>';
				}
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
			var chamyeoClassTag = $("#chamyeoClassTag");
			if(resp.chamyeoClass != null){
			chamyeoClassTag.empty();
			html = "";
				for(var i = 0; i < chamyeoClass.length; i++){
					var userNickname = chamyeoClass[i].userVo[0].userNickname;
					var truncatedNickname = userNickname.length > 8 ? userNickname.substring(0, 8) + '...' : userNickname;
						
					var location = chamyeoClass[i].clasLocation;
					var truncatedlocation = location.length > 12 ? location.substring(0, 10) + '...' : location;
					
					if (chamyeoClass[i] && chamyeoClass[i].userVo) {
					// chamyeoClass[i].userVo 가 존재할 때만 실행되는 코드
					var userProfileFile = chamyeoClass[i].userVo[0].userProfileFile;
					} else {
						// chamyeoClass[i].userVo 가 존재하지 않을 때 실행되는 코드
						var userProfileFile = "./image/profile.png";
					}
				
					var html = '';
					html += '<div class="mb-2" style="padding-bottom:20px;">';
					html += '	<div class="message-list mb-0 p-1">';
					html += ' 		<div class="list">';
					html += '			<div class="col-mail col-mail-1">';
					html += '				<div class="d-flex title align-items-center">';
					html += '				<img src="'+userProfileFile+'" alt="" class="rounded-circle avatar-sm">';
					html += '					<div class="flex-1 ms-2 ps-1 mt-1">';
					html += '						<h5 class="fs-15 mb-0"><a class="text-body" data-toggle="tooltip" title="' + userNickname + '">' + truncatedNickname + '</a></h5>';
					html += '						<a class="text-muted text-uppercase fs-13 mt-1" data-toggle="tooltip" title="' + location + '">' + truncatedlocation + '</a>';
					html += '					</div>';
					html += '				</div>';
					html += '				<span class="bg-success badge me-2" style="margin-top:20px;margin-left: 32px;">'+chamyeoClass[i].clasStatus+'</span>';
					html += '			</div>';
					html += '<div class="col-mail col-mail-2">';
					html += '<a href="classDetail.do?clasId='+chamyeoClass[i].clasId+'" class="subject text-body">';
					html += '<span class="bg-primary badge me-2">참여중</span>';
					if (chamyeoClass[i].clasSeongbyeolJehan == "MONLY") {
						html += '<span class="bg-danger badge me-2">남자만</span>';
					} else if (chamyeoClass[i].clasSeongbyeolJehan == "FONLY") {
						html += '<span class="bg-danger badge me-2">여자만</span>';
					}
					if (chamyeoClass[i].clasNaiJehan != null) {
						html += '<span class="bg-info badge me-2">' + chamyeoClass[i].clasNaiJehan.replace(/:/g, "-") + '</span>';
					}
					html += chamyeoClass[i].clasTitle + " - ";
					html += '<span class="teaser text-muted fw-normal">' + chamyeoClass[i].subjVo[0].subjTitle + '</span>';
					html += '</a>';
					
					html += '<div class="date"><i class=" ri-calendar-2-line me-2 fs-15 align-middle"></i>' + dateChange(chamyeoClass[i].clasRegdate) + '</div>';
					html += '</div>';
					html += '</div>';
					html += '</div>';
					html += '</div>';          						                        
					chamyeoClassTag.append(html);
				}
			
			}else{
				chamyeoClassTag.empty();
				chamyeoClassTag.append('<h6 class="text-muted text-uppercase mb-3">참여 중인 클래스가 없습니다</h6>');
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
				html += ' <a class="page-link" href="javascript:searchPaging(1,\'' + encodeURIComponent(subject) + '\');">first</a>';
				html += '	                        </li>';
			} else{
				html += '	                        <li class="page-item disabled">';
				html += '	                            <span class="page-link">first</span>';
				html += '	                        </li>';       
			}
			
			if(pVo.page - pVo.countPage>0){
				html += '			          		<li class="page-item">';
				html += '                              	<a class="page-link" href="javascript:searchPaging('+(pVo.startPage-pVo.countPage)+',\'' + encodeURIComponent(subject) + '\')">Previous</a>';
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
					html += '				                    <a class="page-link" href="javascript:searchPaging('+i+',\'' + encodeURIComponent(subject) + '\');">'+i+'</a>';
					html += '				                </li>';
				}
			}
			if(pVo.totalPage > (pVo.page+pVo.countPage)){
				html += '					        <li class="page-item">';
				html += '					            <a class="page-link" href="javascript:searchPaging('+(pVo.startPage+pVo.countPage)+',\'' + encodeURIComponent(subject) + '\');">NEXT</a>';
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
				html += '								<a class="page-link" href="javascript:searchPaging(' + pVo.totalPage + ', \'' + encodeURIComponent(subject) + '\');">End</a>';
				html += '	                        </li>';
			}
			html += '               </ul>                                                                                                                                                        ';
			html += '             </nav>                                                                                                                                                         ';
			html += '             </div>                                                                                                                                                         ';
			html += '             </div>                                                                                                                                                         ';
			html += '             </div>                                                                                                                                                         ';
			html += '             </div>                                                        ';
			normalClass.append(html);
			
			for (let i = 1; i <= 6; i++) {
				$(`#category${i}`).attr('class', 'border-bottom');
			  }
			$(`#category${1}`).attr('class', 'active bg-primary-subtle');
			},                                                        
			error: function(){                                     
				alert("통신에 실패하였습니다");
				}
		});

}


function dateChange(dateString) {

    const isoFormattedDateString = dateString.replace(' ', 'T') + 'Z';

    // 서버에서 받은 날짜-시간 정보를 Date 객체로 변환
    const targetDate = new Date(isoFormattedDateString);

    // 현재 날짜와 시간 정보
    const now = new Date();

    // 년, 월, 일만 비교하기 위해 시간 정보를 제거
    now.setHours(0, 0, 0, 0);
    targetDate.setHours(0, 0, 0, 0);

    // 밀리세컨드 단위로 두 날짜의 차이를 계산
    const dateDifferenceInMilliseconds = now.getTime() - targetDate.getTime();

    // 밀리세컨드를 일 단위로 변환
    const dateDifferenceInDays = dateDifferenceInMilliseconds / (24 * 60 * 60 * 1000);

    let output;
    
    if (dateDifferenceInDays === 0) {
        output = "오늘";
    } else if (dateDifferenceInDays > 0 && dateDifferenceInDays < 7) {
        output = "일주일 내";
    } else {
        const formattedDate = [
            targetDate.getFullYear().toString().slice(2),
            ("0" + (targetDate.getMonth() + 1)).slice(-2),
            ("0" + targetDate.getDate()).slice(-2),
        ].join(".");
        output = formattedDate;
    }
    return output;
}

$(function () {
  $('[data-toggle="tooltip"]').tooltip();
});