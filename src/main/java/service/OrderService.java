package service;

import db.DBConnection;
import model.shop.Cart;
import model.shop.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrderService {

    public static void saveOrder(Cart cart, String name, String phone) throws SQLException {

        Map<Integer, Product> productMap = CartService.getProductsFromCart(cart);
        String relOrderQuery = prepareInsertQuery(productMap);

        int orderId = insertOrderWithIdReturning(name, phone);

        DBConnection db = DBConnection.getInstance();

        PreparedStatement statement = db.createPrepareStatement(relOrderQuery);

        int bindOffset = 0;
        // INSERT INTO REL_PRODUCT_ORDERS (FID_ORDERS, FID_PRODUCT, COUNT) VALUES
        for (Integer productId : productMap.keySet()) {
            statement.setInt(bindOffset + 1, orderId);
            statement.setInt(bindOffset + 2, productId);
            statement.setInt(bindOffset + 3, cart.getCountById(productId));
            bindOffset += 3;
        }

        statement.executeUpdate();

    }

    private static int insertOrderWithIdReturning(String name, String phone) throws SQLException {
        DBConnection db = DBConnection.getInstance();
        PreparedStatement statement = db.createPrepareStatement("INSERT INTO ORDERS (PHONE, NAME) VALUES (?, ?)");
        statement.setString(1, phone);
        statement.setString(2, name);

        statement.executeUpdate();


        ResultSet generatedKeys = statement.getGeneratedKeys();

        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }

        throw new SQLException("Failed to get order identifier");
    }

    private static String prepareInsertQuery(Map<Integer, Product> productMap) {

        if (productMap.isEmpty()) {
            throw new IllegalArgumentException("productMap must be not empty");
        }

        String query = "INSERT INTO REL_PRODUCT_ORDERS (FID_ORDERS, FID_PRODUCT, COUNT) VALUES ";

        for (Integer productId : productMap.keySet()) {
            query += "(?, ?, ?), ";
        }
        return query.substring(0, query.length() - 2);
    }

}
