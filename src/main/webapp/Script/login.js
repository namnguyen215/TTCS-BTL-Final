const username = document.getElementById("INPUT_33");
const password = document.getElementById("INPUT_39");

document.querySelector("#BUTTON_44").addEventListener("click", (e) => {
    e.preventDefault();
    if (username.value && password.value) {
        document.getElementsByClassName("error")[0].style.display = "none";

        sendMethod("POST", "/LuxstayDemo/login", {
            username: username.value,
            password: password.value
        })
        .then(status => {
            if (status.status === "200") {
                sessionStorage.setItem("id", status.data.id);
                sessionStorage.setItem("ten", status.data.ten)
                sessionStorage.setItem("chucVu", status.data.chucVu)
                sessionStorage.setItem("accessToken", status.data.accessToken)

                window.location.pathname = "LuxstayDemo/page/home";
            } else {
                window.alert(status.message);
            }
        })
        .catch(err => console.log("login failed"))
    } else {
        document.getElementsByClassName("error")[0].style.display = "block";
    }
})