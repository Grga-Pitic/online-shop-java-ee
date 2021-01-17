<%@ page import="model.menu.MenuElement" %>
<%@ page import="model.menu.Menu" %><%--
  Created by IntelliJ IDEA.
  User: ikon9
  Date: 16.01.2021
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<div>
    <ul class="navbar-nav">
        <%
            Menu menu = (Menu)(request.getAttribute("menu"));
            for (String key : menu.getElements().keySet()) {

                MenuElement element = menu.getElements().get(key);


                if (key.equals("categories")) {
                    String htmlElement = "";
                    htmlElement += "<li class=\"nav-item " + ((element.isSelected()) ? "btn-primary" : "") + "\">";
                    htmlElement += "<a class=\"nav-link dropdown-toggle btn\" href=\"#\" data-toggle=\"dropdown\" aria-expanded=\"false\">" +
                                        element.getCaption() +
                                    "</a>";
                    htmlElement += "<div class=\"dropdown-menu\">";

                    for (MenuElement category : menu.getCategories().values()) {
                        htmlElement += "<a class=\"dropdown-item btn\" href=\"" + category.getHref() + "\">" + category.getCaption() + "</a>";
                    }

                    htmlElement += "</div>";
                    htmlElement += "</li>";
                    out.println(htmlElement);
                    continue;
                }

                out.println("<li class=\"nav-item " + ((element.isSelected()) ? "btn-primary" : "") + "\">" +
                                "<a class=\"nav-link btn\" href=\"" + element.getHref() + "\">" + element.getCaption() + "</a>" +
                             "</li>");
            }
        %>
    </ul>

</div>