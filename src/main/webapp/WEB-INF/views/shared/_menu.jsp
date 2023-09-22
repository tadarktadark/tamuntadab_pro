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
				<!-- 알람!! -->
				<div class="dropdown topbar-head-dropdown ms-1 header-item" id="notificationDropdown">
					<button type="button" class="btn btn-icon btn-topbar btn-ghost-dark rounded-circle" id="page-header-notifications-dropdown" 
						data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-haspopup="true" aria-expanded="false">
						<i class="bx bx-bell fs-20"></i> 
						<span class="position-absolute topbar-badge fs-10 translate-middle badge rounded-pill bg-danger">
							<span class="notification-badge">4</span>
							<span class="visually-hidden">unread messages</span>
						</span>
					</button>
					<div class="dropdown-menu dropdown-menu-lg dropdown-menu-end p-0" aria-labelledby="page-header-notifications-dropdown">

						<div class="dropdown-head rounded-top">
							<div class="p-3 border-bottom border-bottom-dashed">
								<div class="row align-items-center">
									<div class="col">
										<h6 class="mb-0 fs-16 fw-semibold">
											Notifications 
											<span class="badge bg-danger-subtle text-danger   fs-13 notification-badge"> 4</span>
										</h6>
										<p class="fs-14 text-muted mt-1 mb-0">
											You have 
											<span class="fw-semibold notification-unread">3</span>
											unread messages
										</p>
									</div>
									<div class="col-auto dropdown">
										<a href="javascript:void(0);" data-bs-toggle="dropdown" class="link-secondary fs-14">
											<i class="bi bi-three-dots-vertical"></i>
										</a>
										<ul class="dropdown-menu">
											<li><a class="dropdown-item" href="#">All Clear</a></li>
											<li><a class="dropdown-item" href="#">Mark all as read</a></li>
											<li><a class="dropdown-item" href="#">Archive All</a></li>
										</ul>
									</div>
								</div>
							</div>

						</div>

						<div class="py-2 ps-2" id="notificationItemsTabContent">
							<div data-simplebar="init" style="max-height: 300px;" class="pe-2">
								<div class="simplebar-wrapper" style="margin: 0px -8px 0px 0px;">
									<div class="simplebar-height-auto-observer-wrapper">
										<div class="simplebar-height-auto-observer"></div>
									</div>
									<div class="simplebar-mask">
										<div class="simplebar-offset" style="right: 0px; bottom: 0px;">
											<div class="simplebar-content-wrapper" tabindex="0" role="region" aria-label="scrollable content" style="height: auto; overflow: hidden;">
												<div class="simplebar-content" style="padding: 0px 8px 0px 0px;">
													<h6 class="text-overflow text-muted fs-13 my-2 text-uppercase notification-title" style="display: block;">New</h6>
													<div class="text-reset notification-item d-block dropdown-item position-relative unread-message">
														<div class="d-flex">
															<div class="avatar-xs me-3 flex-shrink-0">
																<span class="avatar-title bg-info-subtle  text-info rounded-circle fs-16">
																	<i class="bx bx-badge-check"></i>
																</span>
															</div>
															<div class="flex-grow-1">
																<a href="#!" class="stretched-link">
																	<h6 class="mt-0 fs-14 mb-2 lh-base">
																		Your <b>Elite</b> author Graphic Optimization 
																		<span class="text-secondary">reward</span> is ready!
																	</h6>
																</a>
																<p class="mb-0 fs-11 fw-medium text-uppercase text-muted">
																	<span><i class="mdi mdi-clock-outline"></i> Just 30 sec ago</span>
																</p>
															</div>
															<div class="px-2 fs-15">
																<div class="form-check notification-check">
																	<input class="form-check-input" type="checkbox" value="" id="all-notification-check01"> 
																	<label class="form-check-label" for="all-notification-check01"></label>
																</div>
															</div>
														</div>
													</div>

													<div class="text-reset notification-item d-block dropdown-item position-relative unread-message">
														<div class="d-flex">
															<div class="position-relative me-3 flex-shrink-0">
																<img src="assets/images/users/avatar-2.jpg" class="rounded-circle avatar-xs" alt="user-pic">
																<span class="active-badge position-absolute start-100 translate-middle p-1 bg-success rounded-circle">
																	<span class="visually-hidden">New alerts</span>
																</span>
															</div>
															<div class="flex-grow-1">
																<a href="#!" class="stretched-link">
																	<h6 class="mt-0 mb-1 fs-14 fw-semibold">Angela Bernier</h6>
																</a>
																<div class="fs-13 text-muted">
																	<p class="mb-1">Answered to your comment on the cash flow forecast's graph 🔔.</p>
																</div>
																<p class="mb-0 fs-11 fw-medium text-uppercase text-muted">
																	<span><i class="mdi mdi-clock-outline"></i> 48 min ago</span>
																</p>
															</div>
															<div class="px-2 fs-15">
																<div class="form-check notification-check">
																	<input class="form-check-input" type="checkbox" value="" id="all-notification-check02"> 
																	<label class="form-check-label" for="all-notification-check02"></label>
																</div>
															</div>
														</div>
													</div>

													<div class="text-reset notification-item d-block dropdown-item position-relative unread-message">
														<div class="d-flex">
															<div class="avatar-xs me-3 flex-shrink-0">
																<span class="avatar-title bg-danger-subtle  text-danger rounded-circle fs-16">
																	<i class="bx bx-message-square-dots"></i>
																</span>
															</div>
															<div class="flex-grow-1">
																<a href="#!" class="stretched-link">
																	<h6 class="mt-0 mb-2 fs-14 lh-base"> 
																		You have received <b class="text-success">20</b> new messages in the conversation
																	</h6>
																</a>
																<p class="mb-0 fs-11 fw-medium text-uppercase text-muted">
																	<span><i class="mdi mdi-clock-outline"></i> 2 hrs ago</span>
																</p>
															</div>
															<div class="px-2 fs-15">
																<div class="form-check notification-check">
																	<input class="form-check-input" type="checkbox" value="" id="all-notification-check03"> 
																	<label class="form-check-label" for="all-notification-check03"></label>
																</div>
															</div>
														</div>
													</div>

													<h6 class="text-overflow text-muted fs-13 my-2 text-uppercase notification-title" style="display: block;">Read Before</h6>

													<div class="text-reset notification-item d-block dropdown-item position-relative">
														<div class="d-flex">

															<div class="position-relative me-3 flex-shrink-0">
																<img src="assets/images/users/avatar-8.jpg" class="rounded-circle avatar-xs" alt="user-pic">
																<span class="active-badge position-absolute start-100 translate-middle p-1 bg-warning rounded-circle">
																	<span class="visually-hidden">New alerts</span>
																</span>
															</div>

															<div class="flex-grow-1">
																<a href="#!" class="stretched-link">
																	<h6 class="mt-0 mb-1 fs-14 fw-semibold">Maureen Gibson</h6>
																</a>
																<div class="fs-13 text-muted">
																	<p class="mb-1">We talked about a project on linkedin.</p>
																</div>
																<p class="mb-0 fs-11 fw-medium text-uppercase text-muted">
																	<span><i class="mdi mdi-clock-outline"></i> 4 hrs ago</span>
																</p>
															</div>
															<div class="px-2 fs-15">
																<div class="form-check notification-check">
																	<input class="form-check-input" type="checkbox" value="" id="all-notification-check04"> 
																	<label class="form-check-label" for="all-notification-check04"></label>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="simplebar-placeholder" style="width: 0px; height: 0px;"></div>
								</div>
								<div class="simplebar-track simplebar-horizontal" style="visibility: hidden;">
									<div class="simplebar-scrollbar" style="width: 0px; display: none;"></div>
								</div>
								<div class="simplebar-track simplebar-vertical" style="visibility: hidden;">
									<div class="simplebar-scrollbar" style="height: 0px; display: none;"></div>
								</div>
							</div>
							<div class="notification-actions" id="notification-actions">
								<div class="d-flex text-muted justify-content-center align-items-center"> 
									Select
									<div id="select-content" class="text-body fw-semibold px-1">0</div>
									Result
									<button type="button" class="btn btn-link link-danger p-0 ms-2" data-bs-toggle="modal" data-bs-target="#removeNotificationModal">Remove</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 알람!! 끝 -->
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
	                        <a class="dropdown-item" href="./chatList.do"><i class="bx bx-message-alt-detail text-muted fs-17 align-middle me-1"></i> <span class="align-middle">Messages</span></a>
	                        <a class="dropdown-item" id="logout" href="./logout.do"><i class="bx bx-log-out text-muted fs-17 align-middle me-1"></i> <span class="align-middle" data-key="t-logout">로그아웃</span></a>
						</div>
					</div>
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
                       	<a href="./classList.do?page=1" class="nav-link menu-link"> <i class="ri-apps-2-line"></i> <span data-key="t-class">클래스</span> </a>
                   	</li>

					<li class="nav-item">
                       	<a href="./instrList.do" class="nav-link menu-link"> <i class="ri-account-circle-line"></i> <span data-key="t-instr">강사</span> </a>
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

					<li class="nav-item">
                        <a class="nav-link menu-link" href="#sidebarCenter" data-bs-toggle="collapse" role="button" aria-expanded="false" aria-controls="sidebarCenter">
                            <i class="ri-pencil-ruler-2-line"></i> <span data-key="t-center">고객센터</span>
                        </a>
                        <div class="collapse menu-dropdown" id="sidebarCenter">
                            <ul class="nav nav-sm flex-column">
                                <li class="nav-item">
                                    <a href="./gongjiList.do" class="nav-link" data-key="t-gongji">공지사항 </a>
                                </li>
                                <li class="nav-item">
                                    <a href="./geoneuiList.do" class="nav-link" data-key="t-geoneui">건의사항 </a>
                                </li>
                                <li class="nav-item">
                                    <a href="./fagList.do" class="nav-link" data-key="t-faq">자주 묻는 질문 </a>
                                </li>
                                <li class="nav-item">
                                    <a href="./tupyoPage.do?accountId=TMTD19&clasId=1000000111" class="nav-link" data-key="t-tupyo">투표 </a>
                                </li>
                                <li class="nav-item">
                                    <a href="./chatPage.do" class="nav-link" data-key="t-chat">채팅 </a>
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
				<!-- 알람!!!!!!!!!!! -->
				<div class="dropdown topbar-head-dropdown ms-1 header-item" id="notificationDropdown">
					<button type="button" class="btn btn-icon btn-topbar btn-ghost-dark rounded-circle" id="page-header-notifications-dropdown" 
						data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-haspopup="true" aria-expanded="false">
						<i class="bx bx-bell fs-20"></i> 
						<span class="position-absolute topbar-badge fs-10 translate-middle badge rounded-pill bg-danger">
							<span class="notification-badge">4</span>
							<span class="visually-hidden">unread messages</span>
						</span>
					</button>
					<div class="dropdown-menu dropdown-menu-lg dropdown-menu-end p-0" aria-labelledby="page-header-notifications-dropdown">

						<div class="dropdown-head rounded-top">
							<div class="p-3 border-bottom border-bottom-dashed">
								<div class="row align-items-center">
									<div class="col">
										<h6 class="mb-0 fs-16 fw-semibold">
											Notifications 
											<span class="badge bg-danger-subtle text-danger   fs-13 notification-badge">4</span>
										</h6>
										<p class="fs-14 text-muted mt-1 mb-0">
											You have <span class="fw-semibold notification-unread">3</span> unread messages
										</p>
									</div>
									<div class="col-auto dropdown">
										<a href="javascript:void(0);" data-bs-toggle="dropdown" class="link-secondary fs-14"><i class="bi bi-three-dots-vertical"></i></a>
										<ul class="dropdown-menu">
											<li><a class="dropdown-item" href="#">All Clear</a></li>
											<li><a class="dropdown-item" href="#">Mark all as read</a></li>
											<li><a class="dropdown-item" href="#">Archive All</a></li>
										</ul>
									</div>
								</div>
							</div>

						</div>

						<div class="py-2 ps-2" id="notificationItemsTabContent">
							<div data-simplebar="init" style="max-height: 300px;" class="pe-2">
								<div class="simplebar-wrapper" style="margin: 0px -8px 0px 0px;">
									<div class="simplebar-height-auto-observer-wrapper">
										<div class="simplebar-height-auto-observer"></div>
									</div>
									<div class="simplebar-mask">
										<div class="simplebar-offset" style="right: 0px; bottom: 0px;">
											<div class="simplebar-content-wrapper" tabindex="0" role="region" aria-label="scrollable content" style="height: auto; overflow: hidden;">
												<div class="simplebar-content" style="padding: 0px 8px 0px 0px;">
													<h6 class="text-overflow text-muted fs-13 my-2 text-uppercase notification-title" style="display: block;">New</h6>
													<div class="text-reset notification-item d-block dropdown-item position-relative unread-message">
														<div class="d-flex">
															<div class="avatar-xs me-3 flex-shrink-0">
																<span class="avatar-title bg-info-subtle  text-info rounded-circle fs-16">
																	<i class="bx bx-badge-check"></i>
																</span>
															</div>
															<div class="flex-grow-1">
																<a href="#!" class="stretched-link">
																	<h6 class="mt-0 fs-14 mb-2 lh-base">
																		Your <b>Elite</b> author Graphic Optimization <span class="text-secondary">reward</span> is ready!
																	</h6>
																</a>
																<p class="mb-0 fs-11 fw-medium text-uppercase text-muted">
																	<span><i class="mdi mdi-clock-outline"></i> Just 30 sec ago</span>
																</p>
															</div>
															<div class="px-2 fs-15">
																<div class="form-check notification-check">
																	<input class="form-check-input" type="checkbox" value="" id="all-notification-check01"> 
																	<label class="form-check-label" for="all-notification-check01"></label>
																</div>
															</div>
														</div>
													</div>

													<div class="text-reset notification-item d-block dropdown-item position-relative unread-message">
														<div class="d-flex">
															<div class="position-relative me-3 flex-shrink-0">
																<img src="assets/images/users/avatar-2.jpg" class="rounded-circle avatar-xs" alt="user-pic">
																<span class="active-badge position-absolute start-100 translate-middle p-1 bg-success rounded-circle">
																	<span class="visually-hidden">New alerts</span>
																</span>
															</div>
															<div class="flex-grow-1">
																<a href="#!" class="stretched-link">
																	<h6 class="mt-0 mb-1 fs-14 fw-semibold">Angela Bernier</h6>
																</a>
																<div class="fs-13 text-muted">
																	<p class="mb-1">Answered to your comment on the cash flow forecast's graph 🔔.</p>
																</div>
																<p class="mb-0 fs-11 fw-medium text-uppercase text-muted">
																	<span><i class="mdi mdi-clock-outline"></i> 48 min ago</span>
																</p>
															</div>
															<div class="px-2 fs-15">
																<div class="form-check notification-check">
																	<input class="form-check-input" type="checkbox" value="" id="all-notification-check02"> 
																	<label class="form-check-label" for="all-notification-check02"></label>
																</div>
															</div>
														</div>
													</div>

													<div
														class="text-reset notification-item d-block dropdown-item position-relative unread-message">
														<div class="d-flex">
															<div class="avatar-xs me-3 flex-shrink-0">
																<span class="avatar-title bg-danger-subtle  text-danger rounded-circle fs-16">
																	<i class="bx bx-message-square-dots"></i>
																</span>
															</div>
															<div class="flex-grow-1">
																<a href="#!" class="stretched-link">
																	<h6 class="mt-0 mb-2 fs-14 lh-base">
																		You have received <b class="text-success">20</b> new messages in the conversation
																	</h6>
																</a>
																<p class="mb-0 fs-11 fw-medium text-uppercase text-muted">
																	<span><i class="mdi mdi-clock-outline"></i> 2 hrs ago</span>
																</p>
															</div>
															<div class="px-2 fs-15">
																<div class="form-check notification-check">
																	<input class="form-check-input" type="checkbox" value="" id="all-notification-check03"> 
																	<label class="form-check-label" for="all-notification-check03"></label>
																</div>
															</div>
														</div>
													</div>

													<h6 class="text-overflow text-muted fs-13 my-2 text-uppercase notification-title" style="display: block;">Read Before</h6>

													<div class="text-reset notification-item d-block dropdown-item position-relative">
														<div class="d-flex">

															<div class="position-relative me-3 flex-shrink-0">
																<img src="assets/images/users/avatar-8.jpg" ㄴclass="rounded-circle avatar-xs" alt="user-pic">
																<span class="active-badge position-absolute start-100 translate-middle p-1 bg-warning rounded-circle">
																	<span class="visually-hidden">New alerts</span>
																</span>
															</div>

															<div class="flex-grow-1">
																<a href="#!" class="stretched-link">
																	<h6 class="mt-0 mb-1 fs-14 fw-semibold">Maureen Gibson</h6>
																</a>
																<div class="fs-13 text-muted">
																	<p class="mb-1">We talked about a project on linkedin.</p>
																</div>
																<p class="mb-0 fs-11 fw-medium text-uppercase text-muted">
																	<span><i class="mdi mdi-clock-outline"></i> 4 hrs ago</span>
																</p>
															</div>
															<div class="px-2 fs-15">
																<div class="form-check notification-check">
																	<input class="form-check-input" type="checkbox" value="" id="all-notification-check04"> 
																	<label class="form-check-label" for="all-notification-check04"></label>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="simplebar-placeholder" style="width: 0px; height: 0px;"></div>
								</div>
								<div class="simplebar-track simplebar-horizontal" style="visibility: hidden;">
									<div class="simplebar-scrollbar" style="width: 0px; display: none;"></div>
								</div>
								<div class="simplebar-track simplebar-vertical" style="visibility: hidden;">
									<div class="simplebar-scrollbar" style="height: 0px; display: none;"></div>
								</div>
							</div>
							<div class="notification-actions" id="notification-actions">
								<div class="d-flex text-muted justify-content-center align-items-center">
									Select
									<div id="select-content" class="text-body fw-semibold px-1">0</div>
									Result
									<button type="button" class="btn btn-link link-danger p-0 ms-2" data-bs-toggle="modal" ㄴdata-bs-target="#removeNotificationModal">Remove</button>
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
	                        <a class="dropdown-item" href="./chatList.do"><i class="bx bx-message-alt-detail text-muted fs-17 align-middle me-1"></i> <span class="align-middle">Messages</span></a>
	                        <a class="dropdown-item" id="logout" href="./logout.do"><i class="bx bx-log-out text-muted fs-17 align-middle me-1"></i> <span class="align-middle" data-key="t-logout">로그아웃</span></a>
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
						<h4 class="mb-1">Are you sure ?</h4>
						<p class="text-muted mx-4 mb-0">Are you sure you want to
							remove this Notification ?</p>
					</div>
				</div>
				<div class="d-flex gap-2 justify-content-center mt-4 mb-2">
					<button type="button" class="btn w-sm btn-light"
						data-bs-dismiss="modal">Close</button>
					<button type="button" class="btn w-sm btn-danger"
						id="delete-notification">Yes, Delete It!</button>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<script src="./assets/js/app.js"></script>