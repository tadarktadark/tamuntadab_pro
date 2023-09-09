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
					        전체(${sidoCount})
					    </button>
					    <div class="dropdown-menu dropdownmenu-primary sido-group" data-simplebar data-simplebar-track="primary">
					        <a class="dropdown-item sido-dropdown click-btn">전체(${sidoCount})</a>
					 		<c:forEach items="${sido}" var="s">
						        <a class="dropdown-item sido-dropdown click-btn">${s}</a>
					 		</c:forEach>   	
					    </div>
					</div>
					<br>
					<br>
					<div class="row">
					    <div class="col">
	 						<div class="accordion custom-accordionwithicon-plus collapse multi-collapse show" id="accordionWithplusicon">
								<c:forEach items="${comm}" var="c" varStatus="vs">						
								    <div class="accordion-item">
								        <h2 class="accordion-header" id="accordionwithplusExample${vs.index+1}">
								            <button class="accordion-button collapsed show-gangeuisilDetails" type="button" 
								            		data-bs-toggle="collapse" data-bs-target="#accor_plusExamplecollapse${vs.index+1}" 
								            		aria-expanded="true" aria-controls="accor_plusExamplecollapse${vs.index+1}"
								            		value="${c.gacoId}">
								                ${c.gacoName}
								            </button>
								        </h2>
								        <div id="accor_plusExamplecollapse${vs.index+1}" class="accordion-collapse collapse" aria-labelledby="accordionwithplusExample${vs.index+1}" data-bs-parent="#accordionWithplusicon">
								            <div class="accordion-body">
								            	<table class="table table-nowrap">
								            		<thead>
												        <tr>
												            <th scope="col" colspan="4"><i class="ri-map-pin-line"></i>&nbsp;${c.gacoJuso}</th>
												        </tr>
												        <tr>
												            <th scope="col"><i class="ri-haze-line"></i>&nbsp;오픈</th>
												            <td scope="row">${fn:substring(c.gacoOpen,0,2)}:${fn:substring(c.gacoOpen,2,4)}</td>
												            <th scope="col"><i class="ri-moon-clear-line"></i>&nbsp;마감</th>
												            <td scope="row">${fn:substring(c.gacoClose,0,2)}:${fn:substring(c.gacoClose,2,4)}</td>
												        </tr>
												    </thead>
												    <tbody id="${c.gacoId}">												    
												    </tbody>
												</table>
								            </div>
								        </div>
								    </div>
								</c:forEach>
							</div>
							<br>
							<ul class="pagination pagination-rounded justify-content-center">
							    <li class="page-item disabled">
							        <a href="#" class="page-link"><i class="mdi mdi-chevron-left"></i></a>
							    </li>
							    <li class="page-item">
							        <a href="#" class="page-link">1</a>
							    </li>
							    <li class="page-item active">
							        <a href="#" class="page-link">2</a>
							    </li>
							    <li class="page-item">
							        <a href="#" class="page-link">3</a>
							    </li>
							    <li class="page-item">
							        <a href="#" class="page-link">4</a>
							    </li>
							    <li class="page-item">
							        <a href="#" class="page-link">5</a>
							    </li>
							    <li class="page-item">
							        <a href="#" class="page-link"><i class="mdi mdi-chevron-right"></i></a>
							    </li>
							</ul>
					    </div>
					    <div class="col">
					        <div class="collapse multi-collapse show" id="multiCollapseExample2">
					            <div class="card card-body mb-0">
					                Some placeholder content for the second collapse component of this multi-collapse example. This panel is hidden by default but revealed when the user activates the relevant trigger.
					            </div>
					        </div>
					    </div>
					</div>
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp" %>
		</div>
	</div>	
	<%@ include file="./shared/_vender_scripts.jsp" %>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<script type="text/javascript" src="./js/yeyak.js"></script>
</body>
</html>