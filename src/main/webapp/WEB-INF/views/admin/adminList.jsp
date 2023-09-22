<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="vertical" data-topbar="light"
    data-sidebar="light" data-sidebar-size="lg" data-sidebar-image="none"
    data-preloader="disable" data-layout-style="default"
    data-bs-theme="light" data-layout-width="fluid">
<head>
    <meta charset="UTF-8">
    <title>타문타답 | 우리가 만드는 커리큘럼</title>
    <link href="../assets/libs/sweetalert2/sweetalert2.min.css"
        rel="stylesheet" type="text/css" />
    <link href="../assets/libs/jsvectormap/css/jsvectormap.min.css"
        rel="stylesheet" type="text/css" />
    <link href="../assets/libs/swiper/swiper-bundle.min.css"
        rel="stylesheet" type="text/css" />
    <%@ include file="./shared/_head_css.jsp"%>
    <%@ include file="./shared/_vender_scripts.jsp"%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
</head>
<body>
    <div id="layout-wrapper">
        <%@ include file="./shared/_topbar.jsp"%>
        <%@ include file="./shared/_sidebar.jsp"%>
        <div class="vertical-overlay"></div>
        <div class="main-content">
            <div class="page-content">
                <%@ include file="./shared/_page_title.jsp"%>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-xl-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="table-responsive table-card">
<!--                                     <script id="entry-template" type="text/x-handlebars-template"> -->
                                        <table class="table table-hover table-nowrap align-middle mb-0">
                                            <thead>
                                                <tr class="text-muted text-uppercase">
                                                    <th style="width: 50px;">
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" id="checkAll" value="option">
                                                        </div>
                                                    </th>
                                                    <th scope="col">ID</th>
                                                    <th scope="col">이름</th>
                                                    <th scope="col">최근 접속일</th>
                                                    <th scope="col">권한</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                {{#admin}}
                                                <tr>
                                                    <td>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" id="check1" value="option">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <a href="#javascript: void(0);" class="text-body align-middle fw-medium">{{adprId}}</a>
                                                    </td>
                                                    <td>{{adprName}}</td>
                                                    <td>{{adprLastAccess}}</td>
                                                    <td>
                                                        {{#if (eq adprAuth "T")}}
                                                        <span class="badge bg-success-subtle text-success p-2">총관리자</span>
                                                        {{else}}
                                                        <span class="badge bg-success-subtle text-success p-2">관리자</span>
                                                        {{/if}}
                                                    </td>
                                                    <td>
                                                        <div class="dropdown">
                                                            <button class="btn btn-soft-primary btn-sm dropdown" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                                <i class="bx bx-dots-horizontal-rounded align-middle fs-18"></i>
                                                            </button>
                                                            <ul class="dropdown-menu dropdown-menu-end">
                                                                <li>
                                                                    <button class="dropdown-item" href="javascript:void(0);">
                                                                        <i class="las la-pen fs-18 align-middle me-2 text-muted"></i>
                                                                        수정
                                                                    </button>
                                                                </li>
                                                                <li class="dropdown-divider"></li>
                                                                <li>
                                                                    <a class="dropdown-item remove-item-btn" href="#">
                                                                        <i class="las la-trash-alt fs-18 align-middle me-2 text-muted"></i>
                                                                        삭제
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </td>
                                                </tr>
                                                {{/admin}}
                                            </tbody><!-- end tbody -->
                                        </table><!-- end table -->
                                        </script>
                                    </div><!-- end table responsive -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
	Handlebars.registerHelper({
		  auth: function(v1, options) {
		    if (v1 === "T") {
		      return "총관리자";
		    }
		    return "관리자";
		  }
		});
</script>
<script>
$(document).ready(function(){
    // Ajax 요청을 보냅니다.
    $.ajax({
        url: './searchAdminList.do', // 데이터를 받아올 엔드포인트 URL을 여기에 입력합니다.
        type: 'GET', // GET 요청을 보냅니다. 필요에 따라 변경할 수 있습니다.
        dataType: 'json',
        success: function(data) {
            console.log(data);
            // Ajax 요청이 성공하면 data에 응답 데이터가 들어옵니다.
            // 이제 Handlebars로 템플릿을 렌더링합니다.
            var source = $("#entry-template").html(); 
            var template = Handlebars.compile(source); 
            var html = template(data);
            $('.table-responsive').append(html);
        },
        error: function(error) {
            console.error('Ajax 요청 실패:', error);
        }
    });
});
</script>


</html>
