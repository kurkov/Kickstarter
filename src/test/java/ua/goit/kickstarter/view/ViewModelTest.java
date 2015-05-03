package ua.goit.kickstarter.view;

import org.junit.Test;
import static org.junit.Assert.*;

import ua.goit.kickstarter.controller.Controller;
import ua.goit.kickstarter.controller.ControllerFactory;
import ua.goit.kickstarter.controller.ControllerFactoryImpl;
import ua.goit.kickstarter.servlet.Request;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewModelTest {
  @Test
  public void givenRequest_WhenInvokeProcessOfCategoryController_ThenWouldReturnExpectedStringOfViewModel()
      throws ServletException, IOException {
    String expected = "/WEB-INF/jsp/categories.jsp";

    Map<String, String[]> parameters = new HashMap<String, String[]>(){{
      put("name", new String[]{"Music"});
    }};
    String method = "POST";
    String url = "/category/add";
    Request request = new Request(parameters, method, url);
    ControllerFactory controllerFactory = new ControllerFactoryImpl();
    Controller controller = controllerFactory.getCategoryController();
    ViewModel viewModel =  controller.process(request);
    String actual = viewModel.getView();

    assertEquals(expected, actual);
  }
}
