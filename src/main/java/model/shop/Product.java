package model.shop;

import db.DBConnection;
import exception.ProductNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Product {

    public static Product findById(int productId) throws SQLException{
        String query = "SELECT * FROM PRODUCT WHERE ID_PRODUCT = ?";
        PreparedStatement statement = DBConnection.getInstance().createPrepareStatement(query);
        statement.setInt(1, productId);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            int id  = result.getInt("id_product");
            String name = result.getString("name");
            String description = result.getString("description");
            String shortDescription = result.getString("short_description");
            return new Product(id, name, description, shortDescription);
        }

        throw new ProductNotFoundException("Product not found");
    }

    public static List<Product> findAllByCategoryId(int categoryId) throws SQLException {
        List <Product> productList = new ArrayList<Product>();

        String query = "SELECT * FROM PRODUCT WHERE ID_PRODUCT IN (" +
                            "SELECT FID_PRODUCT FROM REL_PRODUCT_CATEGORY WHERE FID_CATEGORY = ?" +
                        ")";

        PreparedStatement statement = DBConnection.getInstance().createPrepareStatement(query);
        statement.setInt(1, categoryId);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            int id  = result.getInt("id_product");
            String name = result.getString("name");
            String description = result.getString("description");
            String shortDescription = result.getString("short_description");

            productList.add(new Product(id, name, description, shortDescription));
        }

        return productList;
    }

    public static Map<Integer, Product> findByIdCollection(Collection<Integer> idCollection) throws SQLException{

        Map<Integer, Product> products = new HashMap<Integer, Product>();
        if (idCollection.isEmpty()) {
            return products;
        }

        String query = "SELECT * FROM PRODUCT WHERE ID_PRODUCT in ";
        Statement statement = DBConnection.getInstance().getStatement();

        String preparedIds = "";
        for (Integer id : idCollection) {
            preparedIds += id + ", ";
        }
        preparedIds = preparedIds.substring(0, preparedIds.length() - 2);
        query += "(" + preparedIds + ")";


        statement.executeQuery(query);

        ResultSet result = statement.executeQuery(query);

        while (result.next()) {
            int id  = result.getInt("id_product");
            String name = result.getString("name");
            String description = result.getString("description");
            String shortDescription = result.getString("short_description");
            products.put(id, new Product(id, name, description, shortDescription));
        }

        return products;
    }

    private int id;
    private String name;
    private String description;
    private String shortDescription;

    public Product(int id, String name, String description, String shortDescription) {
        this.id               = id;
        this.name             = name;
        this.description      = description;
        this.shortDescription = shortDescription;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        return shortDescription;
    }
}
