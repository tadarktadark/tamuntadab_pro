<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title}|타문타답</title>
<%@ include file="./shared/_vender_scripts.jsp"%>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
	charset="UTF-8"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery.qrcode@1.0.3/jquery.qrcode.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<%@ include file="./shared/_head_css.jsp"%>
<script type="text/javascript" src="./js/instrDetail.js"></script>
<style type="text/css">
.badge-label a {
	text-decoration: none;
	color: inherit;
}

.modal-dialog {
    max-width: 250px;
}
</style>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp"%>
		<%@ include file="./shared/_sidebar.jsp"%>

		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<%@ include file="./shared/_page_title.jsp"%>
				</div>

				<div style="width: 50%; margin: auto;">
					<div class="row g-0 bg-body-secondary position-relative">
						<div class="col-md-3 mb-md-0 p-md-4">
							<img
								src="${not empty userInfo? simpleVo.userProfileVo[0].userProfileFile : './assets/images/users/user-dummy-img.jpg'}"
								alt="" data-bs-toggle="${empty userInfo? 'tooltip' : ''}"
								title="${empty userInfo? '로그인 후 볼 수 있습니다.' : ''}" class="w-100"
								alt="...">
						</div>
						<div class="col-md-6 p-4 ps-md-0">
							<div class="d-flex align-items-center">
							    <h5 class="mt-0">${simpleVo.userProfileVo[0].userNickname}</h5>
							    <div class="flex-shrink-0" style="margin-left: 10px;">
							        <img src="./image/heart_do.png" alt="" class="avatar-sm rounded-circle like-do"/>
							    </div>
							</div>
						
							<%-- <h5 class="mt-0">${simpleVo.userProfileVo[0].userNickname}</h5>
							<div class="flex-shrink-0">
					        	<img src="./image/heart_do.png" alt="" class="avatar-sm rounded-circle like-do"/>
					    	</div> --%>
							<p></p>
							<p>${simpleVo.inprIntro}</p>
							가능한 과목&nbsp;
							<c:forEach var="subject" items="${simpleVo.subjectsTitle}">
								<span class="badge text-bg-info">
								${subject}</span>
							</c:forEach>
							<p></p>
							<c:if test="${not empty simpleVo.inprSiteYoutube}">
							<span class="badge badge-label bg-secondary"><i
								class="mdi mdi-circle-medium"></i><a
								href="${simpleVo.inprSiteYoutube}">YOUTUBE</a></span> 
							</c:if>
							<c:if test="${not empty simpleVo.inprSiteWeb}">	
							<span
								class="badge badge-label bg-success"><i
								class="mdi mdi-circle-medium"></i><a
								href="${simpleVo.inprSiteWeb}">WEB SITE</a></span> 
							</c:if>
							<c:if test="${not empty simpleVo.inprSiteMobile}">	
							<span
								class="badge badge-label bg-warning"><i
								class="mdi mdi-circle-medium"></i><a href="#"
								data-bs-toggle="modal" data-bs-target="#qrModal">MOBILE SITE</a></span>
							</c:if>
						</div>
					</div>

					<ul class="nav nav-tabs nav-border-top nav-justified">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="#">Active</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
						<li class="nav-item"><a class="nav-link disabled"
							aria-disabled="true">Disabled</a></li>
					</ul>
				</div>
				<!-- Modal -->
				<div class="modal fade flip" id="qrModal" tabindex="-1"
					aria-labelledby="qrModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-flip">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="qrModalLabel">모바일 사이트 QR</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
							</div>
							<div id='qrcode' class='modal-body'>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp"%>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function () {
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl, { delay: { "show": 100, "hide": 100 } })
    })
})
</script>
</html>