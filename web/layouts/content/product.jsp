<%--
  Created by IntelliJ IDEA.
  User: ikon9
  Date: 24.01.2021
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1>${product.getName()}</h1>
    <hr>
    <p>${product.getDescription()}</p>
    <div>
        <input type="button" class="btn btn-primary add-to-cart-button" value="Добавить в корзину" data-product-id="${product.getId()}">
    </div>
</div>