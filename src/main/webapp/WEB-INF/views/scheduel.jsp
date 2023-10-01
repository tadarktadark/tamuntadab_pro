<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/community.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="card-body">
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
		    	{{#stateD}}
		    	<span class="badge bg-warning-subtle text-warning pay-btn">대기</span>
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
</body>
</html>