$(document).ready(function(){
	/* 한국어 */
});

if (document.querySelectorAll("[toast-list]") || document.querySelectorAll('[data-choices]') || document.querySelectorAll("[data-provider]")) {
	/** 에셋 */
  document.writeln("<script type='text/javascript' src='https://cdn.jsdelivr.net/npm/toastify-js'></script>");
  document.writeln("<script type='text/javascript' src='./assets/libs/choices.js/public/assets/scripts/choices.min.js'></script>");
  document.writeln("<script type='text/javascript' src='./assets/libs/flatpickr/flatpickr.min.js'></script>");
}

var CurrentLayoutAttributes = {};