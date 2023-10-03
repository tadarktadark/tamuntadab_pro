<%@page import="java.util.Map"%>
<%@page import="com.tdtd.tmtd.vo.UserProfileVo"%>
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
<head>
<link href="../assets/libs/jsvectormap/css/jsvectormap.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/libs/swiper/swiper-bundle.min.css"
	rel="stylesheet" type="text/css" />
<%@ include file="./shared/_head_css.jsp"%>
<%@ include file="./shared/_vender_scripts.jsp"%>
</head>
<body>
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp"%>
		<%@ include file="./shared/_sidebar.jsp"%>
		<% 
			Map<String,Object> map = (Map<String,Object>)request.getAttribute("admin"); 
			AdminVo adminVo = (AdminVo)map.get("adminVo");
			List<AdminVo> ipList = (List<AdminVo>)map.get("adminIp");
		%>
		<div class="vertical-overlay"></div>
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-xxl-12">
						<div class="card">
								<ul class="nav nav-tabs-custom nav-justified" id="pills-tab"
									role="tablist">
									<li class="nav-item"><a class="nav-link px-3"
										data-bs-toggle="tab" href="#info" role="tab"
										aria-selected="false"> <i class="bx bx-user-circle fs-20"></i>
											<span class="d-none d-sm-block">관리자 정보</span>
									</a></li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane row active" id="info" role="tabpanel">
										<div class="p-4">
											<div class="container-fluid">
												    <div class="row">
												        <div class="col-2">
												            <div class="mb-3">
												                <label class="form-label"><b>아이디</b></label>
												                <input type="text" class="form-control bg-light-subtle " readonly="readonly" value="<%=adminVo.getAdprId()%>">
												            </div>
												        </div><!--end col-->
												        <div class="col-2">
												            <div class="mb-3">
												                <label class="form-label"><b>이름</b></label>
												                <input type="text" readonly="readonly" class="form-control bg-light-subtle" value="<%=adminVo.getAdprName()%>">
												            </div>
												        </div><!--end col-->
												        <div class="col-2">
												            <div class="mb-3">
												                <label class="form-label"><b>등록 관리자</b></label>
												                <input type="text" readonly="readonly" class="form-control bg-light-subtle" value="<%=adminVo.getAdprAdmin()%>">
												            </div>
												        </div><!--end col-->
												        <div class="col-1">
												            <div class="mb-3">
												                <label class="form-label"><b>삭제 여부</b></label>
												                  <%
													                switch (adminVo.getAdprDelflag()) {
													                case "Y":
													                	%>
												           			 	<input type="text" class="form-control bg-light-subtle" readonly="readonly" value="삭제">    		
													                	<%
													                    break;
													                case "N":
													                	%>
													                	<input type="text" class="form-control bg-light-subtle" readonly="readonly" value="미삭제">
													                	<%
													        	    }
												                %>
												            </div>
												        </div><!--end col-->
												        <div class="col-1">
												            <div class="mb-3">
												                <label class="form-label"><b>권한</b></label>
												                <%
													                switch (adminVo.getAdprAuth()) {
													                case "T":
													                	%>
												           			 	<input type="text" class="form-control bg-light-subtle" readonly="readonly" value="총관리자">    		
													                	<%
													                    break;
													                case "M":
													                	%>
												           			 	<input type="text" class="form-control bg-light-subtle" readonly="readonly" value="회원">    		
													                	<%
													                    break;
													                case "P":
													                	%>
												           			 	<input type="text" class="form-control bg-light-subtle" readonly="readonly" value="결제">    		
													                	<%
													                    break;
													                case "B":
													                	%>
												           			 	<input type="text" class="form-control bg-light-subtle" readonly="readonly" value="게시판">    		
													                	<%
													                    break;
													        	    }
												                %>
												            </div>
												        </div>
												        <div class="col-3">
												            <div class="mb-3">
												                <label class="form-label"><b>최근 접속일</b></label>
												                <input type="text" class="form-control bg-light-subtle" readonly="readonly" value="<%=adminVo.getAdprLastAccess()%>">
												            </div>
												        </div>
												        <%
												        	if(ipList.size()!=0){
														%>												        		
												        <div class="col-12 text-center">
												      	  <div class="col-5 text-center">
												            <table class="table table-hover table-nowrap align-middle mb-0">
											                        <thead>
											                            <tr class="text-muted text-uppercase">
																			 <th class="text-left" scope="col">IP</th>
						                                                     <th class="text-left" scope="col">등록인</th>
						                                                     <th class="text-left" scope="col">삭제</th>
											                            </tr>
											                        </thead>
											                        <tbody class="ipList">
											                        <%
											                       		for(int i = 0; i<ipList.size(); i++){
											                       			%>
										                       				<tr>
												                        		<td><%=ipList.get(i).getIpAddress() %></td>
												                        		<td><%=ipList.get(i).getAdprAdmin() %></td>
												                        		<td>
																		            <button class="btn btn-soft-danger btn-sm dropdown" type="button"
																		            onclick="location.href='./delIP.do?ipAddr=<%=ipList.get(i).getIpAddress()%>&adpradmin=<%=ipList.get(i).getAdprAdmin()%>'">
																		                <i class="ri ri-delete-bin-fill align-middle fs-18"></i>
																		            </button>
																			    </td>
												                        	</tr>
											                       			<%
											                       		}
											                        %>
										                        </tbody>
										                    </table>
										                    </div>
												        </div>
												        <%
												        	}
												        %>
										       		  <div class="col-12 mt-5">
										       		    <div class="text-center">
										       		  	<button type="button" class="btn btn-soft-success col-3 comfirm-filter"
										       		  		onclick="location.href='./adminResetPW.do?adminId=<%=adminVo.getAdprId()%>'">
														    <span class="icon-off"><i class=" ri ri-refresh-line align-bottom me-1"></i>비밀번호 초기화</span>
														</button>
	                                                       <button type="button" class="btn btn-soft-primary col-5 comfirm-filter" onclick="location.href='./adminList.do'">
															    <span class="icon-off"><i class="ri ri-file-list-fill align-bottom me-1"></i>돌아가기</span>
															</button>
															<%
													                switch (adminVo.getAdprDelflag()) {
													                case "Y":
													                	%>
														            		<button type="button" class="btn btn-soft-info col-3 comfirm-filter"
														            		onclick="location.href='./restoreAdmin.do?adminId=<%=adminVo.getAdprId()%>'">
																			    <span class="icon-off"><i class="ri ri-shield-user-fill align-bottom me-1"></i>복구</span>
																			</button>
													                	<%
													                    break;
													                case "N":
													                	%>
														            		<button type="button" class="btn btn-soft-danger col-3 comfirm-filter"
														            		onclick="location.href='./delAdmin.do?adminId=<%=adminVo.getAdprId()%>'">
																			    <span class="icon-off"><i class="ri ri-delete-bin-6-fill align-bottom me-1"></i>삭제</span>
																			</button>
													                	<%
													        	    }
												                %>
														</div>
											        </div>
											    </div><!--end row-->
											</div>
										</div>
									</div>
									<!-- 내정보 끝 -->
								</div>
								<!-- end tab content -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>