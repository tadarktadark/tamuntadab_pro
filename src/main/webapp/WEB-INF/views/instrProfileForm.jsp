<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title}|타문타답</title>
<%@ include file="./shared/_vender_scripts.jsp"%>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" charset="UTF-8"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script type="text/javascript" src="./js/instrProfileForm.js" charset="UTF-8"></script>
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
									<textarea class="form-control" name="inprIntro" id="inprIntro"
										rows="3" placeholder="자신을 소개하는 한마디를 적어주세요(100자 이내)">${not empty profile ? profile.inprIntro : ''}</textarea>
									<span class="introSpan">0</span>/100
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 700px;">
									<label for="inprSiteYoutube" class="form-label">유튜브 링크</label>
									<input type="url" class="form-control" id="inprSiteYoutube"
										name="inprSiteYoutube" placeholder="소개할 자신의 유튜브 url"
										value="${not empty profile ? profile.inprSiteYoutube : ''}">
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 700px;">
									<label for="inprSiteWeb" class="form-label">웹사이트 링크</label> <input
										type="url" class="form-control" id="inprSiteWeb"
										name="inprSiteWeb" placeholder="소개할 웹 홈페이지"
										value="${not empty profile ? profile.inprSiteWeb : ''}">
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 700px;">
									<label for="inprSiteMobile" class="form-label">모바일 사이트
										링크</label> <input type="url" class="form-control" id="inprSiteMobile"
										name="inprSiteMobile" placeholder="소개할 모바일 홈페이지"
										value="${not empty profile ? profile.inprSiteMobile : ''}">
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 700px;">
									<label for="formtextInput" class="form-label"
										style="margin-right: 10px;">학력</label>
									<button type="button" class="btn btn btn-outline-secondary"
										onclick="addEduLevel()">+ 학력추가</button>
									<table class='education-table table table-nowrap'
										style="display: none;">
										<thead class="table-light">
											<tr style="text-align: center;">
												<th scope="col">구분</th>
												<th scope="col">학교명</th>
												<th scope="col">전공</th>
												<th scope="col">부전공</th>
												<th scope="col">입학년월일</th>
												<th scope="col">졸업년월일</th>
												<th scope="col">&nbsp;&nbsp;</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty profile.instrEduVo}">
												<c:forEach var="edu" items="${profile.instrEduVo}" varStatus="vs">
													<tr style="text-align: center;" class="isDb">
														<td>
														<c:choose>
															<c:when test="${edu.inedStage == 1}">대학원(박사)</c:when>
															<c:when test="${edu.inedStage == 2}">대학원(석사)</c:when>
															<c:when test="${edu.inedStage == 3}">대학교(4년)</c:when>
															<c:when test="${edu.inedStage == 4}">대학(2,3년)</c:when>
															<c:otherwise>고등학교</c:otherwise>
														</c:choose>
														<input type="hidden" name="instrEduVo[${vs.index}].inedSeq" value="${edu.inedSeq}">
														<input type="hidden" name="instrEduVo[${vs.index}].inedStage" value="${edu.inedStage}">
														</td>
														<td>${edu.inedSchool}
														<input type="hidden" name="instrEduVo[${vs.index}].inedSchool" value="${edu.inedSchool}">
														</td>
														<td>${edu.inedMajor}
														<input type="hidden" name="instrEduVo[${vs.index}].inedMajor" value="${edu.inedMajor}"></td>
														<td>${edu.inedMinor}
														<input type="hidden" name="instrEduVo[${vs.index}].inedMinor" value="${edu.inedMinor}"></td>
														<td>${edu.inedStart}
														<input type="hidden" name="instrEduVo[${vs.index}].inedStart" value="${edu.inedStart}"></td>
														<td>${edu.inedEnd}
														<input type="hidden" name="instrEduVo[${vs.index}].inedEnd" value="${edu.inedEnd}"></td>
														<td><button type="button" class="cancel-button btn btn-danger" onclick="deleteRow(this)">취소</button></td>
													</tr>
												</c:forEach>
												<script type="text/javascript" charset="UTF-8">
						            	 			  $('.education-table').css('display', 'table');
						            			</script>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 700px;">
									<div class="col-lg-3">
										<label for="inprSubjects" class="form-label">가능한 과목</label>
									</div>
									<div id="selectedSubjects" class="col-lg-9 choices__list choices__list--multiple">
									<c:if test="${not empty profile.subjectsTitle}">
										 <script type="text/javascript" charset="UTF-8">
										 // 과목 기존 정보 있을때 값 불러오기
										 var subjectsTitle = ${profile.subjectsTitle};  
									        var inprSubjects = ${profile.inprSubjects};  

									        $.each(subjectsTitle, function(index, value) {  
									            var $div = $('<div>')
									                .addClass('choices choices__item choices__item--selectable')
									                .attr('data-value', inprSubjects[index])  
									                .attr('data-type', 'select-multiple')
									                .text(value)
									                .appendTo('#selectedSubjects');

									            var $removeButton = $('<button>')
									                .addClass('choices__button')
									                .attr('aria-label', "Remove item: '" + value + "'")
									                .attr('data-button', '')
									                .text("Remove item")
									                .on('click', function() {
									                    $div.remove();
									                 })
									                 .appendTo($div);
									        });
									    	</script>
									   </c:if>
									</div>
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
										class="col-lg-9 choices__list choices__list--multiple">
									</div>
									<div class="col-md-2 choices__inner choices"
										data-type="multiple">
										<input type="search" id="inprSubjectsMajor"
											class="choices__input choices__input--cloned">
									</div>
									<c:if test="${not empty profile.subjectsMajorTitle}">
									<script type="text/javascript" charset="UTF-8">
									// 전문분야 기존 정보 있을때 값 불러오기
									 var subjectsMajorTitle = ${profile.subjectsMajorTitle};  
								        var inprSubjectsMajor = ${profile.inprSubjectsMajor}; 

								        $.each(subjectsMajorTitle, function(index, value) {  
								            var $div = $('<div>')
								                .addClass('choices choices__item choices__item--selectable')
								                .attr('data-value', inprSubjectsMajor[index]) 
								                .attr('data-type', 'select-multiple')
								                .text(value)
								                .appendTo('#selectedSubjectsMajor');

								            var $removeButton = $('<button>')
								                .addClass('choices__button')
								                .attr('aria-label', "Remove item: '" + value + "'")
								                .attr('data-button', '')
								                .text("Remove item")
								                .on('click', function() {
								                    $div.remove();
								                 })
								                 .appendTo($div);
								        });									
									</script>
									</c:if>
								</div>
							</div>
							<div class="row mb-3">
								<div style="width: 350px;">
									<label for="inprFee" class="form-label">최소 수업료</label> <input
										type="number" class="form-control" id="inprFee" name="inprFee"
										placeholder="단위(만원)"
										value="${not empty profile ? profile.inprFee : ''}">
								</div>
							</div>

							<div class="text-end">
								<button type="submit" class="btn btn-primary" style="width: 150px;">저장</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

	</div>
	<%@ include file="./shared/_footer.jsp"%>
</body>
<script type="text/javascript" charset="UTF-8">
function deleteRow(button) {
	var row = $(button).closest('tr');
    var isDbRow = row.attr('class') === 'isDb';

    if (isDbRow) {
        Swal.fire({
            title: "정말로 삭제하시겠습니까?",
            text: "기존의 데이터가 삭제됩니다.",
            icon: "warning",
            showCancelButton: true,
            customClass: {
                confirmButton: 'btn btn-primary w-xs mt-2 me-2',
                cancelButton: 'btn btn-danger w-xs mt-2',
            },
            confirmButtonText: "삭제",
            buttonsStyling: false,
            showCloseButton: true
        }).then(function (result) {
            if (result.isConfirmed) { // 확인 버튼을 누른 경우
                var inedSeq = row.find('input[name$=".inedSeq"]').val();

                $.ajax({
                    url: './deleteInstrEdulevel.do',
                    type: 'POST',
                    data: { inedSeq: inedSeq },
                    success:function(response){
                        if(response == 'true'){
                            Swal.fire({
                                title:'삭제되었습니다',
                                text:'',
                                icon:'success',
                                customClass:{
                                    confirmButton:'btn btn-primary w-xs mt-2'
                                },
                                buttonsStyling:false
                            });
                            row.remove();
                        } else {
                        	Swal.fire({
                                title: '삭제에 실패했습니다',
                                text: '',
                                icon: 'error',
                                 customClass: {
                                    confirmButton: 'btn btn-primary w-xs mt-2',
                                },
                                buttonsStyling: false,
                            })
                        }
                    },
                    error:function(jqXHR, textStatus){
                        alert("Request failed : " + textStatus);
                    }
               });
                
           }
        });
        
    } else {
       row.remove();
     }
}

$('form').on('submit', function(e) {
    e.preventDefault();
    
    var selectedSubjects = $('#selectedSubjects .choices__item--selectable').map(function() {
        return $(this).data('value').toString();
    }).get();
    
    if(selectedSubjects.length == 0) {
    	Swal.fire('가능한 과목을 선택해주세요');
        return false;
    }
    Swal.fire({
        title: '저장하시겠습니까?',
        showDenyButton: true,
        showCancelButton: true,
        confirmButtonText: '예',
        customClass: {
            confirmButton: 'btn btn-primary w-xs me-2',
            cancelButton: 'btn btn-danger w-xs',
            denyButton: 'btn btn-info w-xs me-2',
        },
        buttonsStyling: false,
        denyButtonText: '기존 사항 유지',
        showCloseButton: true
    }).then(function (result) {
        if (result.isConfirmed) {
            
            var formData = new FormData(e.target);
    
    formData.append('inprSubjects', JSON.stringify(selectedSubjects));
    
    
    var selectedSubjectsMajor = $('#selectedSubjectsMajor .choices__item--selectable').map(function() {
	    return $(this).data('value').toString();
	}).get();
    
    formData.append('inprSubjectsMajor', JSON.stringify(selectedSubjectsMajor));
    
    var data = {};

    for (var pair of formData.entries()) {
        console.log(pair[0]+ ', ' + pair[1]); 
        
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
        url : './insertInstrProfile.do',
        type : 'POST',
        data : JSON.stringify(data),
        contentType : 'application/json',  // tell jQuery not to set contentType
        success:function(response){
            if(response == 'true'){
                Swal.fire({
                    title:'성공적으로 저장되었습니다.',
                    icon:'success'
                }).then(function() {
                    window.location.href = './home.do';
                });
            } else {
                Swal.fire({
                    title:'저장 실패',
                    icon:'error'
                }).then(function() {
                    location.reload();
                });
            }
        },
        error:function(jqXHR, textStatus){
            alert("Request failed : " + textStatus);
        }
   });
		} else if (result.isDenied) {
			Swal.fire('변경사항이 적용되지 않았습니다', '', 'info').then(function() {
                window.location.href = './home.do';
            });
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
	max-width: 150px;
	overflow-y: auto;
	overflow-x: hidden;
	overflow-y: auto;
}

.ui-autocomplete li {
	list-style-type: none;
}
</style>
</html>