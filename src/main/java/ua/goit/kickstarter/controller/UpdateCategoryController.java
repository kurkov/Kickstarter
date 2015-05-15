package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.service.CategoryService;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.util.UrlParser;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class UpdateCategoryController implements Controller {
  private final CategoryService categoryService;

  public UpdateCategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
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

  private ViewModel proceedGet(Request request) throws ServletException, IOException {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/categoryItemEdit.jsp");
    Integer categoryId = UrlParser.getObjectId(request.getUrl());
    Category category = categoryService.getById(categoryId);
    viewModel.addAttributes("category", category);
    return viewModel;
  }

  private ViewModel proceedPost(Request request) throws ServletException, IOException {
    String categoryName = request.getParameter("categoryName");
    Integer categoryId = UrlParser.getObjectId(request.getUrl());
    Category category = categoryService.getById(categoryId);
    ViewModel viewModel;

    if (categoryName.equals("")) {
      viewModel = getErrorMessage(category);
    } else {
      category.setName(categoryName);
      categoryService.editCategory(category);
      viewModel = getAllCategories();
    }
    return viewModel;
  }

  private ViewModel getErrorMessage(Category category) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/categoryItemEdit.jsp");
    viewModel.addAttributes("ErrorMessage", "Field 'name' must be filled");
    viewModel.addAttributes("category", category);
    return viewModel;
  }

  private ViewModel getAllCategories() {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/categories.jsp");
    List<Category> categories = categoryService.getAll();
    viewModel.addAttributes("categories", categories);
    return viewModel;
  }
}
