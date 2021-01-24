package controller;

import exception.CategoryNotFoundException;
import model.menu.Menu;
import model.shop.Category;
import model.shop.Product;
import service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/catalog")
public class CatalogController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Menu menu = MenuService.getInstance().createMenu();
        menu.setSelection("categories");

        try {
            int categoryId = Integer.parseInt(request.getParameter("cat"));
            Category category = Category.findById(categoryId);

            List<Product> productList = Product.findAllByCategoryId(categoryId);

            request.setAttribute("content", "content/catalog.jsp");
            request.setAttribute("menu", menu);
            request.setAttribute("category", category);
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("/layouts/main.jsp").forward(request, response);
        } catch (SQLException e) {
            response.setStatus(500);
            request.setAttribute("content", "error/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("menu", menu);
            request.getRequestDispatcher("/layouts/main.jsp").forward(request, response);
        } catch (CategoryNotFoundException e ) {
            response.setStatus(404);
            request.setAttribute("content", "error/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("menu", menu);
            request.getRequestDispatcher("/layouts/main.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.setStatus(500);
            request.setAttribute("content", "error/error.jsp");
            request.setAttribute("errorMessage", "Incorrect category id. Must be a number");
            request.setAttribute("menu", menu);
            request.getRequestDispatcher("/layouts/main.jsp").forward(request, response);
        }
    }
}
