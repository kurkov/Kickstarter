package ua.goit.kickstarter.dao;

import ua.goit.kickstarter.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {

  public CategoryDaoImpl(Connection connection) {
    super(connection);
  }

  @Override
  public Category getById(Integer id) {
    Category category;
    String sqlSelect = "SELECT * FROM categories WHERE id = " + id + ";";
    try {
      ResultSet rs = executeQuery(sqlSelect);
      if (rs.next()) {
        String name = rs.getString("name");
        category = new Category(name);
        category.setId(id);
      } else {
        category = null;
      }
      rs.close();
      statement.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return category;
  }

  @Override
  public List<Category> getAll() {
    List<Category> categoryList = new ArrayList<>();
    String sqlSelect = "SELECT * FROM categories;";
    try {
      ResultSet rs = executeQuery(sqlSelect);
      while (rs.next()) {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        Category category = new Category(name);
        category.setId(id);
        categoryList.add(category);
      }
      rs.close();
      statement.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return categoryList;
  }

  @Override
  public Category add(Category category) {
    int categoryId;
    String sqlInsert = "INSERT INTO categories (name) VALUES (?);";
    try {
      PreparedStatement statement = connection.prepareStatement(sqlInsert,Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, category.getName());

      statement.executeUpdate();

      try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          categoryId = generatedKeys.getInt(1);
          category = getById(categoryId);
        } else {
          throw new SQLException("Creating user failed, no ID obtained.");
        }
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return category;
  }

  @Override
  public void delete(Category category) {
    String query = "DELETE FROM categories WHERE id = " +
            category.getId() + ";";
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
}
