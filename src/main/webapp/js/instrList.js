$(document).ready(function() {
	    $('#orderSelect').change(function() {
	        var order = $(this).val();
	        
	        $.ajax({
	            url: './instrView.do',
	            method: 'GET',
	            data: { order: order },
	            beforeSend: function(xhr){
			        xhr.setRequestHeader('Accept', 'html/text;charset=utf-8');
			    },
	            success: function(data) {
				console.log("조회순 바꾸기 성공!")
				console.log(data);
	            	var html = '';
	                var lists = JSON.parse(data);
	                for (var i = 0; i < lists.length; i++) {
	                    var instr = lists[i];
	            		console.log("instr:::::",instr);
	            html +="	<div class='col-xxl-3 col-md-6'>                                                                                                   ";
				html +="	<div class='card team-box'>                                                                                                        ";
				html +="		<div class='card-body p-4'>                                                                                                    ";
				html +="			<div class='row mb-3'>                                                                                                     ";
				html +="				<div class='col'>                                                                                                      ";
				html +="					<div class='flex-shrink-0 me-2'>   ";
				if (instr.inprCert == 'S') {
					html +="						<span                                                                                                          ";
					html +="							class='badge bg-success-subtle text-success member-designation me-2'>                                      ";
					html +="							<i class=' bx bxs-user-check' style='font-size: 20px;'></i>                                                ";
					html +="						</span>                                                                                                        ";
				}
				html +="					</div>                                                                                                             ";
				html +="				</div>                                                                                                                 ";
                html +="                                                                                                                                       ";
				html +="				<div class='col-auto text-end dropdown'>                                                                               ";
				html +="<span>&nbsp;</span>";
				 if (instr.ingi == 'HOT') {
                        html += "<span class='badge bg-danger-subtle text-danger member-designation me-2' style='font-size: 15px;'>HOT</span>";
                    }
				 if (instr.reviewCount > 0) {
                        html += "<span class='badge bg-info-subtle text-info member-designation me-2' style='font-size: 15px;'>후기있음</span>";
                    }
				html +="				</div>                                                                                                                 ";
				html +="			</div>                                                                                                                     ";
				html +="			<div class='text-center mb-3'>                                                                                             ";
				html +="				<div class='avatar-md mx-auto'>                                                                                        ";
				var userinfo = instr.userProfileVo[0].userAccountId;
				html += `<img src="${userinfo ? instr.userProfileVo[0].userProfileFile : 'assets/images/users/avatar-9.jpg'}" alt='' class='member-img img-fluid d-block rounded-circle'>`;
				html +="				</div>                                                                                                                 ";
				html +="			</div>                                                                                                                     ";
				html +="			<div class='text-center'>                                                                                                  ";
				html +="				<a href='' class='member-name'>                                                                                        ";
				if (instr.userProfileVo && instr.userProfileVo.length > 0) {
				    html += `<h5 class='fs-16 mb-1'>${instr.userProfileVo[0].userNickname}</h5>`;
				} else {
				    console.error('userProfileVo is not defined or empty:', instr);
				}
				html +="				</a>                                                                                                                   ";
				
				html +="				<div class='row'>                                                                                                      ";
				html +="					<div class='col-6'>                                                                                                ";
				html +="						<div class='mt-3'>                                                                                             ";
				html +="							<p class='mb-0 text-muted'>전문분야</p>                                                                    ";
				html +="							<h5 class='mt-1 fs-16 mb-0 text-truncate'>                      ";
				var subjectsMajorTitleList = instr.subjectsMajorTitle.split(',');
                            html += subjectsMajorTitleList[0].trim();
				html +="							</h5>                       ";
				html +="						</div>                                                                                                         ";
				html +="					</div>                                                                                                             ";
				html +="					<div class='col-6'>                                                                                                ";
				html +="						<div class='mt-3'>                                                                                             ";
				html +="							<p class='mb-0 text-muted'>나이</p>                                                                        ";
				html +="							<h5 class='mt-1 fs-16 mb-0 text-truncate'>"+instr.inprAge+"</h5>                                            ";
				html +="						</div>                                                                                                         ";
				html +="					</div>                                                                                                             ";
				html +="				</div>                                                                                                                 ";
				html +="			</div>                                                                                                                     ";
				html +="		</div>                                                                                                                         ";
				html +="		<div class='card-footer pt-3 border-top px-4'>                                                                                 ";
				html +="		                                                                                                                               ";
				html +="			<p class='mb-1 text-muted text-truncate'>                                                                                  ";
				html +="				<i class='mdi mdi-book'></i>전문분야:                                                                                  ";
				   for(var j=0; j<subjectsMajorTitleList.length; j++){
					html += subjectsMajorTitleList[j].trim();
					 if(j != subjectsMajorTitleList[j].length - 1){
					        html += ' ';  // 쉼표와 뒤이어 공백 한 칸 추가
					    }
				    }
				html +="			</p>                                                                                                                       ";
				html +="			<p class='mb-1 text-muted text-truncate'>                                                                                  ";
				html +="				<i class='mdi mdi-book'></i>가능한 과목:                                                                               ";
				var subjectsTitleList = instr.subjectsTitle.split(',');
				   for(var k=0; k<subjectsTitleList.length; k++){
					html += subjectsTitleList[k].trim();
					if(j != subjectsTitleList[k].length - 1){
					        html += ' ';  // 쉼표와 뒤이어 공백 한 칸 추가
					    }
				    }
				html +="			</p>                                                                                                                       ";
				html +="		</div>                                                                                                                         ";
				html +="	</div>                                                                                                                             ";
				html +="</div>                                                                                                                                 ";
	                }	
	            $('.output-area').html(html);	
	            	
	            },
	            error: function(jqXHR, textStatus, errorThrown) {
	                console.log(textStatus, errorThrown);
	            }
	        });
	    });
	});