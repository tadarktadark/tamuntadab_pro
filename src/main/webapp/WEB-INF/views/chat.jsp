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
		            <div class="text-center bg-light rounded px-4 py-4">
		                <div class="chat-user-status">
		                    <img src="${userInfo.userProfileFile}" class="avatar-md rounded-circle" alt="">
		                    <div class="">
		                        <div class="status"></div>
		                    </div>
		                </div>
		                <h5 class="fs-16 mb-1 mt-3"><a href="#" id="nickname" class="text-body">${userInfo.userNickname}</a></h5>
		                <p class="text-muted mb-0">${(userInfo.userAuth eq 'I')?'강사':'학생'}</p>
		            </div>
		        </div>
		        <div class="chat-leftsidebar-nav">
		            <ul class="nav nav-pills nav-justified bg-light m-3 rounded">
		                <li class="nav-item">
		                    <a href="#chat" data-bs-toggle="tab" aria-expanded="true" class="nav-link active">
		                        <i class="bx bx-chat fs-20 d-sm-none"></i>
		                        <span class="d-none d-sm-block">Chat</span>
		                    </a>
		                </li>
		            </ul>
		            <div class="tab-content">
		                <div class="tab-pane show active" id="chat">
		                    <div class="chat-message-list" data-simplebar>
		                        <div class="pt-3">
		                            <div class="px-3">
		                                <h5 class="fs-15 mb-3">Recent</h5>
		                            </div>
		                            <ul class="list-unstyled chat-list p-3">
		                            <c:forEach var="room" items="${roomList}">
		                            	<li class="chatRoomList" value="${room.chroId}" onclick="openChat(this)">
		                                    <a href="#">
		                                        <div class="d-flex align-items-center">
		                                            <div class="flex-grow-1 overflow-hidden">
		                                                <h5 class="text-truncate fs-15 mb-0">${room.chroTitle}</h5>
		                                            </div>
		                                            <div class="flex-shrink-0">
		                                            </div>
		                                        </div>
		                                    </a>
		                                </li>
		                            </c:forEach>
		                                <li class="active">
		                                    <a href="#">
		                                        <div class="d-flex align-items-center">
		                                            <div class="flex-grow-1 overflow-hidden">
		                                                <h5 class="text-truncate fs-15 mb-0">Jennie Sherlock</h5>
		                                            </div>
		                                            <div class="flex-shrink-0">
		                                                <span class="badge bg-danger rounded-pill">1</span>
		                                            </div>
		                                        </div>
		                                    </a>
		                                </li>
		                                <li>
		                                    <a href="#">
		                                        <div class="d-flex align-items-center">
		                                            <div class="flex-shrink-0 user-img online align-self-center me-3">
		                                                <div class="avatar-xs align-self-center">
		                                                    <span class="avatar-title rounded-circle bg-primary-subtle  text-primary fs-15 fs-15">
		                                                        S
		                                                    </span>
		                                                </div>
		                                                <span class="user-status"></span>
		                                            </div>
		
		                                            <div class="flex-grow-1 overflow-hidden">
		                                                <h5 class="text-truncate fs-15 mb-0">Stacie Dube</h5>
		                                            </div>
		                                            <div class="flex-shrink-0">
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
		                    <div class="col-xl-8 col-5">
		                        <ul class="list-inline user-chat-nav text-end mb-0">
		                            <li class="list-inline-item">
		                                <div class="dropdown">
		                                    <div class="dropdown-menu dropdown-menu-end dropdown-menu-md p-2">
		                                        <form class="px-2">
		                                            <div>
		                                                <input type="text" class="form-control border bg-light-subtle " placeholder="Search...">
		                                            </div>
		                                        </form>
		                                    </div>
		                                </div>
		                            </li>
		
		                            <li class="list-inline-item">
		                                <div class="dropdown">
		                                    <button class="btn nav-btn" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		                                        <i class="bx bx-dots-horizontal-rounded"></i>
		                                    </button>
		                                    <div class="dropdown-menu dropdown-menu-end">
		                                        <a class="dropdown-item" href="#">Profile</a>
		                                        <a class="dropdown-item" href="#">Archive</a>
		                                        <a class="dropdown-item" href="#">Muted</a>
		                                        <a class="dropdown-item" href="#">Delete</a>
		                                    </div>
		                                </div>
		                            </li>
		                        </ul>
		                    </div>
		                </div>
		            </div>
		
		            <div class="chat-conversation p-3" data-simplebar>
		                <ul  id="total-chat-list" class="list-unstyled mb-0">
		                    <li class="chat-day-title">
		                        <span class="title">Thursday</span>
		                    </li>
		                    <li>
		                        <div class="conversation-list">
		                            <div class="d-flex">
		                                <img src="~/assets/images/users/avatar-6.jpg" class="rounded-circle avatar-sm" alt="">
		                                <div class="flex-1 ms-3">
		                                    <div class="d-flex justify-content-between">
		                                        <h5 class="fs-16 conversation-name align-middle"> Jennie Sherlock </h5>
		                                        <span class="time fw-normal text-muted me-0 me-md-4">Thursday 10:02 AM</span>
		                                    </div>
		                                    <div class="ctext-wrap">
		                                        <div class="ctext-wrap-content bg-light">
		                                            <p class="mb-0">
		                                                Hi Jordan! </br>
		                                                Feels like it's been a while! Home are you? Do you
		                                                have ant time for the remainder of the week to help me with an ongoing project?
		                                            </p>
		
		                                        </div>
		                                        <div class="dropdown align-self-start">
		                                            <a href="#" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		                                                <i class="bx bx-dots-vertical-rounded fs-18 me-2"></i>
		                                            </a>
		                                            <div class="dropdown-menu">
		                                                <a class="dropdown-item" href="#">Copy</a>
		                                                <a class="dropdown-item" href="#">Save</a>
		                                                <a class="dropdown-item" href="#">Forward</a>
		                                                <a class="dropdown-item" href="#">Delete</a>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
		                        </div>
		                    </li>
		
		                    <li class="right">
		                        <div class="conversation-list">
		                            <div class="d-flex">
		                                <div class="flex-1 me-3">
		                                    <div class="d-flex justify-content-between">
		                                        <span class="time fw-normal text-muted ms-0 ms-md-4">Thursday 10:02 AM</span>
		                                        <h5 class="fs-16 conversation-name align-middle"> Jimmie Williams </h5>
		                                    </div>
		                                    <div class="ctext-wrap">
		                                        <div class="ctext-wrap-content">
		                                            <p class="mb-0 text-start">
		                                                Hi Martin, Glad to hear from you, I'm fine,what about you? and how it's going with the velocity website?
		                                                </br>
		                                                Of course I will help with this project
		                                            </p>
		
		                                        </div>
		                                        <div class="dropdown align-self-start">
		                                            <a href="#" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		                                                <i class="bx bx-dots-vertical-rounded fs-18 me-2"></i>
		                                            </a>
		                                            <div class="dropdown-menu">
		                                                <a class="dropdown-item" href="#">Copy</a>
		                                                <a class="dropdown-item" href="#">Save</a>
		                                                <a class="dropdown-item" href="#">Forward</a>
		                                                <a class="dropdown-item" href="#">Delete</a>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>
		                                <img src="~/assets/images/users/avatar-3.jpg" class="rounded-circle avatar-sm" alt="">
		                            </div>
		
		                        </div>
		
		                    </li>
		
		                    <li>
		                        <div class="conversation-list">
		                            <div class="d-flex">
		                                <img src="~/assets/images/users/avatar-6.jpg" class="rounded-circle avatar-sm" alt="">
		                                <div class="flex-1 ms-3">
		                                    <div class="d-flex justify-content-between">
		                                        <h5 class="fs-16 conversation-name align-middle"> Jennie Sherlock </h5>
		                                        <span class="time fw-normal text-muted me-0 me-md-4">Thursday 10:04 AM</span>
		                                    </div>
		                                    <div class="ctext-wrap">
		                                        <div class="ctext-wrap-content bg-light">
		                                            <p class="mb-0">
		                                                Super, I will get you the new brief for our own site over to you this evening, so you have time to read over I'm good thank you!
		                                            </p>
		                                        </div>
		                                        <div class="dropdown align-self-start">
		                                            <a href="#" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		                                                <i class="bx bx-dots-vertical-rounded fs-18 me-2"></i>
		                                            </a>
		                                            <div class="dropdown-menu">
		                                                <a class="dropdown-item" href="#">Copy</a>
		                                                <a class="dropdown-item" href="#">Save</a>
		                                                <a class="dropdown-item" href="#">Forward</a>
		                                                <a class="dropdown-item" href="#">Delete</a>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
		
		                        </div>
		                    </li>
		
		                    <li class="chat-day-title">
		                        <span class="title">Today</span>
		                    </li>
		
		                    <li class="right">
		                        <div class="conversation-list">
		                            <div class="d-flex">
		                                <div class="flex-1 me-3">
		                                    <div class="d-flex justify-content-between">
		                                        <span class="time fw-normal text-muted ms-0 ms-md-4">Today 10:08 AM</span>
		                                        <h5 class="fs-16 conversation-name align-middle"> Jimmie Williams </h5>
		                                    </div>
		                                    <div class="ctext-wrap">
		                                        <div class="ctext-wrap-content">
		                                            <p class="mb-0 text-start">
		                                                Of course I can, just catching up with Steve on it and i'll write it out. Speak tomorrow! Let me know if you have any questions!
		                                            </p>
		
		                                            <p class="mb-0 text-start mt-2">
		                                                img-1.jpg & img-2.jpg images for a New Projects
		                                            </p>
		
		                                            <ul class="list-inline message-img mt-2 mb-0">
		                                                <li class="list-inline-item message-img-list">
		                                                    <a class="d-inline-block" href="">
		                                                        <img src="~/assets/images/small/img-1.jpg" alt="" class="rounded img-thumbnail">
		                                                    </a>
		                                                </li>
		
		                                                <li class="list-inline-item message-img-list">
		                                                    <a class="d-inline-block" href="">
		                                                        <img src="~/assets/images/small/img-2.jpg" alt="" class="rounded img-thumbnail">
		                                                    </a>
		                                                </li>
		                                            </ul>
		                                        </div>
		
		                                        <div class="dropdown align-self-start">
		                                            <a href="#" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		                                                <i class="bx bx-dots-vertical-rounded fs-18 me-2"></i>
		                                            </a>
		                                            <div class="dropdown-menu">
		                                                <a class="dropdown-item" href="#">Copy</a>
		                                                <a class="dropdown-item" href="#">Save</a>
		                                                <a class="dropdown-item" href="#">Forward</a>
		                                                <a class="dropdown-item" href="#">Delete</a>
		                                            </div>
		                                        </div>
		                                    </div>
		
		                                </div>
		                                <img src="~/assets/images/users/avatar-3.jpg" class="rounded-circle avatar-sm" alt="">
		                            </div>
		                        </div>
		                    </li>
		
		                    <li>
		                        <div class="conversation-list">
		                            <div class="d-flex">
		                                <img src="~/assets/images/users/avatar-6.jpg" class="rounded-circle avatar-sm" alt="">
		                                <div class="flex-1 ms-3">
		                                    <div class="d-flex justify-content-between">
		                                        <h5 class="fs-16 conversation-name align-middle"> Jennie Sherlock </h5>
		                                        <span class="time fw-normal text-muted me-0 me-md-4">Today 10:04 AM</span>
		                                    </div>
		                                    <div class="ctext-wrap">
		                                        <div class="ctext-wrap-content bg-light">
		                                            <p class="mb-0">
		                                                Thank You very much, I am waiting Project.
		                                            </p>
		                                        </div>
		                                        <div class="dropdown align-self-start">
		                                            <a href="#" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		                                                <i class="bx bx-dots-vertical-rounded fs-18 me-2"></i>
		                                            </a>
		                                            <div class="dropdown-menu">
		                                                <a class="dropdown-item" href="#">Copy</a>
		                                                <a class="dropdown-item" href="#">Save</a>
		                                                <a class="dropdown-item" href="#">Forward</a>
		                                                <a class="dropdown-item" href="#">Delete</a>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
		                        </div>
		                    </li>
		
		                    <li class="right">
		                        <div class="conversation-list">
		                            <div class="d-flex">
		                                <div class="flex-1 me-3">
		                                    <div class="d-flex justify-content-between">
		                                        <span class="time fw-normal text-muted ms-0 ms-md-4">Today 10:08 AM</span>
		                                        <h5 class="fs-16 conversation-name align-middle"> Jimmie Williams </h5>
		                                    </div>
		                                    <div class="ctext-wrap">
		                                        <div class="ctext-wrap-content">
		                                            <p class="mb-0 text-start">
		                                                When someone thanks us, our automatic response is to say, “You’re welcome.” This is something that we have
		                                                learned from our parents and family and have been doing for a long time.
		                                            </p>
		                                        </div>
		
		                                        <div class="dropdown align-self-start">
		                                            <a href="#" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		                                                <i class="bx bx-dots-vertical-rounded fs-18 me-2"></i>
		                                            </a>
		                                            <div class="dropdown-menu">
		                                                <a class="dropdown-item" href="#">Copy</a>
		                                                <a class="dropdown-item" href="#">Save</a>
		                                                <a class="dropdown-item" href="#">Forward</a>
		                                                <a class="dropdown-item" href="#">Delete</a>
		                                            </div>
		                                        </div>
		                                    </div>
		
		                                </div>
		                                <img src="~/assets/images/users/avatar-3.jpg" class="rounded-circle avatar-sm" alt="">
		                            </div>
		                        </div>
		                    </li>
		                    <li class="right"><!-- 내 채팅인지 아닌지에 따라서 왼쪽인지 오른쪽인지 -->
		                        <div class="conversation-list">
		                            <div class="d-flex">
		                                <div class="flex-1 me-3">
		                                    <div class="d-flex justify-content-between"><!-- 시간과 이름 -->
		                                        <span class="time fw-normal text-muted ms-0 ms-md-4">Thursday 10:02 AM</span>
		                                        <h5 class="fs-16 conversation-name align-middle"> Jimmie Williams </h5>
		                                    </div>
		                                    <div class="ctext-wrap"><!-- 채팅 내용 -->
		                                        <div class="ctext-wrap-content">
		                                            <p class="mb-0 text-start">
		                                                Hi Martin, Glad to hear from you, I'm fine,what about you? and how it's going with the velocity website?
		                                                </br>
		                                                Of course I will help with this project
		                                            </p>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
		                        </div>
		                    </li>
		                </ul>
		            </div>
		
		            <div class="p-3 border-top">
		                <div class="row">
		                    <div class="col">
		                        <div class="position-relative">
		                            <input id="chatInput" type="text" class="form-control border chat-input" onkeypress="if(event.keyCode==13) $('#chat_btn').click();">
		                        </div>
		                    </div>
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