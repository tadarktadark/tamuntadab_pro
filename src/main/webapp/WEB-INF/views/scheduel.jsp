<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="card-body">
		<div class="card mb-0">
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
           <div class="d-flex justify-content-end mt-3">
            <div class="row align-items-center mb-4 gy-3">
			    <div class="col-sm-auto ms-auto">
		            <ul class="comm-page pagination mb-0">
		            </ul>
			    </div>
			</div>
        </div>
	</div>
</body>
</html>