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
                        	
                        	<!-- left Sidebar -->
                            <div class="email-leftbar col-md-3 col-lg-3" style="margin-left: 20px; margin-right: 20px;">
                                <div class="card">
                                    <div class="card-body">
    
                                        <div class="">
                                            <div class="row mb-4">
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
                                                    <h5 class="fs-15 mb-0">${cVo.userVo[0].userNickname}</h5>
                                                    <small class="text-muted">${cVo.clasLocation}</small>
                                                </div>
                                            </div>
    
                                            <h4 class="fs-16">${cVo.clasTitle}</h4>
    
    										<ul class="list-group">
											    <li class="list-group-item"><i class="ri-bill-line align-middle me-2"></i> Send the billing agreement</li>
											    <li class="list-group-item"><i class="ri-file-copy-2-line align-middle me-2"></i>Send over all the documentation.</li>
											    <li class="list-group-item"><i class="ri-question-answer-line align-middle me-2"></i>Meeting with daron to review the intake form</li>
											    <li class="list-group-item"><i class="ri-secure-payment-line align-middle me-2"></i>Check uikings theme and give customer support</li>
											</ul>
                                            
                                            <hr>
    
    										<p>${cVo.clasContent}</p>
    
                                            <a href="./myClass.do?clasId=${cVo.clasId}" class="btn btn-secondary waves-effect mt-4"><i class="mdi mdi-reply me-1"></i> 참여 중이라면 '클래스 페이지', 아니라면 '참여하기' </a>
                                   			
                                         </div>
    
                                    </div>
    
                                </div>
                            </div> <!-- end-->
    
                            <!-- right sidebar -->
                            <div class="email-leftbar col-md-3 col-lg-3" style="margin-left: 20px;">
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
                                                            <h5 class="fs-15 mb-0">전체 클래스</h5>
                                                            <span class="text-muted fs-13 mt-1 text-truncate">모든 클래스를 조회합니다</span>
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
                                                <a href="#" class="border-bottom"><span class="mdi mdi-arrow-right-drop-circle text-danger float-end"></span>성별 제한</a>
												<a href="#" class="border-bottom"><span class="mdi mdi-arrow-right-drop-circle text-info float-end"></span>나이 제한</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
    
                            </div>
                            <!-- End Left sidebar -->
                        </div>
                    </div>
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp" %>
			<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
			<script type="text/javascript" src="./js/classList.js"></script>
		</div>
	</div>
	<%@ include file="./shared/_vender_scripts.jsp" %>
</body>
</html>