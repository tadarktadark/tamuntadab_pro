<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light" data-topbar="light" data-sidebar="light" data-sidebar-size="sm" data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title} | 타문타답</title>
<%@ include file="./shared/_head_css.jsp" %>
<link href="./assets/libs/sweetalert2/sweetalert2.min.css" rel="stylesheet" type="text/css" />
<link href="./css/community.css" rel="stylesheet" type="text/css" />
<link href="./css/communityList.css" rel="stylesheet" type="text/css" />
<%@ include file="./shared/_logout.jsp" %>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_menu.jsp" %>
		
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<%@ include file="./shared/_page_title.jsp" %>
					<div class="row gy-3">
						<form>
							<!-- Input with Icon -->
							<c:if test="${sessionScope.community ne 'jayu'}">
								<div class="d-flex gap-3">
								    <div class="form-icon right-input">
								    	<div class="choices" data-type="select-multiple" role="combobox" aria-autocomplete="list" aria-haspopup="true" aria-expanded="false">
											<div class="choices__inner">
												<div id="selectedSubjects" class="choices__list choices__list--multiple">
												</div>
												<input type="search" id="subjects" name="subjects" class="choices__input choices__input--cloned" placeholder="과목 또는 클래스로 검색해보세요!" autocomplete="off" autocapitalize="off" spellcheck="false" role="textbox" aria-autocomplete="list" aria-label="null">
											</div>
										</div>
								    </div>
								    <div class="col-sm-auto ms-auto">
									    <button id="reset-btn" type="button" class="btn btn-outline-primary rigth-btn">초기화</button>
								    </div>
								</div>
							</c:if>
							<div class="d-flex gap-3 mb-3 mt-1">
							    <div class="form-icon right-input">
							        <input type="text" class="form-control" id="search-basic" placeholder="작성자, 제목, 내용으로 검색해보세요!">
							    </div>
							    <div class="col-sm-auto ms-auto">
								    <button type="submit" class="btn btn-primary rigth-btn">검색</button>
							    </div>
							</div>
						</form>
					</div>
					<div class="row gy-3">
						<div class="col-sm-8 d-flex gap-3" id="select-box">
							<div class="col-sm-auto" ><p id="date" class="mt-3 order-by"><i class="mdi mdi-circle-medium fs-18 text-primary align-middle me-1"></i>최신순</p></div>
							<div class="col-sm-auto" ><p id="view" class="mt-3 order-by"><i class="mdi mdi-circle-medium fs-18 text-muted align-middle me-1"></i> 조회순</p></div>
							<div class="col-sm-auto" ><p id="like" class="mt-3 order-by"><i class="mdi mdi-circle-medium fs-18 text-muted align-middle me-1"></i> 좋아요순</p></div>
							<div class="col-sm-auto" ><p id="reply" class="mt-3 order-by"><i class="mdi mdi-circle-medium fs-18 text-muted align-middle me-1"></i> 댓글많은순</p></div>
						</div>
						<c:if test="${sessionScope.community ne 'pilgi'}">
							<div class="col-sm-auto ms-auto">
						        <a id="write-btn" class="btn btn-primary rigth-btn"><i class="bx bx-plus me-1 fs-16 align-middle"></i>글쓰기</a>
						    </div>
						</c:if>
					</div>							
					<div class="mb-3 list-group">
					</div>
					<div class="row align-items-center mb-4 gy-3">
					    <div class="col-sm-auto ms-auto">
				            <ul class="pagination mb-0">
				            </ul>
					    </div>
					</div>
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp" %>
		</div>
	</div>	
	<div class="hidden">
		<input id="clasId" value="${clasId}">
		<input id="clasTitle" value="${clasTitle}">
		<button type="button" class="btn btn-primary btn-sm" id="sa-basic"></button>
		<button type="button" class="btn btn-primary btn-sm" id="sa-warning"></button>
		<button type="button" class="btn btn-primary btn-sm" id="sa-error">Click me</button>
	</div>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" charset="UTF-8"></script>
	<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
    <script src="./assets/libs/sweetalert2/sweetalert2.min.js"></script>
	<script src="./js/community.js" charset="UTF-8"></script>
	<script src="./js/communityList.js" charset="UTF-8"></script>
	<script id="board-list-template" type="text/x-handlebars-template">
		<div class="list-group-item list-group-item-action view-detail" id="{{id}}">
			<div class="float-end">
					{{accountId}}
			</div>
			<div class="d-flex mb-2 align-items-center">
		    	<div class="flex-shrink-0">
		        	<img src="./image/heart_{{heart}}.png" alt="" class="avatar-sm rounded-circle like-do" id="{{id}}"/>
		    	</div>
		    	<div class="flex-grow-1 ms-3">
		        	<h5 class="list-title fs-15 mb-1">
						{{title}}
						{{#isChaetaek}}
							&ensp;<i class="ri-check-double-fill text-success"></i>
						{{/isChaetaek}}
					</h5>
		        	<p class="list-text mb-0 fs-12"> 
			        	<i class=" ri-timer-2-fill"></i>&ensp;{{regdate}}&ensp;
				    	<i class="ri-eye-fill"></i>&ensp;{{viewCount}}&ensp;
			        	<i class="ri-heart-fill"></i>&ensp;<span>{{likeCount}}</span>&ensp;
		        	</p>
		    	</div>
			</div>
			<div class="list-text mb-0" class="sub-list">
				{{#isClass}}
      			<span class="badge bg-primary-subtle text-primary">{{clasId}}</span>
    			{{/isClass}}
				{{#each subjects}}
				<span class="badge bg-{{color}}-subtle text-{{color}}">{{subject}}</span>
				{{/each}}
			</div>					    	
		</div>
	</script>
	<script id="page-list-template" type="text/x-handlebars-template">
		{{#each page}}
		<li class="page-item {{state}}">
			<span class="page-link" id="{{id}}">{{{htmlOrText value}}}</span>
		</li>
		{{/each}}
	</script>
	<%@ include file="./shared/_vender_scripts.jsp" %>
</body>
</html>