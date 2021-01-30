package controller;

import model.shop.Cart;
import service.CartService;
import service.JsonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cart")
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
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
