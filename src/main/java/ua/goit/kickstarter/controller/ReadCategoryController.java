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

public class ReadCategoryController implements Controller {
  private final CategoryService categoryService;
  private final ProjectService projectService;

  public ReadCategoryController(CategoryService categoryService, ProjectService projectService) {
    this.categoryService = categoryService;
    this.projectService = projectService;
  }

  @Override
  public ViewModel process(Request request) throws ServletException, IOException {
    Integer categoryId = UrlParser.getObjectId(request.getUrl());
    Category category = categoryService.getById(categoryId);
    return getCategoryView(category);
  }

  private ViewModel getCategoryView(Category category) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/categoryItem.jsp");
    List<Project> projects = null;
    if (category != null) {
      projects = projectService.getByCategory(category);
    }
    viewModel.addAttributes("projects", projects);
    viewModel.addAttributes("category", category);
    return viewModel;
  }
}
