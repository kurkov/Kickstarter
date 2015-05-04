package ua.goit.kickstarter.view;

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
  @Test
  public void givenRequest_WhenInvokeProcessOfCategoryController_ThenWouldReturnExpectedStringOfViewModel()
      throws ServletException, IOException, SQLException {
    String expected = "/WEB-INF/jsp/categories.jsp";
    Connection connection = ConnectionPool.getConnection();

    Map<String, String[]> parameters = new HashMap<String, String[]>(){{
      put("categoryName", new String[]{"Music"});
    }};
    String method = "POST";
    String url = "/add";
    Request request = new Request(parameters, method, url);
    Controller controller = Factory.createCategoryController
        (CategoryController.class, connection);
    ViewModel viewModel =  controller.process(request);
    String actual = viewModel.getView();

    assertEquals(expected, actual);
    connection.rollback();
    connection.close();
  }
}
