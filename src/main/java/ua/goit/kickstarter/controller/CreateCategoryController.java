package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.service.CategoryService;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class CreateCategoryController implements Controller {
  private final CategoryService categoryService;

  public CreateCategoryController(CategoryService categoryService) {
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

  private ViewModel proceedGet (Request request) throws ServletException, IOException {
    return new ViewModel("/WEB-INF/jsp/categoryItemAdd.jsp");
  }

  private ViewModel proceedPost (Request request) throws ServletException, IOException {
    String categoryName = request.getParameter("categoryName");
    ViewModel viewModel;

    if (categoryName.equals("")) {
      viewModel = getErrorMessage();
    } else {
      categoryService.add(new Category(categoryName));
      viewModel = getViewModelForAllCategories();
    }
    return viewModel;
  }

  private ViewModel getErrorMessage() {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/categoryItemAdd.jsp");
    viewModel.addAttributes("ErrorMessage", "Field 'name' must be filled");
    return viewModel;
  }

  private ViewModel getViewModelForAllCategories() {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/categories.jsp");
    List<Category> categories = categoryService.getAll();
    viewModel.addAttributes("categories", categories);
    return viewModel;
  }
}
