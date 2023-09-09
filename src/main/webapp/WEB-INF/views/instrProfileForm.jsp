<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-layout="horizontal" data-layout-mode="light" data-topbar="light" data-sidebar="light" data-sidebar-size="sm" data-sidebar-image="none" data-preloader="disable">
<head>
<meta charset="UTF-8">
<title>${title} | 타문타답</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<%@ include file="./shared/_head_css.jsp" %>
</head>
<body class="twocolumn-panel">
	<div id="layout-wrapper">
		<%@ include file="./shared/_topbar.jsp" %>
		<%@ include file="./shared/_sidebar.jsp" %>
	
		<div class="main-content">
			<div class="page-content">
				<div class="container-fluid">
					<%@ include file="./shared/_page_title.jsp" %>
				</div>
				<div class="card">
                                <div class="card-header">
                                    <h4 class="card-title mb-0">Choices</h4>
                                </div><!-- end card header -->

                                <div class="card-body">
                                <div>
								    <label for="formtextInput" class="form-label">한줄 소개</label>
								    <input type="password" class="form-control" id="formtextInput">
								    <div id="passwordHelpBlock" class="form-text">
								        100자 이내로 작성해주세요
								    </div>
								</div>
								<div>
									<label for="formtextInput" class="form-label">학력</label>
									<br>
									<button type="button" class="btn btn-secondary custom-toggle active" data-bs-toggle="button">
									    <span class="icon-on"><i class="ri-user-add-line align-bottom me-1"></i> 학력추가</span>
									    <span class="icon-off"><i class="ri-check-fill align-bottom me-1"></i> 학력추가</span>
									</button>
                                </div>
                                <br>
                                <div>
								  <label for="formtextInput" class="form-label">가능한 과목</label>
								  <div class="choices__inner choices" data-type="select-one">
									  <input type="search" id="inprSubjects" name="inprSubjects" class="choices__input choices__input--cloned">
									  <!-- <input type="text" id="inprSubjects" name="inprSubjects"> -->
									  <div id="selectedSubjects" class="choices__list choices__list--multiple"></div>
								  </div>
								</div>
								
                                </div><!-- end card-body -->
                            </div>
			</div>
			<%@ include file="./shared/_footer.jsp" %>
		</div>
	</div>	
	<%@ include file="./shared/_vender_scripts.jsp" %>
</body>
<script type="text/javascript">
function searchElastic(query, callback) {
	  $.ajax({
	    url: 'http://192.168.8.164:9200/subject_tag/_search',
	    type: 'POST',
	    dataType: 'json',
	    headers: {
	      'Content-Type': 'application/json',
	      'Authorization': 'Basic ' + btoa('elastic:elastic')
	    },
	    data: JSON.stringify({
	      suggest: {
	        "title-suggestion": {
	          "prefix": query,
	          "completion": {
	            "field": "title"
	          }
	        }
	      }
	    }),
	    success: function(response) {
	      var results = response.suggest['title-suggestion'][0].options.map(function(option) { 
	        return {
	          label: option._source.title[0],
	          value: option._source.code
	        };
	      });

	      callback(results);
	    },
	    error: function() {
	      callback([]);
	    }
	  });
	}
	
$(function() {
	  $("#inprSubjects").autocomplete({
	    minLength: 1,
	    source: function(request, response) {
	      searchElastic(request.term, function(results) {
	        response(results);
	      });
	    },
	    focus: function() {
	      return false;
	    },
	    select: function(event, ui) {
	        this.value = '';
	        
	        // 선택된 값들이 추가될 ul 요소
	        var $selectedSubjects = $('#selectedSubjects');
	        
	        // 이미 추가된 값인지 확인
	        if ($selectedSubjects.find('li[data-value="' + ui.item.value + '"]').length > 0) {
	          return false;
	        }
	        
	        // 새로운 div 요소 생성 및 추가
	        var $div = $('<div>')
	        .addClass('choices choices__item choices__item--selectable')
	        .attr('data-value', ui.item.value)
	        .attr('data-type','select-multiple')
	        .text(ui.item.label)
	        .appendTo($selectedSubjects);
        
		     // 삭제 버튼 생성 및 추가
		     var $removeButton = $('<button>')
		       .addClass('choices__button')
		       .attr('aria-label', "Remove item: '" + ui.item.label + "'")
		       .attr('data-button', '')
		       .text("Remove item")
		       .on('click', function() { $div.remove(); })
		       .appendTo($div);
		
			       return false;
			}
	  }).data("ui-autocomplete")._renderItem = function(ul, item){
		   return $("<li>")
		     .addClass("form-select")
		     .attr('size', '3')
		     .append("<a>" + item.label + "</a>")
		     .appendTo(ul);
		 };
	});
</script>
</html>