package service;

import db.DBConnection;
import model.menu.Menu;
import model.shop.Category;

import java.util.ArrayList;
import java.util.List;

public class MenuService {

    private static MenuService instance;

    private List<Category> categoryList;

    private MenuService() {
        categoryList = new ArrayList<Category>();
    }

    public Menu createMenu() {
        return new Menu(categoryList);
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public static MenuService getInstance() {
        if (instance == null) {
            instance = new MenuService();
        }

        return instance;
    }

}
