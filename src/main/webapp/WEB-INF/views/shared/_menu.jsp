<%@page import="com.tdtd.tmtd.vo.UserProfileVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
UserProfileVo userInfo = (UserProfileVo)request.getSession().getAttribute("userInfo");
%>
<header id="page-topbar">
	<div class="layout-width">
		<div class="navbar-header">
			<div class="d-flex">
				<!-- LOGO -->
				<div class="navbar-brand-box horizontal-logo">
					<a href="./" class="logo logo-dark"> 
						<span class="logo-sm"> 
							<img src="./image/logo-img.png" alt="" height="22">
						</span> 
						<span class="logo-lg"> 
							<img src="./image/logo.png" alt="" height="45">
						</span>
					</a> 
					<a href="./" class="logo logo-light"> 
						<span class="logo-sm"> 
							<img src="./image/logo-img.png" alt="" height="22">
						</span> 
						<span class="logo-lg"> 
							<img src="./image/logo.png" alt="" height="45">
						</span>
					</a>
				</div>

				<button type="button" class="btn btn-sm px-3 fs-16 header-item vertical-menu-btn topnav-hamburger" id="topnav-hamburger-icon">
					<span class="hamburger-icon open"> 
						<span></span> 
						<span></span>
						<span></span>
					</span>
				</button>
			</div>

			<div class="d-flex align-items-center">
				<!-- 프로필!!!! -->
				<%
					if(userInfo ==null){
				%>
					<div class="dropdown header-item">
	                    <button type="button" class="btn" id="page-header-user-dropdown" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                        <span class="d-flex align-items-center">
	                            <img class="rounded-circle header-profile-user" src="./assets/images/users/user-dummy-img.jpg" alt="Header Avatar">
	                        </span>
                 	   </button>
	                  	  <div class="dropdown-menu dropdown-menu-end">
                     			  <a class="dropdown-item" href="./loginForm.do"><i class="bx bx-log-in text-muted fs-17 align-middle me-1"></i> <span class="align-middle" data-key="t-logout">로그인</span></a>
                     			  <a class="dropdown-item" href="./regist.do"><i class="mdi mdi-account-plus text-muted fs-17 align-middle me-1"></i> <span class="align-middle" data-key="t-logout">회원가입</span></a>
                                 </div>
        					</div>
        					<script type="text/javascript" src="./js/autoLogin.js"></script>
				<%
					}else{
				%>
					<div class="dropdown header-item">
						<button type="button" class="btn" id="page-header-user-dropdown" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                        <span class="d-flex align-items-center">
	                            <img class="rounded-circle header-profile-user" src="<%=userInfo.getUserProfileFile() %>" alt="Header Avatar">
	                        </span>
						</button>
						<div class="dropdown-menu dropdown-menu-end">
	                        <h6 class="dropdown-header"><%=userInfo.getUserNickname()%>님 어서오세요</h6>
	                        <a class="dropdown-item" href="./mypage.do"><i class="ri-profile-line text-muted fs-17 align-middle me-1"></i> <span class="align-middle">MyPage</span></a>
	                        <a class="dropdown-item" id="logout" href="./logout.do" onclick="localStorage.removeItem('autoLoginToken');"><i class="bx bx-log-out text-muted fs-17 align-middle me-1"></i> <span class="align-middle" data-key="t-logout">로그아웃</span></a>
						</div>
					</div>
					<script src="./js/jeongjiCheck.js"></script>
				<%
					}
                   %>
				<!-- 프로필!!!! 끝!!! -->
				
				
			</div>

		</div>
	</div>
</header>
<div class="app-menu navbar-menu">
	<div class="container-fluid">

		<!-- LOGO -->
		<div class="navbar-brand-box">
			<a href="./" class="logo logo-dark"> 
				<span class="logo-sm"> 
					<img src="./image/logo-img.png" alt="" height="22">
				</span> 
				<span class="logo-lg"> 
					<img src="./image/logo.png" alt="" height="45">
				</span>
			</a> 
			<a href="./" class="logo logo-light"> 
				<span class="logo-sm"> 
					<img src="./image/logo-img.png" alt="" height="22">
				</span> 
				<span class="logo-lg"> 
					<img src="./image/logo.png" alt="" height="45">
				</span>
			</a>
			<button type="button" class="btn btn-sm p-0 fs-20 header-item float-end btn-vertical-sm-hover" id="vertical-hover">
				<i class="ri-record-circle-line"></i>
			</button>
		</div>

		<div id="scrollbar">
			<div class="container-fluid">
				<div id="two-column-menu"></div>

				<ul class="navbar-nav" id="navbar-nav">
					<li class="menu-title">
						<span data-key="t-menu">Menu</span>
					</li>
					<li class="nav-item">
                       	<a href="./classList.do" class="nav-link menu-link"> <i class="ri-apps-2-line"></i> <span data-key="t-class">클래스</span> </a>
                   	</li>

					<li class="nav-item">
                       	<a href="./instrList.do" class="nav-link menu-link"> <i class="ri-account-circle-line"></i> <span data-key="t-instr">강사</span> </a>
               	 	</li>
					<li class="nav-item">
						<a href="./chatPage.do" class="nav-link menu-link"> <i class="bx bx-chat"></i> <span data-key="t-chat">채팅</span> </a>
					</li>
					<li class="nav-item">
                       	<a href="./yeyak.do" class="nav-link menu-link"> <i class="ri-map-pin-line"></i> <span data-key="t-yeyak">예약</span> </a>
                   	</li>

					<li class="nav-item">
                        <a class="nav-link menu-link" href="#sidebarComm" data-bs-toggle="collapse" role="button" aria-expanded="false" aria-controls="sidebarComm">
                            <i class="ri-pages-line"></i> <span data-key="t-comm">커뮤니티</span>
                        </a>
                        <div class="collapse menu-dropdown" id="sidebarComm">
                            <ul class="nav nav-sm flex-column">
                                <li class="nav-item">
                                    <a href="./community.do?board=pilgi" class="nav-link" data-key="t-pilgi">필기 </a>
                                </li>
                                <li class="nav-item">
                                    <a href="./community.do?board=jilmun" class="nav-link" data-key="t-jilmun">질문 </a>
                                </li>
                                <li class="nav-item">
                                    <a href="./community.do?board=jayu" class="nav-link" data-key="t-jayu">자유 </a>
                                </li>
                            </ul>
                        </div>
                    </li>
				</ul>

				<div class="help-box text-center">
					<img src="assets/images/upgrade.png" class="img-fluid" alt="">
					<p class="mb-3 mt-2 text-muted">Upgrade To Pro For More Features</p>
					<div class="mt-3">
						<a href="" class="btn btn-primary"> Upgrade Plan</a>
					</div>
				</div>
			</div>
			<!-- Sidebar -->
		</div>

		<div class="sidebar-profile-menu text-center d-flex">
			<div class="d-flex align-items-center">
			<input id="userId" type="hidden" value="${userInfo.userAccountId}">
				<!-- 알람!!!!!!!!!!! -->
			<div class="dropdown topbar-head-dropdown ms-1 header-item" id="notificationDropdown">
			    <button type="button" class="btn btn-icon btn-topbar btn-ghost-dark rounded-circle" id="page-header-notifications-dropdown" data-bs-toggle="dropdown"  data-bs-auto-close="outside" aria-haspopup="true" aria-expanded="false">
			        <i class='bx bx-bell fs-20'></i>
			        <span class="position-absolute topbar-badge fs-10 translate-middle badge rounded-pill bg-danger"><span class="notification-unread">0</span><span class="visually-hidden">unread messages</span></span>
			    </button>
			    <div class="dropdown-menu dropdown-menu-lg dropdown-menu-end p-0" aria-labelledby="page-header-notifications-dropdown">
			
			        <div class="dropdown-head rounded-top">
			            <div class="p-3 border-bottom border-bottom-dashed">
			                <div class="row align-items-center">
			                    <div class="col">
			                        <h6 class="mb-0 fs-16 fw-semibold"> 전체 알림 <span class="badge bg-danger-subtle text-danger   fs-13 notification-badge"> 4</span></h6>
			                        <p class="fs-14 text-muted mt-1 mb-0"><span class="fw-semibold notification-unread"></span>개의 안 읽은 알림이 있습니다</p>
			                    </div>
			                </div>
			            </div>
			
			        </div>
			
			        <div class="py-2 ps-2" id="notificationItemsTabContent">
			            <div data-simplebar id="notificationList" style="max-height: 300px;" class="pe-2">
			            </div>
			            <div class="notification-actions" id="notification-actions">
			                <div class="d-flex text-muted justify-content-center align-items-center">
			                    <div id="select-content" class="text-body fw-semibold px-1">0</div>개 선택됨 <button type="button" class="btn btn-link link-danger p-0 ms-2" data-bs-toggle="modal" data-bs-target="#removeNotificationModal">삭제</button>
			                </div>
			            </div>
			        </div>
			    </div>
			</div>
			<!-- 알람!!!!!!!!!!! 끝-->
				<!-- 프로필!!!!!!!!!!! -->
				<%
					if(userInfo ==null){
				%>
					<div class="dropdown header-item">
	                    <button type="button" class="btn" id="page-header-user-dropdown" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                        <span class="d-flex align-items-center">
	                            <img class="rounded-circle header-profile-user" src="./assets/images/users/user-dummy-img.jpg" alt="Header Avatar">
	                        </span>
                 	   </button>
	                  	  <div class="dropdown-menu dropdown-menu-end">
                     			  <a class="dropdown-item" href="./loginForm.do"><i class="bx bx-log-in text-muted fs-17 align-middle me-1"></i> <span class="align-middle" data-key="t-logout">로그인</span></a>
                     			  <a class="dropdown-item" href="./regist.do"><i class="mdi mdi-account-plus text-muted fs-17 align-middle me-1"></i> <span class="align-middle" data-key="t-logout">회원가입</span></a>
                                 </div>
        					</div>
        					<script type="text/javascript" src="./js/autoLogin.js"></script>
				<%
					}else{
				%>
					<script src="./js/jeongjiCheck.js"></script>
					<div class="dropdown header-item">
						<button type="button" class="btn" id="page-header-user-dropdown" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                        <span class="d-flex align-items-center">
	                            <img class="rounded-circle header-profile-user" src="<%=userInfo.getUserProfileFile() %>" alt="Header Avatar">
	                        </span>
						</button>
						<div class="dropdown-menu dropdown-menu-end">
	                        <h6 class="dropdown-header"><%=userInfo.getUserNickname()%>님 어서오세요</h6>
	                        <a class="dropdown-item" href="./mypage.do"><i class="ri-profile-line text-muted fs-17 align-middle me-1"></i> <span class="align-middle">MyPage</span></a>
	                        <a class="dropdown-item" id="logout" href="./logout.do" onclick="localStorage.removeItem('autoLoginToken');"><i class="bx bx-log-out text-muted fs-17 align-middle me-1"></i> <span class="align-middle" data-key="t-logout">로그아웃</span></a>
						</div>
					</div>
				<%
					}
                   %>
				<!-- 프로필 끝!!! -->
			</div>
			
		</div>

	</div>

	<div class="sidebar-background"></div>
</div>
<div id="removeNotificationModal" class="modal fade zoomIn"
	tabindex="-1" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close" id="NotificationModalbtn-close"></button>
			</div>
			<div class="modal-body p-md-5">
				<div class="text-center">
					<div class="text-danger">
						<i class="bi bi-trash display-4"></i>
					</div>
					<div class="mt-4 fs-15">
						<h4 class="mb-1">정말로 삭제하시겠습니까 ?</h4>
						<p class="text-muted mx-4 mb-0">삭제된 알림은 복구할 수 없습니다</p>
					</div>
				</div>
				<div class="d-flex gap-2 justify-content-center mt-4 mb-2">
					<button type="button" class="btn w-sm btn-light"
						data-bs-dismiss="modal">닫기</button>
					<button type="button" class="btn w-sm btn-danger"
						id="delete-notification">삭제</button>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<script src="./assets/js/app.js" defer="defer"></script>
<script type="text/javascript" src="./js/notification.js"></script>