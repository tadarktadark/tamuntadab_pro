<%@page import="com.tdtd.tmtd.vo.PagingVo"%>
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
    <script src="../js/adminList.js"></script>
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
                    <div class="row px-5">
                        <div class="col-xl-12">
                            <div class="card">
                                <div class="card-body">
                                <div class="row g-4 mb-3">
                                           <div class="col-sm-auto">
                                                <div>
                                                    <button class="btn btn-soft-danger" onClick="deleteMultiple()"><i class="ri-delete-bin-2-line"></i></button>
                                                </div>
                                            </div>
                                            <div class="col-sm">
                                                <div class="d-flex justify-content-sm-end">
                                                    <div class="search-box ms-2">
                                                        <input type="text" class="form-control search" placeholder="Search...">
                                                        <i class="ri-search-line search-icon"></i>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    <div class="table-responsive table-card mt-3 mb-1">
                                    <table class="table table-hover table-nowrap align-middle mb-0">
                                           <thead class="table-light">
                                                    <tr>
                                                        <th scope="col" style="width: 50px;">
                                                            <div class="form-check">
                                                                <input class="form-check-input" type="checkbox" id="checkAll" value="option">
                                                            </div>
                                                        </th>
                                                        <th class="sort" data-sort="id">ID</th>
                                                        <th class="sort" data-sort="name">이름</th>
                                                        <th class="sort" data-sort="date">최근 접속일</th>
                                                        <th class="sort" data-sort="auth">관리자 권한</th>
                                                        <th data-sort="action">동작</th>
                                                    </tr>
                                                </thead>
                                            <tbody class="adminList">
                                            </tbody>
                                        </table><!-- end table -->
                                        <div class="d-flex justify-content-center mt-2">
										<div class="pagination-wrap hstack gap-2">
											<ul class="pagination listjs-pagination mb-0">
											</ul>
										</div>
									</div>
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
<script id="adminList-template" type="text/x-handlebars-template">
{{#admin}}
<tr>
    <td>
        <div class="form-check">
            <input class="form-check-input" type="checkbox" id="check1" value="{{adprId}}">
        </div>
    </td>
    <td><a href="#javascript: void(0);" class="text-body align-middle fw-medium">{{adprId}}</a></td>
    <td>{{adprName}}</td>
    <td>{{adprLastAccess}}</td>
    <td>
      {{#eq adprAuth "T"}}
       	 <span class="badge badge-label bg-danger fs-15"><i class="mdi mdi-circle-medium"></i> 총관리자</span>
        {{else eq adprAuth "P"}}
			<span class="badge badge-label bg-info fs-15"><i class="mdi mdi-circle-medium"></i> 결제 관리자</span>
        {{else eq adprAuth "M"}}
			<span class="badge badge-label bg-secondary fs-15"><i class="mdi mdi-circle-medium"></i> 회원 관리자</span>
        {{else eq adprAuth "B"}}
			<span class="badge badge-label bg-success fs-15"><i class="mdi mdi-circle-medium"></i> 게시판 관리자</span>
        {{/eq}}
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
</script>
<script id="page-List-template" type="text/x-handlebars-template">
    {{#each page}}
        <li class="page-item">
            <span class="page-link" id="{{id}}">{{{htmlOrText value}}}</span>
        </li>
    {{/each}}
</script>

<script type="text/javascript">
Handlebars.registerHelper('eq', function(v1, v2, options) {
  if (v1 === v2) {
    return options.fn(this);
  }
  return options.inverse(this);
});
</script>
<script type="text/javascript">
// Handlebars.js 템플릿 컴파일 시, htmlOrText 헬퍼 함수 등록
Handlebars.registerHelper('htmlOrText', function(value) {
    // value가 HTML 태그를 포함하는지 여부 확인
    const containsHtmlTags = /<[a-z][\s\S]*>/i.test(value);

    // value가 HTML 태그를 포함하면서 안전한 방법으로 출력하기 위해 {{{}}} 사용
    if (containsHtmlTags) {
        return new Handlebars.SafeString(value);
    }

    // value가 일반 문자열인 경우 {{}} 사용하여 출력
    return value;
});
</script>
</html>
