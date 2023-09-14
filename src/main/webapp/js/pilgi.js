var boardSource = $("#board-list-template").html();
var boardTemplate = Handlebars.compile(boardSource);
var boardData = {
	"accountId":"계정", 
	"heart":"full", 
	"title":"제목",
	"regdate":"등록일",
	"likeCount":152,
	"viewCount":135
}
var boardItem = boardTemplate(boardData);
$(".list-group").append(boardItem);

var color = ["success","danger","warning","info","success"];
var subSource = $("#sub-list-template").html();
var subTemplate = Handlebars.compile(subSource);
var subData = {
	"color":color[0],
	"subject":"과목"
}
var subItem = subTemplate(subData);
$(".sub-list").append(subItem);

console.log($(".sub-list"));