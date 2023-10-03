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
			<div class="row p-2">
					    <div class="col-xl-12">
					        <div class="card">
					            <div class="card-body">
					             <div class="row g-4 mb-3">
                                           <div class="col-sm-auto">
                                                <div class="hstack gap-2 flex-wrap mb-3">
                                                    <button type="button" class="btn btn-soft-primary"
                                                     data-bs-toggle="collapse" data-bs-target=".filter-collapse" 
                                                   	 aria-expanded="false" aria-controls="filter-collapse">
													    <span class="icon-off"><i class="ri-filter-fill align-bottom me-1"></i>필터</span>
													</button>
                                                </div>
                                            </div>
                                            <div class="col-sm">
                                                <div class="d-flex justify-content-sm-end">
                                                   <div class="d-flex gap-3">
											        <div>
											        	 <select class="form-select mb-3 tag">
														    <option selected value="ADPR_ID">아이디</option>
														    <option value="ADPR_NAME">이름</option>
														</select>
											        </div>
											            <div class="search-box">
											            	<div class="input-group">
											                <input type="text" class="form-control search-value" placeholder="검색어를 입력하세요">
											               		<i class="bx bx-search search-icon fs-16"></i>
											                <button type="button"class="btn btn-soft-primary btn-icon fs-16 search"><i class="bx bx-search-alt-2 fs-16"></i></button>
											                </div>
											            </div>
											        </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--필터 부분-->
					             <div class="col-12 mb-3">
                                           <div class="col-sm-auto">
                                                <div class="hstack gap-2 flex-wrap">
													<div class="col-12 px-2">
												        <div class="collapse filter-collapse" id="filter-collapse">
												        	<div class="row ">
												        		<div class="col-4">
												        			<span class="badge bg-info-subtle text-info fs-17 mb-2 col-12">유형</span>
												        			<div class="">
												        				<div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-auth" type="radio" name="filter-auth" id="T" value="T">
                                                                           <label class="form-check-label" for="T">총관리자</label>
                                                                       </div>
                                                                       <div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-auth" type="radio" name="filter-auth" id="M" value="M">
                                                                           <label class="form-check-label" for="M">회원 관리자</label>
                                                                       </div>
                                                                       <div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-auth" type="radio" name="filter-auth" id="P" value="P">
                                                                           <label class="form-check-label" for="P">결제 관리자</label>
                                                                       </div>
                                                                       <div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-auth" type="radio" name="filter-auth" id="B" value="B">
                                                                           <label class="form-check-label" for="B">게시판 관리자</label>
                                                                       </div>
												        			</div>
                                                                 </div>
                                                                 <div class="col-2">
												        			<span class="badge bg-info-subtle text-info fs-17 mb-2 col-12" >삭제 여부</span>
												        			<div>
												        				<div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-delflag" type="radio" name="filter-delflag" id="hwaldong" value="N">
                                                                           <label class="form-check-label" for="hwaldong">미삭제</label>
                                                                       </div>
                                                                       <div class="form-check form-check-inline mb-2">
                                                                           <input class="form-check-input filter filter-delflag" type="radio" name="filter-delflag" id="deleted" value="Y">
                                                                           <label class="form-check-label" for="deleted">삭제</label>
                                                                       </div>
												        			</div>
                                                                   </div>
													        		<div class="col-1">
													        			<span class="badge bg-info-subtle text-info fs-17 mb-2 col-12">ID</span>
													        				<div class="form-check form-check-inline mb-2">
	                                                                           <input class="form-check-input filter filter-sort" type="radio" name="filter-sort" id="ADPR_ID_ASC" 
																			       value='{"column":"ADPR_ID","value":"ASC"}'>
	                                                                           <label class="form-check-label" for="ADPR_ID_ASC">오름차</label>
	                                                                       </div>
	                                                                       <div class="form-check form-check-inline mb-2">
	                                                                           <input class="form-check-input filter filter-sort" type="radio" name="filter-sort" id="ADPR_ID_DESC" 
																					value='{"column":"ADPR_ID","value":"DESC"}'>
	                                                                           <label class="form-check-label" for="ADPR_ID_DESC">내림차</label>
	                                                                       </div>
	                                                                </div>
													        		<div class="col-1">
													        			<span class="badge bg-info-subtle text-info fs-17 mb-2 col-12">이름</span>
													        				<div class="form-check form-check-inline mb-2">
	                                                                           <input class="form-check-input filter filter-sort" type="radio" name="filter-sort" id="ADPR_NAME_ASC" 
																			       value='{"column":"ADPR_NAME","value":"ASC"}'>
	                                                                           <label class="form-check-label" for="ADPR_NAME_ASC">오름차</label>
	                                                                       </div>
	                                                                       <div class="form-check form-check-inline mb-2">
	                                                                           <input class="form-check-input filter filter-sort" type="radio" name="filter-sort" id="ADPR_NAME_DESC" 
																					value='{"column":"ADPR_NAME","value":"DESC"}'>
	                                                                           <label class="form-check-label" for="ADPR_NAME_DESC">내림차</label>
	                                                                       </div>
	                                                                </div>
													        		<div class="col-1">
													        			<span class="badge bg-info-subtle text-info fs-17 mb-2 col-12">접속일</span>
													        				<div class="form-check form-check-inline mb-2">
	                                                                           <input class="form-check-input filter filter-sort" type="radio" name="filter-sort" id="ADPR_LAST_ACCESS_ASC" 
																			       value='{"column":"ADPR_LAST_ACCESS","value":"ASC"}'>
	                                                                           <label class="form-check-label" for="ADPR_LAST_ACCESS_ASC">오름차</label>
	                                                                       </div>
	                                                                       <div class="form-check form-check-inline mb-2">
	                                                                           <input class="form-check-input filter filter-sort" type="radio" name="filter-sort" id="ADPR_LAST_ACCESS_DESC" 
																					value='{"column":"ADPR_LAST_ACCESS","value":"DESC"}'>
	                                                                           <label class="form-check-label" for="ADPR_LAST_ACCESS_DESC">내림차</label>
	                                                                       </div>
                                                             	   </div>
                                                                <div class="text-center">
	                                                               	<button type="button" class="btn btn-soft-success col-5 comfirm-filter">
																	    <span class="icon-off"><i class="mdi mdi-filter-check align-bottom me-1"></i>필터 적용</span>
																	</button>
	                                                               	<button type="button" class="btn btn-soft-danger col-5 remove-filter">
																	    <span class="icon-off"><i class="mdi mdi-filter-remove align-bottom me-1"></i>필터 초기화</span>
																</button>
																</div>
											        		</div>
												        </div>
													</div>
                                                </div>
                                            </div>
                                        </div>
	                                        <!-- 필터 끝 -->
					                <div class="table-responsive table-card">
					                    <table class="table table-hover table-nowrap align-middle mb-0">
					                        <thead>
					                            <tr class="text-muted text-uppercase">
													 <th class="text-left" scope="col">ID</th>
                                                     <th class="text-left" scope="col">이름</th>
                                                     <th class="text-left" scope="col">최근 접속일</th>
                                                     <th class="text-left" scope="col">관리자 권한</th>
                                                     <th class="text-left" scope="col">삭제 여부</th>
                                                     <th class="text-left" scope="col">동작</th>
					                            </tr>
					                        </thead>
					                        <tbody class="adminList">
					                        </tbody>
					                    </table>
					                     <div class="d-flex justify-content-center mt-2">
											<div class="pagination-wrap hstack gap-2">
											<ul class="pagination listjs-pagination mb-0">
											</ul>
										</div>
									</div>
					                </div>
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
    <td><a href="./adminDetail.do?id={{adprId}}" class="text-body align-middle fw-medium">{{adprId}}</a></td>
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
		{{#eq adprDelflag "Y"}}
			<td class="text-left"><span class="badge bg-danger-subtle text-danger  p-2">{{adprDelflag}}</span></td>
        {{else eq adprDelflag "N"}}
			<td class="text-left"><span class="badge bg-success-subtle text-success  p-2">{{adprDelflag}}</span></td>
       	{{/eq}}
					</td>
					    <td>
					        <div class="dropdown">
					            <button class="btn btn-soft-primary btn-sm dropdown" type="button" data-bs-toggle="dropdown" aria-expanded="false">
					                <i class="bx bx-dots-horizontal-rounded align-middle fs-18"></i>
					            </button>
					            <ul class="dropdown-menu dropdown-menu-end">
					                <li>
					                    <button class="dropdown-item" onclick='location.href="./adminDetail.do?id={{adprId}}"'>
					                        <i class="las la-pen fs-18 align-middle me-2 text-muted"></i>
					                        상세보기
					                    </button>
					                </li>
					                <li class="dropdown-divider"></li>
					                <li>
					                    <a class="dropdown-item remove-item-btn" href="./adminResetPW.do?adminId={{adprId}}">
					                        <i class="las la-trash-alt fs-18 align-middle me-2 text-muted"></i>
					                        비밀번호 초기화
					                    </a>
					                </li>
									{{#eq adprDelflag "N"}}
									<li>
					                    <a class="dropdown-item remove-item-btn" href="./delAdmin.do?adminId={{adprId}}">
					                        <i class="las la-trash-alt fs-18 align-middle me-2 text-muted"></i>
					                        삭제
					                    </a>
					                </li>
     							   {{else eq adprDelflag "Y"}}
									<li>
					                    <a class="dropdown-item remove-item-btn" href="./restoreAdmin.do?adminId={{adprId}}">
					                        <i class="las la-trash-alt fs-18 align-middle me-2 text-muted"></i>
					                        복구
					                    </a>
					                </li>
							       	{{/eq}}
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
