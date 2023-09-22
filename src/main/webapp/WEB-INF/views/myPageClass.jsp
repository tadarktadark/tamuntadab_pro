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
</head>
	<div class="card-body">
		<!-- Nav tabs -->
		<ul class="nav nav-pills nav-customs nav-danger" role="tablist">
			<li class="nav-item">
				<a class="nav-link active" data-bs-toggle="tab" href="#participating" role="tab">참여중</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" data-bs-toggle="tab" href="#ended" role="tab">종료</a>
			</li>
		</ul>
		<div class="card mb-0">
			<div class="tab-content text-muted">
				<div class="tab-pane active" id="participating" role="tabpanel">
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
	                                            상태
	                                        </th>
	                                        <th class="bg-primary">제목</th>
			                                <th class="bg-primary">공개</th>
			                                <th class="bg-primary">다운로드</th> 
			                                <th class="bg-primary">조횟수</th>
			                                <th class="bg-primary">좋아요</th>
			                                <th class="bg-primary">댓글</th>
	                                        <th class="bg-primary">작성일</th>
	                                        <th class="bg-primary">상태</th>
	                                        <c:if test="${board == 'pilgi'}">
		                                        <th class="bg-primary"></th>
	                                        </c:if>
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
	                                                    <button class="btn btn-sm btn-success edit-item-btn" data-bs-toggle="modal" data-bs-target="#showModal">Edit</button>
	                                                </div>
	                                                <div class="remove">
	                                                    <button class="btn btn-sm btn-danger remove-item-btn" data-bs-toggle="modal" data-bs-target="#deleteRecordModal">Remove</button>
	                                                </div>
	                                            </div>
	                                        </td>
	                                    </tr>
	                                </tbody>
	                            </table>
	                        </div>
   	                        <div class="d-flex justify-content-end mt-3">
	                            <div class="pagination-wrap hstack gap-2">
	                                <a class="page-item pagination-prev disabled" href="#">
	                                    Previous
	                                </a>
	                                <ul class="pagination listjs-pagination mb-0"></ul>
	                                <a class="page-item pagination-next" href="#">
	                                    Next
	                                </a>
	                            </div>
	                        </div>
	                    </div>
	                </div>
				</div>
				<div class="tab-pane" id="ended" role="tabpanel">
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
	                                            상태
	                                        </th>
	                                        <th class="bg-primary">제목</th>
			                                <th class="bg-primary">공개</th>
			                                <th class="bg-primary">다운로드</th> 
			                                <th class="bg-primary">조횟수</th>
			                                <th class="bg-primary">좋아요</th>
			                                <th class="bg-primary">댓글</th>
	                                        <th class="bg-primary">작성일</th>
	                                        <th class="bg-primary">상태</th>
	                                        <c:if test="${board == 'pilgi'}">
		                                        <th class="bg-primary"></th>
	                                        </c:if>
	                                    </tr>
	                                </thead>
	                                <tbody class="list form-check-all">
	                                    <tr>
	                                        <th scope="row">
	                                            <div class="form-check">
	                                                <img src="./image/heart_do.png" alt="" class="avatar-sm rounded-circle like-do" id=""/>
	                                            </div>
	                                        </th>
	                                        <td class="title" id="endTitle"></td>
	                                        <td class="view"></td>
	                                        <td class="download"></td>
	                                        <td class="regdate"></td>
	                                        <td class="state"><button class="badge bg-success-subtle text-success text-uppercase"
	                                        					data-bs-toggle="modal" data-bs-target="#zoomInModal"
															data-reason="신고사유">신고대기</button></td>
	                                        <td>
	                                            <div class="d-flex gap-2">
	                                                <div class="edit">
	                                                    <button class="btn btn-sm btn-success edit-item-btn" data-bs-toggle="modal" data-bs-target="#showModal">Edit</button>
	                                                </div>
	                                                <div class="remove">
	                                                    <button class="btn btn-sm btn-danger remove-item-btn" data-bs-toggle="modal" data-bs-target="#deleteRecordModal">Remove</button>
	                                                </div>
	                                            </div>
	                                        </td>
	                                    </tr>
	                                </tbody>
	                            </table>
	                        </div>
   	                        <div class="d-flex justify-content-end mt-3">
	                            <div class="pagination-wrap hstack gap-2">
	                                <a class="page-item pagination-prev disabled" href="#">
	                                    Previous
	                                </a>
	                                <ul class="pagination listjs-pagination mb-0"></ul>
	                                <a class="page-item pagination-next" href="#">
	                                    Next
	                                </a>
	                            </div>
	                        </div>
	                    </div>
	                </div>
				</div>
			</div>
		</div>
		<div id="zoomInModal" class="modal fade zoomIn" tabindex="-1"
			aria-labelledby="zoomInModalLabel" aria-hidden="true"
			style="display: none;">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="zoomInModalLabel">반려 사유</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<h5 class="fs-16"></h5>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" charset="UTF-8"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
</body>
</html>