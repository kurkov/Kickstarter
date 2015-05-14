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

public class UpdateProjectController implements Controller {

  private final CategoryService categoryService;
  private final ProjectService projectService;

  public UpdateProjectController(CategoryService categoryService, ProjectService projectService) {
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
    Integer projectId = UrlParser.getObjectId(request.getUrl());
    return getViewModelForProjectEdit(projectId);
  }

  private ViewModel proceedPost(Request request) {
    Integer projectId = getIdInteger(request.getParameter("projectId"));
    Integer categoryId = getIdInteger(request.getParameter("categoryId"));
    String projectName = request.getParameter("projectName");
    String projectDescription = request.getParameter("projectDescription");
    Category category = categoryService.getById(categoryId);
    Project project = new Project(projectId, projectName, category, projectDescription);
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/categoryItemEdit.jsp");

    if (projectName.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'name' must be filled");
    } else if (projectDescription.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'description' must be filled");
    } else {
      projectService.editProject(project);
      viewModel = getViewModelForProjectsViewInCategory(categoryId);
    }

    return viewModel;
  }

  private ViewModel getViewModelForProjectsViewInCategory(Integer categoryId) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/categoryItem.jsp");
    Category category = categoryService.getById(categoryId);
    List<Project> projects = null;

    if (category != null) {
      projects = projectService.getByCategory(category);
    }

    viewModel.addAttributes("projects", projects);
    viewModel.addAttributes("category", category);
    viewModel.setUrlForRedirect("/servlet/category/" + categoryId);

    return viewModel;
  }

  private ViewModel getViewModelForProjectEdit(Integer projectId) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItemEdit.jsp");
    Project project = projectService.getById(projectId);
    viewModel.addAttributes("categoryId", project.getCategory().getId());
    viewModel.addAttributes("project", project);

    return viewModel;
  }

  private Integer getIdInteger(String idStr) {
    Integer id = null;
    try {
      id = Integer.parseInt(idStr);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }

    return id;
  }
}
