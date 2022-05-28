const topLocationsElement = document.getElementById("content-slider");
const listLocationImg = ["location1.png", "location2.png", 'location3.png', "location4.png",
    "location5.png", "location6.png", "location7.png"];

const getLocation = async () => {
    const data = await getMethod("/LuxstayDemo/get-top-location");

    const listLocationElement = data.map((item, index) => `
        <div class="item content__slider-item">
            <a href="/LuxstayDemo/page/location/?position=${normalizeVietnamese(item.thanhPho)}"><img src="./../../assets/imgs/location/${listLocationImg[index]}" alt="" class="content__slider-img">
                <div class="content__slider-name">
                    <h3 class="content__slider-heading">${item.thanhPho}</h3>
                    <p class="content__slider-description">${item.soLuong} Chỗ ở</p>
                </div>
            </a>
        </div>
    `)


    topLocationsElement.innerHTML = listLocationElement.join("");

    setTimeout(() => {
        const script2 = document.createElement('script');
        script2.type = 'text/javascript';
        script2.src = '../../Script/slider/slider.js';

        document.body.appendChild(script2);
    }, 1000)

    const script1 = document.createElement('script');
    script1.type = 'text/javascript';
    script1.src = '../../Script/slider/owl.carousel.js';

    document.head.appendChild(script1);
}

window.onload = () => {
    getLocation();
}