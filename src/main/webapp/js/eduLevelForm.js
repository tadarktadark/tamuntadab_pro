$(document).ready(function() { 
    $('#highSchoolForm').hide();
    $('#universityForm').hide();
    $('#blackEnd').parent().hide();

    $('.form-select.mb-3').change(function() {
        var selectedValue = $(this).val();

        if (selectedValue == '1') {  // 고등학교 추가 선택시
            $('#highSchoolForm').show();
            $('#universityForm').hide();
        } else if (selectedValue == '2') {  // 대학/대학원 추가 선택시
            $('#highSchoolForm').hide();
            $('#universityForm').show();
            
        } else {
            $('#highSchoolForm').hide();
            $('#universityForm').hide();
        }
    });
    
	// 대입 검정고시 체크박스의 상태가 변경될 때 실행할 함수
	$('#inedSchoolCheck').change(function() {
		
			if ($(this).is(':checked')) { 
			
				$('#highSchool, #highSchoolStart, #highSchoolEnd, #highSchoolMajor ').parent().hide(); 
				
				$('#blackEnd ').parent().show(); 
				
		 } else {  
			 $('#highSchool, #highSchoolStart, #highSchoolEnd, #highSchoolMajor ').parent().show(); 
			 
			 $('#blackEnd ').parent().hide(); 
	  }
   });
	
	//달력 유효값 처리
	
	var startDateSelected = false;
	
	//졸업년월일을 먼저 선택했을때
	$('#highSchoolForm #highSchoolEnd, #universityForm #univEnd').on('focus', function() {
    var correspondingStartDateId = this.id.replace('End', 'Start');
    if (!$('#' + correspondingStartDateId).val() && !startDateSelected) {
        Swal.fire('먼저 입학년월일을 선택해주세요');
        $(this).blur();
        startDateSelected = true;  // Flag 설정
    } else {
        startDateSelected = false;  // Flag 초기화
    }
});
	
   
	$('#highSchoolStart, #univStart')
		 .attr('max', new Date().toISOString().split('T')[0]);

	$('#highSchoolStart')
		 .on('change', function () {
			 $("#highSchoolEnd").attr("min", this.value);
		 });

	$("#univStart")
		 .on(
			 'change',
			 function () {
				 $("#univEnd").attr("min", this.value);
			 });
			 
	$('#highSchoolEnd, #univEnd')
     .attr('max', new Date().toISOString().split('T')[0]);
   
});

function regist() {
	console.log("등록")
    var selectedValue = $('.form-select.mb-3').val();
    var table = window.opener.document.querySelector('.education-table');
    var tableBody = window.opener.document.querySelector('.education-table tbody');
    console.log(tableBody);
    if (selectedValue == '1') {
        if ($('#inedSchoolCheck').is(':checked')) {
            if (!$('#blackEnd').val()) {
                alert('합격년월일을 입력해주세요.');
                return;
            }
            var stage = getEduLevelText('5');
            var highSchool = '대입검정고시';
            var highSchoolStart = document.getElementById('blackEnd').value;
            var highSchoolEnd = document.getElementById('blackEnd').value;
            var highSchoolMajor = '';
            
            createAndAppendRow(tableBody, [stage, highSchool, highSchoolMajor, '', highSchoolStart, highSchoolEnd],5, tableBody.rows.length);
        } else {  // 대입 검정고시 체크 제외 나머지 필드 유효값 처리
            if (!$('#highSchool').val() || !$('#highSchoolStart').val() || !$('#highSchoolEnd').val() || $('#highSchoolMajor option:selected').text() == '전공계열') {
                alert('모든 필수 항목을 입력해주세요.');
                return;
            }
	       	var stage = getEduLevelText('5');
	        var highSchool = document.getElementById('highSchool').value;
	        var highSchoolStart = document.getElementById('highSchoolStart').value;
	        var highSchoolEnd = document.getElementById('highSchoolEnd').value;
	        var highSchoolMajor = document.getElementById('highSchoolMajor').value;
	
	        createAndAppendRow(tableBody, [stage, highSchool, highSchoolMajor, '', highSchoolStart, highSchoolEnd],5, tableBody.rows.length);
        }

		table.style.display = 'table';
    } else if (selectedValue == '2') {  // 대학/대학원 추가 선택시
        if ($('#univStage').val() == '대학구분*'  || !$('#univSchool').val() || !$('#major').val()
            || !$('#univStart').val() || !$('#univEnd').val()) {
            alert('모든 필수 항목을 입력해주세요.');
            return;
        }
        var univStage = document.getElementById('univStage').value;
		var stage = getEduLevelText(univStage);
		var univSchool = document.getElementById('univSchool').value;
		var major = document.getElementById('major').value;
		var minor = document.getElementById('minor').value || '';  // 부전공 null값 처리
		var univStart = document.getElementById('univStart').value;
		var univEnd= 	document.getElementById('univEnd').value;

    	createAndAppendRow(tableBody, [stage, univSchool, major, minor, univStart, univEnd],univStage, tableBody.rows.length);
   		table.style.display = 'table';
    }
	
	window.close();
}

function createAndAppendRow(parentElm ,dataArr, stageValue, rowIndex){
    let trItem = document.createElement("tr");
    trItem.style.textAlign = "center";
    
    let inputNames = ["inedStage", "inedSchool", "inedMajor", "inedMinor", "inedStart", "inedEnd"];
    
    for(let i=0; i<dataArr.length; i++){
        let tdItem = document.createElement("td");
        let textNode = document.createTextNode(dataArr[i])
        
        let inputField = document.createElement("input");
        inputField.type = "hidden";
        
        if (i < inputNames.length) {
            inputField.name = `instrEduVo[${rowIndex}].${inputNames[i]}`;
            
            if(i == 0){
               	inputField.value = stageValue;
            } else {
            	inputField.value = dataArr[i];
	    }
	    
	    tdItem.appendChild(inputField);
	}
	
	tdItem.appendChild(textNode)
	trItem.appendChild(tdItem)
   }
   var deleteButtonTd = document.createElement('td');
   var deleteButton = document.createElement('button');
   
   deleteButton.innerHTML ='취소';
   deleteButton.className ='cancel-button btn btn-danger';
   deleteButton.setAttribute('onclick', 'deleteRow(this)');
   
   deleteButtonTd.appendChild(deleteButton);
   trItem.appendChild(deleteButtonTd);

   
   parentElm.appendChild(trItem)
}

function getEduLevelText(value) {
    switch(value) {
        case '1': return '대학원(박사)';
        case '2': return '대학원(석사)';
        case '3': return '대학교(4년)';
        case '4': return '대학(2,3년)';
        case '5': return '고등학교';
        default: return '';
    }
}