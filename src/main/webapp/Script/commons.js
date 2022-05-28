const normalizeVietnamese = (str) => {
    return str
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .replace(/đ/g, "d")
        .replace(/Đ/g, "D")
        .toLowerCase()
}

const searchRoom = () => {
    const inputSearch = document.getElementsByClassName("header__search-input")[0];

    window.location.href = `/LuxstayDemo/page/search/?keyword=${normalizeVietnamese(inputSearch.value)}`
}

function padTo2Digits(num) {
    return num.toString().padStart(2, '0');
}

function formatDate(date) {
    return [
        padTo2Digits(date.getDate()),
        padTo2Digits(date.getMonth() + 1),
        date.getFullYear(),
    ].join('-');
}

const checkLogin = () => {
    if (sessionStorage.getItem("accessToken")) {
        document.getElementsByClassName("wrap-name")[0].style.display = "block";
        document.getElementsByClassName("wrap-logout")[0].style.display = "block";
        document.getElementsByClassName("wrap-sign-in")[0].style.display = "none";
        document.getElementsByClassName("wrap-sign-up")[0].style.display = "none";
        document.getElementsByClassName("wrap-my-booking")[0].style.display = "block";

        document.getElementById("name-cus-content").textContent = sessionStorage.getItem("ten");
    } else {
        document.getElementsByClassName("wrap-name")[0].style.display = "none";
        document.getElementsByClassName("wrap-logout")[0].style.display = "none";
        document.getElementsByClassName("wrap-sign-in")[0].style.display = "block";
        document.getElementsByClassName("wrap-sign-up")[0].style.display = "block";
        document.getElementsByClassName("wrap-my-booking")[0].style.display = "none";
    }
}

checkLogin();

document.getElementById("log-out").addEventListener("click", () => {
    sessionStorage.clear();
})

document.getElementById("name-cus").addEventListener("click", (e) => {
    e.preventDefault();
})

