$(function(){
	ajaxClass(1,1);
});

function ajaxClass(ppage, epage){
	$.ajax({
		url: "./myPageClass.do",
		type: "post",
		async: true,
		data: {
			"ppage": ppage,
			"epage": epage
		},
		dataType: "JSON",
		success: function(resp) {
			var pClass = $("#pClassTbody");
			var pClassList = resp.pClassList;
			pClass.empty();
			var html='';
			for(var i=0; i<pClassList.length; i++){
				
				var location = pClassList[i].clasLocation;
				var truncatedlocation = location.length > 12 ? location.substring(0, 10) + '...' : location;
				
				html ='';
				html+='<tr>';
				html+='    <th scope="row">';
				html+='        <div class="form-check">';
				html+='            <span class="bg-success badge me-2" style="margin-left:10px; margin-top:20px;">'+pClassList[i].clasStatus+'</span>';
				html+='        </div>';
				html+='    </th>';
				html+='    <td class="title"><a href="justGoMyClass.do?clasId='+pClassList[i].clasId+'" class="subject text-body">'+pClassList[i].clasTitle+'</a></td>';
				html+='    <td class="location"><a class="text-muted text-uppercase fs-13 mt-1" data-toggle="tooltip" title="' + location + '">' + truncatedlocation + '</a></td>';
				html+='    <td class="classDay">'+pClassList[i].clasSueopNaljja+'</td>';
				html += '<td class="instr">' + (pClassList[i].clasAccountId ? pClassList[i].clasAccountId : "매칭 미완료") + '</td>';
				html+='</tr>';
				pClass.append(html);
			}
			
			var pPaging = $("#pPaging");
			var ppVo = resp.ppVo;
			var epVo = resp.epVo
			pPaging.empty();
			html ='';
			html += '           <nav aria-label="...">';
			html += '               <ul class="pagination mb-0">';
			if(ppVo.page>1){
				html += '	              <li class="page-item">';
				html += '	                  <a class="page-link" href="javascript:ajaxClass(1,'+epVo.page+');"><i class="bx bxs-chevrons-left"></i></a>';
				html += '	              </li>';
			} else{
				html += '	              <li class="page-item disabled">';
				html += '	                  <span class="page-link"><i class="bx bxs-chevrons-left"></i></span>';
				html += '	              </li>';       
			}
			if(ppVo.page - ppVo.countPage>0){
				html += '			          		<li class="page-item">';
				html += '                              	<a class="page-link" href="javascript:ajaxClass('+(ppVo.startPage-ppVo.countPage)+','+epVo.page+')"><i class="bx bxs-chevron-left"></i></a></a>';
				html += '                           </li>';
			}else{
				html += '							<li class="page-item disabled">';
				html += '                              	<span class="page-link"><i class="bx bxs-chevron-left"></i></span>';
				html += '                           </li>';
			}
			for(let i=ppVo.startPage; i<=ppVo.endPage;i++){
				if(ppVo.page==i){
					html += '				                <li class="page-item active">';
					html += '				                    <span class="page-link">'+i+'</span>';
					html += '				                </li>';
				} else{
					html += '				                <li class="page-item">';
					html += '				                    <a class="page-link" href="javascript:ajaxClass('+i+','+epVo.page+');">'+i+'</a>';
					html += '				                </li>';
				}
			}
			if(ppVo.totalPage > (ppVo.page+ppVo.countPage)){
				html += '					        <li class="page-item">';
				html += '					            <a class="page-link" href="javascript:ajaxClass('+(ppVo.startPage+ppVo.countPage)+','+epVo.page+');"><i class="bx bxs-chevron-right"></i></a>';
				html += '					        </li>';
			}else{
				html += '					        <li class="page-item disabled">';
				html += '					            <span class="page-link"><i class="bx bxs-chevron-right"></i></span>';
				html += '					        </li>';
			}
			if(ppVo.page==ppVo.totalPage){
				html += '	                       <li class="page-item disabled">';
				html += '	                            <span class="page-link"><i class="bx bxs-chevrons-right"></i></span>';
				html += '	                        </li> ';
			}else{
				html += '	                    	 <li class="page-item">';
				html += '	                            <a class="page-link" href="javascript:ajaxClass('+ppVo.totalPage+','+epVo.page+');"><i class="bx bxs-chevrons-right"></i></a>';
				html += '	                        </li>';
			}
			html += '               </ul>                                                                                                                                              ';
			html += '             </nav> ';
			pPaging.append(html);
			
			
			//종료 클래스 페이지
			var eClsss = $("#eClassTbody");
			var eClassList = resp.eClassList;
			eClsss.empty();
			for(var i=0; i<eClassList.length; i++){
				
				var location = eClassList[i].clasLocation;
				var truncatedlocation = location.length > 12 ? location.substring(0, 10) + '...' : location;
				
				html ='';
				html+='<tr>';
				html+='    <th scope="row">';
				html+='        <div class="form-check">';
				html+='            <span class="bg-danger badge me-2" style="margin-left:10px; margin-top:20px;">'+eClassList[i].clasStatus+'</span>';
				html+='        </div>';
				html+='    </th>';
				html+='    <td class="title"><a href="justGoMyClass.do?clasId='+eClassList[i].clasId+'" class="subject text-body">'+eClassList[i].clasTitle+'</a></td>';
				html+='    <td class="location"><a class="text-muted text-uppercase fs-13 mt-1" data-toggle="tooltip" title="' + location + '">' + truncatedlocation + '</a></td>';
				html+='    <td class="classDay">'+eClassList[i].clasSueopNaljja+'</td>';
				html+='    <td class="instr">' + (eClassList[i].clasAccountId ? eClassList[i].clasAccountId : "강사일반테스트닉네임999") + '</td>';
				html+='    <td class="buttons">';
				
				html += '<button type="button" class="btn btn-secondary me-2" onclick="location.href=\'./communityWriteForm.do?board=pilgi&id=' + eClassList[i].clasId + '\'" ' + (eClassList[i].clchPilgiStatus === 'Y' ? 'disabled' : '') + '>필기</button>';
				html += '<button type="button" class="btn btn-primary me-2" onclick="reivewForm(' + eClassList[i].clasId + ')" ' + (eClassList[i].clchReviewStatus === 'Y' ? 'disabled' : '')  + '>후기</button>';

				html+='    </td>';
				html+='</tr>';
				eClsss.append(html);
			}
			
			var ePaging = $("#ePaging");
			ePaging.empty();
			html ='';
			html += '           <nav aria-label="...">';
			html += '               <ul class="pagination mb-0">';
			if(epVo.page>1){
				html += '	              <li class="page-item">';
				html += '	                  <a class="page-link" href="javascript:ajaxClass('+ppVo.page+',1);"><i class="bx bxs-chevrons-left"></i></a>';
				html += '	              </li>';
			} else{
				html += '	              <li class="page-item disabled">';
				html += '	                  <span class="page-link"><i class="bx bxs-chevrons-left"></i></span>';
				html += '	              </li>';       
			}
			if(epVo.page - epVo.countPage>0){
				html += '			          		<li class="page-item">';
				html += '                              	<a class="page-link" href="javascript:ajaxClass('+ppVo.page+','+(epVo.startPage-epVo.countPage)+')"><i class="bx bxs-chevron-left"></i></a>';
				html += '                           </li>';
			}else{
				html += '							<li class="page-item disabled">';
				html += '                              	<span class="page-link"><i class="bx bxs-chevron-left"></i></span>';
				html += '                           </li>';
			}
			for(let i=epVo.startPage; i<=epVo.endPage;i++){
				if(epVo.page==i){
					html += '				                <li class="page-item active">';
					html += '				                    <span class="page-link">'+i+'</span>';
					html += '				                </li>';
				} else{
					html += '				                <li class="page-item">';
					html += '				                    <a class="page-link" href="javascript:ajaxClass('+ppVo.page+','+i+');">'+i+'</a>';
					html += '				                </li>';
				}
			}
			if(epVo.totalPage > (epVo.page+ppVo.countPage)){
				html += '					        <li class="page-item">';
				html += '					            <a class="page-link" href="javascript:ajaxClass('+ppVo.page+','+(epVo.startPage+epVo.countPage)+');"><i class="bx bxs-chevron-right"></i></a>';
				html += '					        </li>';
			}else{
				html += '					        <li class="page-item disabled">';
				html += '					            <span class="page-link"><i class="bx bxs-chevron-right"></i></span>';
				html += '					        </li>';
			}
			if(epVo.page==epVo.totalPage){
				html += '	                       <li class="page-item disabled">';
				html += '	                            <span class="page-link"><i class="bx bxs-chevrons-right"></i></span>';
				html += '	                        </li> ';
			}else{
				html += '	                    	 <li class="page-item">';
				html += '	                            <a class="page-link" href="javascript:ajaxClass('+ppVo.page+','+epVo.totalPage+');"><i class="bx bxs-chevrons-right"></i></a>';
				html += '	                        </li>';
			}
			html += '               </ul>                                                                                                                                              ';
			html += '             </nav> ';
			ePaging.append(html);
		},
		error:{
		}
	});
}

function reivewForm(clasId){
	var url = "./reviewWriteForm.do?classId="+clasId;
	var title = "후기 등록";
	var width = 650;
    var height = 600;

    // 화면 중앙에 위치시키기 위한 좌표 계산
    var left = (screen.width/2)-(width/2);
    var top = (screen.height/2)-(height/2);

    // window.open 함수에 위치와 크기를 지정
    window.open(url, title, 'width='+width+', height='+height+', top='+top+', left='+left);
}