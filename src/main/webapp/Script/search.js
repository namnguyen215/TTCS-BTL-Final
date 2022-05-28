const href = window.location.href;
const url = new URL(href);
const keyword = url.searchParams.get("keyword");
const listRoom = document.getElementById("list_room");

const getPosition = async () => {
    const data = await getMethod(`/LuxstayDemo/get-list-room-by-location?position=${keyword}`);

    const listRoomElement = data.map((item, index) => `
        <div class="col-xs-6 col-md-3">
            <a href="/LuxstayDemo/page/room/?id=${item.id}">
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

window.onload = () => {
    getPosition();
}
