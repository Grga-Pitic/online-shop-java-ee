import model.shop.Cart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class CartTest {

    private Cart cartWithProducts;
    private Cart emptyCart;

    @Before
    public void setUp() {
        cartWithProducts = createCartWithProducts();
        emptyCart = new Cart();
    }

    @Test
    public void productCountTest() {

        Assert.assertEquals(3, cartWithProducts.getCountById(1));
        Assert.assertEquals(2, cartWithProducts.getCountById(2));
        Assert.assertEquals(2, cartWithProducts.getCountById(3));
        Assert.assertEquals(0, cartWithProducts.getCountById(4));

    }

    @Test
    public void productIdSetTest() {

        Cart cartWithRemovedProducts = createCartWithProducts();

        cartWithRemovedProducts.removeOne(2);
        cartWithRemovedProducts.removeOne(2);
        cartWithRemovedProducts.removeAll(3);

        Set<Integer> expectedCartWithProductsIdSet = new HashSet<>();
        expectedCartWithProductsIdSet.add(1);
        expectedCartWithProductsIdSet.add(2);
        expectedCartWithProductsIdSet.add(3);

        Set<Integer> expectedEmptyCartIdSet = new HashSet<>();

        Set<Integer> expectedCartWithRemovedProductsIdSet = new HashSet<>();
        expectedCartWithRemovedProductsIdSet.add(1);

        Assert.assertEquals(expectedCartWithProductsIdSet, cartWithProducts.getProductIdSet());
        Assert.assertEquals(expectedEmptyCartIdSet, emptyCart.getProductIdSet());
        Assert.assertEquals(expectedCartWithRemovedProductsIdSet, cartWithRemovedProducts.getProductIdSet());

    }

    @Test
    public void clearTest() {

        cartWithProducts.clear();
        Set<Integer> expectedEmptyCartIdSet = new HashSet<>();

        Assert.assertEquals(expectedEmptyCartIdSet, cartWithProducts.getProductIdSet());

    }

    private Cart createCartWithProducts() {

        Cart cart = new Cart();

        cart.putOne(1);
        cart.putOne(1);
        cart.putOne(1);

        cart.putOne(2);
        cart.putOne(2);

        cart.putOne(3);
        cart.putOne(3);

        return cart;

    }

}
