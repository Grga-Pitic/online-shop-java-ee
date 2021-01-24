package model.shop;

import db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Product {

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
