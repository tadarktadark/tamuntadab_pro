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
<link href="./css/yeyak.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="./assets/libs/@simonwep/pickr/themes/classic.min.css" /> <!-- 'classic' theme -->
<link rel="stylesheet" href="./assets/libs/@simonwep/pickr/themes/monolith.min.css" /> <!-- 'monolith' theme -->
<link rel="stylesheet" href="./assets/libs/@simonwep/pickr/themes/nano.min.css" /> <!-- 'nano' theme -->
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp" %>
		<%@ include file="./shared/_sidebar.jsp" %>
	
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<%@ include file="./shared/_page_title.jsp" %>
					<div class="btn-group" id="btn-sido">
					    <button id="sido-title" class="btn btn-primary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					    </button>
					    <div class="dropdown-menu dropdownmenu-primary sido-group" data-simplebar data-simplebar-track="primary">
					        <a class="dropdown-item sido-dropdown click-btn"></a>
							</div>
					</div>
					<br>
					<br>
					<div class="row">
					    <div class="col">
	 						<div class="accordion custom-accordionwithicon-plus collapse multi-collapse show" id="accordionWithplusicon">
							</div>
							<br>
							<ul id="pagination" class="pagination pagination-rounded justify-content-center">
							</ul>
					    </div>
					    <div class="col">
					        <div class="collapse multi-collapse" id="multiCollapseExample2">
					            <div class="card card-body mb-0" id="multiCollapseExample2-content">
					            <div>hi</div>
					            </div>
					        </div>
					    </div>
					</div>
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp" %>
		</div>
	</div>	
	<div id="yeyak-form" style="display:none;">
		<%@ include file="./yeyakForm.jsp" %>
	</div>
	<%@ include file="./shared/_vender_scripts.jsp" %>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="./assets/libs/@simonwep/pickr/pickr.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="./assets/js/pages/form-pickers.init.js" charset="UTF-8"></script>
	<script src="https://npmcdn.com/flatpickr/dist/flatpickr.min.js" charset="UTF-8"></script>
	<script src="https://npmcdn.com/flatpickr/dist/l10n/ko.js" charset="UTF-8"></script>
    <script src="./assets/js/pages/form-wizard.init.js" charset="UTF-8"></script>
	<script type="text/javascript" src="./assets/js/app.js" charset="UTF-8"></script>
	<script type="text/javascript" src="./js/yeyak.js" charset="UTF-8"></script>
</body>
</html>