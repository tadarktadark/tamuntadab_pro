$(document).ready(function() {
    // Initially hide both forms and the 'blackEnd' field
    $('#highSchoolForm').hide();
    $('#universityForm').hide();
    $('#blackEnd').parent().hide();

    // Listen for changes on the select element
    $('.form-select.mb-3').change(function() {
        var selectedValue = $(this).val();

        if (selectedValue == '1') {  // If "고등학교 추가" is selected
            $('#highSchoolForm').show();
            $('#universityForm').hide();
        } else if (selectedValue == '2') {  // If "대학/대학원 추가" is selected
            $('#highSchoolForm').hide();
            $('#universityForm').show();
        } else {  // If "추가 학력 입력*" is selected or nothing is selected
            $('#highSchoolForm').hide();
            $('#universityForm').hide();
        }
    });

	// 대입 검정고시 체크박스의 상태가 변경될 때 실행할 함수
	$('#inedSchoolCheck').change(function() {
		
			if ($(this).is(':checked')) {  // 체크박스가 선택된 경우
			
				// 고등학교 관련 입력 필드를 숨깁니다.
				$('#highSchool, #highSchoolStart, #highSchoolEnd, #highSchoolMajor ').parent().hide(); 
				
				// 합격년월일을 보여줍니다.
				$('#blackEnd ').parent().show(); 
				
		 } else {  // 체크박스가 해제된 경우
			
			 // 고등학교 관련 입력 필드를 보여줍니다.
			 $('#highSchool, #highSchoolStart, #highSchoolEnd, #highSchoolMajor ').parent().show(); 
			 
			 // 합격년월일을 숨깁니다.
			 $('#blackEnd ').parent().hide(); 
	  }
   });
});

function regist() {
	console.log("등록")
	 // Check if all required fields are filled
    var selectedValue = $('.form-select.mb-3').val();
    var table = window.opener.document.querySelector('.education-table');
    var tableBody = window.opener.document.querySelector('.card-body table tbody');
    
    if (selectedValue == '1') {  // If "고등학교 추가" is selected
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
        } else {  // If 대입 검정고시 checkbox is not checked, check all other fields
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
    } else if (selectedValue == '2') {  // If "대학/대학원 추가" is selected
        if ($('#univStage').val() == '대학구분*'  || !$('#univSchool').val() || !$('#major').val()
            || !$('#univStart').val() || !$('#univEnd').val()) {
            alert('모든 필수 항목을 입력해주세요.');
            return;
        }
        var univStage = document.getElementById('univStage').value;
		var stage = getEduLevelText(univStage);
		var univSchool = document.getElementById('univSchool').value;
		var major = document.getElementById('major').value;
		var minor = document.getElementById('minor').value || '';  // Use an empty string if no minor
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
    
    // Define the names for each input field
    let inputNames = ["inedStage", "inedSchool", "inedMajor", "inedMinor", "inedStart", "inedEnd"];
    
    for(let i=0; i<dataArr.length; i++){
        let tdItem = document.createElement("td");
        let textNode = document.createTextNode(dataArr[i])
        
        // Create an input field for each cell
        let inputField = document.createElement("input");
        inputField.type = "hidden";
        
        // Assign a name attribute based on the index (you can modify this to suit your needs)
        if (i < inputNames.length) {
            inputField.name = `instrEduVo[${rowIndex}].${inputNames[i]}`;
            
            // If this is the first element (stage), use the stageValue parameter as its value
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
   // Add a delete button to the last cell of each row
   var deleteButtonTd = document.createElement('td');
   var deleteButton = document.createElement('button');
   
   deleteButton.textContent =String('취소').toString();
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