package controller;

import model.menu.Menu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "welcome", urlPatterns = "")
public class MainController extends HttpServlet {

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
