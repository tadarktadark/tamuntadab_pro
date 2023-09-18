<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light" data-topbar="light" data-sidebar="light" data-sidebar-size="sm" data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title} | 타문타답</title>
<link href="./css/ckeditor.css" rel="stylesheet" type="text/css" />
<%@ include file="./shared/_head_css.jsp" %>
<link href="./assets/libs/sweetalert2/sweetalert2.min.css" rel="stylesheet" type="text/css" />
<link href="./css/communityWriteForm.css" rel="stylesheet" type="text/css" />
<link href="./css/community.css" rel="stylesheet" type="text/css" />
</head>
<body class="twocolumn-panel" data-editor="ClassicEditor" data-collaboration="false" data-revision-history="false">
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp" %>
		<%@ include file="./shared/_sidebar.jsp" %>
		
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<div class="row">
					    <div class="col-lg-12">
					        <div class="card">
					        	<form action="./commynityWrite.do" method="post" enctype="multipart/form-data">
					        		<div class="card-body" id="write-top">
						        		<c:choose>
						        			<c:when test="${sessionScope.community eq 'pilgi'}">
											    <label for="title" class="form-label">제목</label>	
											    <input type="text" class="form-control" id="title" value="${classVo.clasTitle}" disabled="disabled">
											    <input type="hidden" class="hidden" id="clasId" name="clasId" value="${classVo.clasId}">
											    <label for="subject" class="form-label">과목</label>
										    	<div class="list-text mb-0" class="sub-list">
												    <c:forEach items="${subArr}" var="sub">
														<span class="badge text-bg-primary">${sub}</span>
												    </c:forEach>
												</div>
						        			</c:when>
						        			<c:otherwise>
											    <label for="title" class="form-label">제목</label>	
											    <input type="text" class="form-control" id="title" name="title">
						        			</c:otherwise>
						        		</c:choose>
							        	<c:if test="${sessionScope.community eq 'jilmun'}">
											<label for="class" class="form-label">클래스</label>
									    	<c:choose>
									    		<c:when test="${classList.size() > 0}">
												    <select id="class" name="clasId" class="form-select" aria-label="Default select example">
														<option value="none" selected>클래스를 선택해주세요.</option>
														<c:forEach items="${classList}" var="c">															
															<option value="${c.clasId}">${c.clasTitle}</option>
														</c:forEach>
													</select>
									    		</c:when>
									    		<c:otherwise>
									    			<input id="class" class="form-control" value="진행 중인 클래스가 없습니다." disabled="disabled">
									    		</c:otherwise>
									    	</c:choose>
									    	<label for="subject" class="form-label">과목</label>
									    	<select class="form-control" id="choices-multiple-remove-button" data-choices data-choices-removeItem name="subject" multiple>
		                                    </select>
											<input type="hidden" class="hidden" name="subjectCode" id="formSubject">
							        	</c:if>
							        </div>
						        	<div class="card-body" id="${sessionScope.community.equals('jilmun')?'write-content':''}">
						        		<textarea id="ckeditor" name="content"></textarea>
 						            </div><!--end card-body -->
 						            <c:if test="${sessionScope.community eq 'pilgi'}">
 						            	<div class="card-body gap-3" id="write-bottom">
							        		<div class="input-group">
										    	<input type="file" name="file" class="form-control" id="inputGroupFile01" multiple="multiple">
											</div>
										</div>
 						            </c:if>
 						            <div class="card-body gap-3" id="write-bottom">
 						            	<c:if test="${sessionScope.community eq 'pilgi'}">
										    <select id="viewGroup" name="viewGroup" class="form-select rounded-pill mb-3" aria-label="Default select example">
											    <option value="A" selected>전체 공개</option>
											    <option value="C">클래스 공개</option>
											    <option value="I">나만 보기</option>
											</select>
										    <select id="downloadGroup" name="downloadGroup" class="form-select rounded-pill mb-3" aria-label="Default select example">
											    <option value="A" selected>전체 다운로드</option>
											    <option value="C">클래스 다운로드</option>
											    <option value="I">나만 다운로드</option>
											</select>
 						            	</c:if>
									    <button id="write-btn" type="button" class="btn btn-primary">작성완료</button>
 						            </div>
					            </form>
					        </div><!-- end card -->
					    </div>
					    <!-- end col -->
					</div>
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp" %>
		</div>
	</div>	
	<div class="hidden">
		<button type="button" class="btn btn-primary btn-sm" id="sa-basic"></button>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
	<script type="text/javascript" src="./libs/ckeditor5-39.0.2/build/ckeditor.js"></script>
	<%@ include file="./shared/_vender_scripts.jsp" %>
	<script src="./assets/js/app.js"></script>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js" charset="UTF-8"></script>
	<script src="./assets/libs/sweetalert2/sweetalert2.min.js"></script>
	<script type="text/javascript" src="./js/ckeditor.js"></script>
	<script type="text/javascript" src="./js/commynityWriteForm.js"></script>
	<script id="select-template" type="text/x-handlebars-template">
		<div id="class-selected" class="choices cursor-default" data-type="select-multiple" role="combobox" aria-autocomplete="list" aria-haspopup="true" aria-expanded="false">
			<div class="choices__inner cursor-default">
				<select disabled="disabled" class="form-control choices__input cursor-default" id="choices-multiple-remove-button" data-choices="" data-choices-removeitem="" name="subject" multiple="" hidden="" tabindex="-1" data-choice="active">
					{{#each options}}
						<option value="{{option}}" data-custom-properties="[object Object]">{{option}}</option>
					{{/each}}	
				</select>
				<div class="choices__list choices__list--multiple">
					{{#each subjects}}
						<div class="choices__item choices__item--selectable cursor-default" data-item="" data-id="{{no}}" data-value="{{subject}}" data-custom-properties="[object Object]" aria-selected="true" data-deletable="">
							{{subject}}
							<button type="button" class="choices__button cursor-default" aria-label="Remove item: '{{subject}}'" data-button="">Remove item</button>
						</div>
					{{/each}}
				</div>
			</div>
		</div>
	</script>
	<script id="none-template" type="text/x-handlebars-template">
		<div class="choices" data-type="select-multiple" role="combobox" aria-autocomplete="list" aria-haspopup="true" aria-expanded="false">
			<div class="choices__inner">
				<select class="form-control choices__input" id="choices-multiple-remove-button" data-choices="" data-choices-removeitem="" name="subject" multiple="" hidden="" tabindex="-1" data-choice="active">
					<option value="과목" data-custom-properties="[object Object]">과목</option>
				</select>
				<div class="choices__list choices__list--multiple">
					<div class="choices__item choices__item--selectable" data-item="" data-id="1" data-value="과목" data-custom-properties="[object Object]" aria-selected="true" data-deletable="">
						과목
						<button type="button" class="choices__button" aria-label="Remove item: '과목'" data-button="">Remove item</button>
					</div>
				</div>
				<input type="search" name="search_terms" class="choices__input choices__input--cloned" autocomplete="off" autocapitalize="off" spellcheck="false" role="textbox" aria-autocomplete="list" aria-label="null">
			</div>
			<div class="choices__list choices__list--dropdown" aria-expanded="false">
				<div class="choices__list" aria-multiselectable="true" role="listbox">
					<div class="choices__item choices__item--choice has-no-choices">No choices to choose from</div>
				</div>
			</div>
		</div>
	</script>
</body>
</html>