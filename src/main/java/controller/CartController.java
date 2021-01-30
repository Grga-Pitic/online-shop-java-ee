package controller;

import model.menu.Menu;
import model.shop.Cart;
import model.shop.Product;
import service.CartService;
import service.JsonService;
import service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cart")
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Menu menu = MenuService.getInstance().createMenu();
        menu.setSelection("cart");
        request.setAttribute("menu", menu);

        HttpSession session = request.getSession();

        Cart cart = CartService.getCart(session);
        try {
            Map<Integer, Product> productsMap = CartService.getProductsFromCart(cart);

            request.setAttribute("products", productsMap);
            request.setAttribute("cart", cart);

            request.setAttribute("content", "content/cart.jsp");


            request.getRequestDispatcher("/layouts/main.jsp").forward(request, response);
        } catch (SQLException e) {
            response.setStatus(500);
            request.setAttribute("content", "error/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/layouts/main.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        Cart cart = CartService.getCart(session);

        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            String action = request.getParameter("action");

            CartService.doAction(cart, productId, action);

            JsonService.sendJson(response.getWriter(), cart);

        } catch (NumberFormatException e) {
            response.setStatus(500);
            Map<String, String> body = new HashMap<String, String>();
            body.put("errorMessage", "Id is not valid. Must be a number");
            JsonService.sendJson(response.getWriter(), body);
        }
    }
}
