$(document).ready(function() {
	$("form").on("submit", function(event) {
		event.preventDefault();

		let classTitle = $("#classTitle").val();
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
		} else if (!clasSeongbyeolJehan || clasSeongbyeolJehan === "성별무관") {
			clasSeongbyeolJehan = "GFREE";
		} else if (!clasSeongbyeolJehan || clasSeongbyeolJehan === "남자만") {
			clasSeongbyeolJehan = "MONLY";
		} else clasSeongbyeolJehan = "FONLY";

		if (!minAge || minAge === "최소") {
			minAge = "0";
		}

		if (!maxAge || maxAge === "최소") {
			maxAge = "0";
		}

		if (!clasChoisoSugangnyo || clasChoisoSugangnyo === "최소 (1,0000원 단위로 입력해주세요)") {
			clasChoisoSugangnyo = "0";
		}
		if (!clasChoidaeSugangnyo || clasChoidaeSugangnyo === "최대 (1,0000원 단위로 입력해주세요)") {
			clasChoidaeSugangnyo = "0";
		}
		this.submit();
	});
});

let subjectCount = 1;
const maxSubjects = 3; // 최대 과목 입력란 개수

const addButton = document.querySelector('.btn-plus');
const minusButton = document.querySelector('.btn-minus');

addButton.addEventListener('click', () => {
	if (subjectCount < maxSubjects) {
		subjectCount++;
		const newInput = document.createElement('div');
		newInput.innerHTML = `
        <input type="text" name="subject${subjectCount}" class="form-control" id="subject${subjectCount}" placeholder="과목" style="margin-top:10px;">
      `;
		document.querySelector('.input-group').appendChild(newInput);

		if (subjectCount === maxSubjects) {
			addButton.disabled = true;
		}

		if (subjectCount > 1) {
			minusButton.disabled = false;
		}
	}
});

minusButton.addEventListener('click', () => {
	if (subjectCount > 1) {
		const inputToRemove = document.querySelector(`#subject${subjectCount}`);
		inputToRemove.parentNode.removeChild(inputToRemove);
		subjectCount--;

		if (subjectCount < maxSubjects) {
			addButton.disabled = false;
		}

		if (subjectCount === 1) {
			minusButton.disabled = true;
		}
	}
});


//날짜 유효값 처리
document.addEventListener("DOMContentLoaded", function() {
	const dateInput = document.getElementById("clasSueopNaljja");

	dateInput.addEventListener("change", function() {
		const selectedDate = new Date(dateInput.value);
		const today = new Date();

		if (selectedDate < today) {
			Swal.fire({
				icon: 'error',
				title: '오늘 이전의 날짜를 선택할 수 없습니다.',
				text: '',
			});
			dateInput.value = ""; // 입력 영역 초기화
		}
	});
});
//날짜 유효값 처리의 끝



document.addEventListener("DOMContentLoaded", function() {
	const minAgeInput = document.getElementById("minAge");
	const maxAgeInput = document.getElementById("maxAge");

	// 연령 입력 유효성 검사
	minAgeInput.addEventListener("change", function() {
		if (minAgeInput.value.length > 2) {
			Swal.fire({
				icon: 'error',
				title: '연령은 2자리 숫자만 입력 가능합니다.',
				text: '',
			});
			minAgeInput.value = ""; // 입력 초기화
		}

		const minAge = parseInt(minAgeInput.value);
		const maxAge = parseInt(maxAgeInput.value);

		if (minAge > maxAge) {
			Swal.fire({
				icon: 'error',
				title: '최소 연령이 최대 연령보다 큽니다.',
				text: '',
			});
			minAgeInput.value = ""; // 입력 초기화
		}
	});

	maxAgeInput.addEventListener("change", function() {
		if (maxAgeInput.value.length > 2) {
			Swal.fire({
				icon: 'error',
				title: '연령은 2자리 숫자만 입력 가능합니다.',
				text: '',
			});
			maxAgeInput.value = ""; // 입력 초기화
		}

		const minAge = parseInt(minAgeInput.value);
		const maxAge = parseInt(maxAgeInput.value);

		if (minAge > maxAge) {
			Swal.fire({
				icon: 'error',
				title: '최소 연령이 최대 연령보다 큽니다.',
				text: '',
			});
			maxAgeInput.value = ""; // 입력 초기화
		}
	});
	
// 최소 수강료 입력 필드
const minSugangnyoInput = document.getElementById('clasChoisoSugangnyo');
// 최대 수강료 입력 필드
const maxSugangnyoInput = document.getElementById('clasChoidaeSugangnyo');

// 최소 수강료 입력 이벤트 핸들러
minSugangnyoInput.addEventListener('blur', () => {
    const minValue = parseInt(minSugangnyoInput.value);
    const maxValue = parseInt(maxSugangnyoInput.value);

    if (minValue > 0 && minValue % 10000 !== 0) {
        Swal.fire({
            icon: 'error',
            title: '경고문',
            text: '최소 수강료는 10,000원 단위로 입력해주세요.',
        });
        minSugangnyoInput.value = ''; // 입력 초기화
    }

    if (minSugangnyoInput.value.length > 10) {
        Swal.fire({
            icon: 'error',
            title: '경고문',
            text: '최소 수강료는 10자리를 초과할 수 없습니다.',
        });
        minSugangnyoInput.value = ''; // 입력 초기화
    }
    
    if (minValue > maxValue) {
        Swal.fire({
            icon: 'error',
            title: '경고문',
            text: '최소 수강료는 최대 수강료보다 작아야 합니다.',
        });
        minSugangnyoInput.value = ''; // 입력 초기화
    }
});

// 최대 수강료 입력 이벤트 핸들러
maxSugangnyoInput.addEventListener('blur', () => {
	const minValue = parseInt(minSugangnyoInput.value);
    const maxValue = parseInt(maxSugangnyoInput.value);

    if (maxValue > 0 && maxValue % 10000 !== 0) {
        Swal.fire({
            icon: 'error',
            title: '경고문',
            text: '최대 수강료는 10,000원 단위로 입력해주세요.',
        });
        maxSugangnyoInput.value = ''; // 입력 초기화
    }

    if (maxSugangnyoInput.value.length > 10) {
        Swal.fire({
            icon: 'error',
            title: '경고문',
            text: '최대 수강료는 10자리를 초과할 수 없습니다.',
        });
        maxSugangnyoInput.value = ''; // 입력 초기화
    }
    
     if (minValue > maxValue) {
        Swal.fire({
            icon: 'error',
            title: '경고문',
            text: '최대 수강료는 최소 수강료보다 커야 합니다.',
        });
        maxSugangnyoInput.value = ''; // 입력 초기화
    }
});
});