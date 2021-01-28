<%--
  Created by IntelliJ IDEA.
  User: ikon9
  Date: 16.01.2021
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="model.menu.MenuElement" %>
<%@ page import="model.menu.Menu" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <ul class="navbar-nav">

        <c:forEach items="${menu.getElements().keySet()}" var="key">
            <c:if test="${key.intern() == 'categories'}">
                <li class="nav-item ${menu.getElements().get(key).isSelected() ? 'btn-primary' : ''}">
                    <a class="nav-link dropdown-toggle btn menu-${key}" href="#" data-toggle="dropdown" aria-expanded="false">
                        ${menu.getElements().get(key).getCaption()}
                    </a>
                    <div class="dropdown-menu">
                        <c:forEach items="${menu.getCategories().values()}" var="category">
                            <a class="dropdown-item btn" href="${category.getHref()}">${category.getCaption()}</a>
                        </c:forEach>
                    </div>
                </li>
            </c:if>
            <c:if test="${key.intern() != 'categories'}">
                <li class="nav-item ${menu.getElements().get(key).isSelected() ? 'btn-primary' : ''}">
                    <a class="nav-link btn" href="${menu.getElements().get(key).getHref()}">${menu.getElements().get(key).getCaption()}</a>
                </li>
            </c:if>
        </c:forEach>
    </ul>

</div>