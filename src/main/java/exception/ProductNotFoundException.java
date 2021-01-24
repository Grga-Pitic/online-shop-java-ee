package exception;

import exception.base.ShopException;

public class ProductNotFoundException extends ShopException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
