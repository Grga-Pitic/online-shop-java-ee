$(document).ready(function () {
    $(".add-to-cart-button").click(function () {

        $.ajax({
            url: '/cart',
            method: 'POST',
            data: {
                id: 1,
            },
            datatype: 'json',
            onsuccess: function (data) {

            },
            onerror: function () {

            },
        });

    });
});