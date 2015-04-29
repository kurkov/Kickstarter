package ua.goit.kickstarter.dao;

import org.apache.log4j.Logger;
import org.junit.Test;
import ua.goit.kickstarter.dao.CategoryDao;
import ua.goit.kickstarter.dao.DaoFactory;
import ua.goit.kickstarter.dao.ProjectDao;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.*;
import static org.junit.Assert.fail;

public class ProjectDaoTest {


  private Integer getExistingCategoryId() {

    Integer res = null;
    DaoFactory daoFactory = Factory.getDaoFactory();
    CategoryDao categoryDao = daoFactory.getCategoryDao();
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
  public void add_New_Project() throws SQLException {

    DaoFactory daoFactory = Factory.getDaoFactory();
    CategoryDao categoryDao = daoFactory.getCategoryDao();
    ProjectDao projectDao = daoFactory.getProjectDao();
    Connection connection = Factory.getConnection();
    connection.setAutoCommit(false);


    Project project = new Project();
    project.setName("New project test");
    project.setDescription("Some new project");

    int idCategory = getExistingCategoryId();
    Category category = categoryDao.getById(idCategory);
    project.setCategory(category);

    Project addedProject = projectDao.add(project);

    assertEquals(addedProject.getName(), project.getName());
    assertEquals(addedProject.getDescription(), project.getDescription());
    assertEquals(addedProject.getCategory(), project.getCategory());
    connection.rollback();
    Factory.closeConnection(connection);
  }


  @Test
  public void getProject__byId() throws SQLException {

    Connection connection = Factory.getConnection();

    connection.setAutoCommit(false);

    DaoFactory daoFactory = Factory.getDaoFactory();
    CategoryDao categoryDao = daoFactory.getCategoryDao();
    ProjectDao projectDao = daoFactory.getProjectDao();

    Project project = projectDao.getById(1);
    assertNotNull(project);
    connection.rollback();
    Factory.closeConnection(connection);
  }

  @Test
  public void getProjects__byCategoryId() throws SQLException {
    Connection connection = Factory.getConnection();
    connection.setAutoCommit(false);

    DaoFactory daoFactory = Factory.getDaoFactory();
    CategoryDao categoryDao = daoFactory.getCategoryDao();
    ProjectDao projectDao = daoFactory.getProjectDao();

    List<Project> projects = projectDao.getByCategoryId(1);

    Logger logger = Logger.getLogger(this.getClass());
    logger.info(projects);
    logger.info("projects.size()" + projects.size());

    assertNotNull(projects);
    assertTrue(projects.size() > 0);

    connection.rollback();
    Factory.closeConnection(connection);
  }


  @Test
  public void getProjects__byCategory() throws SQLException {


    Connection connection = Factory.getConnection();

    connection.setAutoCommit(false);

    DaoFactory daoFactory = Factory.getDaoFactory();
    CategoryDao categoryDao = daoFactory.getCategoryDao();
    ProjectDao projectDao = daoFactory.getProjectDao();

    Category category = categoryDao.getById(1);

    List<Project> projects = projectDao.getByCategory(category);
    assertNotNull(projects);
    assertTrue(projects.size() > 0);

    connection.rollback();
    Factory.closeConnection(connection);
  }

  @Test
  public void getProjects__all() throws SQLException {

    Connection connection = Factory.getConnection();
    connection.setAutoCommit(false);
    DaoFactory daoFactory = Factory.getDaoFactory();
    CategoryDao categoryDao = daoFactory.getCategoryDao();
    ProjectDao projectDao = daoFactory.getProjectDao();
    List<Project> projects = projectDao.getAll();
    assertNotNull(projects);
    assertTrue(projects.size() > 0);
    connection.rollback();
    Factory.closeConnection(connection);
  }


}
