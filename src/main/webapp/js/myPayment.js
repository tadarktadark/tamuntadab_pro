$(function(){
	getClassPaymentList(1);
});

$(function(){
	getYeyakPaymentList(1);
});

function getClassPaymentList(page){
	
	$.ajax({
		url: "./getClassPaymentList.do",
		type: "post",
		async: true,
		data: {
			page: page
		},
		dataType: "JSON",
		success: function(resp){
			var classPayTbody = $("#classPayTbody");
			var classPayList = resp.gVoList;
			classPayTbody.empty();
			var html = '';
			for(var i=0; i<classPayList.length; i++){
				
				var gyeoStatus = classPayList[i].gyeoStatus;
				 switch (gyeoStatus) {
		            case 'W':
		                gyeoStatus = '결제 대기중';
		                badgeColor = 'warning'; // 결제 대기중은 warning
		                break;
		            case 'C':
		                gyeoStatus = '취소됨';
		                badgeColor = 'danger'; // 취소됨은 danger
		                break;
		            case 'P':
		                gyeoStatus = '결제완료';
		                badgeColor = 'success'; // 결제완료는 success
		                break;
		            case 'R':
		                gyeoStatus = '환불됨';
		                badgeColor = 'info'; // 환불됨은 info
		                break;
		            default:
		                console.error('알 수 없는 gyeoStatus 값입니다: ' + gyeoStatus);
		        }
				
				html ='';
				html+='<tr>';
				html+='    <th scope="row">';
		        html+='        <div class="form-check">';
		        html+='            <span class="bg-' + badgeColor + ' badge me-2" style="margin-left:10px; margin-top:20px;">'+gyeoStatus+'</span>'; // Badge 색상 적용
		        html+='        </div>';
		        html+='    </th>';
				html+='    <td class="title"><a href="justGoMyClass.do?clasId='+classPayList[i].clasVo[0].clasId+'" class="subject text-body">'+classPayList[i].clasVo[0].clasTitle+'</a></td>';
				html+='    <td class="location"><span class="text-muted text-uppercase fs-13 mt-1">' + classPayList[i].gyeoGeumaek + '</span></td>';
				html+='    <td class="classDay">'+classPayList[i].gyeoWanryoil+'</td>';
				html+='    <td class="buttons">';
				if(gyeoStatus == '결제 대기중'){
					html += '<button type="button" class="btn btn-primary me-2" onclick="location.href=\'./payment.do?clasId=' + classPayList[i].clasVo[0].clasId + '\'">결제하기</button>';
				}else{
					html += '<button type="button" class="btn btn-primary me-2" onclick="#" disabled>결제하기</button>';
				}
				html+='    </td>';
				html+='</tr>';
				classPayTbody.append(html);
			}
		},
		error:{
		}
	})
	
}

function getYeyakPaymentList(page){
	$.ajax({
		url: "./getYeyakPaymentList.do",
		type: "post",
		async: true,
		data: {
			page: page
		},
		dataType: "JSON",
		success: function(resp){
			var rentPayTbody = $("#rentPayTbody");
			var rentPayList = resp.gVoList;
			rentPayTbody.empty();
			var html = '';
			for(var i=0; i<rentPayList.length; i++){
				var gyeoStatus = rentPayList[i].gyeoStatus;
				 switch (gyeoStatus) {
		            case 'W':
		                gyeoStatus = '결제 대기중';
		                badgeColor = 'warning'; // 결제 대기중은 warning
		                break;
		            case 'C':
		                gyeoStatus = '취소됨';
		                badgeColor = 'danger'; // 취소됨은 danger
		                break;
		            case 'P':
		                gyeoStatus = '결제완료';
		                badgeColor = 'success'; // 결제완료는 success
		                break;
		            case 'R':
		                gyeoStatus = '환불됨';
		                badgeColor = 'info'; // 환불됨은 info
		                break;
		            default:
		                console.error('알 수 없는 gyeoStatus 값입니다: ' + gyeoStatus);
		        }
		        
		        var dateStr = rentPayList[i].yeyakVo[0].gayeYeyakDate;
				var timeStr = rentPayList[i].yeyakVo[0].gayeStartTime;
				
				var formattedDate = dateStr.substring(2, 4) + "년 " +
				                    dateStr.substring(4, 6) + "월 " +
				                    dateStr.substring(6, 8) + "일 ";
				                    
				var formattedTime = timeStr.substring(0, 2) + "시 " +
				                    timeStr.substring(2, 4) + "분";
				
				html ='';
				html+='<tr>';
				html+='    <th scope="row">';
		        html+='        <div class="form-check">';
		        html+='            <span class="bg-' + badgeColor + ' badge me-2" style="margin-left:10px; margin-top:20px;">'+gyeoStatus+'</span>'; // Badge 색상 적용
		        html+='        </div>';
		        html+='    </th>';
				html+='    <td class="title">'+rentPayList[i].gasiVo[0].gacoName+rentPayList[i].gasiVo[0].gagaName+'</td>';
				html+='    <td class="amount">'+rentPayList[i].gyeoGeumaek+'</td>';
				html+='    <td class="location"><span class="text-muted text-uppercase fs-13 mt-1">' + formattedDate + formattedTime + '</span></td>';
				html+='    <td class="classDay">'+rentPayList[i].yeyakVo[0].gayeHours+'시간'+'</td>';
				html+='    <td class="buttons">';
				if(gyeoStatus == '결제 대기중'){
					html += '<button type="button" class="btn btn-primary me-2" onclick="location.href=\'./rentPayment.do?gayeId=' + rentPayList[i].yeyakVo[0].gayeId + '\'">결제하기</button>';
				}else{
					html += '<button type="button" class="btn btn-primary me-2" onclick="#" disabled>결제하기</button>';
				}
				html+='    </td>';
				html+='</tr>';
				rentPayTbody.append(html);
			}
		},
		error:{
			
		}
	})
}