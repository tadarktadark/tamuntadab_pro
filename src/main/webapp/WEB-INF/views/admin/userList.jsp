<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="vertical" data-topbar="light"
	data-sidebar="light" data-sidebar-size="lg" data-sidebar-image="none"
	data-preloader="disable" data-layout-style="default"
	data-bs-theme="light" data-layout-width="fluid">
<head>
<meta charset="UTF-8">
<title>타문타답 | 우리가 만드는 커리큘럼</title>
<link href="../assets/libs/sweetalert2/sweetalert2.min.css"
	rel="stylesheet" type="text/css" />
<head>
<link href="../assets/libs/jsvectormap/css/jsvectormap.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/libs/swiper/swiper-bundle.min.css"
	rel="stylesheet" type="text/css" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
<%@ include file="./shared/_head_css.jsp"%>
<%@ include file="./shared/_vender_scripts.jsp"%>
<script src="../js/userList.js"></script>
</head>
<body>
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp"%>
		<%@ include file="./shared/_sidebar.jsp"%>
		<div class="vertical-overlay"></div>
		<div class="main-content">
			<div class="page-content">
             <%@ include file="./shared/_page_title.jsp"%>
				<div class="container-fluid">
					<div class="row">
					    <div class="col-xl-12">
					        <div class="card">
					            <div class="card-body">
					             <div class="row g-4 mb-3">
                                           <div class="col-sm-auto">
                                                <div class="hstack gap-2 flex-wrap mb-3">
                                                    <button type="button" class="btn btn-soft-primary"
                                                     data-bs-toggle="collapse" data-bs-target=".filter-collapse" 
                                                   	 aria-expanded="false" aria-controls="filter-collapse">
													    <span class="icon-off"><i class="ri-filter-fill align-bottom me-1"></i>필터</span>
													</button>
                                                </div>
                                            </div>
                                            <div class="col-sm">
                                                <div class="d-flex justify-content-sm-end">
                                                   <div class="d-flex gap-3">
											        <div>
											        	 <select class="form-select mb-3 tag">
														    <option selected value="USER_ACCOUNT_ID">아이디</option>
														    <option value="USER_EMAIL">이메일</option>
														    <option value="USER_NAME">이름</option>
														    <option value="USER_NICKNAME">닉네임</option>
														</select>
											        </div>
											            <div class="search-box">
											            	<div class="input-group">
											                <input type="text" class="form-control search-value" placeholder="검색어를 입력하세요">
											               		<i class="bx bx-search search-icon fs-16"></i>
											                <button type="button"class="btn btn-soft-primary btn-icon fs-16 search"><i class="bx bx-search-alt-2 fs-16"></i></button>
											                </div>
											            </div>
											        </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--필터 부분-->
					             <div class="col-12 mb-3">
                                           <div class="col-sm-auto">
                                                <div class="hstack gap-2 flex-wrap mb-5">
													<div class="col-6 px-2">
												        <div class="collapse filter-collapse" id="filter-collapse">
												        	<div class="row">
												        		<div class="col-2">
												        			<span class="badge bg-primary-subtle text-primary fs-17 mb-2 col-12">성별</span>
												        			<div>
												        				<div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-gender" type="radio" name="filter-gender" id="male" value="M">
                                                                           <label class="form-check-label" for="male">남자</label>
                                                                       </div>
                                                                       <div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-gender" type="radio" name="filter-gender" id="female" value="F">
                                                                           <label class="gender-filter-label" for="female">여자</label>
                                                                       </div>
												        			</div>
                                                                   </div>
												        		<div class="col-2">
												        			<span class="badge bg-primary-subtle text-primary fs-17 mb-2 col-12">유형</span>
												        			<div>
												        				<div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-auth" type="radio" name="filter-auth" id="Student" value="S">
                                                                           <label class="form-check-label" for="Student">학생</label>
                                                                       </div>
                                                                       <div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-auth" type="radio" name="filter-auth" id="Intr" value="I">
                                                                           <label class="form-check-label" for="Intr">강사</label>
                                                                       </div>
												        			</div>
                                                                   </div>
												        		<div class="col-3">
												        			<span class="badge bg-primary-subtle text-primary fs-17 mb-2 col-12">가입 경로</span>
												        			<div>
												        				<div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-site" type="radio" name="filter-site" id="tamun" value="T">
                                                                           <label class="form-check-label " for="tamun">일반</label>
                                                                       </div>
                                                                       <div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-site" type="radio" name="filter-site" id="naver" value="N">
                                                                           <label class="form-check-label" for="naver">네이버</label>
                                                                       </div>
                                                                       <div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-site" type="radio" name="filter-site" id="kakao" value="K">
                                                                           <label class="form-check-label" for="kakao">카카오</label>
                                                                       </div>
                                                                       <div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-site" type="radio" name="filter-site" id="google" value="G">
                                                                           <label class="form-check-label" for="google">구글</label>
                                                                       </div>
												        			</div>
                                                                   </div>
												        		<div class="col-3">
												        			<span class="badge bg-primary-subtle text-primary fs-17 mb-2 col-12" >삭제 여부</span>
												        			<div>
												        				<div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-delflag" type="radio" name="filter-delflag" id="hwaldong" value="N">
                                                                           <label class="form-check-label" for="hwaldozng">미삭제</label>
                                                                       </div>
                                                                       <div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-delflag" type="radio" name="filter-delflag" id="deleted" value="Y">
                                                                           <label class="form-check-label" for="deleted">삭제</label>
                                                                       </div>
                                                                       <div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-delflag" type="radio" name="filter-delflag" id="Human" value="H">
                                                                           <label class="form-check-label" for="Human">휴면</label>
                                                                       </div>
												        			</div>
                                                                   </div>
												        		<div class="col-2">
												        			<span class="badge bg-primary-subtle text-primary fs-17 mb-2 col-12">정지 여부</span>
												        			<div>
												        				<div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-jeongji" type="radio" name="filter-jeongji" id="notjeongji" value="N">
                                                                           <label class="form-check-label" for="notjeongji">정상</label>
                                                                       </div>
                                                                       <div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-jeongji" type="radio" name="filter-jeongji" id="jeongji" value="Y">
                                                                           <label class="form-check-label" for="jeongji">정지</label>
                                                                       </div>
												        			</div>
                                                                </div>
                                                                <div class="text-center">
                                                               	<button type="button" class="btn btn-soft-success col-5 comfirm-filter">
																    <span class="icon-off"><i class="mdi mdi-filter-check align-bottom me-1"></i>필터 적용</span>
																</button>
                                                               	<button type="button" class="btn btn-soft-danger col-5 remove-filter">
																    <span class="icon-off"><i class="mdi mdi-filter-remove align-bottom me-1"></i>필터 초기화</span>
																</button>
																</div>
											        		</div>
												        </div>
													</div>
                                                </div>
                                            </div>
                                        </div>
	                                        <!-- 필터 끝 -->
					                <div class="table-responsive table-card">
					                    <table class="table table-hover table-nowrap align-middle mb-0">
					                        <thead>
					                            <tr class="text-muted text-uppercase">
					                                <th style="width: 50px;">
					                                    <div class="form-check">
					                                        <input class="form-check-input" type="checkbox" id="checkAll" value="option">
					                                    </div>
					                                </th>
					                                <th class="text-center" scope="col">ID</th>
					                                <th class="text-center" scope="col">Email</th>
					                                <th class="text-center" scope="col">이름</th>
					                                <th class="text-center" scope="col">닉네임</th>
					                                <th class="text-center" scope="col">전화번호</th>
					                                <th class="text-center" scope="col">성별</th>
					                                <th class="text-center" scope="col">생년월일</th>
					                                <th class="text-center" scope="col">가입일</th>
					                                <th class="text-center" scope="col">최근 접속일</th>
					                                <th class="text-center" scope="col">유형</th>
					                                <th class="text-center" scope="col">가입경로</th>
					                                <th class="text-center" scope="col">삭제 여부</th>
					                                <th class="text-center" scope="col">정지 여부</th>
					                                <th class="text-center" scope="col">동작</th>
					                            </tr>
					                        </thead>
					                        <tbody class="userList">
					                        </tbody>
					                    </table>
					                </div>
					            </div>
					        </div>
					    </div>
					</div>
					<div class="row align-items-center mb-4 gy-3">
					    <div class="col-sm-auto ms-auto">
					        <nav aria-label="...">
					            <ul class="pagination mb-0">
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
					            </ul>
					        </nav>
					    </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script id="userList-template" type="text/x-handlebars-template">
{{#user}}
<tr>
					<td>
					    <div class="form-check">
					        <input class="form-check-input" type="checkbox" id="check1" value="option">
					    </div>
					</td>
					<td><p class="fw-medium mb-0">{{userAccountId}}</p></td>
					<td>
					    <a href="#javascript: void(0);" class="text-body align-middle fw-medium">{{userEmail}}</a>
					</td>
					<td>{{userName}}</td>
					<td>{{userNickname}}</td>
					<td>{{userPhoneNumber}}</td>
					<td class="text-center"><span class="badge bg-success-subtle text-success  p-2">{{userGender}}</span></td>
					<td>{{userBirth}}</td>
					<td>{{userJoinDate}}</td>
					<td>{{userLastAccessDate}}</td>
					<td class="text-center"><span class="badge bg-success-subtle text-success  p-2">{{userAuth}}</span></td>
					<td class="text-center"><span class="badge bg-success-subtle text-success  p-2">{{userSite}}</span></td>
					<td class="text-center"><span class="badge bg-success-subtle text-success  p-2">{{userDelflag}}</span></td>
					<td class="text-center"><span class="badge bg-danger-subtle text-danger  p-2">{{userJeongJiSangTae}}</span></td>
					<td>
					    <div class="dropdown">
					        <button class="btn btn-soft-primary btn-sm dropdown" type="button" data-bs-toggle="dropdown" aria-expanded="false">
					            <i class="bx bx-dots-horizontal-rounded align-middle fs-18"></i>
					        </button>
					        <ul class="dropdown-menu dropdown-menu-end">
					            <li>
					                <button class="dropdown-item" href="javascript:void(0);">
					                    <i class="las la-eye fs-18 align-middle me-2 text-muted"></i>
					                    View
					                </button>
					            </li>
					            <li>
					                <button class="dropdown-item" href="javascript:void(0);">
					                    <i class="las la-pen fs-18 align-middle me-2 text-muted"></i>
					                    Edit
					                </button>
					            </li>
				</ul>
			</div>
		</td>
	</tr>
{{/user}}
</script>
</html>