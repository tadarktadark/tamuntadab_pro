<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light" data-topbar="light" data-sidebar="light" data-sidebar-size="sm" data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title} | 타문타답</title>
<%@ include file="./shared/_head_css.jsp" %>
<link href="./css/community.css" rel="stylesheet" type="text/css" />
<link href="./css/yeyak.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="./assets/libs/@simonwep/pickr/themes/classic.min.css" /> <!-- 'classic' theme -->
<link rel="stylesheet" href="./assets/libs/@simonwep/pickr/themes/monolith.min.css" /> <!-- 'monolith' theme -->
<link rel="stylesheet" href="./assets/libs/@simonwep/pickr/themes/nano.min.css" /> <!-- 'nano' theme -->
<link rel="stylesheet" href="./assets/libs/@simonwep/pickr/themes/nano.min.css" /> <!-- 'nano' theme -->
<%@ include file="./shared/_logout.jsp" %>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_menu.jsp" %>
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<%@ include file="./shared/_page_title.jsp" %>
					<div class="btn-group mb-1" id="btn-sido">
					    <button id="sido-title" class="btn btn-primary dropdown-toggle sido-title" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">전체(<span></span>)</button>
					    <div class="dropdown-menu dropdownmenu-primary sido-group" data-simplebar data-simplebar-track="primary">
					        <a class="dropdown-item sido-dropdown sido-title click-btn">전체(<span></span>)</a>
						</div>
					</div>
					<div class="row">
					    <div class="col">
	 						<div class="accordion custom-accordionwithicon-plus collapse multi-collapse show mb-1" id="gangeuisilList">'
							</div>
							<ul class="comm-page pagination mb-1">
			            	</ul>
					    </div>
					    <div class="col ps-0">
					        <div class="collapse multi-collapse" id="multiCollapseExample2">
					            <div class="card card-body mb-1" id="multiCollapseExample2-content">
					            	
					            </div>
					        </div>
					    </div>
					</div>
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp" %>
		</div>
	</div>	
	<div class="hidden">
		<button type="button" class="btn btn-primary btn-sm" id="sa-warning"></button>
	</div>
	<script type="text/javascript" src="./assets/libs/@simonwep/pickr/pickr.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="./assets/js/pages/form-pickers.init.js" charset="UTF-8"></script>
	<script src="https://npmcdn.com/flatpickr/dist/flatpickr.min.js" charset="UTF-8"></script>
	<script src="https://npmcdn.com/flatpickr/dist/l10n/ko.js" charset="UTF-8"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
    <script src="./assets/js/pages/form-wizard.init.js" charset="UTF-8"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=99gs7xiokw"></script>
	<script type="text/javascript" src="./assets/js/app.js" charset="UTF-8"></script>
	<script type="text/javascript" src="./js/community.js"></script>
	<script type="text/javascript" src="./js/yeyak.js" charset="UTF-8"></script>
	<script id="page-list-template" type="text/x-handlebars-template">
		{{#each page}}
		<li class="page-item {{state}}">
			<span class="page-link" id="{{id}}">{{{htmlOrText value}}}</span>
		</li>
		{{/each}}
	</script>
	<script id="map-template" type="text/x-handlebars-template">
		<div id="map" style="width:100%;height:400px;"></div>
	</script>
	<script id="form-template" type="text/x-handlebars-template">
		<form id="yeyakForm">
			<input style="display:none;" id="gayeAccountId" name="gayeAccountId" value="${userInfo.userAccountId}">
			<input style="display:none;" id="gayeGagaId" name="gayeGagaId" value="{{gayeGagaId}}">
			<div class="row mb-3">
			    <b class="ri-home-wifi-fill text-primary"> {{gagaName}}</b>
			</div>
			<div class="row mb-3">
			    <div class="col-lg-3">
			        <label for="nameInput" class="form-label"><b>성명</b></label>
			    </div>
			    <div class="col-lg-9">
			        <input type="text" class="form-control" value="${userInfo.userNickname}" disabled="disabled">
			    </div>
			</div>
			<div class="row mb-3">
			    <div class="col-lg-3">
			        <label for="contactNumber" class="form-label"><b>전화번호</b></label>
			    </div>
			    <div class="col-lg-9">
			        <input type="text" class="form-control" id="contactNumber" name="gayePhoneNumber" value="${userInfo.userPhoneNumber}" disabled="disabled">
			    </div>
			</div>
			<div class="row mb-3">
			    <div class="col-lg-3">
			        <label for="dateInput" class="form-label"><b>예약 날짜</b></label>
			    </div>
			    <div class="col-lg-9">
			        <input type="text" class="form-control flatpickr" id="dateInput" name="gayeYeyakDate" placeholder="날짜를 선택해주세요.">
			    </div>
			</div>
			<div class="row mb-3">
			    <div class="col-lg-3">
			        <label for="timeInput" class="form-label"><b>예약 시간</b></label>
			    </div>
			    <div class="col-lg-9" id="timeInput">
			        <input type="text" class="form-control flatpickr" placeholder="날짜 선택시 버튼이 생성됩니다." disabled="disabled">
			    </div>
			</div>
			<div class="row mb-3">
			    <div class="col-lg-3">
			        <label for="endInput" class="form-label"><b>종료 시간</b></label>
			    </div>
			    <div class="col-lg-9">
			        <input type="text" class="form-control" id="endInput" disabled="disabled" placeholder="종료 시간을 확인하세요!">
			    </div>
			</div>
			<div class="row mb-3">
			    <div class="col-lg-3">
			        <label for="classSelect" class="form-label"><b>클래스</b>(선택)</label>
			    </div>
			    <div class="col-lg-9">
			<select id="classSelect" name="gayeClasId" class="form-select mb-3" aria-label="Default select example">
			</select>
			    </div>
			</div>
			<div class="row mb-3">
			    <div class="col-lg-3">
			        <label class="form-label"><b>결제 방법</b></label>
			    </div>
			    <div class="col-lg-9">
			        <div class="form-check form-switch">
			    <input class="form-check-input" type="checkbox" role="switch" id="payment" name="gayeGyeoljeType" disabled="disabled">
			    <label class="form-check-label" for="payment">클래스원과 함께 결제</label>
			</div>
			    </div>
			</div>
			<div class="row mb-3">
			    <div class="col-lg-3">
			        <label class="form-label"><b>결제 금액</b></label>
			    </div>
			    <div class="col-lg-9">        	
			        <input type="hidden" class="form-control" id="gyeolje-hour" value="{{gyeoljeHour}}" disabled="disabled">
			        <input type="text" class="form-control" name="gyeoGeumaek" id="gyeolje-won" disabled="disabled" placeholder="결제 금액을 확인하세요!">
			    </div>
			</div>
			<div class="text-end">
			    <button id="yeyakSubmit" class="btn btn-primary"><b>예약하기</b></button>
			</div>
		</form>
	</script>
	<%@ include file="./shared/_vender_scripts.jsp" %>
</body>
</html>