package ua.goit.kickstarter.dao;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
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
  private static Connection connection;
  private static CategoryDao categoryDao;
  private static ProjectDao projectDao;

  @BeforeClass
  public static void init() throws SQLException {
    connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);
    categoryDao = Factory.getCategoryDao(connection);
    projectDao = Factory.getProjectDao(connection);
  }

  private Integer getExistingCategoryId() {
    Integer res = null;
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
  }

  @Test
  public void getProjectById() throws SQLException {
    Project project = projectDao.getById(1);

    assertNotNull(project);

    connection.rollback();
  }

  @Test
  public void getProjectsByCategoryId() throws SQLException {
    List<Project> projects = projectDao.getByCategoryId(1);

    Logger logger = Logger.getLogger(this.getClass());
    logger.info(projects);
    logger.info("projects.size()" + projects.size());

    assertNotNull(projects);
    assertTrue(projects.size() > 0);

    connection.rollback();
  }

  @Test
  public void getProjectsByCategory() throws SQLException {
    Category category = categoryDao.getById(1);
    List<Project> projects = projectDao.getByCategory(category);

    assertNotNull(projects);
    assertTrue(projects.size() > 0);

    connection.rollback();
  }

  @Test
  public void getAllProjects() throws SQLException {
    List<Project> projects = projectDao.getAll();

    assertNotNull(projects);
    assertTrue(projects.size() > 0);

    connection.rollback();
  }

  @Test
  public void deleteProject() throws SQLException {
    Project project = projectDao.add("NewProject", "Description",
        getExistingCategoryId());
    Integer projectId = project.getId();
    Project projectFromDB = projectDao.getById(projectId);

    assertNotNull(projectFromDB);

    projectDao.delete(projectFromDB);
    projectFromDB = projectDao.getById(projectId);

    assertNull(projectFromDB);

    connection.rollback();
  }

  @Test
  public void updateProject() throws SQLException {
    Project project = projectDao.add("NewProject", "Description",
        getExistingCategoryId());
    project.setName("BrandNewName");
    projectDao.update(project);
    Integer projectId = project.getId();
    Project updatedProject = projectDao.getById(projectId);

    assertEquals("BrandNewName", updatedProject.getName());

    connection.rollback();
  }

  @AfterClass
  public static void closeConnection() throws SQLException {
    connection.close();
  }
}
