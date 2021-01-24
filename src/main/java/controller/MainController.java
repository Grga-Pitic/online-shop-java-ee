package controller;

import db.DBConnection;
import model.menu.Menu;
import model.shop.Category;
import service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "welcome", urlPatterns = "")
public class MainController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        DBConnection connection = DBConnection.getInstance();
        try {
            connection.connect();
            MenuService.getInstance().setCategoryList(Category.findAll());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Menu menu = new Menu();
        menu.setSelection("main");

        request.setAttribute("content", "content/index.jsp");
        request.setAttribute("menu", menu);
        request.getRequestDispatcher("/layouts/main.jsp").forward(request, response);
    }
}
