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
    String categoryIdStr = request.getParameter("categoryId");
    Integer categoryId = getIntegerId(categoryIdStr);
    Category category = categoryService.getById(categoryId);
    return getProjectAddFromCategory(category);
  }

  private Integer getIntegerId(String categoryIdStr) {
    Integer id = null;
    try {
      id = Integer.parseInt(categoryIdStr);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return id;
  }

  private ViewModel getProjectAddFromCategory(Category category) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItemAdd.jsp");
    List<Category> categories = categoryService.getAll();
    viewModel.addAttributes("categories", categories);
    viewModel.addAttributes("category", category);
    return viewModel;
  }

  private ViewModel proceedPost(Request request) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItemAdd.jsp");
    String projectName = request.getParameter("projectName");
    String projectDescription = request.getParameter("projectDescription");
    String categoryIdStr = request.getParameter("categoryId");
    Integer categoryId = getIntegerId(categoryIdStr);
    Category category = categoryService.getById(categoryId);
    List<Category> categories = categoryService.getAll();

    if (projectName.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'name' must be filled");
      viewModel.addAttributes("category", category);
      viewModel.addAttributes("categories", categories);
    } else if (projectDescription.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'description' must be filled");
      viewModel.addAttributes("category", category);
      viewModel.addAttributes("categories", categories);
    } else {
      System.out.println("Before creating project");
      Project newProject = new Project(projectName, projectDescription, category);
      System.out.println(newProject.toString());
      Project project = projectService.addNewProject(newProject);
      System.out.println(project.toString());
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
