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

function updateB(){
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

function updateContent(){
	
}
