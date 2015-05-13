package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.factory.ConnectionPool;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;

public class FrontController {
  public ViewModel dispatchRequest(Request request) throws ServletException, IOException {
    ViewModel viewModel;
    String url = request.getUrl();
    if (url.contains("category")) {
      viewModel = getViewModelFromCategoryControllers(request, url);
    } else if (url.contains("project")) {
      viewModel = getViewModelFromProjectControllers(request, url);
    } else if (url.contains("comment")) {
      viewModel = getViewModelFromCommentControllers(request, url);
    } else if (url.contains("blogpost")) {
      viewModel = getViewModelFromBlogPostControllers(request, url);
    } else {
      Controller errorController = Factory.createErrorController
          (ErrorController.class, ConnectionPool.getConnection());
      viewModel = errorController.process(request);
    }
    
    return viewModel;
  }

  private ViewModel getViewModelFromCategoryControllers(Request request, String url) throws ServletException,
      IOException {
    ViewModel viewModel;
    if (url.contains("add")) {
      Controller createCategoryController = Factory.createCategoryController
          (CreateCategoryController.class, ConnectionPool.getConnection());
      viewModel = createCategoryController.process(request);
    } else if (url.contains("edit")) {
      Controller updateCategoryController = Factory.createCategoryController
          (UpdateCategoryController.class, ConnectionPool.getConnection());
      viewModel = updateCategoryController.process(request);
    } else if (url.contains("delete")) {
      Controller deleteCategoryController = Factory.createCategoryController
          (DeleteCategoryController.class, ConnectionPool.getConnection());
      viewModel = deleteCategoryController.process(request);
    } else if(url.matches(".*\\d+.*")) {
      Controller readCategoryController = Factory.createReadCategoryController
          (ReadCategoryController.class, ConnectionPool.getConnection());
      viewModel = readCategoryController.process(request);
    } else {
      Controller readAllCategoriesController = Factory.createCategoryController
          (ReadAllCategoriesController.class, ConnectionPool.getConnection());
      viewModel = readAllCategoriesController.process(request);
    }
    return viewModel;
  }

  private ViewModel getViewModelFromProjectControllers(Request request, String url) throws ServletException,
      IOException {
    ViewModel viewModel;
    if (url.contains("add")) {
      Controller createProjectController = Factory.createCreateProjectController
          (CreateProjectController.class, ConnectionPool.getConnection());
      viewModel = createProjectController.process(request);
    } else if (url.contains("edit")) {
      Controller updateProjectController = Factory.createProjectController
          (UpdateProjectController.class, ConnectionPool.getConnection());
      viewModel = updateProjectController.process(request);
    } else if (url.contains("delete")) {
      Controller deleteProjectController = Factory.createProjectController
          (DeleteProjectController.class, ConnectionPool.getConnection());
      viewModel = deleteProjectController.process(request);
    } else if(url.matches(".*\\d+.*")) {
      Controller readProjectController = Factory.createReadProjectController
          (ReadProjectController.class, ConnectionPool.getConnection());
      viewModel = readProjectController.process(request);
    } else {
      Controller readAllProjectsController = Factory.createReadAllProjectsController
          (ReadAllProjectsController.class, ConnectionPool.getConnection());
      viewModel = readAllProjectsController.process(request);
    }
    return viewModel;
  }

  private ViewModel getViewModelFromCommentControllers(Request request, String url) throws ServletException,
      IOException {
    ViewModel viewModel;
    if (url.contains("add")) {
      Controller createCommentController = Factory.createCommentController
          (CreateCommentController.class, ConnectionPool.getConnection());
      viewModel = createCommentController.process(request);
    } else if (url.contains("delete")) {
      Controller deleteCommentController = Factory.createCommentController
          (DeleteCommentController.class, ConnectionPool.getConnection());
      viewModel = deleteCommentController.process(request);
    } else {
      Controller errorController = Factory.createErrorController
          (ErrorController.class, ConnectionPool.getConnection());
      viewModel = errorController.process(request);
    }
    return viewModel;
  }

  private ViewModel getViewModelFromBlogPostControllers(Request request, String url) throws ServletException,
      IOException {
    ViewModel viewModel;
    if (url.contains("add")) {
      Controller createBlogPostController = Factory.createBlogPostController
          (CreateBlogPostController.class, ConnectionPool.getConnection());
      viewModel = createBlogPostController.process(request);
    } else if (url.contains("edit")) {
      Controller updateBlogPostController = Factory.createBlogPostController
          (UpdateBlogPostController.class, ConnectionPool.getConnection());
      viewModel = updateBlogPostController.process(request);
    } else if (url.contains("delete")) {
      Controller deleteBlogPostController = Factory.createBlogPostController
          (DeleteBlogPostController.class, ConnectionPool.getConnection());
      viewModel = deleteBlogPostController.process(request);
    } else {
      Controller errorController = Factory.createErrorController
          (ErrorController.class, ConnectionPool.getConnection());
      viewModel = errorController.process(request);
    }
    return viewModel;
  }
}
