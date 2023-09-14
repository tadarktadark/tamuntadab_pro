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
					
					
					<div class="row">
                        <div class="col-12">
    
                            <!-- Left sidebar -->
                            <div class="email-leftbar">
                                <div class="card">
                                    <div class="card-body">
                                        <button type="button" class="btn btn-danger waves-effect waves-light w-100" data-bs-toggle="modal" data-bs-target="#composemodal">
                                            Compose
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
    
                                        <h5 class="mt-3 fs-15 text-uppercase">Labels</h5>
    
                                        <div class="card p-0 overflow-hidden mt-3 shadow-none">
                                            <div class="mail-list">
                                                <a href="#" class="border-bottom"><span class="mdi mdi-arrow-right-drop-circle text-info float-end"></span>Theme Support</a>
                                                <a href="#" class="border-bottom"><span class="mdi mdi-arrow-right-drop-circle text-warning float-end"></span>Freelance</a>
                                                <a href="#" class="border-bottom"><span class="mdi mdi-arrow-right-drop-circle text-primary float-end"></span>Social</a>
                                                <a href="#" class="border-bottom"><span class="mdi mdi-arrow-right-drop-circle text-danger float-end"></span>Friends</a>
                                                <a href="#"><span class="mdi mdi-arrow-right-drop-circle text-success float-end"></span>Family</a>
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

                                            <div class="d-flex align-items-center mb-4">
                                                <div class="flex-shrink-0 me-3">
                                                    <img class="rounded-circle avatar-sm" src="assets/images/users/avatar-2.jpg" alt="Generic placeholder image">
                                                </div>
                                                <div class="flex-grow-1">
                                                    <h5 class="fs-15 mb-0">Humberto D. Champion</h5>
                                                    <small class="text-muted">support@domain.com</small>
                                                </div>
                                            </div>
    
                                            <h4 class="fs-16">This Week's Top Stories</h4>
    
                                            <p>Dear Lorem Ipsum,</p>
                                            <p>Praesent dui ex, dapibus eget mauris ut, finibus vestibulum enim. Quisque arcu leo, facilisis in fringilla id, luctus in tortor. Nunc vestibulum est quis orci varius viverra. Curabitur dictum volutpat massa vulputate molestie. In at felis ac velit maximus convallis.
                                            </p>
                                            <p>Sed elementum turpis eu lorem interdum, sed porttitor eros commodo. Nam eu venenatis tortor, id lacinia diam. Sed aliquam in dui et porta. Sed bibendum orci non tincidunt ultrices. Vivamus fringilla, mi lacinia dapibus condimentum, ipsum urna lacinia lacus, vel tincidunt mi nibh sit amet lorem.</p>
                                            <p>Sincerly,</p>
                                            <hr>
    
                                            <div class="row">
                                                <div class="col-xl-2 col-6">
                                                    <div class="card">
                                                        <img class="card-img-top img-fluid" src="assets/images/small/img-3.jpg" alt="Card image cap">
                                                        <div class="py-2 text-center">
                                                            <a href="javascript: void(0);" class="fw-medium">Download</a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-xl-2 col-6">
                                                    <div class="card">
                                                        <img class="card-img-top img-fluid" src="assets/images/small/img-4.jpg" alt="Card image cap">
                                                        <div class="py-2 text-center">
                                                            <a href="javascript: void(0);" class="fw-medium">Download</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
    
                                            <a href="./myClass.do" class="btn btn-secondary waves-effect mt-4"><i class="mdi mdi-reply me-1"></i> 참여 중이라면 '클래스 페이지', 아니라면 '참여하기' </a>
                                   
                                         </div>
    
                                    </div>
    
                                </div>
                            </div> <!-- end-->
    
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