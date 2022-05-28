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

const getMethod = async (url) => {
    const response = await fetch(url, {
        method: "GET",
        mode: 'cors',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    return response.json();
}

