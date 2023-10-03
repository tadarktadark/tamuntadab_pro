<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title}|타문타답</title>
<%@ include file="./shared/_logout.jsp"%>
<%@ include file="./shared/_head_css.jsp"%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_menu.jsp"%>
	<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<%@ include file="./shared/_page_title.jsp" %>
					
					<div class="row">
                        <div class="col-12">
    
                            <!-- Left sidebar -->
                            <div class="email-leftbar" style="width: 270px;">
                                <div class="card">
                                    <div class="card-body">
                                        <button type="button" class="btn btn-primary waves-effect waves-light w-100" onclick="location.href='./classWrite.do'" data-bs-toggle="modal" data-bs-target="#composemodal" >
                                            새 클래스 개설
                                        </button>
    
                                        <div class="card p-0 overflow-hidden mt-4 shadow-none">
                                            <div class="mail-list">
                                                <a href="javascript:ajaxPaging(1,1)" class="active bg-primary-subtle" id="category1">
                                                    <div class="d-flex align-items-center">
                                                        <i class="bx bx-book-open fs-20 align-middle me-3"></i>
                                                        <div class="flex-grow-1">
                                                            <h5 class="fs-15 mb-0">전체 클래스</h5>
                                                            <span class="text-muted fs-13 mt-1 text-truncate">모든 클래스 조회</span>
                                                        </div>
                                                        <div class="flex-shrink-0">
                                                            <div class="float-end">
<!--                                                                 <span class="bg-primary badge"></span> -->
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                                <a href="javascript:ajaxPaging(1,2)" class="border-bottom" id="category2">
                                                    <div class="d-flex align-items-center">
                                                        <i class="bx bx-extension fs-20 align-middle me-3"></i>
                                                        <div class="flex-grow-1">
                                                            <h5 class="fs-15 mb-0">개발 · 프로그래밍</h5>
                                                            <span class="text-muted fs-13 mt-1 text-truncate">코드로 세상 변혁</span>
                                                        </div>
                                                        <div class="flex-shrink-0">
                                                        </div>
                                                    </div>
                                                </a>
        
                                                <a href="javascript:ajaxPaging(1,3)" class="border-bottom" id="category3">
                                                    <div class="d-flex align-items-center">
                                                        <i class="bx bx-shield fs-20 align-middle me-3"></i>
                                                        <div class="flex-grow-1">
                                                            <h5 class="fs-15 mb-0">보안 · 네트워크</h5>
                                                            <span class="text-muted fs-13 mt-1 text-truncate">안전한 연결의 길</span>
                                                        </div>
                                                        <div class="flex-shrink-0">
                                                        </div>
                                                    </div>
                                                </a>
        
                                                <a href="javascript:ajaxPaging(1,4)" class="border-bottom" id="category4">
                                                    <div class="d-flex align-items-center">
                                                        <i class="bx bx-trending-up fs-20 align-middle me-3"></i>
                                                        <div class="flex-grow-1">
                                                            <h5 class="fs-15 mb-0">데이터 사이언스</h5>
                                                            <span class="text-muted fs-13 mt-1 text-truncate">데이터로 미래 예측</span>
                                                        </div>
                                                        <div class="flex-shrink-0">
                                                        </div>
                                                    </div>
                                                </a>
        
                                                <a href="javascript:ajaxPaging(1,5)" class="border-bottom"id="category5">
                                                    <div class="d-flex align-items-center">
                                                        <i class="bx bx-joystick fs-20 align-middle me-3"></i>
                                                        <div class="flex-grow-1">
                                                            <h5 class="fs-15 mb-0">게임 개발</h5>
                                                            <span class="text-muted fs-13 mt-1 text-truncate">재미로 세상을 물들이기</span>
                                                        </div>
                                                        <div class="flex-shrink-0">
                                                            <div class="float-end">
<!--                                                                 <span class="bg-primary badge">08</span> -->
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                                <a href="javascript:ajaxPaging(1,6)" class="border-bottom" id="category6">
                                                    <div class="d-flex align-items-center">
                                                        <i class="bx bx-hdd fs-20 align-middle me-3"></i>
                                                        <div class="flex-grow-1">
                                                            <h5 class="fs-15 mb-0">하드웨어</h5>
                                                            <span class="text-muted fs-13 mt-1 text-truncate">물리적 기술의 미래</span>
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
    
                            <!-- Right Sidebar -->
                            <div class="email-rightbar mb-3" style="margin-left: 280px;">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="">
                                            <div class="row mb-4">
											    <div class="col-xl-3 col-md-12">
											        <div class="pb-3 pb-xl-0">
											            <form class="email-search" id="classSearchForm">
											                <div class="position-relative">
											                    <div class="choices" data-type="select-multiple" role="combobox" aria-autocomplete="list" aria-haspopup="true" aria-expanded="false">
											                        <div class="choices__inner" style="width:400px;">
											                            <div id="selectedSubjects" class="choices__list choices__list--multiple" >
											                            </div>
											                            <div class="input-group" >
											                                <input type="search" id="subjects" name="subjects" placeholder="과목 또는 클래스명으로 검색" class="form-control bg-light" autocomplete="off" autocapitalize="off" spellcheck="false" role="textbox" aria-autocomplete="list" aria-label="null" style="width:300px;">
											                                <button type="submit" class="btn btn-primary right-btn">검색</button>
											                            </div>
											                        </div>
											                    </div>
											                </div>
											            </form>
											        </div>
											    </div>
											</div>
                                            <!-- Collapse with Icon -->
												<div class="hstack gap-3 mb-3">
												    <a class="link-success" data-bs-toggle="collapse" href="#collapseWithicon" role="button" aria-expanded="true" aria-controls="collapseWithicon">
												        <i class="ri-arrow-down-circle-line fs-16"></i>
												        참여 중인 클래스 목록
												        <i class="ri-arrow-down-circle-line fs-16"></i>
												    </a>
												</div>
												<div class="collapse" id="collapseWithicon">
												    <div class="card mb-0">
												        <div class="card-body">
												        	 <div class="mb-2" id="chamyeoClassTag">
		                                   					 </div>
												        </div>
												    </div>
												</div>
		                                    <div class="pt-2" id="joayoClassTag">
		                                 	</div>
		    
		                                    <div class="pt-2" id="normalClass">
		                                    </div>
		                               </div>
    
                                    </div>
                                </div>
    
                            </div> <!-- end Col-9 -->
    
                        </div>
                    </div>
		        	
		        	
		        	<input type="hidden" id="indexSub" value="${subjects}">
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp" %>
			<script type="text/javascript" src="./js/classList.js"></script>
		</div>
	</div>
	<%@ include file="./shared/_vender_scripts.jsp" %>
</body>
	<style type="text/css">
			.email-leftbar {
			  position: sticky;
			  top: 100px;
			}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			var subjects = $('#indexSub').val();
	
				    if (subjects) {
				        $('#classSearchForm #subjects').val(subjects);
				        searchPaging(1, subjects);
				    }
			});
	</script>
</html>