package exception;

import exception.base.ShopException;

public class CartActionNotFound extends ShopException {

    public CartActionNotFound(String message) {
        super(message);
    }

}
