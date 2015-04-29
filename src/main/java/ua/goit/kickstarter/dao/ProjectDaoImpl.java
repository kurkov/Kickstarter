package ua.goit.kickstarter.dao;

import org.apache.log4j.Logger;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl extends AbstractDaoImpl<Project>
        implements ProjectDao {

  @Override
  public Project add(Project newProject) {

    String sqlInsert = "INSERT INTO projects (name,description, id_category) " +
            "VALUES ( ?, ?, ? );";
    Connection connection = Factory.getConnection();
    try {
      PreparedStatement statement = connection.prepareStatement(sqlInsert);
      statement.setString(1, newProject.getName());
      statement.setString(2, newProject.getDescription());
      statement.setString(3, newProject.getCategory().getId().toString());

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
  public Project add(String name, String categoryId, String
      description) {

    CategoryDao categoryDao = new DaoFactoryImpl().getCategoryDao();
    Category category = categoryDao.getById(categoryId);

    if (category == null) {
      throw new RuntimeException("wrong category id ( category_id ='" + categoryId + "'");
    }
    return add(new Project(0, name, category, description));
  }


  @Override
  public Project getById(Integer id) {
    Project project;
    Category category;
    CategoryDao categoryDao = new CategoryDaoImpl();
    String sqlQuery = "SELECT * FROM projects WHERE id = " + id + ";";
    Connection connection = Factory.getConnection();
    try {
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(sqlQuery);
      String name = rs.getString("name");
      String description = rs.getString("description");
      Integer id_category = rs.getInt("id_category");
      category = categoryDao.getById(id_category);
      project = new Project(id, name, category, description);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return project;
  }

  @Override
  public List<Project> getByCategoryId(Integer categoryId) {
    List<Project> projectList = new ArrayList<>();
    Category category;
    CategoryDao categoryDao = new CategoryDaoImpl();
    Project project;
    String sqlQuery = "SELECT * FROM projects WHERE id_category = " +
            categoryId;
    ResultSet rs;
    try {
      rs = executeQuery(sqlQuery);
      while (rs.next()) {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        Integer id_category = rs.getInt("id_category");
        category = categoryDao.getById(id_category);
        project = new Project(id, name, category, description);
        projectList.add(project);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return projectList;
  }


  @Override
  public List<Project> getByCategory(Category category) {
    return getByCategoryId(category.getId());
  }


  @Override
  public List<Project> getAll() {
    Project project;
    Category category;
    CategoryDao categoryDao = new CategoryDaoImpl();
    List<Project> projectList = new ArrayList<>();
    String sqlQuery = "SELECT * FROM projects";
    ResultSet rs;
    try {
      rs = executeQuery(sqlQuery);
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        Integer id_category = rs.getInt("id_category");
        category = categoryDao.getById(id_category);
        project = new Project(id, name, category, description);
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
    deleteById(project.getId());
  }



  @Override
  public void deleteById(Integer id) {
    Logger logger = Logger.getLogger(this.getClass());
    logger.info("Deleted! id=" + id);
    String query = "DELETE FROM projects " +
        " WHERE id = " + id + ";";
    executeUpdate(query);
  }


}
