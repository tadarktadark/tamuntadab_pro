<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light" data-topbar="light" data-sidebar="light" data-sidebar-size="sm" data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title} | 타문타답</title>
<!-- <link href="./css/ckeditor.css" rel="stylesheet" type="text/css" /> -->
<%@ include file="./shared/_head_css.jsp" %>
<link href="./assets/libs/sweetalert2/sweetalert2.min.css" rel="stylesheet" type="text/css" />
<link href="./css/community.css" rel="stylesheet" type="text/css" />
<link href="./css/communityDetails.css" rel="stylesheet" type="text/css" />
<%@ include file="./shared/_logout.jsp" %>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_menu.jsp" %>
		
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<%@ include file="./shared/_page_title.jsp" %>
					<div class="row">
					    <div class="col-12 gap-3">
					    	<c:if test="${sessionScope.community eq 'pilgi'}">
								<!-- Left sidebar -->
						        <div class="email-leftbar col-1">
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
							                                    <h4 id="title">${bVo.title}
							                                    	<c:if test="${bVo.chaetaek eq 'Y'}">
							                                    		&ensp;<i class="ri-check-double-fill text-success" data-bs-toggle="tooltip" data-bs-placement="top" title="채택된 글은 수정 또는 삭제가 불가능합니다."></i>
							                                    	</c:if>
							                                    </h4>
							                                    <p class="list-text mb-0 fs-12 ml-5 text-muted"> 
													        		<i class="ri-user-fill"></i>&ensp;<span id="boardAccountId">${bVo.accountId}</span>&ensp;
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
					                                        	<div class="hidden" id="boardId">${bVo.id}</div>
					                                        	<c:if test="${bVo.state ne 'P'}">
						                                        	<c:if test="${bVo.downloadGroup eq 1}">
							                                            <button type="button" id="pdf-btn" class="btn btn-primary waves-light waves-effect"><i class=" ri-file-3-fill align-middle"></i></button>
						                                        	</c:if>
						                                        	<c:choose>
							                                        	<c:when test="${sessionScope.userInfo.userNickname eq bVo.accountId && bVo.chaetaek ne 'Y'}">
								                                            <button type="button" id="update-btn" class="btn btn-primary waves-light waves-effect"><i class="bx bx-pencil align-middle"></i></button>
								                                            <button type="button" id="delete-btn" class="btn btn-primary waves-light waves-effect"><i class="bx bx-trash align-middle"></i></button>
							                                        	</c:when>
							                                        	<c:when test="${sessionScope.userInfo.userNickname ne bVo.accountId}">
								                                            <button type="button" class="singo-btn btn btn-primary waves-light waves-effect"><i class="ri-alarm-warning-line"></i></button>
							                                        	</c:when>
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
													    <a class="link-danger contentShow" data-bs-toggle="collapse" href="#collapseWithicon" role="button" aria-expanded="true" aria-controls="collapseWithicon">
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
					                        <a id="reply-btn" class="btn btn-primary">댓글보기&ensp;<i class="ri-arrow-down-s-line"></i></a>
											<div id="reply-list" class="list-group mt-3" style="display:none;">
												<div id="reply-group">
												</div>
											    <div id="page-list" class="row align-items-center mt-3 gy-3">
												    <div class="col-sm-auto ms-auto mt-0">
											            <ul class="pagination mb-0">
											            </ul>
												    </div>
												</div>
											</div>
											<c:if test="${bVo.state ne 'P'}">
												<form id="rootReplyWrite" action="./replyWrite.do" method="post">
													<div class="mt-3">
														<input type="hidden" name="seq" id="updateSeq">
														<input type="hidden" name="boardId" value="${bVo.id}">
													    <textarea id="ckeditor" name="content" style="display:none;"></textarea>											    
													</div>
													<div class="pt-3 float-end" id="reply-write-btn-group">
														<input id="reply-write-btn" type="button" class="btn btn-primary" value="댓글작성">
													</div>
													<div class="pt-3 float-end" id="reply-update-btn-group" style="display:none;">
														<input id="reply-cancel-btn" type="button" class="btn btn-soft-primary" value="수정취소">
														<input id="reply-update-btn" type="button" class="btn btn-primary ms-2" value="댓글수정">
													</div>
												</form>
											</c:if>
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
	                	<input type="hidden" name="daesangId" id="daesangId">
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
	                <button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="singo-submit">신고</button>
	            </div>
	        </div>
	    </div>
	</div>
	<div class="hidden">
		<div id="loginUser">${sessionScope.userInfo.userNickname}</div>
		<div id="chaetaek">${bVo.chaetaek}</div>
		<button type="button" class="btn btn-primary btn-sm" id="sa-basic"></button>
		<button type="button" class="btn btn-primary btn-sm" id="sa-warning"></button>
		<button type="button" class="btn btn-primary btn-sm" id="sa-delete"></button>
		<button type="button" class="btn btn-primary btn-sm" id="sa-pilgi-delete"></button>
		<button type="button" class="btn btn-primary btn-sm" id="sa-reply-length"></button>
		<button type="button" id="modal-btn" class="btn btn-primary waves-light waves-effect" data-bs-toggle="modal" data-bs-target="#varyingcontentModal" data-bs-whatever="@mdo"></button>
	</div>
	<script src="./assets/libs/sweetalert2/sweetalert2.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
	<script type="text/javascript" src="./libs/ckeditor5/build/ckeditor.js"></script>
	<script type="text/javascript" src="./js/ckeditor.js"></script>
	<script src="./assets/js/app.js"></script>
	<script src="./js/html2canvas.js" charset="UTF-8"></script>
	<script src="./js/jspdf.min.js" charset="UTF-8"></script>
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
	<script id="page-list-template" type="text/x-handlebars-template">
		{{#each page}}
		<li class="page-item {{state}}">
			<span class="page-link" id="{{id}}">{{{htmlOrText value}}}</span>
		</li>
		{{/each}}
	</script>
	<script id="reply-list-template" type="text/x-handlebars-template">
		<div class="list-group-item bg-light">
			{{#isDel}}
			{{#stateN}}
	        <div class="list-text mb-2">{{{content}}}</div>
			{{/stateN}}
			{{#stateP}}
			<div class="hstack gap-3 mb-3">
				<a class="link-danger contentShow" data-bs-toggle="collapse" href="#collapseWithicon" role="button" aria-expanded="true" aria-controls="collapseWithicon">
					<i class="ri-arrow-down-circle-line fs-16"></i><span>신고되어 처리중인 게시글입니다. 내용을 확인하시려면 클릭해주세요.</span>
				</a>
			</div>
			<div class="collapse" id="collapseWithicon">
				<div class="list-text mb-2">
					{{{content}}}
				</div>
			</div>
			{{/stateP}}
			{{#stateD}}
	        <div class="list-text mb-2 text-danger">관리자에 의해 삭제된 게시글입니다.</div>
			{{/stateD}}
			{{else}}
			<p style="color:red;" class="list-text mb-2">{{content}}</p>
			{{/isDel}}	
	        <div class="d-flex align-items-center reply-group mb-1">
	        	<div class="flex-grow-1 reply-align">
	        		<div class="hidden replySeq">{{seq}}</div>
					{{#isDel}}
					{{#stateN}}
	        			{{#isNotWriter}}
	        			<a class="singo-btn reply-align reply-icon"><i class="ri-alarm-warning-line"></i></a>
	        			{{else}}
						{{#notChaetaek}}
	        			<a class="reply-delete-btn reply-align reply-icon"><i class="bx bx-trash align-middle"></i></a>
						<a class="reply-update-btn reply-align reply-icon"><i class="bx bx-pencil align-middle"></i></a>
						{{/notChaetaek}}
						{{/isNotWriter}}
	        			<a class="re-reply-btn reply-align reply-icon"><i class="ri-message-3-fill"></i></a>
						{{#noChaetaek}}
						<a class="reply-chaetaek-btn reply-align reply-icon"><i class="ri-check-double-fill"></i></a>
						{{/noChaetaek}}
					{{/stateN}}
					{{/isDel}}											        		
	        	</div>
	            <div class="ms-3 float-end">
	                <h5 class="list-title fs-15 mb-0">
	                	{{#isChaetaek}}
	                	<i class="ri-check-double-fill text-success" data-bs-toggle="tooltip" data-bs-placement="top" title="채택된 글은 수정 또는 삭제가 불가능합니다."></i> 
						{{/isChaetaek}}
	                	<i class="ri-user-fill"></i> {{writerId}}</h5>
	                <div class="list-text mb-0 fs-12">
						{{#isUpdate}}
			        	<span class="text-danger"><i class="ri-restart-line"></i>&ensp;{{update}}&ensp;</span>
						{{/isUpdate}}
		        		<i class="ri-timer-2-fill"></i>&ensp;{{regdate}}&ensp;
	                </div>
	            </div>
	        </div>
			{{#each re-reply}}
			<div class="list-group-item re-reply-group ms-3">
				{{#isReDel}}
				{{#reStateN}}
				<p class="list-text mb-2">{{{re-content}}}</p>
				{{/reStateN}}
				{{#reStateP}}
				<div class="hstack gap-3 mb-3">
					<a class="link-danger contentShow" data-bs-toggle="collapse" href="#collapseWithicon" role="button" aria-expanded="true" aria-controls="collapseWithicon">
						<i class="ri-arrow-down-circle-line fs-12"></i><span>신고되어 처리중인 게시글입니다. 내용을 확인하시려면 클릭해주세요.</span>
					</a>
				</div>
				<div class="collapse" id="collapseWithicon">
					<div class="list-text mb-2">
						{{{re-content}}}
					</div>
				</div>
				{{/reStateP}}
				{{#reStateD}}
	        	<div class="list-text mb-2 text-danger">관리자에 의해 삭제된 게시글입니다.</div>
				{{/reStateD}}
				{{else}}
				<p style="color:red;" class="list-text mb-2">{{re-content}}</p>
				{{/isReDel}}
				<div class="d-flex align-items-center reply-group">
					<div class="flex-grow-1 reply-align">
						<div class="hidden replySeq">{{re-seq}}</div>
						{{#isReDel}}
						{{#reStateN}}
							{{#isReWriter}}
							<a class="singo-btn reply-align reply-icon"><i class="ri-alarm-warning-line"></i></a>
							{{else}}
							<a class="reply-delete-btn reply-align reply-icon"><i class="bx bx-trash align-middle"></i></a>
							<a class="re-update-btn reply-align reply-icon"><i class="bx bx-pencil align-middle"></i></a>
							{{/isReWriter}}
						{{/reStateN}}
						{{/isReDel}}										        		
					</div>
					<div class="ms-3 float-end">
						<h6 class="list-title fs-12 mb-0"><i class="ri-user-fill"></i> {{re-writerId}}</h6>
						<div class="list-text mb-0 fs-9">
							{{#isReUpdate}}
								<span class="text-danger"><i class="ri-restart-line"></i>&ensp;{{re-update}}&ensp;</span>
							{{/isReUpdate}}
							<i class="ri-timer-2-fill"></i>&ensp;{{re-regdate}}&ensp;
						</div>
					</div>
				</div>
			</div>
			{{/each}}
	    </div>
	</script>
	<script id="re-reply-template" type="text/x-handlebars-template">
		<div id="re-insert" class="list-group-item re-reply-group ms-3">
			<form id="re-write-form" action="./insertReReply.do" method="post">
	        	<div>
					<input type="hidden" name="boardId" id="re-boardId">
					<input type="hidden" name="rootSeq" id="re-rootSeq">
	        		<textarea id="re-textBox" name="content" style="resize: none;" maxlength="300" rows="3"></textarea>
	        	</div>
	        	<div>
	        		<span class="flex-grow-1"><span id="textCount">0</span>/300</span>
	        		<button id="re-write-btn" class="badge text-bg-primary ms-1 float-end">작성</button>
	        		<button id="re-cancel-btn" class="badge bg-primary-subtle text-primary float-end">취소</button>
	        	</div>
			</form>
	    </div>
	</script>
	<script id="re-update-template" type="text/x-handlebars-template">
		<form id="re-update-form" action="./updateReReply.do" method="post">
	       	<div>
				<input type="hidden" name="boardId" id="re-boardId">
				<input type="hidden" name="seq" id="re-seq">
	       		<textarea id="re-textBox" name="content" style="resize: none;" maxlength="300" rows="3">{{content}}</textarea>
	       	</div>
	       	<div>
	       		<span class="flex-grow-1"><span id="textCount">{{length}}</span>/300</span>
	       		<button id="re-update" class="badge text-bg-primary ms-1 float-end">작성</button>
	       		<button id="re-cancel" class="badge bg-primary-subtle text-primary float-end">취소</button>
	       	</div>
		</form>
	</script>
	<%@ include file="./shared/_vender_scripts.jsp" %>
</body>
</html>