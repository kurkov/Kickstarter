package ua.goit.kickstarter.dao;

import ua.goit.kickstarter.factory.ConnectionFactory;
import ua.goit.kickstarter.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends AbstractDaoImpl<Category> implements CategoryDao {

  @Override
  public Category getById(Integer id) {
    Category category;
    String sqlSelect = "SELECT * FROM categories WHERE id = " + id + ";";
    try {
      ResultSet rs = executeQuery(sqlSelect);
      if (rs.next()) {
        String name = rs.getString("name");
        category = new Category(id, name);
      } else {
        category = null;
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return category;
  }

  @Override
  public List<Category> getAll() {
    List<Category> categoryList = new ArrayList<>();
    String sqlSelect = "SELECT * FROM categories";
    try {
      ResultSet rs = executeQuery(sqlSelect);
      while (rs.next()) {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        Category category = new Category(id, name);
        categoryList.add(category);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return categoryList;
  }

  @Override
  public Category add(Category element) {
    return add(element.getName());
  }

  @Override
  public void deleteById(Integer id) {
    String query = "DELETE FROM categories WHERE id = " +
        id + ";";
    executeUpdate(query);

  }

  @Override
  public Category update(Category element) {
    String query = "UPDATE categories " +
        " SET name = '" + element.getName() + "'" +
        " WHERE id = " + element.getId() + ";";
    executeUpdate(query);
    return element;
  }

  @Override
  public Category add(String categoryName) {
    Category category;
    int categoryID;
    String sqlInsert = "INSERT INTO categories (name) VALUES ( ? )";
    Connection con = ConnectionFactory.getConnection();
    try {
      PreparedStatement statement = con.prepareStatement(sqlInsert);
      statement.setString(1, categoryName);

      int affectedRows = statement.executeUpdate();

      if (affectedRows == 0) {
        throw new SQLException("Creating user failed, no rows affected.");
      }
      try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          categoryID = generatedKeys.getInt(1);
          category = getById(categoryID);
        } else {
          throw new SQLException("Creating user failed, no ID obtained.");
        }
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return category;
  }


}
