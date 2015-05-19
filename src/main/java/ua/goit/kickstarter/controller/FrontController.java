package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;

public class FrontController {
  Connection connection;

  public FrontController(Connection connection) {
    this.connection = connection;
  }

  public ViewModel dispatchRequest(Request request) throws ServletException, IOException {
    ViewModel viewModel;
    String url = request.getUrl();
    if (url.contains("category")) {
      viewModel = getViewModelFromCategoryControllers(request, url);
    } else if (url.contains("project")) {
      viewModel = getViewModelFromProjectControllers(request, url);
    } else {
      Controller errorController = Factory.createErrorController
          (ErrorController.class, connection);
      viewModel = errorController.process(request);
    }
    
    return viewModel;
  }

  private ViewModel getViewModelFromCategoryControllers(Request request, String url) throws ServletException,
      IOException {
    ViewModel viewModel;
    if (url.contains("add")) {
      Controller createCategoryController = Factory.createCategoryController
          (CreateCategoryController.class, connection);
      viewModel = createCategoryController.process(request);
    } else if (url.contains("edit")) {
      Controller updateCategoryController = Factory.createCategoryController
          (UpdateCategoryController.class, connection);
      viewModel = updateCategoryController.process(request);
    } else if (url.contains("delete")) {
      Controller deleteCategoryController = Factory.createCategoryController
          (DeleteCategoryController.class, connection);
      viewModel = deleteCategoryController.process(request);
    } else if(url.matches(".*\\d+.*")) {
      Controller readCategoryController = Factory.createReadCategoryController
          (ReadCategoryController.class, connection);
      viewModel = readCategoryController.process(request);
    } else {
      Controller readAllCategoriesController = Factory.createCategoryController
          (ReadAllCategoriesController.class, connection);
      viewModel = readAllCategoriesController.process(request);
    }
    return viewModel;
  }

  private ViewModel getViewModelFromProjectControllers(Request request, String url) throws ServletException,
      IOException {
    ViewModel viewModel;
    if (url.contains("add")) {
      Controller createProjectController = Factory.createCreateProjectController
          (CreateProjectController.class, connection);
      viewModel = createProjectController.process(request);
    } else if (url.contains("edit")) {
      Controller updateProjectController = Factory.createProjectController
          (UpdateProjectController.class, connection);
      viewModel = updateProjectController.process(request);
    } else if (url.contains("delete")) {
      Controller deleteProjectController = Factory.createProjectController
          (DeleteProjectController.class, connection);
      viewModel = deleteProjectController.process(request);
    } else if(url.matches(".*\\d+.*")) {
      Controller readProjectController = Factory.createReadProjectController
          (ReadProjectController.class, connection);
      viewModel = readProjectController.process(request);
    } else {
      Controller readAllCategoriesController = Factory.createCategoryController
          (ReadAllCategoriesController.class, connection);
      viewModel = readAllCategoriesController.process(request);
    }
    return viewModel;
  }
}
