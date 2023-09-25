<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" data-layout="vertical" data-topbar="light"
	data-sidebar="light" data-sidebar-size="lg" data-sidebar-image="none"
	data-preloader="disable" data-layout-style="default"
	data-bs-theme="light" data-layout-width="fluid">
<head>
<meta charset="UTF-8">
<title>타문타답 | 우리가 만드는 커리큘럼</title>
<link href="../assets/libs/sweetalert2/sweetalert2.min.css" rel="stylesheet" type="text/css" />
<link href="../assets/libs/jsvectormap/css/jsvectormap.min.css" rel="stylesheet" type="text/css" />
<link href="../assets/libs/swiper/swiper-bundle.min.css" rel="stylesheet" type="text/css" />
<link href="../css/adminSingo.css" rel="stylesheet" type="text/css" />
<%@ include file="./shared/_head_css.jsp"%>
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
					<div id="singoList" class="card mt-3">
						<div class="table-responsive table-card">
						    <table class="table table-nowrap mb-0">
						    	<col width="25%">
						    	<col width="25%">
						    	<col width="25%">
						    	<col width="25%">
						        <thead class="table-light">
						            <tr class="table-light">
						                <th scope="col">대상 게시글</th>
						                <th scope="col">작성자</th>
						                <th scope="col">상태</th>
						                <th scope="col">상세 보기</th>
						            </tr>
						        </thead>
						        <tbody>
						        	<c:forEach items="${list}" var="d">
							            <tr>
							                <td>${d.daesangId}</td>
							                <td>${d.accountId}</td>
							                <td>
								                <c:choose>
								                	<c:when test="${d.state eq 'B'}">
								                		<span class="badge bg-success-subtle text-success">반려(게시)</span>
								                	</c:when>
								                	<c:when test="${d.state eq 'D'}">
								                		<span class="badge bg-danger-subtle text-danger">수리(삭제)</span>
								                	</c:when>
								                	<c:otherwise>
								                		<span class="badge bg-warning-subtle text-warning">처리대기</span>
								                	</c:otherwise>
								                </c:choose>
							                </td>
							                <td data-bs-toggle="collapse" data-bs-target="#collapse${d.id}" aria-expanded="true" aria-controls="collapseOne"><span class="badge bg-success">Paid</span></td>
							            </tr>
							            <tr id="collapse${d.id}" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#default-accordion-example">
								            <td colspan="5">
								                <table class="table table-nowrap mb-0">
								                	<col width="20%">
											    	<col width="20%">
											    	<col width="55%">
											    	<col width="5%">
								                    <thead class="table-light">
								                        <tr class="table-warning">
								                            <th scope="col">#</th>
								                            <th scope="col">Student</th>
								                            <th scope="col">Course</th>
								                            <th scope="col">Author</th>
								                        </tr>
								                    </thead>
								                    <tbody>
								                        <tr>
								                            <th scope="row">1</th>
								                            <td>Milana Scot</td>
								                            <td>3d Animation</td>
								                            <td>James Black</td>
								                        </tr>
								                    </tbody>
								                </table>
								            </td>
								        </tr>
						        	</c:forEach>
						        </tbody>
						    </table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="./shared/_vender_scripts.jsp"%>
</html>