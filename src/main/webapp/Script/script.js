const href = window.location.href;
const data = {};

const modalAdd = document.getElementById("myModalAdd");

const modalEdit = document.getElementById("myModalEdit");

const btn = document.getElementById("add-button");

const spanAdd = document.getElementsByClassName("close")[0]

const spanEdit = document.getElementsByClassName("close")[1];

btn.onclick = function() {
    modalAdd.style.display = "block";
}

spanAdd.onclick = function() {
    modalAdd.style.display = "none";
}

spanEdit.onclick = function() {
    modalEdit.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modalAdd || event.target == modalEdit) {
        modalAdd.style.display = "none";
        modalEdit.style.display = "none";
    }
}

const sendMethod = async (method, url, data = {}) => {
    const response = await fetch(url, {
        method: method,
        mode: 'cors',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    return response.json();
}


let callApi = async () => {
    let res = await fetch("/LuxstayDemo/manage-customer");
    // let res = await fetch(`${href}manage-customer`);
    let json = await res.json()

    let ele = [];

    for (let i = 0; i < json.length; i++) {
        data[json[i].id] = {};
        data[json[i].id].name = json[i].name;
        data[json[i].id].age = json[i].age;
        data[json[i].id].phoneNumber = json[i].phoneNumber;
        data[json[i].id].address = json[i].address;

        ele.push(`
                <tr>
                    <td>${json[i].id}</td>
                    <td>${json[i].name}</td>
                    <td>${json[i].age}</td>
                    <td>${json[i].phoneNumber}</td>
                    <td>${json[i].address}</td>
                    <td>
                        <button class="edit-button" id="${json[i].id}-edit" onclick="editUser(${json[i].id})">Edit</button>
                    </td>
                    <td><button class="delete-button" id="${json[i].id}-delete" onclick="deleteUser(${json[i].id})">Delete</button></td>
                </tr>
            `)
    }
    document.getElementById("table").innerHTML = ele.join("");
    return "render-done";
}

const editUser = (id) => {
    document.getElementById("edit-id").value = id;
    document.getElementById("edit-name").value = data[id].name;
    document.getElementById("edit-age").value = data[id].age;
    document.getElementById("edit-phone-number").value = data[id].phoneNumber;
    document.getElementById("edit-address").value = data[id].address;

    modalEdit.style.display = "block";

}

const submitEdit = () => {
    const id = document.getElementById("edit-id").value;
    const name = document.getElementById("edit-name").value;
    const age = document.getElementById("edit-age").value;
    const phoneNumber = document.getElementById("edit-phone-number").value;
    const address = document.getElementById("edit-address").value;

    sendMethod("PUT", "/LuxstayDemo/manage-customer", {
        customerId: id,
        name: name,
        age: age,
        phoneNumber: phoneNumber,
        address: address
    }).then(data => console.log(data));

    modalEdit.style.display = "none";

    callApi();
}

let addUser = () => {
    const name = document.getElementById("add-name").value;
    const age = document.getElementById("add-age").value;
    const phoneNumber = document.getElementById("add-phone-number").value;
    const address = document.getElementById("add-address").value;

    sendMethod("POST", "/LuxstayDemo/manage-customer", {
        name: name,
        age: age,
        phoneNumber: phoneNumber,
        address: address
    }).then(data => console.log(data))

    document.getElementById("add-name").value = ""
    document.getElementById("add-age").value = "";
    document.getElementById("add-phone-number").value = "";
    document.getElementById("add-address").value = "";

    modalAdd.style.display = "none";

    callApi();
}

const deleteUser = async (id) => {
    sendMethod("DELETE", "/LuxstayDemo/manage-customer", { id: id })
        .then(data => console.log(data))
    callApi();
}

window.onload = () => {
    callApi();
}