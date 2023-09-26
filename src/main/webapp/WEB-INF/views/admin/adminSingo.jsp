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
					<div id="singo-wrapper" class="card mt-3">
						<div id="singo-list" class="table-responsive table-card">
						    <table class="table mb-0">
						    	<col width="25%">
						    	<col width="25%">
						    	<col width="25%">
						    	<col width="25%">
						        <thead>
						            <tr>
						                <th scope="col" class="bg-primary text-light">대상 게시글</th>
						                <th scope="col" class="bg-primary text-light">작성자</th>
						                <th scope="col" class="bg-primary text-light">상태</th>
						                <th scope="col" class="bg-primary text-light">상세 보기</th>
						            </tr>
						        </thead>
						        <tbody id="singo-list-tbody">
						        </tbody>
						    </table>
						</div>
					</div>
		            <ul class="comm-page pagination justify-content-center mt-3 pt-3 mb-0">
		            </ul>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="./shared/_vender_scripts.jsp"%>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" charset="UTF-8"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<script type="text/javascript" src="../js/community.js"></script>
<script type="text/javascript" src="../js/adminSingo.js"></script>
<script id="page-list-template" type="text/x-handlebars-template">
	{{#each page}}
	<li class="page-item {{state}}">
		<span class="page-link" id="{{id}}">{{{htmlOrText value}}}</span>
	</li>
	{{/each}}
</script>
<script id="singo-list-template" type="text/x-handlebars-template">
	<tr id="{{id}}">
	    <td>{{daesangId}}</td>
	    <td>{{accountId}}</td>
	    <td>
	    	{{#stateB}}
	   		<span class="badge bg-success-subtle text-success">반려(게시)</span>
	   		{{/stateB}}
	   		{{#stateD}}
	   		<span class="badge bg-danger-subtle text-danger">수리(삭제)</span>
	   		{{/stateD}}
	   		{{#stateP}}
	   		<span class="badge bg-warning-subtle text-warning">처리대기</span>
	   		{{/stateP}}
	    </td>
	    <td id="flush-heading{{i}}" class="viewDetail collapsed" data-bs-toggle="collapse" data-bs-target="#flush-collapse{{i}}" aria-expanded="false" aria-controls="flush-collapse{{i}}"><span class="badge bg-primary view-btn">열기</span></td>
	</tr>
	<tr id="flush-collapse{{i}}" class="accordion-collapse collapse" aria-labelledby="flush-heading{{i}}" data-bs-parent="#accordionFlushExample">
		<td colspan="4">
	    	<table class="table table-nowrap mb-0">
	     		<col width="20%">
				<col width="30%">
				<col width="30%">
				<col width="20%">
	            <thead class="table-light">
	                <tr class="table-warning">
	                    <th colspan="4">{{{daesangContent}}}</th>
	                </tr>
	            </thead>
	            <tbody>
	                <tr class="table-primary">
	                    <th>신고자</th>
	                    <th>신고 카테고리</th>
	                    <th>기타 내용</th>
	                    <th>신고일</th>
	                </tr>
	                {{#each sayu}}
	                <tr>
	                	<td>{{sayuAccountId}}</td>
	                	<td>{{category}}</td>
	                	<td>{{content}}</td>
	                	<td>{{regdate}}</td>
	                </tr>
	                {{/each}}
					{{#stateP}}
	   					<tr>
	                		<td colspan="3"></td>
	                		<td>
								<span class="updateB badge text-bg-success">반려(게시)</span>
								<span class="updateD badge text-bg-danger">수리(삭제)</span>
							</td>
	                	</tr>
	   				{{/stateP}}
	            </tbody>
	        </table>
	    </td>
	</tr>
</script>
</html>