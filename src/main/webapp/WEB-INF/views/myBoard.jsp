<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<link href="./css/myBoard.css" rel="stylesheet" type="text/css" />
<link href="./css/community.css" rel="stylesheet" type="text/css" />
</head>
	<div class="card-body">
		<!-- Nav tabs -->
		<ul id="myComm" class="nav nav-pills nav-customs nav-danger" role="tablist">
			<li class="nav-item"><a class="nav-link myPilgi active" data-bs-toggle="tab" href="#write-pilgi" role="tab">작성 필기</a></li>
			<li class="nav-item"><a class="nav-link myJilmun" data-bs-toggle="tab" href="#write-jilmun" role="tab">작성 질문</a></li>
			<li class="nav-item"><a class="nav-link myJayu" data-bs-toggle="tab" href="#write-jayu" role="tab">작성 자유</a></li>
			<li class="nav-item"><a class="nav-link myReply" data-bs-toggle="tab" href="#write-reply" role="tab">작성 댓글</a></li>
			<li class="nav-item"><a class="nav-link myLike" data-bs-toggle="tab" href="#like-comm" role="tab">좋아요</a></li>
		</ul>
		<div class="card mb-0">
			<!-- Tab panes -->
			<div class="tab-content text-muted">
				<div class="tab-pane active" id="write-pilgi" role="tabpanel">
					<div class="card-body pt-3">
	                    <div id="customerList">
	                        <div class="table-responsive table-card">
	                            <table class="table align-middle table-nowrap" id="customerTable">
	                            	<col width="55%">
	                            	<col width="10%">
	                            	<col width="10%">
	                            	<col width="10%">
	                            	<col width="15%">	                            	
	                                <thead class="table-primary">
	                                    <tr>
	                                        <th class="bg-primary">제목</th>
	                                        <th class="bg-primary">공개</th>
	                                        <th class="bg-primary">다운로드</th>
	                                        <th class="bg-primary">상태</th>
	                                        <th class="bg-primary">관리</th>
	                                    </tr>
	                                </thead>
	                                <tbody id="write-pilgi-tbody" class="list form-check-all">
	                                </tbody>
	                            </table>
	                        </div>
	                    </div>
	                </div>
				</div>
				<div class="tab-pane" id="write-jilmun" role="tabpanel">
					<div class="card-body pt-3">
	                    <div id="customerList">
	                        <div class="table-responsive table-card">
	                            <table class="table align-middle table-nowrap" id="customerTable">
	                            	<col width="80%">
	                            	<col width="10%">
	                            	<col width="10%">                          	
	                                <thead class="table-primary">
	                                    <tr>
	                                        <th class="bg-primary">제목</th>
	                                        <th class="bg-primary">채택</th>
	                                        <th class="bg-primary">상태</th>
	                                    </tr>
	                                </thead>
	                                <tbody id="write-jilmun-tbody" class="list form-check-all">
	                                </tbody>
	                            </table>
	                        </div>
	                    </div>
	                </div>
				</div>
				<div class="tab-pane" id="write-jayu" role="tabpanel">
					<div class="card-body pt-3">
	                    <div id="customerList">
	                        <div class="table-responsive table-card">
	                            <table class="table align-middle table-nowrap" id="customerTable">
	                            	<col width="90%">
	                            	<col width="10%">
	                                <thead class="table-primary">
	                                    <tr>
	                                        <th class="bg-primary">제목</th>
	                                        <th class="bg-primary">상태</th>
	                                    </tr>
	                                </thead>
	                                <tbody id="write-jayu-tbody" class="list form-check-all">
	                                </tbody>
	                            </table>
	                        </div>
	                    </div>
	                </div>
				</div>
				<div class="tab-pane" id="write-reply" role="tabpanel">
					<div class="card-body pt-3">
	                    <div id="customerList">
	                        <div class="table-responsive table-card">
	                            <table class="table align-middle table-nowrap" id="customerTable">
	                            	<col width="45%">
	                            	<col width="45%">
	                            	<col width="10%">
	                                <thead class="table-primary">
	                                    <tr>
	                                        <th class="bg-primary">글제목</th>
	                                        <th class="bg-primary">내 댓글</th>
	                                        <th class="bg-primary">상태</th>
	                                    </tr>
	                                </thead>
	                                <tbody id="write-reply-tbody" class="list form-check-all">
	                                </tbody>
	                            </table>
	                        </div>
	                    </div>
	                </div>
				</div>
				<div class="tab-pane" id="like-comm" role="tabpanel">
					<div class="card-body pt-3">
	                    <div id="customerList">
	                        <div class="table-responsive table-card">
	                            <table class="table align-middle table-nowrap" id="customerTable">
	                            	<col width="50px">
	                            	<col width="300px">
	                            	<col width="100px">
	                            	<col width="100px">
	                            	<col width="100px">
	                            	<col width="100px">
	                            	<col width="100px">	                            	
	                                <thead class="table-primary">
	                                    <tr>
	                                        <th scope="col" style="width: 50px;" class="bg-primary">
	                                            <div class="form-check">
	                                                <i class="ri-heart-add-line"></i>
	                                            </div>
	                                        </th>
	                                        <th class="bg-primary">제목</th>
	                                        <th class="bg-primary">공개</th>
	                                        <th class="bg-primary">다운로드</th>
	                                        <th class="bg-primary">작성일</th>
	                                        <th class="bg-primary">상태</th>
	                                        <th class="bg-primary">관리</th>
	                                    </tr>
	                                </thead>
	                                <tbody class="list form-check-all">
	                                    <tr>
	                                        <th scope="row">
	                                            <div class="form-check">
	                                                <img src="./image/heart_do.png" alt="" class="avatar-sm rounded-circle like-do" id=""/>
	                                            </div>
	                                        </th>
	                                        <td class="title"><a>자바 스크립트 강의 자바 스크립트 강의 자바 스크립트 강의</a></td>
	                                        <td class="view">전체</td>
	                                        <td class="download">클래스</td>
	                                        <td class="regdate">2023.09.01</td>
	                                        <td class="state"><button class="badge bg-success-subtle text-success text-uppercase"
	                                        					data-bs-toggle="modal" data-bs-target="#zoomInModal"
															data-reason="신고사유">신고대기</button></td>
	                                        <td>
	                                            <div class="d-flex gap-2">
	                                                <div class="edit">
	                                                    <button class="btn btn-sm btn-success edit-item-btn">Edit</button>
	                                                </div>
	                                                <div class="remove">
	                                                    <button class="btn btn-sm btn-danger remove-item-btn">Remove</button>
	                                                </div>
	                                            </div>
	                                        </td>
	                                    </tr>
	                                </tbody>
	                            </table>
	                        </div>
	                    </div>
	                </div>
				</div>
			</div>
            <div class="d-flex justify-content-end mt-3">
	            <div class="row align-items-center mb-4 gy-3">
				    <div class="col-sm-auto ms-auto">
			            <ul class="comm-page pagination mb-0">
			            </ul>
				    </div>
				</div>
	        </div>
		</div>
	</div>
	<div class="hidden">
		<button id="completion-btn"></button>
	</div>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" charset="UTF-8"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
	<script type="text/javascript" src="./js/community.js"></script>
	<script type="text/javascript" src="./js/myBoard.js"></script>
	<script id="page-list-template" type="text/x-handlebars-template">
		{{#each page}}
		<li class="page-item {{state}}">
			<span class="page-link" id="{{id}}">{{{htmlOrText value}}}</span>
		</li>
		{{/each}}
	</script>
	<script id="pilgi-list-template" type="text/x-handlebars-template">
		<tr>
		    <td class="title"><b><a id="{{id}}" class="view-pilgi">{{title}}</a></b></td>
		    <td class="view">{{viewGroup}}</td>
		    <td class="download">{{downloadGroup}}</td>
		    <td class="state">
		    	{{#stateN}}
				{{#viewI}}
		    	<span class="badge badge-outline-info">나만 보기</span>
				{{else}}
		    	<span class="badge bg-success-subtle text-success">게시</span>
		    	{{/viewI}}
		    	{{/stateN}}
		    	{{#stateY}}
		    	<span class="badge badge-outline-danger">삭제</span>
		    	{{/stateY}}
		    	{{#stateP}}
		    	<span class="badge badge-outline-warning">신고 대기</span>
		    	{{/stateP}}
		    	{{#stateD}}
		    	<span class="badge badge-outline-primary" data-bs-toggle="tooltip" data-bs-placement="top" title="신고 사유는 관리자에게 문의하세요.">신고 삭제</span>
		    	{{/stateD}}
		   	</td>
		    <td>
		    	{{#stateY}}
		        <div class="d-flex">
		            <div>
		                <button class="badge text-bg-success pilgi-restore" id="{{id}}">내용 복원</button>
		            </div>
		            <div>
		                <button class="badge text-bg-danger pilgi-delete" id="{{id}}">완전 삭제</button>
		            </div>
		        </div>
			    {{/stateY}}
		    </td>
		</tr>
	</script>
	<script id="jilmun-list-template" type="text/x-handlebars-template">
		<tr>
		    <td class="title"><b><a id="{{id}}" class="view-jilmun">{{title}}</a></b></td>
			<td>
		    	{{#chaetaekY}}
		        <div><i class="ri-check-double-fill text-success"></i></div>
			    {{/chaetaekY}}
		    </td>
		    <td class="state">
		    	{{#stateN}}
		    	<span class="badge bg-success-subtle text-success">게시</span>
		    	{{/stateN}}
		    	{{#stateY}}
		    	<span class="badge badge-outline-danger">삭제</span>
		    	{{/stateY}}
		    	{{#stateP}}
		    	<span class="badge badge-outline-warning">신고 대기</span>
		    	{{/stateP}}
		    	{{#stateD}}
		    	<span class="badge badge-outline-primary" data-bs-toggle="tooltip" data-bs-placement="top" title="신고 사유는 관리자에게 문의하세요.">신고 삭제</span>
		    	{{/stateD}}
		   	</td>
		</tr>
	</script>
	<script id="jayu-list-template" type="text/x-handlebars-template">
		<tr>
		    <td class="title"><b><a id="{{id}}" class="view-jayu">{{title}}</a></b></td>
		    <td class="state">
		    	{{#stateN}}
		    	<span class="badge bg-success-subtle text-success">게시</span>
		    	{{/stateN}}
		    	{{#stateY}}
		    	<span class="badge badge-outline-danger">삭제</span>
		    	{{/stateY}}
		    	{{#stateP}}
		    	<span class="badge badge-outline-warning">신고 대기</span>
		    	{{/stateP}}
		    	{{#stateD}}
		    	<span class="badge badge-outline-primary" data-bs-toggle="tooltip" data-bs-placement="top" title="신고 사유는 관리자에게 문의하세요.">신고 삭제</span>
		    	{{/stateD}}
		   	</td>
		</tr>
	</script>
	<script id="reply-list-template" type="text/x-handlebars-template">
		<tr>
		    <td class="title"><b><a id="{{id}}" class="view-reply">{{title}}</a></b></td>
		    <td class="content">{{content}}</td>
		    <td class="state">
		    	{{#stateN}}
		    	<span class="badge bg-success-subtle text-success">게시</span>
		    	{{/stateN}}
		    	{{#stateY}}
		    	<span class="badge badge-outline-danger">삭제</span>
		    	{{/stateY}}
		    	{{#stateP}}
		    	<span class="badge badge-outline-warning">신고 대기</span>
		    	{{/stateP}}
		    	{{#stateD}}
		    	<span class="badge badge-outline-primary" data-bs-toggle="tooltip" data-bs-placement="top" title="신고 사유는 관리자에게 문의하세요.">신고 삭제</span>
		    	{{/stateD}}
		   	</td>
		</tr>
	</script>
</body>
</html>