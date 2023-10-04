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
	                                <thead class="table-primary">
	                                    <tr>
	                                        <th scope="col" style="width: 50px;" class="bg-primary">
	                                            상태
	                                        </th>
	                                        <th class="bg-primary">클래스명</th>
			                                <th class="bg-primary">진행 지역</th>
			                                <th class="bg-primary">수업 날짜</th>
			                                <th class="bg-primary">강사</th>
	                                    </tr>
	                                </thead>
	                                <tbody class="list form-check-all" id="pClassTbody">
	                                
	                                </tbody>
	                            </table>
	                        </div>
   	                        <div class="d-flex justify-content-end mt-3">
	                            <div class="pagination-wrap hstack gap-2" id="pPaging">
	                                
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
	                            	<col width="250px">
	                            	<col width="100px">
	                            	<col width="100px">
	                            	<col width="100px">
	                            	<col width="150px">	                            	
	                                <thead class="table-primary">
	                                    <tr>
	                                        <th scope="col" style="width: 50px;" class="bg-primary">
	                                            상태
	                                        </th>
	                                        <th class="bg-primary">클래스명</th>
			                                <th class="bg-primary">잔행 지역</th>
			                                <th class="bg-primary">수업 날짜</th> 
			                                <th class="bg-primary">강사</th>
			                                <th class="bg-primary">작성하기</th>
	                                    </tr>
	                                </thead>
	                                <tbody class="list form-check-all" id="eClassTbody">
	                                    
	                                </tbody>
	                            </table>
	                        </div>
   	                        <div class="d-flex justify-content-end mt-3">
	                            <div class="pagination-wrap hstack gap-2" id="ePaging">
	                                
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
	<script type="text/javascript" src="./js/myPageClass.js"></script>
</body>
</html>