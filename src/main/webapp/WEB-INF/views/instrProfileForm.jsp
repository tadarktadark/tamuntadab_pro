<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title}|타문타답</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="./js/instrProfileForm.js"></script>
<%@ include file="./shared/_head_css.jsp"%>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp"%>
		<%@ include file="./shared/_sidebar.jsp"%>

		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<%@ include file="./shared/_page_title.jsp"%>
				</div>
				<div class="card">
					<div class="card-body">
						<form action="./insertInstrProfile.do" method="post">
						<input type="hidden" value="${accountId}" name="inprAccountId">
							<div class="row mb-3">
								<div style="width: 700px;">
									<label for="inprIntro" class="form-label">한줄 소개</label>
									<textarea class="form-control" name="inprIntro" id="inprIntro" rows="3"
										placeholder="자신을 소개하는 한마디를 적어주세요(100자 이내)"></textarea>
										<span class="introSpan">0</span>/100
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 700px;">
									<label for="inprSiteYoutube" class="form-label">유튜브 링크</label>
									<input type="url" class="form-control" id="inprSiteYoutube" name="inprSiteYoutube"
										placeholder="소개할 자신의 유튜브 url">
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 700px;">
									<label for="inprSiteWeb" class="form-label">웹사이트 링크</label> <input
										type="url" class="form-control" id="inprSiteWeb" name="inprSiteWeb"
										placeholder="소개할 웹 홈페이지">
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 700px;">
									<label for="inprSiteMobile" class="form-label">모바일 사이트
										링크</label> <input type="url" class="form-control" id="inprSiteMobile" name="inprSiteMobile"
										placeholder="소개할 모바일 홈페이지">
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 700px;">
									<label for="formtextInput" class="form-label"
										style="margin-right: 10px;">학력</label>
									<button type="button" class="btn btn btn-outline-secondary"
										onclick="addEduLevel()">+ 학력추가</button>

									<table class='education-table table table-nowrap' style="display: none;">
										<thead class="table-light">
											<tr>
												<th scope="col">구분</th>
										            <th scope="col">학교명</th>
										            <th scope="col">전공</th>
										            <th scope="col">부전공</th>
										            <th scope="col" >입학년월일</th>
										 	    <th scope="col">졸업년월일</th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 700px;">
									<div class="col-lg-3">
										<label for="inprSubjects" class="form-label">가능한 과목</label>
									</div>
									<div id="selectedSubjects"
										class="col-lg-9 choices__list choices__list--multiple"></div>
									<div class="choices__inner choices hstack gap-3"
										data-type="multiple">
										<input type="search" id="inprSubjects"
											class="choices__input choices__input--cloned">
									</div>
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 700px;">
									<div class="col-lg-3">
										<label for="inprSubjectsMajor" class="form-label">전문
											분야 과목</label>
									</div>
									<div id="selectedSubjectsMajor"
										class="col-lg-9 choices__list choices__list--multiple"></div>
									<div class="col-md-2 choices__inner choices"
										data-type="multiple">
										<input type="search" id="inprSubjectsMajor"
											class="choices__input choices__input--cloned">
									</div>
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 350px;">
									<label for="inprFee" class="form-label">최소 수업료</label> <input
										type="number" class="form-control" id="inprFee" name="inprFee"
										placeholder="단위(만원)">
								</div>
							</div>

							<div class="text-end">
								<button type="submit" class="btn btn-primary">저장</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

	</div>
	<%@ include file="./shared/_footer.jsp"%>
	<%@ include file="./shared/_vender_scripts.jsp"%>
</body>
<script type="text/javascript">
	$('form').on('submit', function(e) {
	    e.preventDefault();

	    var formData = new FormData(this);
	    
	    var selectedSubjects = $('#selectedSubjects .choices__item--selectable').map(function() {
	        return $(this).data('value').toString();
	    }).get();
	    
	    formData.append('inprSubjects', JSON.stringify(selectedSubjects));
	    
	    var selectedSubjectsMajor = $('#selectedSubjectsMajor .choices__item--selectable').map(function() {
		    return $(this).data('value').toString();
		}).get();
	    
	    formData.append('inprSubjectsMajor', JSON.stringify(selectedSubjectsMajor));
	    
	    var data = {};

	 // Iterate over the FormData entries
	    for (var pair of formData.entries()) {
	        console.log(pair[0]+ ', ' + pair[1]); 
	        
	        // Handle array field names (like "instrEduVo[x].fieldName")
	        var match = pair[0].match(/^(\w+)\[(\d+)\]\.(\w+)$/);
	        if (match) {
	            var arrayName = match[1];
	            var index = parseInt(match[2]);
	            var propertyName = match[3];
	            
	            data[arrayName] = data[arrayName] || [];
	            data[arrayName][index] = data[arrayName][index] || {};
	            data[arrayName][index][propertyName] = pair[1];
	        } else {
	            // Handle non-array field names
	          	data[pair[0]] = pair[1];
	      	}
	    }
	   
	   $.ajax({
	       url: './insertInstrProfile.do',
	       type: 'POST',
	       data: JSON.stringify(data),
	       contentType: 'application/json',  // tell jQuery not to set contentType
	       success:function(response){
	           console.log(response);
	           window.location.href = './instrProfileForm.do';  // Redirect to a success page (modify this)
	      },
	      error:function(jqXHR, textStatus){
	          alert("Request failed : " + textStatus);
	      }
	   });
	});
</script>
<style type="text/css">
body {
	padding: 30px;
}

.card-body {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
}

.ui-autocomplete {
	background-color: #f9f9f9;
	max-height: 200px;
	max-width: 150px; overflow-y : auto;
	overflow-x: hidden;
	overflow-y: auto;
}

.ui-autocomplete li {
	list-style-type: none;
}
</style>
</html>