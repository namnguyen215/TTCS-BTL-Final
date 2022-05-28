const href = window.location.href;
const url = new URL(href);
const id = url.searchParams.get("id");
const roomName = document.getElementsByClassName("noi-dung__ten-phong")[0];
const districtName = document.getElementsByClassName("dia-chi__quan")[0];
const cityName = document.getElementsByClassName("dia-chi__thanh-pho")[0];
const acreageRoom = document.getElementsByClassName("dien-tich__so")[0];
const badRoom = document.getElementsByClassName("thong-tin__phong-tam")[0];
const bedRoomNumber = document.getElementsByClassName("thong-tin__phong-ngu")[0];
const floorCount = document.getElementsByClassName("thong-tin__tang")[0];
const desRoom = document.getElementsByClassName("noi-dung__mota")[0];
const listRoom = document.getElementById("list_room");
const roomMore =  document.getElementsByClassName("ten-phong")[0];
const roomPrice = document.getElementsByClassName("gia-phong")[0];

Date.prototype.toDateInputValue = (function() {
    const local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});

document.getElementById("addtocart-btn").addEventListener("click", () => {
    const checkinTime = document.getElementById("checkin-time").value.replaceAll(" ", "");
    const checkoutTime = document.getElementById("checkout-time").value.replaceAll(" ", "");

    if (sessionStorage.getItem("accessToken")) {
        sendMethod("POST", "/LuxstayDemo/book-room", {
            customerId: sessionStorage.getItem("id"),
            checkIn: checkinTime,
            checkOut: checkoutTime,
            roomId: id,
        })
            .then(res => {
                if (res.status === "200") {
                    alert("Bạn đã đặt phòng thành công");
                    window.location.href = window.location.href = "/LuxstayDemo/page/my-booking/";
                } else {
                    alert(res.message)
                }
            });
    } else {
        alert("Bạn cần đăng nhập để đặt phòng")
    }


})


const getRoomInfo = async () => {
    const data = await getMethod("/LuxstayDemo/get-detail-room-by-id?" + new URLSearchParams({
        id
    }));

    const roomInfo = data[0];
    const location = normalizeVietnamese(roomInfo.viTriThanhPho);
    const idRoom = roomInfo.id;

    roomName.textContent = roomInfo.ten;
    districtName.textContent = roomInfo.viTriQuan;
    cityName.textContent = roomInfo.viTriThanhPho;
    acreageRoom.textContent = roomInfo.dienTich;
    badRoom.textContent = roomInfo.soPhongTam;
    bedRoomNumber.textContent = roomInfo.soPhongNgu;
    floorCount.textContent = roomInfo.soTang;
    desRoom.textContent = roomInfo.moTa;

    roomMore.textContent = roomInfo.ten;
    roomPrice.textContent = String(roomInfo.gia).replace(/(.)(?=(\d{3})+$)/g,'$1,') + "đ"

    document.getElementById('checkin-time').value = new Date().toDateInputValue();
    document.getElementById('checkout-time').value = new Date().toDateInputValue();

    await getRoomSimilar(location, idRoom);
}

const getRoomSimilar = async (location, id) => {
    const data = await getMethod(`/LuxstayDemo/get-list-room-by-location?position=${location}`);

    const listRoomElement = data
        .filter(room => room.id !== id)
        .slice(0, 8)
        .map((item, index) => `
        <div class="col-xs-6 col-md-3">
            <a href="/LuxstayDemo/page/room/?id=${item.id}" onclick="localStorage.setItem('roomImage', '${item.anhPhong}')">
                <div class="room">
                    <div class="room_img">
                        <img src="${item.anhPhong}" alt="room image" />
                    </div>
                    <div class="room_info">
                        <div class="room_deilta">
                            Căn hộ dịch vụ - <span id="num-bed-room">${item.soPhongNgu}</span> phòng ngủ
                        </div>
                        <div class="room_vote">
                            &#11088; ${item.danhGia}
                        </div>
                    </div>
                    <div class="room_name">
                        ${item.ten}
                    </div>
                    <div class="room_price">
                        <strong>
                            ${String(item.gia).replace(/(.)(?=(\d{3})+$)/g,'$1,')}
                        </strong>
                    </div>
                </div>  
            </a>
        </div>
    `)

    listRoom.innerHTML = listRoomElement.join("");
}


const fillImageLink = () => {
    const imageDesEle = document.getElementById("anh-mo-ta-phong__anh");

    imageDesEle.src = localStorage.getItem("roomImage");
}

window.onload = () => {
    fillImageLink();
    getRoomInfo();
}