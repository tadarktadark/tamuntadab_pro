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
                                                                <h5 class="fs-15 mb-0"><a href="" class="text-body">클래스장 닉네임</a></h5>
                                                                <a href="" class="text-muted text-uppercase fs-13 mt-1">서울특별시 금천구 가산동</a>
                                                            </div>
                                                        </div>
                                                        <span class="star-toggle bx bx-star"></span>
                                                    </div>
                                                    <div class="col-mail col-mail-2">
                                                        <a href="#" class="subject text-body"><span class="bg-primary badge me-2">참여 중</span>클래스 제목<span class="teaser text-muted fw-normal">과목 명, 과목 명, 과목 명</span>
                                                        </a>
                                                        <div class="date"><i class="bx bx-link-alt me-2 fs-15 align-middle"></i>23.08.16</div>
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
                                                            <img src="assets/images/users/avatar-2.jpg" class="avatar-sm rounded-circle" alt="">
                                                            <div class="flex-1 ms-2 ps-1 mt-1">
                                                                <h5 class="fs-15 mb-0"><a href="" class="text-body">클래스장 닉네임</a></h5>
                                                                <a href="" class="text-muted text-uppercase fs-13 mt-1">부산광역시 해운대구 우동</a>
                                                            </div>
                                                        </div>
                                                        <span class="star-toggle bx bx-star"></span>
                                                    </div>
                                                    <div class="col-mail col-mail-2">
                                                        <a href="#" class="subject text-body">
                                                        	<span class="bg-warning badge me-2">좋아요</span>
                                                        	클래스 제목
                                                        	<span class="teaser text-muted fw-normal"> 과목 명 / 과목 명 / 과목 명</span>
                                                        	<span class="bg-danger badge me-2">여자만</span>
                                                        </a>
                                                        <div class="date"><i class="bx bx-link-alt me-2 fs-15 align-middle"></i>일주일 이내</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                 	</div>
    
                                        <div class="pt-2" id="normalClass">
                                            <h6 class="text-muted text-uppercase mb-3">클래스 목록</h6>
                                            <div class="mb-2">
                                                <div class="message-list mb-0 p-1">
                                                    <div class="list">
                                                        <div class="col-mail col-mail-1">
                                                            <div class="d-flex title align-items-center">
                                                                <img src="assets/images/users/avatar-1.jpg" class="avatar-sm rounded-circle" alt="">
                                                                <div class="flex-1 ms-2 ps-1 mt-1">
                                                                    <h5 class="fs-15 mb-0"><a href="" class="text-body">클래스장 닉네임</a></h5>
                                                                    <a href="" class="text-muted text-uppercase fs-13 mt-1">대전광역시 동구 중앙동</a>
                                                                </div>
                                                            </div>
                                                            <span class="star-toggle bx bx-star"></span>
                                                        </div>
                                                        <div class="col-mail col-mail-2">
                                                            <a href="classDetail.do" class="subject text-body">
                                                            	클래스 제목 
                                                            	<span class="teaser text-muted fw-normal"> 과목 명 / 과목 명 / 과목 명</span>
                                                            	<span class="bg-danger badge me-2">남자만</span>
                                                            	<span class="bg-info badge me-2">20-40</span>
                                                            </a>
                                                            <div class="date"><i class="bx bx-link-alt me-2 fs-15 align-middle"></i>오늘</div>
                                                        </div>
                                                    </div>
                                                </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-7">
                                                Showing 퍼스트 - 라스트 of 토탈카운트
                                            </div>
                                            <div class="col-sm-auto ms-auto">
					                            <nav aria-label="...">
					                                <ul class="pagination mb-0">
					                                	<li class="page-item disabled">
					                                    <span class="page-link">first</span>
					                                  </li>
					                                  <li class="page-item disabled">
					                                    <span class="page-link">Previous</span>
					                                  </li>
					                                  <li class="page-item active"><a class="page-link" href="#">1</a></li>
					                                  <li class="page-item" aria-current="page">
					                                    <span class="page-link">2</span>
					                                  </li>
					                                  <li class="page-item"><a class="page-link" href="#">3</a></li>
					                                  <li class="page-item">
					                                    <a class="page-link" href="#">Next</a>
					                                  </li>
					                                  <li class="page-item">
					                                    <a class="page-link" href="#">last</a>
					                                  </li>
					                                </ul>
					                              </nav>
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