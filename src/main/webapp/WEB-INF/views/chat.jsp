<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-topbar="light" data-sidebar="light" data-sidebar-size="lg" data-sidebar-image="none" data-preloader="disable" data-bs-theme="light" data-layout-width="fluid" data-layout-position="fixed" data-layout-style="default"><head>
<head>
<meta charset="UTF-8">
<title> ${title} | 타문타답</title>
<%@ include file="./shared/_head_css.jsp" %>
<style type="text/css">
	.right img{
		display: none;
	}
	
</style>
</head>
<body>
<div id="layout-wrapper">
	<%@ include file="./shared/_menu.jsp"%>
	<div class="main-content">
		<div class="page-content">
			<div class="container-fluid">
				<%@ include file="./shared/_page_title.jsp"%>
			</div>
		<div class="d-lg-flex">
		    <div class="chat-leftsidebar card">
		        <div class="card-body">
		            <div class="text-center bg-light rounded px-4 py-5">
		                <div class="chat-user-status">
		                    <img src="${userInfo.userProfileFile}" class="avatar-md rounded-circle" alt="">
		                    <input type="hidden" id="profileImg" value="${userInfo.userProfileFile}">
		                    <div class="">
		                        <div class="status"></div>
		                    </div>
		                </div>
		                <h5 class="fs-16 mb-1 mt-3"><a href="#" id="nickname" class="text-body">${userInfo.userNickname}</a></h5>
		                <input id="userAccountId" type="hidden" value="${userInfo.userAccountId}">
		                <p class="text-muted mb-0">${(userInfo.userAuth eq 'I')?'강사':'학생'}</p>
		            </div>
		        </div>
		        <div class="chat-leftsidebar-nav">
		            <ul class="nav nav-pills nav-justified bg-light m-3 rounded">
		                <li class="nav-item">
		                    <a href="#chat" data-bs-toggle="tab" aria-expanded="true" class="nav-link active">
		                        <i class="bx bx-chat fs-20 d-sm-none"></i>
		                        <span class="d-none d-sm-block">채팅</span>
		                    </a>
		                </li>
		            </ul>
		            <div class="tab-content">
		                <div class="tab-pane show active" id="chat">
		                    <div id="chat-message-list" class="chat-message-list" data-simplebar>
		                        <div class="pt-3">
		                            <div class="px-3">
		                                <h5 class="fs-15 mb-3">Recent</h5>
		                            </div>
		                            <input type="hidden" id="selectedChatRoom">
		                            <ul class="list-unstyled chat-list p-3">
		                            <c:forEach var="room" items="${roomList}">
		                            	<li class="chatRoomList" data-chroid="${room.chroId}" value="${room.chroId}">
		                                    <a href="#">
		                                        <div class="d-flex align-items-center">
		                                            <div class="flex-grow-1 overflow-hidden">
		                                            <c:choose>
		                                            <c:when test="${room.chroTitle.contains('/')}">
		                                        	    <c:choose>
			                                            	<c:when test="${userInfo.userAuth eq 'I' }">
				                                                <h5 class="text-truncate fs-15 mb-0">${room.chroTitle.substring(room.chroTitle.indexOf('/')+1,room.chroTitle.length())}</h5>
			                                            	</c:when>
			                                            	<c:otherwise>
			                                            		<h5 class="text-truncate fs-15 mb-0">${room.chroTitle.substring(0,room.chroTitle.indexOf('/'))}</h5>
			                                            	</c:otherwise>
			                                            </c:choose>
		                                            </c:when>
		                                            <c:otherwise>
			                                            <h5 class="text-truncate fs-15 mb-0">${room.chroTitle}</h5>
		                                            </c:otherwise>
		                                            </c:choose>
		                                            </div>
		                                            <div class="countOfMessage flex-shrink-0">
		                                            </div>
		                                        </div>
		                                    </a>
		                                </li>
		                            </c:forEach>
		                            <li>
	                                    <a href="#">
	                                        <div class="d-flex align-items-center">
	                                            <div class="flex-grow-1 overflow-hidden">
	                                            	<h5 class="text-truncate fs-15 mb-0"><i style="font-size: 20px;vertical-align: middle;position: relative;top: -1px;" class="ph-robot"></i> 챗봇</h5>
	                                            </div>
	                                        </div>
	                                    </a>
	                                </li>
		                            </ul>
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		    <!-- end chat-leftsidebar -->
		    <div id="chatDetail" style="display:none;" class="w-100 user-chat mt-4 mt-sm-0 ms-lg-3">
		        <div class="card">
		            <div class="p-3 px-lg-4 border-bottom">
		                <div class="row">
		                    <div class="col-xl-4 col-7">
		                        <div class="d-flex align-items-center">
		                            <div class="flex-shrink-0 avatar-sm me-3 d-sm-block d-none">
		                                <img src="~/assets/images/users/avatar-6.jpg" alt="" class="img-fluid d-block rounded-circle">
		                            </div>
		                            <div class="flex-grow-1">
		                                <h5 class="fs-16 mb-1 text-truncate"><a href="#" id="chatRoomTitle" class="text-body"></a></h5>
		                                <p id="chatRoomDate" class="text-muted text-truncate mb-0">Online</p>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		            </div>
		
		            <div id="chat-conversation" class="chat-conversation p-3" data-simplebar>
		                <ul  id="total-chat-list" class="list-unstyled mb-0">
		                </ul>
		            </div>
		
		            <div class="p-3 border-top">
		                <div class="row">
		                    <div class="col">
		                        <div class="position-relative">
		                            <input id="chatInput" type="text" class="form-control border chat-input" autocomplete="off">
		                        </div>
		                    </div>
<!-- 							<div class="col-4"> -->
<!-- 							    <input class="form-control border chat-input" type="file" id="formFile"> -->
<!-- 							</div> -->
		                    <div class="col-auto">
		                        <button type="button" id="chatBtn" class="btn btn-primary chat-send w-md waves-effect waves-light"><span class="d-none d-sm-inline-block me-2">전송</span> <i class="mdi mdi-send float-end"></i></button>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		
		    <!-- end user chat -->
<%@ include file="./shared/_footer.jsp" %>
		</div>
	</div>
</div>

<!-- End d-lg-flex  -->
<%@ include file="./shared/_vender_scripts.jsp" %>
<script type="text/javascript" src="~/assets/js/app.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script type='text/javascript' src="./js/chat.js"></script>
</body>
</html>