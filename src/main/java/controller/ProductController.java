package controller;

import model.menu.Menu;
import model.shop.Product;
import service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/product")
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Menu menu = MenuService.getInstance().createMenu();
        menu.setSelection("categories");
        request.setAttribute("menu", menu);

        try {
            int productId = Integer.parseInt(request.getParameter("productId"));

            Product product = Product.findById(productId);

            request.setAttribute("content", "content/product.jsp");
            request.setAttribute("product", product);

            request.getRequestDispatcher("/layouts/main.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.setStatus(500);
            request.setAttribute("content", "error/error.jsp");
            request.setAttribute("errorMessage", "Incorrect product id. Must be a number");

            request.getRequestDispatcher("/layouts/main.jsp").forward(request, response);
        } catch (SQLException e) {
            response.setStatus(500);
            request.setAttribute("content", "error/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());

            request.getRequestDispatcher("/layouts/main.jsp").forward(request, response);
        }



    }
}
