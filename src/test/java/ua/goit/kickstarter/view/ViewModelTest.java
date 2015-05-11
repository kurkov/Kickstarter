package ua.goit.kickstarter.view;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import ua.goit.kickstarter.controller.CategoryController;
import ua.goit.kickstarter.controller.Controller;
import ua.goit.kickstarter.factory.ConnectionPool;
import ua.goit.kickstarter.factory.Factory;
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
  public void givenRequest_WhenInvokeProcessOfCategoryController_ThenReturnExpectedStringOfViewModel()
      throws ServletException, IOException, SQLException {
    String expected = "/WEB-INF/jsp/categories.jsp";
    Map<String, String[]> parameters = new HashMap<String, String[]>(){{
      put("categoryName", new String[]{"Music"});
    }};
    String method = "POST";
    String url = "/category/add";
    String simpleUrl = "/category";
    Request request = new Request(parameters, method, url, simpleUrl);
    Controller controller = Factory.createCategoryController
        (CategoryController.class, connection);
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
