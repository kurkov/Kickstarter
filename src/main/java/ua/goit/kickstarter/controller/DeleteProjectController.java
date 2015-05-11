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

public class DeleteProjectController implements Controller {

  private final CategoryService categoryService;
  private final ProjectService projectService;

  public DeleteProjectController(CategoryService categoryService, ProjectService projectService) {
    this.categoryService = categoryService;
    this.projectService = projectService;
  }

  @Override
  public ViewModel process(Request request) throws ServletException, IOException {
    return proceedPost(request);
  }

  private ViewModel proceedPost(Request request) {

    ViewModel viewModel;
    Integer categoryId;
    Integer projectId;

    projectId = getIdInteger(request.getParameter("projectId"));
    categoryId = getIdInteger(request.getParameter("categoryId"));

    projectService.deleteProject(projectId);

    viewModel = getViewModelForProjectsViewInCategory(categoryId);

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
