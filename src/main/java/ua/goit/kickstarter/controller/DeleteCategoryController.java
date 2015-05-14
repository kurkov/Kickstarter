package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.service.CategoryService;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.util.UrlParser;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class DeleteCategoryController implements Controller {
  private final CategoryService categoryService;

  public DeleteCategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @Override
  public ViewModel process(Request request) throws ServletException, IOException {
    Integer categoryId = UrlParser.getObjectId(request.getUrl());
    categoryService.deleteItem(categoryId);
    return getAllCategories();
  }

  private ViewModel getAllCategories() {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/categories.jsp");
    List<Category> categories = categoryService.getAll();
    viewModel.addAttributes("categories", categories);
    viewModel.setUrlForRedirect("/servlet/category");
    return viewModel;
  }
}
