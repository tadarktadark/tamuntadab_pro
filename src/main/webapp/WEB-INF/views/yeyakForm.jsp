<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form>
	<div class="form-header"></div>
    <div style="display:none;" id="gayeAccountId">TMTD1</div>
    <div style="display:none;" id="gayeGagaId"></div>
	<br>
    <div class="row mb-3">
        <div class="col-lg-3">
            <label for="nameInput" class="form-label">이름</label>
        </div>
        <div class="col-lg-9">
            <input type="text" class="form-control" id="nameInput" value="학생일반테스트이름1" disabled="disabled">
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-lg-3">
            <label for="dateInput" class="form-label">예약 날짜</label>
        </div>
        <div class="col-lg-9">
            <input type="text" class="form-control flatpickr" id="dateInput">
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-lg-3">
            <label for="timeInput" class="form-label">예약 시간</label>
        </div>
        <div class="col-lg-9">
            <input type="time" class="form-control" id="timeInput">
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-lg-3">
            <label for="contactNumber" class="form-label">전화번호</label>
        </div>
        <div class="col-lg-9">
            <input type="number" class="form-control" id="contactNumber" placeholder="01012341234">
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-lg-3">
            <label for="meassageInput" class="form-label">Message</label>
        </div>
        <div class="col-lg-9">
            <textarea class="form-control" id="meassageInput" rows="3" placeholder="Enter your message"></textarea>
        </div>
    </div>
    <div class="text-end">
        <button type="submit" class="btn btn-primary">Add Leave</button>
    </div>
</form>