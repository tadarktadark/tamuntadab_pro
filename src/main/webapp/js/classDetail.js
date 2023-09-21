$(document).ready(function() {
  var $sidebar = $('.email-leftbar');
  var $window = $(window);
  var offset = $sidebar.offset();
  var topPadding = 120;

  $window.scroll(function() {
    if ($window.scrollTop() > offset.top) {
      $sidebar.stop().animate({
        marginTop: $window.scrollTop() - offset.top + topPadding
      });
    } else {
      $sidebar.stop().animate({
        marginTop: 0
      });
    }
  });
});

$(function(){
  // 로깅을 위해 받아온 값들을 콘솔에 출력
  console.log("받아 온 객체들 :", genJehan, userGender, userAge, naiJehan, isSession);

  // 세션이 있는지 여부를 확인
  var hasSession = isSession !== null && isSession !== undefined && isSession !== '';

  // 성별 제한을 확인
  var genderValid = (genJehan === 'GFREE') || (genJehan === userGender);

  // 나이 제한을 확인
  var ageValid = true;
  if (naiJehan !== null && naiJehan !== '') {
    var ageLimits = naiJehan.split(':');
    var minAge = parseInt(ageLimits[0], 10);
    var maxAge = parseInt(ageLimits[1], 10);
    ageValid = (userAge >= minAge && userAge <= maxAge);
  }

  // 모든 조건을 만족하는지 확인
  var isValid = hasSession && genderValid && ageValid;

  // 버튼 활성화/비활성화
  if (isValid) {
    $("#joinButton").removeAttr("disabled");
  } else {
    $("#joinButton").attr("disabled", "disabled");
  }

  console.log("IsValid: ", isValid);  // 결과 로깅
});
