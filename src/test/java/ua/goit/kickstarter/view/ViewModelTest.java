package ua.goit.kickstarter.view;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import ua.goit.kickstarter.controller.Controller;
import ua.goit.kickstarter.controller.CreateCategoryController;
import ua.goit.kickstarter.controller.UpdateProjectController;
import ua.goit.kickstarter.dao.CategoryDao;
import ua.goit.kickstarter.dao.ProjectDao;
import ua.goit.kickstarter.factory.ConnectionPool;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.servlet.Request;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ViewModelTest {
  private static Connection connection;

  @BeforeClass
  public static void createConnection() throws SQLException {
    connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);
  }

  @Test
  public void givenRequest_WhenInvokeProcessOfCreateCategoryController_ThenReturnExpectedPageOfViewModel()
      throws ServletException, IOException, SQLException {
    String expected = "/WEB-INF/jsp/categories.jsp";
    Map<String, String[]> parameters = new HashMap<String, String[]>(){{
      put("categoryName", new String[]{"Music"});
    }};
    String method = "POST";
    String url = "/category/add";
    Request request = new Request(parameters, method, url);
    Controller controller = Factory.createCategoryController
        (CreateCategoryController.class, connection);
    ViewModel viewModel =  controller.process(request);
    String actual = viewModel.getView();

    assertEquals(expected, actual);

    connection.rollback();
  }

  @Test
  public void givenRequest_WhenInvokeProcessOfUpdateProjectController_ThenReturnExpectedPageOfViewModel()
      throws ServletException, IOException, SQLException {
    String expected = "/WEB-INF/jsp/categoryItem.jsp";

    Category category = new Category("Music");
    CategoryDao categoryDao = Factory.getCategoryDao(connection);
    Category addedCategory = categoryDao.add(category);
    final Integer categoryId = addedCategory.getId();
    Project project = new Project("Player", "Some description", category);
    ProjectDao projectDao = Factory.getProjectDao(connection);
    Project addedProject = projectDao.add(project);
    final Integer projectId = addedProject.getId();

    Map<String, String[]> parameters = new HashMap<String, String[]>(){{
      put("projectName", new String[]{"Player"});
      put("projectDescription", new String[]{"Some description"});
      put("projectId", new String[]{projectId.toString()});
      put("categoryId", new String[]{categoryId.toString()});
    }};
    String method = "POST";
    String url = "/project/22/edit";

    Request request = new Request(parameters, method, url);
    Controller controller = Factory.createProjectController
        (UpdateProjectController.class, connection);
    ViewModel viewModel =  controller.process(request);
    String actual = viewModel.getView();

    assertEquals(expected, actual);

    connection.rollback();
  }

  @AfterClass
  public static void closeConnection() throws SQLException {
    connection.close();
  }
}
