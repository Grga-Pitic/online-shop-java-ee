package controller;

import model.menu.Menu;
import model.shop.Cart;
import service.CartService;
import service.MenuService;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/order")
public class OrderController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Menu menu = MenuService.getInstance().createMenu();
        request.setAttribute("menu", menu);
        menu.setSelection("cart");

        HttpSession session = request.getSession();

        Cart cart = CartService.getCart(session);

        try {
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");

            OrderService.saveOrder(cart, name, phone);
            cart.clear();

            request.setAttribute("content", "content/order.jsp");
            request.setAttribute("menu", menu);
            request.getRequestDispatcher("/layouts/main.jsp").forward(request, response);

        } catch (SQLException | IllegalArgumentException e) {
            response.setStatus(500);
            request.setAttribute("content", "error/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/layouts/main.jsp").forward(request, response);
        }
    }
}
