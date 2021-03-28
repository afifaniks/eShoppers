function doAction(productId, action) {
    fetch("/add-to-cart", {
        method: 'POST',
        headers: {
            "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
        },
        body: "productId=" + productId + "&action=" + action
    }).then(
        (response) => {
            if (response.redirected) {
                window.location.replace(response.url)
            }
        });
}