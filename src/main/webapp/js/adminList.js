//$(document).ready({
//	//핸들바 템플릿 가져오기
//	var source = $("#entry-template").html(); 
//	
//	//핸들바 템플릿 컴파일
//	var template = Handlebars.compile(source); 
//	
//	//핸들바 템플릿에 바인딩할 데이터
//	var data = {
//	    	users: [
//	            { seq: "홍길동1", name: "aaa1", lastAccessDate: "aaa1@gmail.com" ,auth:"총관리자"},
//	            { seq: "홍길동1", name: "aaa1", lastAccessDate: "aaa1@gmail.com" ,auth:"총관리자"},
//	            { seq: "홍길동1", name: "aaa1", lastAccessDate: "aaa1@gmail.com" ,auth:"총관리자"},
//	            { seq: "홍길동1", name: "aaa1", lastAccessDate: "aaa1@gmail.com" ,auth:"총관리자"},
//	            { seq: "홍길동1", name: "aaa1", lastAccessDate: "aaa1@gmail.com" ,auth:"총관리자"},
//	        ]
//	}; 
//	
//	//핸들바 템플릿에 데이터를 바인딩해서 HTML 생성
//	var html = template(data);
//	
//	//생성된 HTML을 DOM에 주입
//	$('.table-responsive').append(html);
//
//})

//var source, template, data, html;
//
//$(document).ready(function() {
//    source = $("#entry-template").html();
//    template = Handlebars.compile(source);
//    data = {
//	    	users: [
//	            { seq: "홍길동1", name: "aaa1", lastAccessDate: "aaa1@gmail.com" ,auth:"총관리자"},
//	            { seq: "홍길동1", name: "aaa1", lastAccessDate: "aaa1@gmail.com" ,auth:"총관리자"},
//	            { seq: "홍길동1", name: "aaa1", lastAccessDate: "aaa1@gmail.com" ,auth:"총관리자"},
//	            { seq: "홍길동1", name: "aaa1", lastAccessDate: "aaa1@gmail.com" ,auth:"총관리자"},
//	            { seq: "홍길동1", name: "aaa1", lastAccessDate: "aaa1@gmail.com" ,auth:"총관리자"},
//	        ]    };
//    html = template(data);
//    $('.table-responsive').append(html);
//});

// 여기서 source, template, data, html 변수를 사용할 수 있습니다.
