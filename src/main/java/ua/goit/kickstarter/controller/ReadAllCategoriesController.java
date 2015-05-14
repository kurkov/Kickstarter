package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.service.CategoryService;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class ReadAllCategoriesController implements Controller {
  private final CategoryService categoryService;

  public ReadAllCategoriesController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @Override
  public ViewModel process(Request request) throws ServletException, IOException {
    return proceedGet();
  }

  private ViewModel proceedGet() {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/categories.jsp");
    List<Category> categories = categoryService.getAll();
    viewModel.addAttributes("categories", categories);
    return viewModel;
  }
}
