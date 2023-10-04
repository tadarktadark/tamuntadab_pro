<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en" data-layout="horizontal" data-layout-mode="light"
	data-topbar="light" data-sidebar="light" data-sidebar-size="sm"
	data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title}|타문타답</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script src=></script>
<%@ include file="./shared/_head_css.jsp"%>
<%@ include file="./shared/_logout.jsp" %>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_menu.jsp"%>
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<%@ include file="./shared/_page_title.jsp" %>
					
					<div class="row justify-content-center">
                        <div class="col-xxl-12">
                            <div class="card" id="demo">
                                <div class="card-body">
                                    <div class="row p-4">
                                        <div class="col-lg-9">
                                        	
                                            <h4 class="mb-4">결제 유형 : 
											    <c:choose>
											        <c:when test="${fn:startsWith(gyeoljeVo.gyeoId, 'CL')}">
											            수강료 결제
											        </c:when>
											        <c:when test="${fn:startsWith(gyeoljeVo.gyeoId, 'RE')}">
											            강의실 대여료 결제
											        </c:when>
											    </c:choose>
											</h4>
                                            <div class="row g-4">
                                                <div class="col-lg-6 col-6">
                                                    <p class="text-muted mb-1 text-uppercase fw-medium fs-13">결제 ID</p>
                                                    <h5 class="fs-16 mb-0"><span id="invoice-no">${gyeoljeVo.gyeoId}</span></h5>
                                                </div>
                                               <div class="col-lg-6 col-6">
												    <p class="text-muted mb-1 text-uppercase fw-medium fs-13">결제 요청일</p>
												    <h5 class="fs-16 mb-0">
												        <span id="invoice-date">
												            ${gyeoljeVo.gyeoRegdate}
												        </span> 
												    </h5>
												</div>
												<div class="col-lg-6 col-6">
												    <p class="text-muted mb-1 text-uppercase fw-medium fs-13">결제 기한</p>
												    <h5 class="fs-16 mb-0">
												        <span id="payment-due-date">
												            ${formattedDueDate}
												        </span> 
												    </h5>
												</div>

                                                <div class="col-lg-6 col-6">
                                                    <p class="text-muted mb-1 text-uppercase fw-medium fs-13">총 결제금액</p>
                                                    <h5 class="fs-16 mb-0"><span id="total-amount">${sugangryoVo.sugaYocheongGeumaek}</span></h5>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="mt-sm-0 mt-3">
                                                <div class="mb-4">
                                                    <img src="./image/logo.png" class="card-logo card-logo-dark" alt="logo dark" height="40">
                                                    <img src="./image/logo.png" class="card-logo card-logo-light" alt="logo light" height="40">
                                                </div>
                                                <h6 class="text-muted text-uppercase">중개자 : © TamunTadap.</h6>
                                                <p class="text-muted mb-1" id="address-details">소재지 : 서울시 금천구 가산동</p>
                                                <p class="text-muted mb-1" id="zip-code"><span>우편번호 : </span> 90201</p>
                                                <h6><span class="text-muted fw-normal">대표 이메일 : </span><span id="email">Tmtd@gmail.com</span></h6>
                                                <h6><span class="text-muted fw-normal">웹 사이트 : </span> <a href="/tamuntadab_pro/" class="link-primary" target="_blank" id="website">www.tmtd.com</a></h6>
                                                <h6 class="mb-0"><span class="text-muted fw-normal">연락처 : </span><span id="contact-no"> +(82) 123 4567</span></h6>
                                            </div>
                                        </div>
                                    </div><!--end col-->

                                    <div class="row p-4 border-top border-top-dashed">
                                        <div class="col-lg-9">
                                            <div class="row g-3">
                                                <div class="col-6">
                                                    <h6 class="text-muted text-uppercase mb-3">결제자 정보</h6>
                                                    <p class="fw-medium mb-2" id="billing-name">${userVo.userNickname}</p>
                                                    <p class="text-muted mb-1" id="billing-address-line-1">${userVo.userEmail}</p>
                                                    <p class="text-muted mb-1"><span>Phone: +</span><span id="billing-phone-no">${userVo.userPhoneNumber}</span></p>
                                                </div>
                                                <!--end col-->
                                                <div class="col-6">
	                                                <h6 class="text-muted text-uppercase mb-3">청구 금액</h6>
	                                                <h3 class="fw-bold mb-2">${gyeoljeVo.gyeoGeumaek} 원</h3>
	                                                <span class="badge bg-success-subtle text-success  fs-12">납부 마감 : ${formattedDueDate}</span>
                                                </div>
                                                <!--end col-->
                                            </div>
                                            <!--end row-->
                                        </div><!--end col-->

                                        <div class="col-lg-3">
                                        </div>

                                    </div>

                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="card-body p-4">
                                                <div class="table-responsive">
                                                    <table class="table table-borderless text-center table-nowrap align-middle mb-0">
                                                        <thead>
                                                            <tr class="table-active">
                                                                <th scope="col" style="width: 50px;">결제 유형</th>
                                                                <th scope="col">결제 대상</th>
                                                                <th scope="col">총 결제금액</th>
                                                                <th scope="col">결제 인원</th>
                                                                <th scope="col" class="text-end">1인당 결제액</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="products-list">
                                                            <tr>
                                                                <th scope="row">
                                                               	 	<c:choose>
																        <c:when test="${fn:startsWith(gyeoljeVo.gyeoId, 'CL')}">
																            수강료
																        </c:when>
																        <c:when test="${fn:startsWith(gyeoljeVo.gyeoId, 'RE')}">
																            강의실 대여료
																        </c:when>
																    </c:choose>
                                                                </th>
                                                                <td class="text-start" style="text-align: center;">
                                                                    <span class="fw-medium">${classVo.clasTitle}</span>
                                                                    <p class="text-muted mb-0" id="clasId">${classVo.clasId}</p>
                                                                </td>
                                                                <td>${sugangryoVo.sugaYocheongGeumaek} 원</td>
                                                                <td>${classVo.clasHyeonjaeInwon} 명</td>
                                                                <td class="text-end">${gyeoljeVo.gyeoGeumaek} 원</td>
                                                            </tr>
                                                        </tbody>
                                                    </table><!--end table-->
                                                </div>
                                                <div class="border-top border-top-dashed mt-2">
                                                    <table class="table table-borderless table-nowrap align-middle mb-0 ms-auto" style="width:250px">
                                                        <tbody>
                                                            <tr>
                                                                <td>총 결제금액</td>
                                                                <td class="text-end">${sugangryoVo.sugaYocheongGeumaek} 원</td>
                                                            </tr>
                                                            <tr>
                                                                <td>할인 <small class="text-muted">(소액 지원)</small></td>
                                                                <td class="text-end">- ${sugangryoVo.sugaYocheongGeumaek - (classVo.clasHyeonjaeInwon*gyeoljeVo.gyeoGeumaek)} 원</td>
                                                            </tr>
                                                            <tr>
                                                                <td>본인 외 부담 금액</td>
                                                                <td class="text-end">- ${(classVo.clasHyeonjaeInwon-1)*gyeoljeVo.gyeoGeumaek} 원</td>
                                                            </tr>
                                                            <tr class="border-top border-top-dashed fs-15">
                                                                <th scope="row">청구 금액</th>
                                                                <th class="text-end">${gyeoljeVo.gyeoGeumaek} 원</th>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                    <!--end table-->
                                                </div>
                                                <div class="mt-3">
                                                    <h6 class="text-muted text-uppercase mb-3">결제 세부사항 </h6>
                                                    <div class="form-group d-flex align-items-center">
													    <label for="payment-method" class="text-muted mb-0 mr-2">결제 방법 :</label>
													    <select class="form-control" id="payment-method" style="width:auto;">
													        <option selected>선택하세요</option>
													        <option value="credit-card">신용카드</option>
													        <option value="kakao-pay">카카오페이</option>
													        <option value="toss">토스</option>
													    </select>
													</div>

                                                    <p class="text-muted mb-1">결제자 : <span class="fw-medium" id="card-holder-name">${userVo.userName}</span></p>
                                                    <p class="text-muted">결제액 : <span class="fw-medium" id=""></span><span id="card-total-amount">${gyeoljeVo.gyeoGeumaek}</span>원</p>
                                                </div>
                                                <div class="mt-4">
                                                    <div class="alert alert-info">
                                                        <p class="mb-0"><span class="fw-semibold">NOTES : </span>
                                                            <span id="note">모든 결제는 요청된 날로부터 7일 이내에 결제 되어야 합니다. 
                                                            결제는 신용카드 또는 카카오페이와 토스를 통한 간편결제로 이루어지며,
                                                            7일 이내에 결제되지 않는다면 해당 결제 요청은 취소 처리됩니다.
                                                            </span>
                                                        </p>
                                                    </div>
                                                </div>
                                                
                                                <div class="hstack gap-2 justify-content-end d-print-none mt-4">
                                                    <a href="javascript:history.back();" class="btn btn-secondary">취소</a>
                                                    <input type="button" class="btn btn-primary" onclick="request_pay()" value="결제하기">
                                                </div>
                                            </div><!--end card-body-->
                                        </div><!--end col-->
                                    </div><!-- end row -->
                                </div>
                            </div>
                        </div>
                        <!--end col-->
                    </div>
					<input type="hidden" id="accountId" value="${userVo.userAccountId}">
<!-- 					<div class="row"> -->
<!--                         <div class="col-12"> -->
<!--                         	<div class ="btns"> -->
<!--                         		<input type="button" id="check2" onclick="request_pay1()" value="이니시스"> -->
<!-- 								<input type="button" id="check3" onclick="request_pay2()" value="토스"> -->
<!-- 								<input type="button" id="check4" onclick="requestPay3()" value="카카오페이"> -->
<!-- 								<h1><a href="javascript:doCheck()">정보확인</a></h1> -->
<!-- 								<a href="javascript:doF()">결제 취소</a> -->
<!-- 								<button onclick="cancelPay()">환불하기</button> -->
<!-- 							</div> -->
<!--                         </div> -->
<!--                     </div> -->
				</div>
			</div>
			<%@ include file="./shared/_footer.jsp" %>
		</div>
	</div>
	<script src="./assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="./assets/libs/simplebar/simplebar.min.js"></script>
	<script src="./assets/js/pages/plugins/lord-icon-2.1.0.js"></script>
	<script src="./assets/js/plugins.js"></script>
	<script type="text/javascript" src="./js/logout.js"></script>
	<script type="text/javascript" src="./js/payment.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
</body>
</html>