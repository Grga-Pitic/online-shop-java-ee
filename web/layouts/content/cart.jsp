<%--
  Created by IntelliJ IDEA.
  User: ikon9
  Date: 29.01.2021
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <h1>Корзина</h1>

    <hr>

    <c:forEach items="${products.keySet()}" var="id">
        <div class="product">
            <div><a href="/product?productId=${products.get(id).getId()}">${products.get(id).getName()}</a></div>
            <p>${products.get(id).getShortDescription()}</p>
            <div>Количество: <span class="count-in-cart" data-product-id="${products.get(id).getId()}">${cart.getCountById(id)}</span></div>
            <div>
                <input type="button" class="btn btn-primary add-to-cart-button" data-product-id="${products.get(id).getId()}" value="Добавить">
                <input type="button" class="btn btn-primary remove-one-button" data-product-id="${products.get(id).getId()}" value="Удалить">
                <input type="button" class="btn btn-primary remove-all-button" data-product-id="${products.get(id).getId()}" value="Удалить все">
            </div>
            <hr>
        </div>
    </c:forEach>
</div>