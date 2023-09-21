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
<link href="./css/communityDetails.css" rel="stylesheet" type="text/css" />
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
					    <div class="col-12 gap-3">
					    	<c:if test="${sessionScope.community eq 'pilgi'}">
								<!-- Left sidebar -->
						        <div class="email-leftbar col-1">
						        	<h5><span class="badge badge-label bg-primary"><i class="mdi mdi-circle-medium"></i> 목차</span></h5>
							        <div class="card p-0 overflow-hidden shadow-none">
			                            <div id="list-example" class="list-group">
			                                <a class="list-group-item list-group-item-action" href="#list-item-1">Item 1</a>
			                                <a class="list-group-item list-group-item-action active" href="#list-item-2">Item 2</a>
			                                <a class="list-group-item list-group-item-action" href="#list-item-3">Item 3</a>
			                                <a class="list-group-item list-group-item-action" href="#list-item-4">Item 4</a>
			                            </div>
			                        </div>
			                        <c:if test="${yList.size() > 0}">
			                        	<h5><span class="badge badge-label bg-primary"><i class="mdi mdi-circle-medium"></i> 연관 필기</span></h5>
					                    <div class="card p-0 overflow-hidden shadow-none">
					                        <div class="mail-list">
					                        	<c:forEach items="${yList}" var="y" varStatus="vs">
					                        		<c:choose>
					                        			<c:when test="${status.last}">
							                            	<a class="view-detail" id="${y.id}"><span class="mdi mdi-arrow-right-drop-circle text-primary float-end"></span><b>${y.title}</b><p class="list-text mb-0 fs-12">${y.accountId}</p></a>
					                        			</c:when>
					                        			<c:otherwise>
							                            	<a class='border-bottom view-detail' id="${y.id}"><span class="mdi mdi-arrow-right-drop-circle text-primary float-end"></span><b>${y.title}</b><p class="list-text mb-0 fs-12">${y.accountId}</p></a>
					                        			</c:otherwise>			                        			
					                        		</c:choose>			                        	
					                        	</c:forEach>
					                        </div>
					                    </div>
			                        </c:if>
				                </div>
						        <!-- End Left sidebar -->
					        <!-- Right Sidebar -->
					    	</c:if>
					        <div class="${condition ? 'email-rightbar col-11 mb-3' : 'row'}">
					        	<div class="card">
					                <div class="card-body">
					
					                    <div class="">
					                        <div class="row mb-4">
					                            <div class="col-xl-9 col-md-12">
					                                <div class="pb-3 pb-xl-0">
					                                	<div class="d-flex gap-2 flex-shrink-0">
					                                		<c:choose>
					                                			<c:when test="${bVo.likeUser eq 1}">
														        	<img src="./image/heart_cancel.png" alt="" class="avatar-sm rounded-circle like-do" id="${bVo.id}"/>
					                                			</c:when>
					                                			<c:otherwise>
														        	<img src="./image/heart_do.png" alt="" class="avatar-sm rounded-circle like-do" id="${bVo.id}"/>
					                                			</c:otherwise>
					                                		</c:choose>
					                                		<div>
							                                    <h4 id="title">${bVo.title}</h4>
							                                    <p class="list-text mb-0 fs-12 ml-5 text-muted"> 
													        		<i class="ri-user-fill"></i>&ensp;${bVo.accountId}&ensp;
													        		<c:if test="${bVo.update ne bVo.regdate}">
														        		<span class="text-success"><i class="ri-restart-line"></i>&ensp;${bVo.update}&ensp;</span>
													        		</c:if>
													        		<i class="ri-timer-2-fill"></i>&ensp;${bVo.regdate}&ensp;
														    		<i class="ri-eye-fill"></i>&ensp;${bVo.viewCount}&ensp;
													        		<i class="ri-heart-fill"></i>&ensp;<span>${bVo.likeCount}</span>&ensp;
													        	</p>
					                                		</div>
												    	</div>
					                                </div>
					                            </div>
					                            <div class="col-xl-3 col-md-12">
					                                <div class="pb-3 pb-xl-0">
					                                    <div class="btn-toolbar float-end" role="toolbar">
					                                        <div class="btn-group me-2 mb-2">
					                                        	<c:if test="${bVo.state ne 'P'}">
						                                        	<c:if test="${bVo.downloadGroup eq 1}">
							                                            <button type="button" id="pdf-btn" class="btn btn-primary waves-light waves-effect"><i class=" ri-file-3-fill align-middle"></i></button>
						                                        	</c:if>
						                                        	<c:choose>
							                                        	<c:when test="${sessionScope.userInfo.userNickname eq bVo.accountId}">
								                                            <button type="button" id="update-btn" class="btn btn-primary waves-light waves-effect"><i class="bx bx-pencil align-middle"></i></button>
								                                            <button type="button" id="delete-btn" class="btn btn-primary waves-light waves-effect"><i class="bx bx-trash align-middle"></i></button>
							                                        	</c:when>
							                                        	<c:otherwise>
								                                            <button type="button" id="singo-btn" class="btn btn-primary waves-light waves-effect"><i class="ri-alarm-warning-line"></i></button>
							                                        	</c:otherwise>
						                                        	</c:choose>
					                                        	</c:if>
					                                        </div>
					                                    </div>
					                                </div>
					                            </div>
						                        <div class="list-text mb-0" class="sub-list">
						                        	<c:if test="${sessionScope.community eq 'jilmun' && bVo.clasId != null && bVo.clasId.length() > 0}">	
														<span class="badge badge-label bg-primary"><i class="mdi mdi-circle-medium"></i>${bVo.clasId}</span>
													</c:if>
													<c:forEach items="${subArr}" var="sub">		
														<span class="badge bg-primary-subtle text-primary ">${sub}</span>
													</c:forEach>
												</div>	
					                        </div>
					                        <hr>
					                        <c:choose>
					                        	<c:when test="${bVo.state eq 'P'}">
					                        		<div class="hstack gap-3 mb-3">
													    <a id="contentShow" class="link-danger" data-bs-toggle="collapse" href="#collapseWithicon" role="button" aria-expanded="true" aria-controls="collapseWithicon">
													        <i class="ri-arrow-down-circle-line fs-16"></i><span>신고되어 처리중인 게시글입니다. 내용을 확인하시려면 클릭해주세요.</span>
													    </a>
													</div>
													<div class="collapse" id="collapseWithicon">
												        <div class="ck-content">
												            ${bVo.content}
												        </div>
													</div>
					                        	</c:when>
					                        	<c:otherwise>
							                        <div class="ck-content" id="ck-content">
							                        	${bVo.content}
							                        </div>
					                        	</c:otherwise>
					                        </c:choose>
					                        <c:if test="${bVo.fileList.size() > 0}">
						                        <h5><span class="badge badge-label bg-primary"><i class="mdi mdi-circle-medium"></i> 첨부파일</span></h5>
			                                	<c:forEach items="${bVo.fileList}" var="file">
							                        <div class="mb-2">
				                                        <a href="./communityDownload.do?save=${file.fileSaveName}&name=${file.fileOriginName}" class="fw-medium download-btn"><i class="ri-download-2-fill"></i> ${file.fileOriginName}</a>
							                        </div>
			                                    </c:forEach>
					                        </c:if>
											<hr> 
					                        <a href="#" class="btn btn-primary dropdown-toggle">댓글보기</a>
					
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
	<!-- Varying modal content -->
	<div class="modal fade" id="varyingcontentModal" tabindex="-1" aria-labelledby="varyingcontentModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header bg-primary pb-3">
	                <h5 class="modal-title text-white" id="varyingcontentModalLabel"><i class="ri-alarm-warning-line"></i> 게시글 신고</h5>
	            </div>
	            <div class="modal-body pt-0">
	                <form id="user-singo">
	                	<input type="hidden" name="daesangId" value="${bVo.id}">
	                    <div class="mb-3">
	                        <label class="col-form-label text-primary"><i class="ri-check-line"></i> 신고 사유를 선택해주세요.</label>
	                        <div id="singo-list"></div>
	                    </div>
	                    <div class="mb-0" id="sayu-insert">
	                        <label for="message-text" class="col-form-label text-primary"><i class="ri-edit-2-line"></i> 기타 사유를 입력해주세요.</label>
	                        <textarea class="form-control" name="content" id="message-text"></textarea>
	                    </div>
	                </form>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-light" data-bs-dismiss="modal">취소</button>
	                <button type="button" class="btn btn-primary" data-bs-dismiss="modal" Sid="singo-submit">신고</button>
	            </div>
	        </div>
	    </div>
	</div>
	<div class="hidden">
		<div id="boardId">${bVo.id}</div>
		<button type="button" class="btn btn-primary btn-sm" id="sa-basic"></button>
		<button type="button" class="btn btn-primary btn-sm" id="sa-warning"></button>
		<button type="button" class="btn btn-primary btn-sm" id="sa-delete"></button>
		<button type="button" class="btn btn-primary btn-sm" id="sa-pilgi-delete"></button>
		<button type="button" id="modal-btn" class="btn btn-primary waves-light waves-effect" data-bs-toggle="modal" data-bs-target="#varyingcontentModal" data-bs-whatever="@mdo"></button>
	</div>
	<%@ include file="./shared/_vender_scripts.jsp" %>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js" charset="UTF-8"></script>
	<script src="./assets/libs/sweetalert2/sweetalert2.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
	<script src="./assets/js/app.js"></script>
	<script src="./js/community.js" charset="UTF-8"></script>
	<script src="./js/communityDetails.js"></script>
	<script id="singo-category-template" type="text/x-handlebars-template">
		<div class="form-check mb-2">
			<input class="form-check-input category" type="radio" name="category" id="singoSayu{{no}}" value="{{id}}">
			<label class="form-check-label" for="singoSayu{{no}}">
				{{category}}
			</label>
		</div>
	</script>
</body>
</html>