package ua.goit.kickstarter.dao;

import org.apache.log4j.Logger;
import ua.goit.kickstarter.factory.ConnectionPool;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl extends AbstractDao<Project> implements ProjectDao {

  public ProjectDaoImpl(Connection connection) {
    super(connection);
  }

  @Override
  public Project add(Project newProject) {
    String sqlInsert = "INSERT INTO projects (name, description, id_category) " +
            "VALUES ( ?, ?, ? );";
    Connection connection = ConnectionPool.getConnection();
    try {
      PreparedStatement statement = connection.prepareStatement(sqlInsert);
      statement.setString(1, newProject.getName());
      statement.setString(2, newProject.getDescription());
      statement.setInt(3, newProject.getCategory().getId());

      int affectedRows = statement.executeUpdate();

      if (affectedRows == 0) {
        throw new SQLException("Creating project failed, no rows affected.");
      }

      try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          newProject.setId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Creating project failed, no ID obtained.");
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return newProject;
  }

  @Override
  public Project getById(Integer id) {
    Project project;
    Category category;
    CategoryDao categoryDao = Factory.getCategoryDao(connection);
    String sqlQuery = "SELECT * FROM projects WHERE id = " + id + ";";
    try {
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(sqlQuery);
      if (rs.next()) {
        String name = rs.getString("name");
        String description = rs.getString("description");
        Integer id_category = rs.getInt("id_category");
        category = categoryDao.getById(id_category);
        project = new Project(name, description, category);
        project.setId(id);
      } else {
        project = null;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return project;
  }

  @Override
  public List<Project> getByCategory(Category category) {
    List<Project> projectList = new ArrayList<>();
    CategoryDao categoryDao = Factory.getCategoryDao(connection);
    Project project;
    String sqlQuery = "SELECT * FROM projects WHERE id_category = " + category.getId();
    ResultSet rs;
    try {
      rs = executeQuery(sqlQuery);
      while (rs.next()) {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        Integer id_category = rs.getInt("id_category");
        category = categoryDao.getById(id_category);
        project = new Project(name, description, category);
        project.setId(id);
        projectList.add(project);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return projectList;
  }

  @Override
  public List<Project> getAll() {
    List<Project> projectList = new ArrayList<>();
    String sqlQuery = "SELECT * FROM projects";
    Project project;
    CategoryDao categoryDao = Factory.getCategoryDao(connection);
    try {
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(sqlQuery);
      while (rs.next()) {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        Integer id_category = rs.getInt("id_category");
        Category category = categoryDao.getById(id_category);
        project = new Project(name, description, category);
        project.setId(id);
        projectList.add(project);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return projectList;
  }

  @Override
  public Project update(Project project) {
    String query = "UPDATE projects " +
        " SET name = '" + project.getName() + "'," +
        " description = '" + project.getDescription() + "'" +
        " WHERE id = " + project.getId() + ";";
    executeUpdate(query);
    return project;
  }

  @Override
  public void delete(Project project) {
    String query = "DELETE FROM projects " +
        " WHERE id = " + project.getId() + ";";
    executeUpdate(query);
  }
}
