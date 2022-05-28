const username = document.getElementById("username");
const fullname = document.getElementById("fullname");
const password = document.getElementById("password");
const confirmPassword = document.getElementById("confirm-password");
const phoneNumber = document.getElementById("phone-number");
const age = document.getElementById("age");
const address = document.getElementById("address");

document.querySelector("#register").addEventListener("click", (e) => {
    e.preventDefault();
    console.log(password.value, confirmPassword.value)
    if (password.value !== confirmPassword.value) {
        document.getElementsByClassName("error-confirm")[0].style.display = "block";
        return;
    }
    document.getElementsByClassName("error-confirm")[0].style.display = "none";

    if (username.value && password.value && fullname.value && phoneNumber.value && address.value && age.value) {
        document.getElementsByClassName("error")[0].style.display = "none";
        document.getElementsByClassName("error-confirm")[0].style.display = "none";

        sendMethod("POST", "/LuxstayDemo/register", {
            username: username.value,
            password: password.value,
            name: fullname.value,
            age: age.value,
            address: address.value,
            phoneNumber: phoneNumber.value
        })
        .then(status => {
            if (status.status === "200") {
                window.alert("Đăng ký thành công, vui lòng đăng nhập.")
                window.location.pathname = "LuxstayDemo/page/dang-nhap";
            } else {
                window.alert("User name đã bị trùng, vui lòng kiểm tra lại.");
            }
        })
        .catch(err => console.log("login failed"))
    } else {
        document.getElementsByClassName("error")[0].style.display = "block";
        document.getElementsByClassName("error-confirm")[0].style.display = "none";
    }
})