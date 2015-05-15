package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.service.CategoryService;
import ua.goit.kickstarter.service.ProjectService;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.util.UrlParser;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class CreateProjectController implements Controller {
  private final CategoryService categoryService;
  private final ProjectService projectService;

  public CreateProjectController(CategoryService categoryService, ProjectService projectService) {
    this.categoryService = categoryService;
    this.projectService = projectService;
  }

  @Override
  public ViewModel process(Request request) throws ServletException, IOException {
    ViewModel viewModel = null;

    if ("GET".equals(request.getMethod())) {
      viewModel = proceedGet(request);
    } else if ("POST".equals(request.getMethod())) {
      viewModel = proceedPost(request);
    }

    return viewModel;
  }

  private ViewModel proceedGet(Request request) {
    Integer categoryId = UrlParser.getObjectId(request.getUrl());
    return getProjectAddFromCategory(categoryId);
  }

  private ViewModel getProjectAddFromCategory(Integer categoryId) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItemAdd.jsp");
    List<Category> categories = categoryService.getAll();
    viewModel.addAttributes("categories", categories);
    viewModel.addAttributes("categoryId", categoryId);
    return viewModel;
  }

  private ViewModel proceedPost(Request request) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItemAdd.jsp");
    Integer categoryId = UrlParser.getObjectId(request.getUrl());
    String projectName = request.getParameter("projectName");
    String projectDescription = request.getParameter("projectDescription");

    if (projectName.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'name' must be filled");
    } else if (projectDescription.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'description' must be filled");
    } else {
      Project project = projectService.addNewProject(new Project(projectName, projectDescription, categoryId));
      viewModel = getViewModelForProjectView(project);
    }

    return viewModel;
  }

  private ViewModel getViewModelForProjectView(Project project) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItem.jsp");
    viewModel.addAttributes("project", project);
    return viewModel;
  }
}
