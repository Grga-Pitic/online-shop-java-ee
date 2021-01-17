package model.menu;


import java.util.HashMap;
import java.util.Map;

public class Menu {

    private Map<String, MenuElement> elements;

    private Map<String, MenuElement> categories;

    public Menu() {
        elements = new HashMap<String, MenuElement>();
        categories = new HashMap<String, MenuElement>();

        // hardcode
        elements.put("main", new MenuElement("Главная", "/"));
        elements.put("categories", new MenuElement("Категории", "/categories"));
        elements.put("cart", new MenuElement("Корзина", "/cart"));

        categories.put("cat1", new MenuElement("Категория1", "/cat1"));
        categories.put("cat2", new MenuElement("Категория2", "/cat2"));
        categories.put("cat3", new MenuElement("Категория3", "/cat3"));
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
