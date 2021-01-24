<%--
  Created by IntelliJ IDEA.
  User: ikon9
  Date: 23.01.2021
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <h1>${category.getCaption()}</h1>
    <p>${category.getDescription()}</p>

    <hr>

    <c:forEach items="${productList}" var="product">
        <div>
            <a href="/product/${product.getId()}">${product.getName()}</a>
            <p>
                ${product.getShortDescription()}
            </p>
            <hr>
        </div>
    </c:forEach>

</div>