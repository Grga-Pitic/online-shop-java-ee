package service;

import exception.CartActionNotFound;
import model.shop.Cart;
import model.shop.Product;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;

public class CartService {

    public static void doAction(Cart cart, int productId, String action) {
        switch (action) {
            case "putOne":
                cart.putOne(productId);
                break;
            case "removeOne":
                cart.removeOne(productId);
                break;
            case "removeAll":
                cart.removeAll(productId);
                break;
            case "clear":
                cart.clear();
            default:
                throw new CartActionNotFound("There's no action: " + action);
        }
    }

    public static Map<Integer, Product> getProductsFromCart(Cart cart) throws SQLException {

        Map<Integer, Product> products = Product.findByIdCollection(cart.getProductIdSet());
        return products;
    }

    public static Cart getCart(HttpSession session) {
        Cart cart;

        try {
            cart = (Cart)session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }
        } catch (ClassCastException e) {
            cart = new Cart();
        }

        session.setAttribute("cart", cart);
        return cart;
    }

}
