package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.service.CategoryService;
import ua.goit.kickstarter.service.ProjectService;
import ua.goit.kickstarter.servlet.Request;
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

    ViewModel viewModel;
    Integer projectId;

    projectId = getIdInteger(request.getParameter("projectId"));
    viewModel = getViewModelForProjectEdit(projectId);

    return viewModel;
  }

  private ViewModel proceedPost(Request request) {

    ViewModel viewModel;
    Integer projectId;
    Integer categoryId;
    String projectName;
    String projectDescription;

    projectId = getIdInteger(request.getParameter("projectId"));
    categoryId = getIdInteger(request.getParameter("categoryId"));

    projectName = request.getParameter("projectName");
    projectDescription = request.getParameter("projectDescription");

    if (projectName.equals("") || projectDescription.equals("")) {
      viewModel = getViewModelForProjectEdit(projectId);
    } else {
      projectService.editProject(projectId, projectName, projectDescription);
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
