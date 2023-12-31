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
  console.log("받아 온 객체들 :", genJehan, userGender, userAge, naiJehan, isSession, isJoined);

  // 성별 제한을 확인
  var genderValid = (genJehan === 'GFREE') || (genJehan.substring(0,1) === userGender);

  // 나이 제한을 확인
  var ageValid = true;
  if (naiJehan !== null && naiJehan !== '') {
    var ageLimits = naiJehan.split(':');
    var minAge = parseInt(ageLimits[0], 10);
    var maxAge = parseInt(ageLimits[1], 10);
    ageValid = (userAge >= minAge && userAge <= maxAge);
  }

  var joined = (isJoined=='true')?true:false;

  // 모든 조건을 만족하는지 확인
  var isValid = isSession && genderValid && ageValid;

  console.log(isValid, isSession, genderValid, ageValid, joined);

  

  if (joined) {
    // 모든 이벤트 리스너와 데이터 속성 제거
    $('#joinButton').off().removeAttr('data-bs-toggle').removeAttr('data-bs-target');
    
    // 버튼 텍스트 변경
    $('#joinButton').html('클래스로 이동');
    
    // 새로운 클릭 이벤트 추가
    $('#joinButton').on('click', function() {
      window.location.href = `./justGoMyClass.do?clasId=`+clasId;
    });
  }else{
    // a태그 클릭 이벤트를 조작
  $("#joinButton").off('click').on('click', function(event){
    if (!isValid) {
      event.preventDefault();
      alert('조건을 만족하지 않아 참가할 수 없습니다.');
    }
  });
  // 버튼 활성화/비활성화
  if (isValid) {
    $("#joinButton").removeClass("disabled");
  } else {
    $("#joinButton").addClass("disabled");
  }
  if (!isSession){
    $("#sidabar-messange").append('<h5 class="mt-3 fs-15 text-uppercase" style="color: red;">로그인 후 참여 가능합니다</h5>')
  }else if(!genderValid){
    $("#sidabar-messange").append('<h5 class="mt-3 fs-15 text-uppercase" style="color: red;">성별이 제한된 클래스입니다</h5>')
  }else if(!ageValid){
    $("#sidabar-messange").append('<h5 class="mt-3 fs-15 text-uppercase" style="color: red;">연령이 제한된 클래스입니다</h5>')
  }
  }
});