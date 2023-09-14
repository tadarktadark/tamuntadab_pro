<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light" data-topbar="light" data-sidebar="light" data-sidebar-size="sm" data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title} | 타문타답</title>
<%@ include file="./shared/_head_css.jsp" %>
<link href="./css/board.css" rel="stylesheet" type="text/css" />
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
					<div class="row">
					    <div class="col-12">
					        <!-- Left sidebar -->
					        <div class="email-leftbar">
			                    <h5 class="mt-3 fs-15 text-uppercase">목차</h5>
						        <div class="card p-0 overflow-hidden mt-3 shadow-none">
		                            <div id="list-example" class="list-group">
		                                <a class="list-group-item list-group-item-action" href="#list-item-1">Item 1</a>
		                                <a class="list-group-item list-group-item-action active" href="#list-item-2">Item 2</a>
		                                <a class="list-group-item list-group-item-action" href="#list-item-3">Item 3</a>
		                                <a class="list-group-item list-group-item-action" href="#list-item-4">Item 4</a>
		                            </div>
		                        </div>
			                    <h5 class="mt-3 fs-15 text-uppercase">연관 필기 목록</h5>
			                    <div class="card p-0 overflow-hidden mt-3 shadow-none">
			                        <div class="mail-list">
			                        	<c:forEach items="${yList}" var="y" varStatus="vs">
			                        		<c:choose>
			                        			<c:when test="${status.last}">
					                            	<a href="#"><span class="mdi mdi-arrow-right-drop-circle text-primary float-end"></span><b>${y.title}</b><p class="list-text mb-0 fs-12">${y.accountId}</p></a>
			                        			</c:when>
			                        			<c:otherwise>
					                            	<a href="#" class='border-bottom'><span class="mdi mdi-arrow-right-drop-circle text-primary float-end"></span><b>${y.title}</b><p class="list-text mb-0 fs-12">${y.accountId}</p></a>
			                        			</c:otherwise>			                        			
			                        		</c:choose>			                        	
			                        	</c:forEach>
<!-- 			                            <a href="#"><span class="mdi mdi-arrow-right-drop-circle text-success float-end"></span>Family</a> -->
			                        </div>
			                    </div>
			                </div>
					        <!-- End Left sidebar -->
					        <!-- Right Sidebar -->
					        <div class="email-rightbar mb-3">
					        	<div class="card">
					                <div class="card-body">
					
					                    <div class="">
					                        <div class="row mb-4">
					                            <div class="col-xl-9 col-md-12">
					                                <div class="pb-3 pb-xl-0">
					                                    <h4 id="title" class="${bVo.id}">${bVo.title}</h4>
					                                </div>
					                            </div>
					                            <div class="col-xl-3 col-md-12">
					                                <div class="pb-3 pb-xl-0">
					                                    <div class="btn-toolbar float-end" role="toolbar">
					                                        <div class="btn-group me-2 mb-2">
					                                            <button type="button" class="btn btn-primary waves-light waves-effect"><i class="bx bx-pencil align-middle"></i></button>
					                                            <button type="button" class="btn btn-primary waves-light waves-effect"><i class="bx bx-trash align-middle"></i></button>
					                                        </div>
					                                    </div>
					                                </div>
					                            </div>
						                        <div class="list-text mb-0" class="sub-list">
													<c:forEach items="${subArr}" var="sub">		
														<span class="badge bg-primary-subtle text-primary ">${sub}</span>
													</c:forEach>
												</div>	
					                        </div>
					                        
					                        ${bVo.content}
					
					                        <div class="row">
					                            <div class="col-xl-2 col-6">
					                                <div class="card">
					                                    <img class="card-img-top img-fluid" src="~/assets/images/small/img-3.jpg" alt="Card image cap">
					                                    <div class="py-2 text-center">
					                                        <a href="javascript: void(0);" class="fw-medium">Download</a>
					                                    </div>
					                                </div>
					                            </div>
					                            <div class="col-xl-2 col-6">
					                                <div class="card">
					                                    <img class="card-img-top img-fluid" src="~/assets/images/small/img-4.jpg" alt="Card image cap">
					                                    <div class="py-2 text-center">
					                                        <a href="javascript: void(0);" class="fw-medium">Download</a>
					                                    </div>
					                                </div>
					                            </div>
					                        </div>
					
					                        <a href="javascript: void(0);" class="btn btn-primary waves-effect mt-4"><i class="mdi mdi-reply me-1"></i> 댓글</a>
					
					                    </div>
					
					                </div>
					
					            </div>
					        </div> <!-- end Col-9 -->
					    </div>
					</div>
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp" %>
		</div>
	</div>	
	<%@ include file="./shared/_vender_scripts.jsp" %>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js" charset="UTF-8"></script>
	<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
	<script src="./assets/js/app.js"></script>
	<script id="board-list-template" type="text/x-handlebars-template">
		
	</script>
	<script id="page-list-template" type="text/x-handlebars-template">
		
	</script>
</body>
</html>