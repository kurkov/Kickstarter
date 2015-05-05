package ua.goit.kickstarter.dao;

import org.apache.log4j.Logger;
import org.junit.Test;
import ua.goit.kickstarter.factory.ConnectionPool;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ProjectDaoTest {
  private Integer getExistingCategoryId(Connection connection) {
    Integer res = null;
    CategoryDao categoryDao = Factory.getCategoryDao(connection);
    List<Category> categoryList = categoryDao.getAll();

    for (Category category : categoryList) {
      res = category.getId();
    }

    if (res == null) {
      fail("No 'Category' record available to test!");
    }

    return res;
  }

  @Test
  public void addNewProject() throws SQLException {
    Connection connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);

    CategoryDao categoryDao = Factory.getCategoryDao(connection);
    ProjectDao projectDao = Factory.getProjectDao(connection);
    Project project = new Project();
    project.setName("New project test");
    project.setDescription("Some new project");
    int idCategory = getExistingCategoryId(connection);
    Category category = categoryDao.getById(idCategory);
    project.setCategory(category);
    Project addedProject = projectDao.add(project);

    assertEquals(addedProject.getName(), project.getName());
    assertEquals(addedProject.getDescription(), project.getDescription());
    assertEquals(addedProject.getCategory(), project.getCategory());

    connection.rollback();
    connection.close();
  }


  @Test
  public void getProjectById() throws SQLException {
    Connection connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);

    ProjectDao projectDao = Factory.getProjectDao(connection);
    Project project = projectDao.getById(1);

    assertNotNull(project);

    connection.rollback();
    connection.close();
  }

  @Test
  public void getProjectsByCategoryId() throws SQLException {
    Connection connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);

    ProjectDao projectDao = Factory.getProjectDao(connection);
    List<Project> projects = projectDao.getByCategoryId(1);

    Logger logger = Logger.getLogger(this.getClass());
    logger.info(projects);
    logger.info("projects.size()" + projects.size());

    assertNotNull(projects);
    assertTrue(projects.size() > 0);

    connection.rollback();
    connection.close();
  }


  @Test
  public void getProjectsByCategory() throws SQLException {
    Connection connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);

    CategoryDao categoryDao = Factory.getCategoryDao(connection);
    ProjectDao projectDao = Factory.getProjectDao(connection);
    Category category = categoryDao.getById(1);
    List<Project> projects = projectDao.getByCategory(category);

    assertNotNull(projects);
    assertTrue(projects.size() > 0);

    connection.rollback();
    connection.close();
  }

  @Test
  public void getAllProjects() throws SQLException {
    Connection connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);

    ProjectDao projectDao = Factory.getProjectDao(connection);
    List<Project> projects = projectDao.getAll();

    assertNotNull(projects);
    assertTrue(projects.size() > 0);

    connection.rollback();
    connection.close();
  }
}
