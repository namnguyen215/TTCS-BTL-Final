const data = {};

let callApi = async () => {
    let res = await getMethod("/LuxstayDemo/my-booking?" + new URLSearchParams({
        customerId: sessionStorage.getItem("id")
    }))

    let ele = [];

    for (let i = 0; i < res.length; i++) {
        ele.push(`
                <tr>
                    <td>${res[i].maDatPhong}</td>
                    <td width="300px"><a href="/LuxstayDemo/page/room/?id=${res[i].maPhong}"><img src="${res[i].urlAnh}"/></a></td>
                    <td><a class="ten-phong__dat" href="/LuxstayDemo/page/room/?id=${res[i].maPhong}">${res[i].tenPhong}</a></td>
                    <td>${formatDate(new Date(res[i].ngayDen))}</td>
                    <td>${formatDate(new Date(res[i].ngayDi))}</td>
                    <td>${res[i].soNgay}</td>
                    <td>${String(parseInt(res[i].tongTien)).replace(/(.)(?=(\d{3})+$)/g,'$1,')}đ</td>
                    <td><button class="delete-button" id="${res[i].maDatPhong}-delete" onclick="deleteBooking(${res[i].maDatPhong})">Hùy Phòng</button></td>
                </tr>
            `)
    }
    document.getElementById("tbody").innerHTML = ele.join("");
    document.getElementById("khong-dat-phong").style.display = ele.length === 0 ? "block" : "none"
    return "render-done";
}

const deleteBooking = async (id) => {
    sendMethod("DELETE", "/LuxstayDemo/cancel-booking", {
        id
    })
        .then(res => alert(res.message))
    callApi();
}

window.onload = () => {
    callApi();
}