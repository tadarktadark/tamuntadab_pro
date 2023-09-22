$(document).ready(function() {
    $('#varyingcontentModal form').on('submit', function(event) {
        event.preventDefault();

        var data = $(this).serialize();

        $.ajax({
            url: './updateB.do',
            type: 'POST',
            data: data,
            success: function(response){
			 if (response.successMessage) {
				Swal.fire({
						        title: response.successMessage,
						        text: '',
						        icon: 'success',
						        customClass:{
						            confirmButton:'btn btn-primary w-xs mt-2'
						        }
						    }).then((result) => {
						      location.reload();
						});
		      }
		     if (response.errorMessage) {
				Swal.fire({
                            title: response.errorMessage,
                            text: '',
                            icon: 'error',
                             customClass: {
                                confirmButton: 'btn btn-primary w-xs mt-2',
                            }
                        });
			}
		},
		error: function(err){
			console.error(err);
			Swal.fire({
                            title: '에러발생',
                            text: '',
                            icon: 'error',
                             customClass: {
                                confirmButton: 'btn btn-primary w-xs mt-2',
                            }
                        });
		}
       });
    });
});



function updateS(){
	var careId = $('#careId').val();
	var userAccountId = $('#userAccountId').val();
	$.ajax({
		url: "./updateS.do",
		method: "get",
		data: {careId : careId, userAccountId : userAccountId},
		success: function(response){
			 if (response.successMessage) {
				Swal.fire({
						        title: response.successMessage,
						        text: '',
						        icon: 'success',
						        customClass:{
						            confirmButton:'btn btn-primary w-xs mt-2'
						        }
						    }).then((result) => {
						      location.reload();
						});
		      }
		     if (response.errorMessage) {
				Swal.fire({
                            title: response.errorMessage,
                            text: '',
                            icon: 'error',
                             customClass: {
                                confirmButton: 'btn btn-primary w-xs mt-2',
                            }
                        });
			}
		},
		error: function(err){
			console.error(err);
			Swal.fire({
                            title: '에러발생',
                            text: '',
                            icon: 'error',
                             customClass: {
                                confirmButton: 'btn btn-primary w-xs mt-2',
                            }
                        });
		}
		
	})
}

function updateD(){
	var careId = $('#careId').val();
	
	$.ajax({
		url: "./updateD.do",
		method: "get",
		data: {careId : careId},
		success: function(response){
			 if (response.successMessage) {
				Swal.fire({
						        title: response.successMessage,
						        text: '',
						        icon: 'success',
						        customClass:{
						            confirmButton:'btn btn-primary w-xs mt-2'
						        }
						    }).then((result) => {
						       location.reload();
						});
		      }
		     if (response.errorMessage) {
				Swal.fire({
                            title: response.errorMessage,
                            text: '',
                            icon: 'error',
                             customClass: {
                                confirmButton: 'btn btn-primary w-xs mt-2',
                            }
                        });
			}
		},
		error: function(err){
			console.error(err);
			Swal.fire({
                            title: '에러발생',
                            text: '',
                            icon: 'error',
                             customClass: {
                                confirmButton: 'btn btn-primary w-xs mt-2',
                            }
                        });
		}
		
	})
}

function modalOpen(careId){
    $('#modal-careId').val(careId);
	
	$('#varyingcontentModal').modal('show');
}

function deleteDB(){
	var careId = $('#careId').val();
	
	$.ajax({
		url: "./deleteCareer.do",
		method: "get",
		data: {careId : careId},
		success: function(response){
			 if (response.successMessage) {
				Swal.fire({
						        title: response.successMessage,
						        text: '',
						        icon: 'success',
						        customClass:{
						            confirmButton:'btn btn-primary w-xs mt-2'
						        }
						    }).then((result) => {
						       location.reload();
						});
		      }
		     if (response.errorMessage) {
				Swal.fire({
                            title: response.errorMessage,
                            text: '',
                            icon: 'error',
                             customClass: {
                                confirmButton: 'btn btn-primary w-xs mt-2',
                            }
                        });
			}
		},
		error: function(err){
			console.error(err);
			Swal.fire({
                            title: '에러발생',
                            text: '',
                            icon: 'error',
                             customClass: {
                                confirmButton: 'btn btn-primary w-xs mt-2',
                            }
                        });
		}
		
	})
}

function showInput(button){
	console.log("수정하기 버튼 클릭")
	console.log("editInput 상태 : ", $('#editInput').css('display'));
	 $(button).closest('.accordion-item').find('.editInput').css('display', 'table-row');
	console.log("editInput 상태 : ", $('#editInput').css('display'));
}

function submitEdit(editCareId){
	var $inputs = $('.editInput input[type="text"], .editInput input[type="hidden"]');
    var data = {};

    $inputs.each(function() {
        var name = $(this).attr('name');
        var value = $(this).val() || $(this).attr('placeholder');
        data[name] = value;
    });
    data.careId = editCareId;
    
	console.log("입력한 값들 : ",data);
	console.log("타입 : ", typeof(data));
	
	
	$.ajax({
		url: "./careerEdit.do",
		method: "get",
		data: data,
		success: function(response){
			 if (response.successMessage) {
				Swal.fire({
						        title: response.successMessage,
						        text: '',
						        icon: 'success',
						        customClass:{
						            confirmButton:'btn btn-primary w-xs mt-2'
						        }
						    }).then((result) => {
						       location.reload();
						});
		      }
		     if (response.errorMessage) {
				Swal.fire({
                            title: response.errorMessage,
                            text: '',
                            icon: 'error',
                             customClass: {
                                confirmButton: 'btn btn-primary w-xs mt-2',
                            }
                        });
			}
		},
		error: function(err){
			console.error(err);
			Swal.fire({
                            title: '에러발생',
                            text: '',
                            icon: 'error',
                             customClass: {
                                confirmButton: 'btn btn-primary w-xs mt-2',
                            }
                        });
		}
		
	})
}

function hideEditRow(cancel){
	console.log("취소 버튼 클릭");
	var $editInputRow = $(cancel).closest('.accordion-item').find('.editInput');
	$editInputRow.css('display', 'none');
    $editInputRow.find('input[type="text"]').val('');
}
