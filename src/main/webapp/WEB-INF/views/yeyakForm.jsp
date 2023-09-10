<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div style="display:none;" id="gayeAccountId">TMTD1</div>
<div style="display:none;" id="gayeGagaId"></div>
<br>
 <div class="row mb-3">
     <div class="col-lg-3">
         <label for="nameInput" class="form-label"><b>성명</b></label>
     </div>
     <div class="col-lg-9">
         <input type="text" class="form-control" value="학생일반테스트이름1" disabled="disabled">
     </div>
 </div>
 <div class="row mb-3">
     <div class="col-lg-3">
         <label for="contactNumber" class="form-label"><b>전화번호</b></label>
     </div>
     <div class="col-lg-9">
         <input type="text" class="form-control" id="contactNumber" value="01000000001" disabled="disabled">
     </div>
 </div>
 <div class="row mb-3">
     <div class="col-lg-3">
         <label for="dateInput" class="form-label"><b>예약 날짜</b></label>
     </div>
     <div class="col-lg-9">
         <input type="text" class="form-control flatpickr" id="dateInput" placeholder="날짜를 선택해주세요.">
     </div>
 </div>
 <div class="row mb-3">
     <div class="col-lg-3">
         <label for="timeInput" class="form-label"><b>시작 시간</b></label>
     </div>
     <div class="col-lg-9" id="timeInput">
         <input type="text" class="form-control flatpickr" placeholder="날짜 선택시 버튼이 생성됩니다.">
     </div>
 </div>
 <div class="row mb-3">
     <div class="col-lg-3">
         <label for="endInput" class="form-label"><b>종료 시간</b></label>
     </div>
     <div class="col-lg-9">
         <input type="text" class="form-control" id="endInput" disabled="disabled" placeholder="종료 시간을 확인하세요!">
     </div>
 </div>
 <div class="row mb-3">
     <div class="col-lg-3">
         <label for="classSelect" class="form-label"><b>클래스</b>(선택)</label>
     </div>
     <div class="col-lg-9">
 <select id="classSelect" class="form-select mb-3" aria-label="Default select example">
</select>
     </div>
 </div>
 <div class="row mb-3">
     <div class="col-lg-3">
         <label class="form-label"><b>결제 방법</b></label>
     </div>
     <div class="col-lg-9">
         <div class="form-check form-switch">
    <input class="form-check-input" type="checkbox" role="switch" id="payment" disabled="disabled">
    <label class="form-check-label" for="payment">클래스원과 함께 결제</label>
</div>
     </div>
 </div>
 <div class="row mb-3">
     <div class="col-lg-3">
         <label class="form-label"><b>결제 금액</b></label>
     </div>
     <div class="col-lg-9">        	
         <input type="hidden" class="form-control" id="gyeolje-hour" disabled="disabled">
         <input type="text" class="form-control" id="gyeolje-won" disabled="disabled" placeholder="결제 금액을 확인하세요!">
     </div>
 </div>
 <div class="text-end">
     <button id="yeyakSubmit" class="btn btn-primary"><b>예약하기</b></button>
 </div>