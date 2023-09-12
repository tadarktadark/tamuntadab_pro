/* ���� ���� */
function addEduLevel(){
	var url = "./eduLevelForm.do";
	var title = "�з� ���� �Է�";
	var width = 700;
    var height = 700;

    // ȭ�� �߾ӿ� ��ġ��Ű�� ���� ��ǥ ���
    var left = (screen.width/2)-(width/2);
    var top = (screen.height/2)-(height/2);

    // window.open �Լ��� ��ġ�� ũ�⸦ ����
    window.open(url, title, 'width='+width+', height='+height+', top='+top+', left='+left);
    

}

	function searchElastic(query, callback) {
		$.ajax({
			url : 'http://192.168.8.164:9200/subject_tag/_search',
			type : 'POST',
			dataType : 'json',
			headers : {
				'Content-Type' : 'application/json',
				'Authorization' : 'Basic ' + btoa('elastic:elastic')
			},
			data : JSON.stringify({
				suggest : {
					"title-suggestion" : {
						"prefix" : query,
						"completion" : {
							"field" : "title"
						}
					}
				}
			}),
			success : function(response) {
				var results = response.suggest['title-suggestion'][0].options
						.map(function(option) {
							return {
								label : option._source.title[0],
								value : option._source.code
							};
						});

				callback(results);
			},
			error : function() {
				callback([]);
			}
		});
	}

	$(function() {
		$("#inprSubjects")
				.autocomplete(
						{
							minLength : 1,
							source : function(request, response) {
								searchElastic(request.term, function(results) {
									response(results);
								});
							},
							focus : function() {
								return false;
							},
							select : function(event, ui) {
								this.value = '';

								// ���õ� ������ �߰��� div ���
								var $selectedSubjects = $('#selectedSubjects');

								// �̹� �߰��� ������ Ȯ��
								if ($selectedSubjects.find('li[data-value="'
										+ ui.item.value + '"]').length > 0) {
									return false;
								}

								// ���ο� div ��� ���� �� �߰�
								var $div = $('<div>')
										.addClass(
												'choices choices__item choices__item--selectable')
										.attr('data-value', ui.item.value)
										.attr('data-type', 'select-multiple')
										.text(ui.item.label).appendTo(
												$selectedSubjects);

								// ���� ��ư ���� �� �߰�
								var $removeButton = $('<button>').addClass(
										'choices__button').attr('aria-label',
										"Remove item: '" + ui.item.label + "'")
										.attr('data-button', '').text(
												"Remove item").on('click',
												function() {
													$div.remove();
												}).appendTo($div);

								return false;
							},
						}).data("ui-autocomplete")._renderItem = function(ul,
				item) {
			return $("<li>").append("<a>" + item.label + "</a>").appendTo(ul);
		};
		$("#inprSubjectsMajor")
				.autocomplete(
						{
							minLength : 1,
							source : function(request, response) {
								searchElastic(request.term, function(results) {
									response(results);
								});
							},
							select : function(event, ui) {
								this.value = '';

								var $selectedSubjectsMajor = $('#selectedSubjectsMajor');

								if ($selectedSubjectsMajor
										.find('div[data-value="'
												+ ui.item.value + '"]').length > 0) {
									return false;
								}

								var $div = $('<div>')
										.addClass(
												'choices choices__item choices__item--selectable')
										.attr('data-value', ui.item.value)
										.attr('data-type', 'select-multiple')
										.text(ui.item.label).appendTo(
												$selectedSubjectsMajor);

								var $removeButton = $('<button>').addClass(
										'choices__button').attr('aria-label',
										"Remove item: '" + ui.item.label + "'")
										.attr('data-button', '').text(
												"Remove item").on('click',
												function() {
													$div.remove();
												}).appendTo($div);

								return false;
							},
						}).data("ui-autocomplete")._renderItem = function(ul,
				item) {
			return $("<li>").append("<a>" + item.label + "</a>").appendTo(ul);
		};
		
		$('#inprSubjects').on('keydown', function(event) {
		    if (event.key === 'Enter') {
		        event.preventDefault();
		        return false;
		    }
		});
		
		$('#inprSubjectsMajor').on('keydown', function(event) {
		    if (event.key === 'Enter') {
		        event.preventDefault();
		        return false;
		    }
		});
	});

$(function(){
	// ���ڼ� �����ϱ�
	$("#inprIntro").on('input', function () {
        var text_length = $(this).val().length;
        if(text_length > 100) {
            alert('100�ڱ����� �ۼ� �����մϴ�.');
            $(this).val($(this).val().substring(0, 100));
        } else {
            $(".introSpan").text(text_length);
        }
    });
    //�ּ� ������ ����
    $('#inprFee').on('input', function () {
        if ($(this).val().length > 4) {
            $(this).val($(this).val().slice(0, 4));
        }
    });
})