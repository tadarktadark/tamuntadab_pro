<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light" data-topbar="light" data-sidebar="light" data-sidebar-size="sm" data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title} | 타문타답</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<%@ include file="./shared/_head_css.jsp" %>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp" %>
		<%@ include file="./shared/_sidebar.jsp" %>
	
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<%@ include file="./shared/_page_title.jsp" %>
					
					<!-- 검색 바 -->
					<div class="row pb-4 gy-3">
			            <div class="col-sm-auto ms-auto">
			              	<div class="d-flex gap-3">
			                  	<div class="search-box">
			                      	<input type="text" class="form-control" placeholder="과목명 검색">
			                         	<i class="bx bx-search search-icon fs-16"></i>
			                          	<div data-lastpass-icon-root="true" style="position: relative !important; height: 0px !important; width: 0px !important; float: left !important;"></div>
			                   	</div>
			                   	<div class="">
			                   		<button type="button" id="dropdownMenuLink1" data-bs-toggle="dropdown" aria-expanded="false" class="btn btn-soft-info btn-icon fs-14">
			                         	<i class="bx bx-dots-vertical-rounded fs-18"></i>
			                       	</button>
			                  	</div>
			              	</div>
			        	</div>
		        	</div><!-- 검색바의 끝 -->
		        	
		        	<!-- 조회 목록 -->
		        	<div class="row">
                        <div class="col-xl-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="table-responsive table-card">
                                        <table class="table table-hover table-nowrap align-middle mb-0">
                                            <thead>
                                                <tr class="text-muted text-uppercase">
                                                    <th scope="col">과목 ID</th>
                                                    <th scope="col">과목명</th>
                                                    <th scope="col" style="width: 20%;">과목 등록일</th>
                                                    <th scope="col" style="width: 20%;">최근 수정 날짜</th>
                                                    <th scope="col">개설된 클래스 수</th>
                                                    <th scope="col">과목 상태</th>
                                                    <th scope="col" style="width: 12%;"> </th>
                                                </tr>
                                            </thead>
        
                                            <tbody>
											    <c:forEach var="subject" items="${subjectList}">
											        <tr>
											            <!-- 과목 ID -->
											            <td><p class="fw-medium mb-0">${subject.subjId}</p></td>
											            <!-- 과목명 -->
											            <td><a href="#javascript: void(0);" 
											                   class="text-body align-middle fw-medium">${subject.subjTitle}</a></td>
											
											            <!-- 과목 등록일 -->
											            <td>${subject.subjRegdate}</td>
											
											            <!-- 최근 수정 날짜 -->
											            <td>
												            <c:choose>
															    <c:when test="${empty subject.subjSujeongil}">
															        수정 내역이 없습니다
															    </c:when>
															    <c:otherwise>
															        ${subject.subjSujeongil}
															    </c:otherwise>
															</c:choose>
											            </td>
											
											          	<!-- 개설된 클래스 수 -->
											          	<td>${subject.clasCount}개</td>
											
											          	<!-- 과목 상태 - 상태 값에 따라 출력 내용 변경 가능-->
											          	<td>
											                <c:choose>
											                    <c:when test="${subject.subjStatus eq 'A' && subject.subjDelflag ne 'Y'}">
											                        <span class="badge bg-success-subtle text-success  p-2" style="color: red;">승인됨</span>
											                    </c:when>
											                    <c:when test="${subject.subjStatus eq 'S' && subject.subjDelflag ne 'Y'}">
											                        <span class="badge bg-warning-subtle text-warning  p-2">요청 전송됨</span>
											                    </c:when>
											                    <c:when test="${subject.subjStatus eq 'R' && subject.subjDelflag ne 'Y'}">
											                        <span class="badge bg-danger-subtle text-success  p-2">반려됨</span>
											                    </c:when>
											                    <c:otherwise>
											                        <span class="badge bg-danger-subtle text-success  p-2">삭제됨</span>
											                    </c:otherwise>
											                </c:choose>  
											          	</td> 
											
											        	<!-- 관리자 기능 -->
											        	<td> 
											            	<div class="dropdown">
											                	<button class="btn btn-soft-primary btn-sm dropdown" type="button" data-bs-toggle="dropdown" aria-expanded="false">
											                    	<i class="bx bx-dots-horizontal-rounded align-middle fs-18"></i>
											                	</button>
											                	
											                	<ul class="dropdown-menu dropdown-menu-end">
		                                                            <li>
		                                                                <button class="dropdown-item"><i class="las la-eye fs-18 align-middle me-2 text-muted"></i>
		                                                                    과목 승인</button>
		                                                            </li>
		                                                            <li>
		                                                                <button class="dropdown-item"><i class="las la-pen fs-18 align-middle me-2 text-muted"></i>
		                                                                    과목 반려</button>
		                                                            </li>
		                                                            <li class="dropdown-divider"></li>
		                                                            <li>
		                                                                <a class="dropdown-item remove-item-btn" href="#">
		                                                                    <i class="las la-trash-alt fs-18 align-middle me-2 text-muted"></i>
		                                                                    과목 관리
		                                                                </a>
		                                                            </li>
		                                                        </ul>

											            	</div>
											        	</td> 
											
											        </tr>
											    </c:forEach> 
											</tbody><!-- tbody의 끝 -->
                                        </table><!-- table의 끝 -->
                                    </div><!-- table 컨테이너의 끝 -->
                                </div>
                            </div>
                        </div>
                    </div><!-- 조회 목록의 끝 -->
		        	
		        	<div class="row align-items-center mb-4 gy-3">
                        <div class="col-md-5">
                            <p class="mb-0 text-muted">Showing <b>1</b> to <b>5</b> of <b>10</b> results</p>
                        </div>
                        <div class="col-sm-auto ms-auto">
                            <nav aria-label="...">
                                <ul class="pagination mb-0">
                                
                                	<!-- first 버튼 구현 -->
                           			<c:choose>
					                    <c:when test="${param.page > 1}">
					                    	<li class="page-item">
					                            <a class="page-link" href="./subjectManage.do?page=1">first</a>
					                        </li>
					                    </c:when>
					                    <c:otherwise>
					                        <li class="page-item disabled">
					                            <span class="page-link">first</span>
					                        </li>
					                    </c:otherwise>
					                </c:choose>
					                
					                <!-- privious버튼 구현 -->
					                <c:choose>
							          	<c:when test="${pVo.page - pVo.countPage > 0}">
							          		<li class="page-item">
			                                   	<a class="page-link" href="./subjectManage.do?page=${(pVo.startPage - pVo.countPage) < 0 ? 1 : (pVo.startPage-pVo.countPage)}">Previous</a>
			                                </li>
							          	</c:when>
							          	<c:otherwise>
											<li class="page-item disabled">
			                                   	<span class="page-link">Previous</span>
			                                </li>							          	
							          	</c:otherwise>
					                </c:choose>
					
									<!-- 페이지 버튼 구현 -->
									<c:forEach var="i" begin="${pVo.startPage}" end="${pVo.endPage}">
								        <c:choose>
								            <c:when test="${param.page eq i}">
								                <li class="page-item active">
								                    <span class="page-link">${i}</span>
								                </li>
								            </c:when>
								            <c:otherwise>
								                <li class="page-item">
								                    <a class="page-link" href="./subjectManage.do?page=${i}">${i}</a>
								                </li>                
								            </c:otherwise>
								        </c:choose>        
								    </c:forEach>
								    
								    <c:choose>
									    <c:when test="${pVo.totalPage > (pVo.page+countPage)}">
									        <li class="page-item">
									            <a class="page-link" href="./subjectManage.do?page=${pVo.startPage+pVo.countPage}">NEXT</a>
									        </li>
									    </c:when>
									    <c:otherwise>
									        <li class="page-item disabled">
									            <span class="page-link">NEXT</span>
									        </li>
									    </c:otherwise>
									</c:choose> 
					                
					                <!-- End 버튼 구현 -->
	                                <c:choose>
					                    <c:when test="${pVo.totalPage == (pVo.page)}">
					                       <li class="page-item disabled">
					                            <span class="page-link">End</span>
					                        </li>
					                    </c:when>
					                    <c:otherwise>
					                    	 <li class="page-item">
					                            <a class="page-link" href="./subjectManage.do?page=${pVo.totalPage}">End</a>
					                        </li>
					                    </c:otherwise>
					                </c:choose>
                                </ul>
                              </nav>
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