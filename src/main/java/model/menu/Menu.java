package model.menu;


import model.shop.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {

    private Map<String, MenuElement> elements;

    private Map<String, MenuElement> categories;

    public Menu(List<Category> categoryList) {
        elements = new HashMap<String, MenuElement>();
        categories = new HashMap<String, MenuElement>();

        // hardcode
        elements.put("main", new MenuElement("Главная", "/"));
        elements.put("categories", new MenuElement("Категории", "/categories"));
        elements.put("cart", new MenuElement("Корзина", "/cart"));

        for (Category category : categoryList) {
            categories.put(category.getName(), new MenuElement(category.getCaption(), "/catalog?cat=" + category.getId()));
        }
    }

    public void setSelection(String key) {
        elements.get(key).setSelected(true);
    }

    public Map<String, MenuElement> getElements() {
        return elements;
    }

    public Map<String, MenuElement> getCategories() {
        return categories;
    }
}
