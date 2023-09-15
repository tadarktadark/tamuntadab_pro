<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light" data-topbar="light" data-sidebar="light" data-sidebar-size="sm" data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title} | 타문타답</title>
<%@ include file="./shared/_head_css.jsp" %>
<link href="./css/board.css" rel="stylesheet" type="text/css" />
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp" %>
		<%@ include file="./shared/_sidebar.jsp" %>
		
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<%@ include file="./shared/_page_title.jsp" %>
						<div class="row pb-4 gy-3">
						    <div class="col-sm-4">
						        <a href="./pilgiWriteForm.do" class="btn btn-primary addMembers-modal"><i class="bx bx-plus me-1 fs-16 align-middle"></i>필기 작성</a>
						    </div>
						
						    <div class="col-sm-auto ms-auto">
						        <div class="d-flex gap-3">
<!-- 						            <div class="search-box"> -->
<!-- 						                <input type="text" class="form-control" placeholder="Search for name or designation..."> -->
<!-- 						                <i class="bx bx-search search-icon fs-16"></i> -->
<!-- 						            </div> -->
						            <div class="row gy-3">
										<div class="col-sm-auto ms-auto" id="select-box">
											<select id="order-by" class="form-select form-select-sm  mb-3" aria-label=".form-select-sm example">
											    <option value="date" selected>최신순</option>
											    <option value="view">조회순</option>
											    <option value="like">좋아요순</option>
											    <option value="reply">댓글순</option>
											</select>
										</div>
									</div>
						        </div>
						    </div>
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
	<%@ include file="./shared/_vender_scripts.jsp" %>
	<script src="./assets/js/app.js"></script>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js" charset="UTF-8"></script>
	<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
	<script src="./js/pilgi.js" charset="UTF-8"></script>
	<script id="board-list-template" type="text/x-handlebars-template">
		<div class="list-group-item list-group-item-action view-detail" id="{{id}}">
			<div class="float-end">
					{{accountId}}
			</div>
			<div class="d-flex mb-2 align-items-center">
		    	<div class="flex-shrink-0">
		        	<img src="./image/heart_{{heart}}.png" alt="" class="avatar-sm rounded-circle like-do" id="like_{{id}}"/>
		    	</div>
		    	<div class="flex-grow-1 ms-3">
		        	<h5 class="list-title fs-15 mb-1">{{title}}</h5>
		        	<p class="list-text mb-0 fs-12"> 
			        		<i class=" ri-timer-2-fill"></i>&ensp;{{regdate}}&ensp;
			        		<i class="ri-heart-fill"></i>&ensp;<span>{{likeCount}}</span>&ensp;
				    		<i class="ri-eye-fill"></i>&ensp;{{viewCount}}&ensp;
		        	</p>
		    	</div>
			</div>
			<div class="list-text mb-0" class="sub-list">
				{{#each subjects}}
				<span class="badge badge-label bg-{{color}}"><i class="mdi mdi-circle-medium"></i>{{subject}}</span>
				{{/each}}
			</div>					    	
		</div>
	</script>
	<script id="page-list-template" type="text/x-handlebars-template">
		{{#each page}}
		<li class="page-item {{state}}">
			<span class="page-link" id="{{id}}">{{value}}</span>
		</li>
		{{/each}}
	</script>
</body>
</html>