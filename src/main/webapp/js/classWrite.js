$(document).ready(function() {
	$("form").on("submit", function(event) {
		event.preventDefault();

		let classTitle = $("#classTitle").val();
		let subjCode = $("#subjCode").val();
		let location = $("#location").val();
		let clasSueopNaljja = $("#clasSueopNaljja").val();
		let clasHuimangInwon = $("#clasHuimangInwon").val();
		let classContent = $("#classContent").val();
		let clasSeongbyeolJehan = $("#clasSeongbyeolJehan").val();
		let minAge = $("#minAge").val();
		let maxAge = $("#maxAge").val();
		let clasChoisoSugangnyo = $('#clasChoisoSugangnyo').val();
		let clasChoidaeSugangnyo = $('#clasChoidaeSugangnyo').val();

		if (!classTitle || classTitle === "클래스 제목을 입력하주세요") {
			Swal.fire({
				icon: 'error',
				title: '클래스 이름을 입력해주세요.',
				text: '',
			});
			return;
		}
		if (!subjCode) {
			Swal.fire({
				icon: 'error',
				title: '수강할 과목을 선택해주세요.',
				text: '',
			});
			return;
		}
		if (!location || location === "'동' 을 입력해 주세요") {
			Swal.fire({
				icon: 'error',
				title: '지역 정보를 입력해주세요.',
				text: '',
			});
			return;
		}
		if (!clasSueopNaljja) {
			Swal.fire({
				icon: 'error',
				title: '수업 날짜를 선택해주세요.',
				text: '',
			});
			return;
		}
		if (!clasHuimangInwon || clasHuimangInwon === "인원") {
			Swal.fire({
				icon: 'error',
				title: '희망 인원을 선택해주세요.',
				text: '',
			});
			return;
		}
		if (!classContent || classContent === "생성할 클래스에 대한 자세한 설명을 적어주세요") {
			Swal.fire({
				icon: 'error',
				title: '클래스 설명을 작성해주세요.',
				text: '',
			});
			return;
		}

		if (!clasSeongbyeolJehan || clasSeongbyeolJehan === "선택하세요") {
			clasSeongbyeolJehan = "GFREE";
			}else if(!clasSeongbyeolJehan || clasSeongbyeolJehan === "성별무관"){
			clasSeongbyeolJehan = "GFREE";
			}else if(!clasSeongbyeolJehan || clasSeongbyeolJehan === "남자만"){
			clasSeongbyeolJehan = "MONLY";
			}else clasSeongbyeolJehan = "FONLY";
			
		if (!minAge || minAge === "최소") {
			minAge = "0";
		}
		
		if (!maxAge || maxAge === "최소") {
			maxAge = "0";
		}
		
		if (!clasChoisoSugangnyo || clasChoisoSugangnyo === "최소 (1,000원 단위로 입력해주세요)") {
			clasChoisoSugangnyo = "0";
		}
		if (!clasChoidaeSugangnyo || clasChoidaeSugangnyo === "최대 (1,000원 단위로 입력해주세요)") {
			clasChoidaeSugangnyo = "0";
		}
		this.submit();
	});
});