/* �ϳ� �ϳ� */
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

		if (!classTitle || classTitle === "Ŭ���� ������ �Է����ּ���") {
			Swal.fire({
				icon: 'error',
				title: 'Ŭ���� �̸��� �Է����ּ���.',
				text: '',
			});
			return;
		}
		if (!subjCode) {
			Swal.fire({
				icon: 'error',
				title: '������ ������ �������ּ���.',
				text: '',
			});
			return;
		}
		if (!location || location === "'��' �� �Է��� �ּ���") {
			Swal.fire({
				icon: 'error',
				title: '���� ������ �Է����ּ���.',
				text: '',
			});
			return;
		}
		if (!clasSueopNaljja) {
			Swal.fire({
				icon: 'error',
				title: '���� ��¥�� �������ּ���.',
				text: '',
			});
			return;
		}
		if (!clasHuimangInwon || clasHuimangInwon === "�ο�") {
			Swal.fire({
				icon: 'error',
				title: '��� �ο��� �������ּ���.',
				text: '',
			});
			return;
		}
		if (!classContent || classContent === "������ Ŭ������ ���� �ڼ��� ������ �����ּ���") {
			Swal.fire({
				icon: 'error',
				title: 'Ŭ���� ������ �ۼ����ּ���.',
				text: '',
			});
			return;
		}

		if (!clasSeongbyeolJehan || clasSeongbyeolJehan === "�����ϼ���") {
			clasSeongbyeolJehan = "GFREE";
			}else if(!clasSeongbyeolJehan || clasSeongbyeolJehan === "��������"){
			clasSeongbyeolJehan = "GFREE";
			}else if(!clasSeongbyeolJehan || clasSeongbyeolJehan === "���ڸ�"){
			clasSeongbyeolJehan = "MONLY";
			}else clasSeongbyeolJehan = "FONLY";
			
		if (!minAge || minAge === "�ּ�") {
			minAge = "0";
		}
		
		if (!maxAge || maxAge === "�ּ�") {
			maxAge = "0";
		}
		
		if (!clasChoisoSugangnyo || clasChoisoSugangnyo === "�ּ� (1,000�� ������ �Է����ּ���)") {
			clasChoisoSugangnyo = "0";
		}
		if (!clasChoidaeSugangnyo || clasChoidaeSugangnyo === "�ִ� (1,000�� ������ �Է����ּ���)") {
			clasChoidaeSugangnyo = "0";
		}
		this.submit();
	});
});