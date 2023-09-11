<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light" data-topbar="light" data-sidebar="light" data-sidebar-size="sm" data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title} | 타문타답</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
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
				</div>
				
				<div class="card">
					<div class="card-header" style="text-align: center; font-weight: bold;">
                       	<h4 class="card-title mb-0">원하는 클래스의 필수 정보를 입력해주세요</h4>
                    </div>
					<div class="card-body">
						<form action="./classWrite.do" method="post">
							
							<div class="row mb-4">
								<div class="col-xxl-3 col-md-6" style="width: 700px;">
									<div>
										<label for="placeholderInput" class="form-label">클래스 이름</label> 
										<input type="text" name="classTitle" class="form-control" id="classTitle" placeholder="클래스 제목을 입력하주세요">
									</div>
								</div>
							</div>
							
							<div class="row mb-4">
								<div style="width: 700px;">
									<div class="col-lg-3">
										<label for="inprSubjects" class="form-label">수강할 과목</label>
									</div>
									<div id="selectedSubjects"
										class="col-lg-9 choices__list choices__list--multiple"></div>
									<div class="choices__inner choices hstack gap-3"
										data-type="multiple">
										<input type="search" id="subjCode" name="subjCode"
											class="choices__input choices__input--cloned">
									</div>
								</div>
							</div>
							
							<div class="row mb-4">
								<div class="col-12" style="width: 700px;">
									<label for="inprIntro" class="form-label">지역</label>
									<div class="input-group">
										<input type="text" name="location" class="form-control" id="location"
											placeholder="'동' 을 입력해 주세요">
										<div class="input-group-text">
											<i class="bx bx-search-alt-2"></i>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row mb-4">
								<div class="col-xxl-3 col-md-6" style="width: 300px; margin-right: 50px;">
	                            	<div>
	                                	<label for="exampleInputdate" class="form-label">수업 날짜</label>
	                                    <input type="date" name="clasSueopNaljja" class="form-control" id="clasSueopNaljja">
	                                <div data-lastpass-icon-root="true" style="position: relative !important; height: 0px !important; width: 0px !important; float: left !important;"></div></div>
	                            </div>
	                            <div class="col-xxl-3 col-md-6" style="width: 300px; margin-right: 50px;">
	                            	<label for="exampleInputdate" class="form-label">희망 인원</label>
	                            	<div class="input-group">
									    <select class="form-select" name="clasHuimangInwon" id="clasHuimangInwon">
									        <option selected>인원</option>
									        	<%for(int i=1; i<=8; i++){
									        		%>
									        		<option value="<%=i%>"><%=i%></option>
									        		<%
									        	}%>
									        </select>
									    <label class="input-group-text" for="inputGroupSelect02">명</label>
									</div>
	                            </div>
                            </div>
                            
                            <div class="row mb-4">
								<div style="width: 700px;">
									<label for="inprIntro" class="form-label">클래스 설명</label>
									<textarea class="form-control" name="classContent" id="classContent" rows="3"
										placeholder="생성할 클래스에 대한 자세한 설명을 적어주세요"></textarea>
								</div>
							</div>
                            
							<br>
							<br>
							<div class="row mb-4" style="width: 700px;">
								<div class="card-header" style="text-align: center; font-weight: bold;">
			                       	<h4 class="card-title mb-0">아래는 선택 입력사항입니다</h4>
			                    </div>
		                    </div>
		                   
		                    <div class="row mb-4">
		                    	<div class="col-xxl-3 col-md-6" style="width: 300px; margin-right: 50px;">
	                            	<label for="inprIntro" class="form-label" style="margin-top: 15px;">성별 제한</label>
			                   		<div class="input-group">
									    <div class="input-group">
										    <select class="form-select" name="clasSeongbyeolJehan" id="clasSeongbyeolJehan" >
										        <option selected>선택하세요</option>
										        <option value="1">성별무관</option>
										        <option value="2">남성만</option>
										        <option value="3">여성만</option>
										    </select>
										</div>
									</div>
								</div>
								<div class="col-xxl-3 col-md-6" style="width: 350px;">
								    <label for="inprIntro" class="form-label" style="margin-top: 15px;">연령 제한</label>
								    <div style="display: flex; justify-content: space-between;">
								        <div style="width: 160px; margin-right: 15px;">
								            <input type="text" name="minAge" class="form-control" id="minAge" placeholder="최소">
								        </div>~
								        <div style="width: 160px; margin-left: 15px;">
								            <input type="text" name="maxAge" class="form-control" id="maxAge" placeholder="최대">
								        </div>
								    </div>
								</div>
							</div>
							
							
							
							<div class="row mb-4">
								<label for="exampleInputdate" class="form-label">희망 수강료</label>
								<div class="col-xxl-3 col-md-6" style="width: 300px; margin-right: 20px;">
	                            	<input type="text" name="clasChoisoSugangnyo" class="form-control" id="clasChoisoSugangnyo" placeholder="최소 (1,000원 단위로 입력해주세요)">
	                            </div>~
	                            <div class="col-xxl-3 col-md-6" style="width: 300px; margin-left:20px; margin-right: 50px;">
	                            	<input type="text" name="clasChoidaeSugangnyo" class="form-control" id="clasChoidaeSugangnyo" placeholder="최대 (1,000원 단위로 입력해주세요)">
	                            </div>
                            </div>
							
							<div class="row mb-4" style="width: 700px;">
								<div class="text-end">
									<button type="submit" class="btn btn-primary">개설하기</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
	</div>	
	<%@ include file="./shared/_footer.jsp" %>
	<%@ include file="./shared/_vender_scripts.jsp" %>
	<script type="text/javascript" src="./js/classWrite.js"></script>
	<script type="text/javascript">
	$(function() {
	    var guAndDong = [];

	    // JSON 파일 로드
	    $.getJSON('./json/seoulsi.json', function(data) {
	        $.each(data, function(key, val) {
	            var fullAddress ='서울특별시 '+ val.gu + ' ' + val.dong;
	            guAndDong.push(fullAddress);
	        });

	        $('#location').autocomplete({
	            source: guAndDong,
	            minLength: 2,
	            select: function(event, ui) {
	                event.preventDefault();
	                $(this).val(ui.item.value);
	            },
	            focus: function(event) { 
	                event.preventDefault(); 
	            }
	        }).autocomplete('instance')._renderMenu = function(ul, items) {
	            var self = this;
	            $.each(items.slice(0, 5), function(index, item) {
	                self._renderItemData(ul, item);
	             });
	         };
	     });
	});
	</script>
</body>
	<style type="text/css">
			body {
				padding: 30px;
			}
			.card-body {
				display: flex;
				justify-content: center;
				align-items: center;
				flex-direction: column;
			}
			.ui-autocomplete {
				background-color: #f9f9f9;
				max-height: 200px;
				max-width: 700px;
				overflow-y: auto;
				overflow-x: hidden;
				overflow-y: auto;
			}
			
			.ui-autocomplete li {
				list-style-type: none;
			}
			
			
	</style>
</html>