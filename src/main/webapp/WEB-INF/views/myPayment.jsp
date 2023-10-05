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
				<a class="nav-link active" data-bs-toggle="tab" href="#classPay" role="tab">클래스 결제 목록</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" data-bs-toggle="tab" href="#rentPay" role="tab">강의실 결제 목록</a>
			</li>
		</ul>
		<div class="card mb-0">
			<div class="tab-content text-muted">
				<div class="tab-pane active" id="classPay" role="tabpanel">
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
	                                            결제 상태
	                                        </th>
	                                        <th class="bg-primary">클래스명</th>
			                                <th class="bg-primary">결제 금액</th>
			                                <th class="bg-primary">결제 완료일</th>
			                                <th class="bg-primary"></th>
	                                    </tr>
	                                </thead>
	                                <tbody class="list form-check-all" id="classPayTbody">
	                                	
	                                </tbody>
	                            </table>
	                        </div>
   	                        <div class="d-flex justify-content-end mt-3">
	                            <div class="pagination-wrap hstack gap-2" id="classPayPaging">
	                                
	                            </div>
	                        </div>
	                    </div>
	                </div>
				</div>
				<div class="tab-pane" id="rentPay" role="tabpanel">
					<div class="card-body pt-3">
	                    <div id="customerList">
	                        <div class="table-responsive table-card">
	                            <table class="table align-middle table-nowrap" id="customerTable">
	                            	<col width="50px">
	                            	<col width="250px">
	                            	<col width="100px">
	                            	<col width="150px">
	                            	<col width="100px">
	                            	<col width="100px">	                          	
	                                <thead class="table-primary">
	                                    <tr>
	                                        <th scope="col" style="width: 50px;" class="bg-primary">
	                                            상태
	                                        </th>
	                                        <th class="bg-primary">예약한 강의실</th>
			                                <th class="bg-primary">예약시간</th>
			                                <th class="bg-primary">예약일시</th> 
			                                <th class="bg-primary">금액</th>
			                                <th class="bg-primary"></th>
	                                    </tr>
	                                </thead>
	                                <tbody class="list form-check-all" id="rentPayTbody">
	                                    
	                                </tbody>
	                            </table>
	                        </div>
   	                        <div class="d-flex justify-content-end mt-3">
	                            <div class="pagination-wrap hstack gap-2" id="rentPayPaging">
	                            </div>
	                        </div>
	                    </div>
	                </div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" charset="UTF-8"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
	<script type="text/javascript" src="./js/myPayment.js"></script>
</body>
</html>