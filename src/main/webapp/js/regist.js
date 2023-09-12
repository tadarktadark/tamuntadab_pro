/* ÀÏ°ö ÀÏ°ö */
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
const nameRegex = /^[A-Za-z°¡-ÆR]{2,}$/;
const birthRegex = /^(19|20)\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/;

$form.addEventListener("submit", (event) => {
    // ÀÌ¸ŞÀÏ È®ÀÎ
    if (!$email.is(':checked')) {
        print("ÀÌ¸ŞÀÏ ÀÎÁõÀ» ¿Ï·áÇØÁÖ¼¼¿ä.");
        event.preventDefault();
        return;
    }

    // ÀÌ¸§ È®ÀÎ
    if (!nameRegex.test($name.val())) {
        print("¿Ã¹Ù¸¥ ÀÌ¸§À» ÀÔ·ÂÇØÁÖ¼¼¿ä.");
        event.preventDefault();
        return;
    }

    // ºñ¹Ğ¹øÈ£ È®ÀÎ
    if (!passwordRegex.test($password.val())) {
        print("¿Ã¹Ù¸¥ ºñ¹Ğ¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
        event.preventDefault();
        return;
    }

    // ºñ¹Ğ¹øÈ£ È®ÀÎ
    if ($password.val() !== $passwordconfirm.val()) {
        print("ºñ¹Ğ¹øÈ£¿Í ºñ¹Ğ¹øÈ£ È®ÀÎÀÌ ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù.");
        event.preventDefault();
        return;
    }

    // ÇÚµåÆù ÀÎÁõ È®ÀÎ
    if (!$confirmPhoneCheck.is(':checked')) {
        print("ÇÚµåÆù ÀÎÁõÀ» ¿Ï·áÇØÁÖ¼¼¿ä.");
        event.preventDefault();
        return;
    }

    // ¼ºº° È®ÀÎ
    if ($gender.filter(':checked').length === 0) {
        print("¼ºº°À» ¼±ÅÃÇØÁÖ¼¼¿ä.");
        event.preventDefault();
        return;
    }
    // ±ÇÇÑ È®ÀÎ
    if ($auth.filter(':checked').length === 0) {
        print("±ÇÇÑÀ» ¼±ÅÃÇØÁÖ¼¼¿ä.");
        event.preventDefault();
        return;
    }

    // »ı³â¿ùÀÏ È®ÀÎ
    if (!birthRegex.test($birth.val())) {
        print("¿Ã¹Ù¸¥ »ı³â¿ùÀÏÀ» ÀÔ·ÂÇØÁÖ¼¼¿ä.");
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