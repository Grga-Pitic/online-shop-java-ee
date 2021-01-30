<%--
  Created by IntelliJ IDEA.
  User: ikon9
  Date: 23.01.2021
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <h1>${category.getCaption()}</h1>
    <p>${category.getDescription()}</p>

    <hr>

    <c:forEach items="${productList}" var="product">
        <div>
            <a href="/product?productId=${product.getId()}">${product.getName()}</a>
            <p>
                ${product.getShortDescription()}
            </p>
            <div><input type="button" class="btn btn-primary add-to-cart-button" data-product-id="${product.getId()}"
                        value="Добавить в корзину"></div>
            <hr>
        </div>
    </c:forEach>

</div>