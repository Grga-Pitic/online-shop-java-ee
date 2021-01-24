package exception;

import exception.base.ShopException;

public class CategoryNotFoundException extends ShopException {

    public CategoryNotFoundException(String message) {
        super(message);
    }

}
