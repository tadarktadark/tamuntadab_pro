/* �� �� */
$(document).ready(function() {
    // Initially hide both forms and the 'blackEnd' field
    $('#highSchoolForm').hide();
    $('#universityForm').hide();
    $('#blackEnd').parent().hide();

    // Listen for changes on the select element
    $('.form-select.mb-3').change(function() {
        var selectedValue = $(this).val();

        if (selectedValue == '1') {  // If "����б� �߰�" is selected
            $('#highSchoolForm').show();
            $('#universityForm').hide();
        } else if (selectedValue == '2') {  // If "����/���п� �߰�" is selected
            $('#highSchoolForm').hide();
            $('#universityForm').show();
        } else {  // If "�߰� �з� �Է�*" is selected or nothing is selected
            $('#highSchoolForm').hide();
            $('#universityForm').hide();
        }
    });

	// ���� ������� üũ�ڽ��� ���°� ����� �� ������ �Լ�
	$('#inedSchoolCheck').change(function() {
		
			if ($(this).is(':checked')) { 
			
				$('#highSchool, #highSchoolStart, #highSchoolEnd, #highSchoolMajor ').parent().hide(); 
				
				$('#blackEnd ').parent().show(); 
				
		 } else {  
			 $('#highSchool, #highSchoolStart, #highSchoolEnd, #highSchoolMajor ').parent().show(); 
			 
			 $('#blackEnd ').parent().hide(); 
	  }
   });
	
	$('#highSchoolForm #highSchoolEnd, #universityForm #univEnd').one('focus', function() {
	    var correspondingStartDateId = this.id.replace('End', 'Start');
	    if (!$('#' + correspondingStartDateId).val()) {
	        Swal.fire('���� ���г������ �������ּ���');
	        $(this).blur();
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
	console.log("���")
    var selectedValue = $('.form-select.mb-3').val();
    var table = window.opener.document.querySelector('.education-table');
    var tableBody = window.opener.document.querySelector('.card-body table tbody');
    
    if (selectedValue == '1') {
        if ($('#inedSchoolCheck').is(':checked')) {
            if (!$('#blackEnd').val()) {
                alert('�հݳ������ �Է����ּ���.');
                return;
            }
            var stage = getEduLevelText('5');
            var highSchool = '���԰������';
            var highSchoolStart = document.getElementById('blackEnd').value;
            var highSchoolEnd = document.getElementById('blackEnd').value;
            var highSchoolMajor = '';
            
            createAndAppendRow(tableBody, [stage, highSchool, highSchoolMajor, '', highSchoolStart, highSchoolEnd],5, tableBody.rows.length);
        } else {  // If ���� ������� checkbox is not checked, check all other fields
            if (!$('#highSchool').val() || !$('#highSchoolStart').val() || !$('#highSchoolEnd').val() || $('#highSchoolMajor option:selected').text() == '�����迭') {
                alert('��� �ʼ� �׸��� �Է����ּ���.');
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
    } else if (selectedValue == '2') {  // If "����/���п� �߰�" is selected
        if ($('#univStage').val() == '���б���*'  || !$('#univSchool').val() || !$('#major').val()
            || !$('#univStart').val() || !$('#univEnd').val()) {
            alert('��� �ʼ� �׸��� �Է����ּ���.');
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
   
   deleteButton.innerHTML ='���';
   deleteButton.className ='cancel-button btn btn-danger';
   deleteButton.setAttribute('onclick', 'deleteRow(this)');
   
   deleteButtonTd.appendChild(deleteButton);
   trItem.appendChild(deleteButtonTd);

   
   parentElm.appendChild(trItem)
}

function getEduLevelText(value) {
    switch(value) {
        case '1': return '���п�(�ڻ�)';
        case '2': return '���п�(����)';
        case '3': return '���б�(4��)';
        case '4': return '����(2,3��)';
        case '5': return '����б�';
        default: return '';
    }
}