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
<!-- 					    <div class="col-sm-4"> -->
<!-- 					        <div class="d-flex gap-3"> -->
<!-- 					            <div class="search-box"> -->
<!-- 					                <input type="text" class="form-control" placeholder="Search for name or designation..."> -->
<!-- 					                <i class="bx bx-search search-icon fs-16"></i> -->
<!-- 					            </div> -->
<!-- 					            <div class=""> -->
<!-- 					                <button type="button" id="dropdownMenuLink1" data-bs-toggle="dropdown" aria-expanded="false" class="btn btn-soft-info btn-icon fs-14"><i class="bx bx-dots-vertical-rounded fs-18"></i></button> -->
<!-- 					                <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink1"> -->
<!-- 					                    <li><a class="dropdown-item" href="#">Print</a></li> -->
<!-- 					                    <li><a class="dropdown-item" href="#">Export to Excel</a></li> -->
<!-- 					                </ul> -->
<!-- 					            </div> -->
<!-- 					        </div> -->
<!-- 					    </div> -->
					</div>
					<div class="row gy-3">
						<div class="col-sm-auto ms-auto">
							<select class="form-select form-select-sm  mb-3" aria-label=".form-select-sm example">
							    <option value="date" selected>최신순</option>
							    <option value="view">조회순</option>
							    <option value="like">좋아요순</option>
							    <option value="reply">댓글순</option>
							</select>
						</div>
					</div>
					<div class="list-group">
						<script id="board-list-template" type="text/x-handlebars-template">
						<div class="list-group-item list-group-item-action">
			        		<div class="float-end">
			            		{{accountId}}
			        		</div>
			        		<div class="d-flex mb-2 align-items-center">
				            	<div class="flex-shrink-0">
				                	<img src="./image/heart_{{heart}}.png" alt="" class="avatar-sm rounded-circle" />
				            	</div>
				            	<div class="flex-grow-1 ms-3">
				                	<h5 class="list-title fs-15 mb-1">{{title}}</h5>
				                	<p class="list-text mb-0 fs-12"> 
			    	            		<i class=" ri-timer-2-fill"></i>&ensp;{{regdate}}&ensp;
			        	        		<i class="ri-heart-fill"></i>&ensp;{{likeCount}}&ensp;
			            	    		<i class="ri-eye-fill"></i>&ensp;{{viewCount}}&ensp;
				                	</p>
				            	</div>
				        	</div>
				        	<div class="list-text mb-0" class="sub-list">
			        		</div>					    	
						</div>
						</script>
						<script id="sub-list-template" type="text/x-handlebars-template">
							<span class="badge badge-label bg-{{color}}"><i class="mdi mdi-circle-medium"></i>{{subject}}</span>
						</script>
					</div>
					<div class="row align-items-center mb-4 gy-3">
					    <div class="col-md-5">
					        <p class="mb-0 text-muted">Showing <b>1</b> to <b>5</b> of <b>10</b> results</p>
					    </div>
					    <div class="col-sm-auto ms-auto">
					        <nav aria-label="...">
					            <ul class="pagination mb-0">
					                <li class="page-item disabled">
					                    <span class="page-link">Previous</span>
					                </li>
					                <li class="page-item active"><a class="page-link" href="#">1</a></li>
					                <li class="page-item" aria-current="page">
					                    <span class="page-link">2</span>
					                </li>
					                <li class="page-item"><a class="page-link" href="#">3</a></li>
					                <li class="page-item">
					                    <a class="page-link" href="#">Next</a>
					                </li>
					            </ul>
					        </nav>
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
</body>
</html>