<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title}|타문타답</title>
<%@ include file="./shared/_head_css.jsp"%>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp"%>
		<%@ include file="./shared/_sidebar.jsp"%>

	<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<%@ include file="./shared/_page_title.jsp" %>
					
					
					<div class="row pb-4 gy-3">
						<div class="col-sm-4">
							<!-- 임시 화면 -->
							<button class="btn btn-primary addMembers-modal"
								data-bs-toggle="modal" data-bs-target="#addmemberModal" onclick="location.href='./subjectManage.do?page=1'">
								<i class="bx bx-plus fs-16 align-middle me-1"></i> 과목 관리 화면
							</button>
						</div>
					</div>
					
					<div class="row">
                        <div class="col-12">
    
                            <!-- Left sidebar -->
                            <div class="email-leftbar">
                                <div class="card">
                                    <div class="card-body">
                                        <button type="button" class="btn btn-danger waves-effect waves-light w-100" onclick="location.href='./classWrite.do'" data-bs-toggle="modal" data-bs-target="#composemodal" >
                                            새 클래스 개설
                                        </button>
    
                                        <div class="card p-0 overflow-hidden mt-4 shadow-none">
                                            <div class="mail-list">
                                                <a href="#" class="active bg-primary-subtle ">
                                                    <div class="d-flex align-items-center">
                                                        <i class="bx bx-mail-send fs-20 align-middle me-3"></i>
                                                        <div class="flex-grow-1">
                                                            <h5 class="fs-15 mb-0">전체 강의</h5>
                                                            <span class="text-muted fs-13 mt-1 text-truncate">How To Boost Website</span>
                                                        </div>
                                                        <div class="flex-shrink-0">
                                                            <div class="float-end">
                                                                <span class="bg-primary badge">18</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                                <a href="#" class="border-bottom">
                                                    <div class="d-flex align-items-center">
                                                        <i class="bx bx-star fs-20 align-middle me-3"></i>
                                                        <div class="flex-grow-1">
                                                            <h5 class="fs-15 mb-0">개발 · 프로그래밍</h5>
                                                            <span class="text-muted fs-13 mt-1 text-truncate">Selected messages</span>
                                                        </div>
                                                        <div class="flex-shrink-0">
                                                        </div>
                                                    </div>
                                                </a>
        
                                                <a href="#" class="border-bottom">
                                                    <div class="d-flex align-items-center">
                                                        <i class="bx bx-diamond fs-20 align-middle me-3"></i>
                                                        <div class="flex-grow-1">
                                                            <h5 class="fs-15 mb-0">보안 · 네트워크</h5>
                                                            <span class="text-muted fs-13 mt-1 text-truncate">Selected messages</span>
                                                        </div>
                                                        <div class="flex-shrink-0">
                                                        </div>
                                                    </div>
                                                </a>
        
                                                <a href="#" class="border-bottom">
                                                    <div class="d-flex align-items-center">
                                                        <i class="bx bx-file fs-20 align-middle me-3"></i>
                                                        <div class="flex-grow-1">
                                                            <h5 class="fs-15 mb-0">데이터 사이언스</h5>
                                                            <span class="text-muted fs-13 mt-1 text-truncate">Re-edit your messages</span>
                                                        </div>
                                                        <div class="flex-shrink-0">
                                                        </div>
                                                    </div>
                                                </a>
        
                                                <a href="#" class="border-bottom">
                                                    <div class="d-flex align-items-center">
                                                        <i class="bx bx-envelope-open fs-20 align-middle me-3"></i>
                                                        <div class="flex-grow-1">
                                                            <h5 class="fs-15 mb-0">게임 개발</h5>
                                                            <span class="text-muted fs-13 mt-1 text-truncate">Successfully messages</span>
                                                        </div>
                                                        <div class="flex-shrink-0">
                                                            <div class="float-end">
                                                                <span class="bg-primary badge">08</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                                <a href="#">
                                                    <div class="d-flex align-items-center">
                                                        <i class="bx bx-trash fs-20 align-middle me-3"></i>
                                                        <div class="flex-grow-1">
                                                            <h5 class="fs-15 mb-0">하드웨어</h5>
                                                            <span class="text-muted fs-13 mt-1 text-truncate">Removed messages</span>
                                                        </div>
                                                        <div class="flex-shrink-0">
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                        </div>
    
                                        <h5 class="mt-3 fs-15 text-uppercase">라벨</h5>
    
                                        <div class="card p-0 overflow-hidden mt-3 shadow-none">
                                            <div class="mail-list">
                                                <a href="#" class="border-bottom"><span class="mdi mdi-arrow-right-drop-circle text-primary float-end"></span>참여 중인 클래스</a>
                                                <a href="#" class="border-bottom"><span class="mdi mdi-arrow-right-drop-circle text-warning float-end"></span>좋아요 한 클래스</a>
                                            </div>
                                        </div>
    
                                        <h5 class="mt-3 fs-15 text-uppercase">Chat</h5>
    
                                        <div class="card p-0 overflow-hidden mt-3 mb-1 shadow-none">
                                            <div class="mail-list">
                                                <a href="javascript: void(0);" class="d-flex align-items-start border-bottom">
                                                    <img class="flex-shrink-0 me-3 rounded-circle" src="assets/images/users/avatar-2.jpg" alt="Generic placeholder image" height="36">
                                                    <div class="flex-grow-1 chat-user-box">
                                                        <p class="user-title m-0">Scott Median</p>
                                                        <p class="text-muted mb-0 fs-13">Hello</p>
                                                    </div>
                                                </a>
                
                                                <a href="javascript: void(0);" class="d-flex align-items-start border-bottom">
                                                    <img class="flex-shrink-0 me-3 rounded-circle" src="assets/images/users/avatar-3.jpg" alt="Generic placeholder image" height="36">
                                                    <div class="flex-grow-1 chat-user-box">
                                                        <p class="user-title m-0">Julian Rosa</p>
                                                        <p class="text-muted mb-0 fs-13">What about our next..</p>
                                                    </div>
                                                </a>
                
                                                <a href="javascript: void(0);" class="d-flex align-items-start border-bottom">
                                                    <img class="flex-shrink-0 me-3 rounded-circle" src="assets/images/users/avatar-4.jpg" alt="Generic placeholder image" height="36">
                                                    <div class="flex-grow-1 chat-user-box">
                                                        <p class="user-title m-0">David Medina</p>
                                                        <p class="text-muted mb-0 fs-13">Yeah everything is fine</p>
                                                    </div>
                                                </a>
                
                                                <a href="javascript: void(0);" class="d-flex align-items-start border-bottom">
                                                    <img class="flex-shrink-0 me-3 rounded-circle" src="assets/images/users/avatar-6.jpg" alt="Generic placeholder image" height="36">
                                                    <div class="flex-grow-1 chat-user-box">
                                                        <p class="user-title m-0">Jay Baker</p>
                                                        <p class="text-muted mb-0 fs-13">Wow that's great</p>
                                                    </div>
                                                </a>
                                            </div>
                                        </div>
    
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
                                            <div class="col-xl-3 col-md-12">
                                                <div class="pb-3 pb-xl-0">
                                                    <form class="email-search">
                                                        <div class="position-relative">
                                                            <input type="text" class="form-control bg-light" placeholder="Search...">
                                                            <span class="bx bx-search fs-18"></span>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                            <div class="col-xl-9 col-md-12">
                                                <div class="pb-3 pb-xl-0">
                                                    <div class="btn-toolbar float-end" role="toolbar">
                                                        <div class="btn-group me-2 mb-2">
                                                            <button type="button" class="btn btn-primary waves-light waves-effect"><i class="bx bxs-inbox align-middle"></i></button>
                                                            <button type="button" class="btn btn-primary waves-light waves-effect"><i class="bx bx-info-circle align-middle"></i></button>
                                                            <button type="button" class="btn btn-primary waves-light waves-effect"><i class="bx bx-trash align-middle"></i></button>
                                                        </div>
                                                        <div class="btn-group me-2 mb-2">
                                                            <button type="button" class="btn btn-primary waves-light waves-effect" data-bs-toggle="dropdown" aria-expanded="false">
                                                                <i class="bx bx-folder align-middle"></i> <i class="mdi mdi-chevron-down ms-1 align-middle"></i>
                                                            </button>
                                                            <div class="dropdown-menu">
                                                                <a class="dropdown-item" href="#">Updates</a>
                                                                <a class="dropdown-item" href="#">Social</a>
                                                                <a class="dropdown-item" href="#">Team Manage</a>
                                                            </div>
                                                        </div>
                                                        <div class="btn-group me-2 mb-2">
                                                            <button type="button" class="btn btn-primary waves-light waves-effect" data-bs-toggle="dropdown" aria-expanded="false">
                                                                <i class="bx bx-purchase-tag-alt align-middle"></i> <i class="mdi mdi-chevron-down ms-1 align-middle"></i>
                                                            </button>
                                                            <div class="dropdown-menu">
                                                                <a class="dropdown-item" href="#">Updates</a>
                                                                <a class="dropdown-item" href="#">Social</a>
                                                                <a class="dropdown-item" href="#">Team Manage</a>
                                                            </div>
                                                        </div>
                        
                                                        <div class="btn-group me-2 mb-2">
                                                            <button type="button" class="btn btn-primary waves-light waves-effect" data-bs-toggle="dropdown" aria-expanded="false">
                                                                More <i class="bx bx-dots-vertical ms-2 align-middle"></i>
                                                            </button>
                                                            <div class="dropdown-menu">
                                                                <a class="dropdown-item" href="#">Mark as Unread</a>
                                                                <a class="dropdown-item" href="#">Mark as Important</a>
                                                                <a class="dropdown-item" href="#">Add to Tasks</a>
                                                                <a class="dropdown-item" href="#">Add Star</a>
                                                                <a class="dropdown-item" href="#">Mute</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            </div>
    
                                    <div>
                                        <h6 class="text-muted text-uppercase mb-3">참여 중인 클래스</h6>
                                        <div class="mb-2">
                                            <div class="message-list mb-0 p-1">
                                                <div class="list">
                                                    <div class="col-mail col-mail-1">
                                                        <div class="d-flex title align-items-center">
                                                            <img src="assets/images/users/avatar-2.jpg" class="avatar-sm rounded-circle" alt="">
                                                            <div class="flex-1 ms-2 ps-1 mt-1">
                                                                <h5 class="fs-15 mb-0"><a href="" class="text-body">Me, Susanna</a></h5>
                                                                <a href="" class="text-muted text-uppercase fs-13 mt-1">07 Threads</a>
                                                            </div>
                                                        </div>
                                                        <span class="star-toggle bx bx-star"></span>
                                                    </div>
                                                    <div class="col-mail col-mail-2">
                                                        <a href="#" class="subject text-body"><span class="bg-warning badge me-2">Freelance</span> Wolombo has been arranged, – <span class="teaser text-muted fw-normal">Alright thanks. I'll have to re-book that somehow, i'll get back to you.</span>
                                                        </a>
                                                        <div class="date"><i class="bx bx-link-alt me-2 fs-15 align-middle"></i> 8:23 AM</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
    
    
                                   <div class="pt-2">
                                       <h6 class="text-muted text-uppercase mb-3">좋아요 한 클래스</h6>
                                       <div class="mb-2">
                                           <div class="message-list mb-0 p-1">
                                               <div class="list">
                                                   <div class="col-mail col-mail-1">
                                                       <div class="d-flex title align-items-center">
                                                           <img src="assets/images/users/avatar-1.jpg" class="avatar-sm rounded-circle" alt="">
                                                           <div class="flex-1 ms-2 ps-1 mt-1">
                                                               <h5 class="fs-15 mb-0"><a href="" class="text-body">Whitney Peter</a></h5>
                                                               <a href="" class="text-muted text-uppercase fs-13 mt-1">23 Threads</a>
                                                           </div>
                                                       </div>
                                                       <span class="star-toggle bx bx-star"></span>
                                                   </div>
                                                   <div class="col-mail col-mail-2">
                                                       <a href="classDetail.do" class="subject text-body"> 클래스 제목<span class="bg-info badge me-2">Support</span> Off on Thursday - <span class="teaser text-muted fw-normal">Eff that place, you might as well stay here with us instead! Sent from my iPhone 4  4 mar 2014 at 5:55 pm</span>
                                                       </a>
                                                       <div class="date"><i class="bx bx-link-alt me-2 fs-15 align-middle"></i> 3:26 AM</div>
                                                   </div>
                                               </div>
                                           </div>
                                       </div>
                                        </div>
    
                                        <div class="pt-2">
                                            <h6 class="text-muted text-uppercase mb-3">클래스 목록</h6>
                                            <div class="mb-2">
                                                <div class="message-list mb-0 p-1">
                                                    <div class="list">
                                                        <div class="col-mail col-mail-1">
                                                            <div class="checkbox-wrapper-mail">
                                                                <input type="checkbox" id="chk6">
                                                                <label for="chk6" class="toggle"></label>
                                                            </div>
                                                            <div class="d-flex title align-items-center">
                                                                <img src="assets/images/users/avatar-1.jpg" class="avatar-sm rounded-circle" alt="">
                                                                <div class="flex-1 ms-2 ps-1 mt-1">
                                                                    <h5 class="fs-15 mb-0"><a href="" class="text-body">Andrew Zimmer</a></h5>
                                                                    <a href="" class="text-muted text-uppercase fs-13 mt-1">02 Threads</a>
                                                                </div>
                                                            </div>
                                                            <span class="star-toggle bx bx-star"></span>
                                                        </div>
                                                        <div class="col-mail col-mail-2">
                                                            <a href="classDetail.do" class="subject text-body"> 클래스 제목Mochila Beta: Subscription Confirmed – <span class="teaser text-muted fw-normal">You've been confirmed! Welcome to the ruling class of the inbox. For your records, here is a copy of the information you submitted to us...</span>
                                                            </a>
                                                            <div class="date"><i class="bx bx-link-alt me-2 fs-15 align-middle"></i> 4:24 PM</div>
                                                        </div>
                                                    </div>
                                                </div>
                                        </div>
    
                                        <div class="mb-2">
                                            <div class="message-list mb-0 p-1">
                                                <div class="list">
                                                    <div class="col-mail col-mail-1">
                                                        <div class="checkbox-wrapper-mail">
                                                            <input type="checkbox" id="chk7">
                                                            <label for="chk7" class="toggle"></label>
                                                        </div>
                                                        <div class="d-flex title align-items-center">
                                                            <img src="assets/images/users/avatar-2.jpg" class="avatar-sm rounded-circle" alt="">
                                                            <div class="flex-1 ms-2 ps-1 mt-1">
                                                                <h5 class="fs-15 mb-0"><a href="" class="text-body">Randy, me (5)</a></h5>
                                                                <a href="" class="text-muted text-uppercase fs-13 mt-1">15 Threads</a>
                                                            </div>
                                                        </div>
                                                        <span class="star-toggle bx bx-star"></span>
                                                    </div>
                                                    <div class="col-mail col-mail-2">
                                                        <a href="#" class="subject text-body"><span class="bg-success badge me-2">Family</span> Weekend on Revibe – <span class="teaser text-muted fw-normal">Today's Friday and we thought maybe you want some music inspiration for the weekend. Here are some trending tracks and playlists we think you should give a listen!</span>
                                                        </a>
                                                        <div class="date"><i class="bx bx-link-alt me-2 fs-15 align-middle"></i> 4:24 PM</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
    
                                        <div class="unread mb-2">
                                            <div class="message-list mb-0 p-1">
                                                <div class="list">
                                                    <div class="col-mail col-mail-1">
                                                        <div class="checkbox-wrapper-mail">
                                                            <input type="checkbox" id="chk8">
                                                            <label for="chk8" class="toggle"></label>
                                                        </div>
                                                        <div class="d-flex title align-items-center">
                                                            <img src="assets/images/users/avatar-7.jpg" class="avatar-sm rounded-circle" alt="">
                                                            <div class="flex-1 ms-2 ps-1 mt-1">
                                                                <h5 class="fs-15 mb-0"><a href="" class="text-body">KanbanFlow</a></h5>
                                                                <a href="" class="text-muted text-uppercase fs-13 mt-1">06 Threads</a>
                                                            </div>
                                                        </div>
                                                        <span class="star-toggle bx bx-star"></span>
                                                    </div>
                                                    <div class="col-mail col-mail-2">
                                                        <a href="classDetail.do" class="subject text-body"> 클래스 제목Task assigned: Clone ARP's website
                                                        –  <span class="teaser text-muted fw-normal">You have been assigned a task by Alex@Work on the board Web.</span>
                                                        </a>
                                                        <div class="date"><i class="bx bx-link-alt me-2 fs-15 align-middle"></i> 7:36 AM</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
    
                                        <div class="mb-2">
                                            <div class="message-list mb-0 p-1">
                                                <div class="list">
                                                    <div class="col-mail col-mail-1">
                                                        <div class="checkbox-wrapper-mail">
                                                            <input type="checkbox" id="chk9">
                                                            <label for="chk9" class="toggle"></label>
                                                        </div>
                                                        <div class="d-flex title align-items-center">
                                                            <img src="assets/images/users/avatar-3.jpg" class="avatar-sm rounded-circle" alt="">
                                                            <div class="flex-1 ms-2 ps-1 mt-1">
                                                                <h5 class="fs-15 mb-0"><a href="" class="text-body">Revibe</a></h5>
                                                                <a href="" class="text-muted text-uppercase fs-13 mt-1">25 Threads</a>
                                                            </div>
                                                        </div>
                                                        <span class="star-toggle bx bx-star"></span>
                                                    </div>
                                                    <div class="col-mail col-mail-2">
                                                        <a href="classDetail.do" class="subject text-body"> 클래스 제목Last pic over my village – <span class="teaser text-muted fw-normal">Yeah i'd like that! Do you remember the video you showed me of your train ride between Colombo and Kandy? The one with the mountain view? I would love to see that one again!</span>
                                                        </a>
                                                        <div class="date"><i class="bx bx-link-alt me-2 fs-15 align-middle"></i> 9:52 PM</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-7">
                                                Showing 1 - 20 of 1,524
                                            </div>
                                            <div class="col-5">
                                                <div class="btn-group float-end">
                                                    <button type="button" class="btn btn-sm btn-success waves-effect"><i class="bx bxs-chevron-left"></i></button>
                                                    <button type="button" class="btn btn-sm btn-success waves-effect"><i class="bx bxs-chevron-right"></i></button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
    
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
</body>
</html>