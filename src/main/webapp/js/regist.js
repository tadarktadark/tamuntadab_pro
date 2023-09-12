/* �ϰ� �ϰ� */
const $form = document.querySelector('form');
const $email = $("#confirmEmailCheck");
const $name = $("#name");
const $password = $("#password");
const $passwordconfirm = $("#passwordconfirm");
const $confirmPhoneCheck = $("#confirmPhoneCheck");
const $gender = $("input:radio[name='userGender']");
const $auth = $("input:radio[name='userAuth']");
const $birth = $("#birth");

const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
const nameRegex = /^[A-Za-z��-�R]{2,}$/;
const birthRegex = /^(19|20)\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/;

$form.addEventListener("submit", (event) => {
    // �̸��� Ȯ��
    if (!$email.is(':checked')) {
        print("�̸��� ������ �Ϸ����ּ���.");
        event.preventDefault();
        return;
    }

    // �̸� Ȯ��
    if (!nameRegex.test($name.val())) {
        print("�ùٸ� �̸��� �Է����ּ���.");
        event.preventDefault();
        return;
    }

    // ��й�ȣ Ȯ��
    if (!passwordRegex.test($password.val())) {
        print("�ùٸ� ��й�ȣ�� �Է����ּ���.");
        event.preventDefault();
        return;
    }

    // ��й�ȣ Ȯ��
    if ($password.val() !== $passwordconfirm.val()) {
        print("��й�ȣ�� ��й�ȣ Ȯ���� ��ġ���� �ʽ��ϴ�.");
        event.preventDefault();
        return;
    }

    // �ڵ��� ���� Ȯ��
    if (!$confirmPhoneCheck.is(':checked')) {
        print("�ڵ��� ������ �Ϸ����ּ���.");
        event.preventDefault();
        return;
    }

    // ���� Ȯ��
    if ($gender.filter(':checked').length === 0) {
        print("������ �������ּ���.");
        event.preventDefault();
        return;
    }
    // ���� Ȯ��
    if ($auth.filter(':checked').length === 0) {
        print("������ �������ּ���.");
        event.preventDefault();
        return;
    }

    // ������� Ȯ��
    if (!birthRegex.test($birth.val())) {
        print("�ùٸ� ��������� �Է����ּ���.");
        event.preventDefault();
        return;
    }

        event.preventDefault();
});

function print(val){
	Swal.fire({
		position: 'top-center',
		icon: 'warning',
		title: val,
		showConfirmButton: false,
		timer: 1500,
		showCloseButton: true
	})	
}