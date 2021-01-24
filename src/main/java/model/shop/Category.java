package model.shop;

import db.DBConnection;
import exception.CategoryNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Category {

    public static Category findById(int id) throws SQLException {
        DBConnection connection = DBConnection.getInstance();
        PreparedStatement statement = connection.createPrepareStatement("SELECT * FROM CATEGORY WHERE ID_CATEGORY = ?");
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            int id_category  = result.getInt("id_category");
            String name = result.getString("name");
            String description = result.getString("description");
            String caption = result.getString("caption");

            return new Category(id_category, name, description, caption);
        }

        throw new CategoryNotFoundException("Category not found");
    }

    public static List<Category> findAll() throws SQLException {
        List <Category> categoryList = new ArrayList<Category>();

        DBConnection connection = DBConnection.getInstance();
        ResultSet result = connection.getStatement().executeQuery("SELECT * FROM CATEGORY");

        while (result.next()) {
            int id  = result.getInt("id_category");
            String name = result.getString("name");
            String description = result.getString("description");
            String caption = result.getString("caption");

            categoryList.add(new Category(id, name, description, caption));
        }

        return categoryList;
    }

    private int id;
    private String name;
    private String description;
    private String caption;

    public Category(int id, String name, String description, String caption) throws SQLException {
        this.id          = id;
        this.name        = name;
        this.description = description;
        this.caption     = caption;
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

    public String getCaption() {
        return caption;
    }
}
