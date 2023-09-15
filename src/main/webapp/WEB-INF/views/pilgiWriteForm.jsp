<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light" data-topbar="light" data-sidebar="light" data-sidebar-size="sm" data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title} | 타문타답</title>
<%@ include file="./shared/_head_css.jsp" %>
<link href="./css/ckeditor.css" rel="stylesheet" type="text/css" />
<link href="./css/pilgiWriteForm.css" rel="stylesheet" type="text/css" />
</head>
<body class="twocolumn-panel" data-editor="ClassicEditor" data-collaboration="false" data-revision-history="false">
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp" %>
		<%@ include file="./shared/_sidebar.jsp" %>
		
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<%@ include file="./shared/_page_title.jsp" %>
					<div class="row">
					    <div class="col-lg-12">
					        <div class="card">
					            <div class="card-header align-items-center d-flex">
					                <h4 class="card-title mb-0">Ckeditor Classic Editor</h4>
					            </div><!-- end card header -->
					
					            <div class="card-body">
					                <p class="text-muted">Use <code>ckeditor-classic</code> class to set ckeditor classic editor.</p>
					                <div id="ckeditor" class="ckeditor-classic"></div>
					            </div><!-- end card-body -->
					        </div><!-- end card -->
					    </div>
					    <!-- end col -->
					</div>
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp" %>
		</div>
	</div>	
	<%@ include file="./shared/_vender_scripts.jsp" %>
	<script src="./assets/js/app.js"></script>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js" charset="UTF-8"></script>
	<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
	<script src="./js/pilgiInsert.js" charset="UTF-8"></script>
	<script id="board-list-template" type="text/x-handlebars-template"></script>
	<script type="text/javascript" src="./libs/ckeditor5-39.0.2/build/ckeditor.js"></script>
	<script type="text/javascript" src="./js/ckeditor.js"></script>
	<script type="text/javascript" src="./js/pilgiInsert.js"></script>
</body>
</html>