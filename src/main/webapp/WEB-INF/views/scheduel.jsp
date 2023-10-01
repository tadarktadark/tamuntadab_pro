<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/community.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	#initial:hover {
	   /* 원래의 스타일로 복원 */
	   background-color: initial;
	   color: initial;
	   cursor: default;
	}
</style>
</head>
<body>
	<div class="card-body">
		<div class="card mb-3"><b>※ 예약 취소는 예약일 1일 전까지만 가능합니다. 결제 상태에 대한 자세한 내용은 상태 버튼을 클릭하세요.</b></div>
		<div class="card mb-0">
	        <div class="table-responsive table-card">
	            <table class="table align-middle table-nowrap" id="customerTable">
	            	<col width="25%">
	            	<col width="25%">
	            	<col width="30%">
	            	<col width="10%">
	            	<col width="10%">                            	
	                <thead class="table-primary">
	                    <tr>
	                        <th class="bg-primary">장소</th>
	                        <th class="bg-primary">일정</th>
	                        <th class="bg-primary">클래스</th>
	                        <th class="bg-primary">상태</th>
	                        <th class="bg-primary">관리</th>
	                    </tr>
	                </thead>
	                <tbody id="yeyak-tbody" class="list form-check-all">
	                </tbody>
	            </table>
	        </div>
		</div>
           <div class="d-flex justify-content-end mt-3">
            <div class="row align-items-center mb-4 gy-3">
			    <div class="col-sm-auto ms-auto">
		            <ul class="yeyak-page pagination mb-0">
		            </ul>
			    </div>
			</div>
        </div>
	</div>
	<!-- Default Modals -->
	<button id="show-modal" data-bs-toggle="modal" data-bs-target="#myModal" style="display: none;"></button>
	<div id="myModal" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
		<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary pb-3">
				<h5 class="modal-title text-white" id="myModalLabel">결제 상태</h5>
			</div>
			<div class="modal-body">
				<div class="row">
	                <ul id="gyeolje-list" class="list list-group list-group-flush mb-0">
	                </ul>
				</div>
				<!-- end row -->
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-bs-dismiss="modal">닫기</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
	<script type="text/javascript" src="./js/myYeyak.js"></script>
	<script id="yeyak-page-template" type="text/x-handlebars-template">
		{{#each page}}
		<li class="page-item {{state}}">
			<span class="yeyak-link page-link" id="{{id}}">{{{htmlOrText value}}}</span>
		</li>
		{{/each}}
	</script>
	<script id="yeyak-list-template" type="text/x-handlebars-template">
		<tr>
		    <td>{{name}}</td>
		    <td>{{date}}</td>
		    <td>{{class}}</td>
		    <td>
		    	{{#stateY}}
		    	<span class="badge bg-success-subtle text-success pay-btn">완료</span>
		    	{{/stateY}}
		    	{{#stateN}}
		    	<span class="badge bg-danger-subtle text-danger pay-btn">취소</span>
		    	{{/stateN}}
		    	{{#stateA}}
		    	<span class="badge bg-warning-subtle text-warning pay-btn">자동 취소</span>
		    	{{/stateA}}
		    	{{#stateD}}
		    	<span class="badge bg-info-subtle text-info pay-btn">대기</span>
		    	{{/stateD}}
		   	</td>
		    <td>
				<input type="hidden" class="gayeId" value="{{gayeId}}">
				<input type="hidden" class="gayeGagaId" value="{{gayeGagaId}}">
				<input type="hidden" class="gayeYeyakDate" value="{{gayeYeyakDate}}">
				<input type="hidden" class="gayeStartTime" value="{{gayeStartTime}}">
				<input type="hidden" class="gayeHours" value="{{gayeHours}}">
				{{#stateN}}
				{{else}}
				<span class="badge text-bg-primary cancel-btn">예약 취소</span>
				{{/stateN}}
			</td>
		</tr>
	</script>
	<script id="modal-template" type="text/x-handlebars-template">
		<li class="list-group-item">
			<div class="d-flex align-items-center pagi-list">	
				<div class="flex-grow-1 overflow-hidden">
					<h5 class="fs-13 mb-1">{{nickname}}</h5>
					{{#stateP}}
					<span class="badge bg-success-subtle text-success">완료</span>
					{{/stateP}}
					{{#stateW}}
					<span class="badge bg-info-subtle text-info">대기</span>
					{{/stateW}}
					{{#stateC}}
					<span class="badge bg-warning-subtle text-warning">취소</span>
					{{/stateC}}
					{{#stateR}}
					<span class="badge bg-primary-subtle text-primary">환불</span>
					{{/stateR}}
					 <span id="geumaek" class="fs-12 text-muted mb-0">{{geumaek}} 원</span>
				</div>
				<div class="flex-shrink-0 ms-2">
					<div>
						<input type="hidden" id="accountId" value="{{accountId}}">
						<input type="hidden" id="daesangId" value="{{daesangId}}">
						{{#stateW}}
						{{#myInfo}}
				    	<div id="initial" class="btn btn-sm btn-outline-primary">결제 필요</div>
						{{else}}
				    	<button type="button" class="send-btn btn btn-sm btn-primary"><i class="bx bx-bell"></i> 결제요청</button>
						{{/myInfo}}
						{{/stateW}}
				    </div>
				</div>
			</div>
		</li>
		<!-- end list item -->
	</script>
</body>
</html>