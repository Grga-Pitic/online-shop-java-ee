package service;

import model.shop.Cart;

import javax.servlet.http.HttpSession;

public class CartService {

    public static void doAction(Cart cart, int productId, String action) {
        switch (action) {
            case "put":
                break;
            case "removeOne":
                break;
            case "removeAll":
                break;
            default:
                break;
        }
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
