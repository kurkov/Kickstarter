package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.service.CategoryService;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.view.ViewModel;

import java.util.List;

public class CategoryController implements Controller {

  private final CategoryService service;

  public CategoryController(CategoryService service) {
    this.service = service;
  }

  @Override
  public ViewModel process(Request request) {

    ViewModel vm = new ViewModel("/WEB-INF/jsp/categories.jsp");
    String categoryName = request.getParameter("categoryName");

    if (categoryName == null) {
      throw new RuntimeException("Category Name is Null");
    }

    service.addNewCategory(new Category(categoryName));

    List<Category> categories = service.getAll();
    return vm.withAttribute("categories", categories);
  }
}




