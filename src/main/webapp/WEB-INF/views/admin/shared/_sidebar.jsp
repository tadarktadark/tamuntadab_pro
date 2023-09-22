<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.tdtd.tmtd.vo.AdminVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="../assets/libs/sweetalert2/sweetalert2.min.css" rel="stylesheet" type="text/css" />
<script src="../assets/libs/sweetalert2/sweetalert2.min.js"></script>
<script src="../js/adminAuth.js"></script>
<% AdminVo adminInfo = (AdminVo)request.getSession().getAttribute("adminInfo"); %>
<div class="app-menu navbar-menu">
    <div class="container-fluid">
        <!-- LOGO -->
        <div class="navbar-brand-box">
            <a href="./adminMain.do" class="logo logo-dark">
	            <span class="logo-lg">
	                <img src="../image/logo.png" alt="" height="80" width="200">
	            </span>
            </a>
        </div>
        <div id="scrollbar" class="mt-5" >
            <div class="container-fluid">
                <ul class="navbar-nav" id="navbar-nav">
                  <li class="nav-item ">
                        <a class="nav-link menu-link fs-3" href="#admin4User" data-bs-toggle="collapse" role="button" aria-expanded="false" aria-controls="sidebarComm">
                            <i class="bx bxs-user-account fs-3"></i> <span data-key="admin4User">회원관리</span>
                        </a>
                        <div class="collapse menu-dropdown" id="admin4User">
                            <ul class="nav nav-sm flex-column ">
                                <li class="nav-item">
                                    <a href="#" class="nav-link fs-5" onclick="check('<%=adminInfo.getAdprAuth()%>','M')" >회원 목록</a>
                                </li>
                                <li class="nav-item">
                                    <a href="#" class="nav-link fs-5" onclick="check('<%=adminInfo.getAdprAuth()%>','M')" >강사 관리</a>
                                </li>
                                <li class="nav-item">
                                    <a href="#" class="nav-link fs-5" onclick="check('<%=adminInfo.getAdprAuth()%>','M','B')" >신고 관리</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                  <li class="nav-item ">
                        <a class="nav-link menu-link fs-3" href="#admin4Pay" data-bs-toggle="collapse" role="button" aria-expanded="false" aria-controls="sidebarComm">
                            <i class="bx bx-money-withdraw fs-3"></i> <span data-key="admin4Pay">결제 관리</span>
                        </a>
                        <div class="collapse menu-dropdown" id="admin4Pay">
                            <ul class="nav nav-sm flex-column ">
                                <li class="nav-item">
                                    <a href="#" class="nav-link fs-5" onclick="check('<%=adminInfo.getAdprAuth()%>','P')">결제 관리</a>
                                </li>
                                <li class="nav-item">
                                    <a href="#" class="nav-link fs-5"  onclick="check('<%=adminInfo.getAdprAuth()%>','P')">정산 관리</a>
                                </li>
                                <li class="nav-item">
                                    <a href="#" class="nav-link fs-5"  onclick="check('<%=adminInfo.getAdprAuth()%>','P')">환불 관리</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                  <li class="nav-item ">
                        <a class="nav-link menu-link fs-3" href="#admin4yeyak" data-bs-toggle="collapse" role="button" aria-expanded="false" aria-controls="sidebarComm">
                            <i class=" bx bxs-calendar fs-3"></i> <span data-key="admin4yeyak">예약 관리</span>
                        </a>
                        <div class="collapse menu-dropdown" id="admin4yeyak">
                            <ul class="nav nav-sm flex-column ">
                                <li class="nav-item">
                                    <a href="#" class="nav-link fs-5"  onclick="check('<%=adminInfo.getAdprAuth()%>','P')">예약 조회</a>
                                </li>
                                <li class="nav-item">
                                    <a href="#" class="nav-link fs-5"  onclick="check('<%=adminInfo.getAdprAuth()%>','P')">예약 추가</a>
                                </li>
                                <li class="nav-item">
                                    <a href="#" class="nav-link fs-5"  onclick="check('<%=adminInfo.getAdprAuth()%>','P')">예약 수정</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                  <li class="nav-item ">
                        <a class="nav-link menu-link fs-3 admin4admin" href="#admin4admin" data-bs-toggle="collapse" role="button" aria-expanded="false" aria-controls="sidebarComm">
                            <i class=" bx bx-lock-alt fs-3"></i> <span data-key="admin4admin">총관리자</span>
                        </a>
                        <div class="collapse menu-dropdown" id="admin4admin">
                            <ul class="nav nav-sm flex-column ">
                                <li class="nav-item">
                                    <a href="./adminList.do" class="nav-link fs-5"  onclick="check('<%=adminInfo.getAdprAuth()%>','T')">관리자 목록</a>
                                </li>
                                <li class="nav-item">
                                    <a href="./adminInsert.do" class="nav-link fs-5" onclick="check('<%=adminInfo.getAdprAuth()%>','T')">관리자 추가</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
	</div>
</div>