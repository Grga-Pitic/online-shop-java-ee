$(document).ready(function () {
    $(".add-to-cart-button").click(function () {

        var productId = $(this).data('product-id');
        var $count = $(`.count-in-cart[data-product-id=${productId}]`);

        sendCartAjax(
            {
                id: productId,
                action: 'putOne',
            },
            function (data) {
                $count.html(data.content[productId]);
            },
            function () {

            }
        );
    });

    $(".remove-one-button").click(function () {

        var productId = $(this).data('product-id');
        var $count = $(`.count-in-cart[data-product-id=${productId}]`);
        var $button = $(this);

        sendCartAjax(
            {
                id: productId,
                action: 'removeOne',
            },
            function (data) {
                if (data.content[productId] === undefined) {
                    $button.closest('.product').remove();
                    return;
                }
                $count.html(data.content[productId]);
            },
            function () {

            }
        );
    });

    $(".remove-all-button").click(function () {

        var $button = $(this);
        var productId = $(this).data('product-id');

        sendCartAjax(
            {
                id: productId,
                action: 'removeAll',
            },
            function (data) {
                $button.closest('.product').remove();
            },
            function () {

            }
        );
    });

    function sendCartAjax(data, onSuccess, onError) {
        $.ajax({
            url: '/cart',
            method: 'POST',
            data: data,
            dataType: 'json',
            success: onSuccess,
            error: onError,
        });
    }

});