package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.service.CategoryService;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.view.ViewModel;

import java.util.List;

public class GetAllCategoriesController implements Controller {

  private final CategoryService service;

  public GetAllCategoriesController(CategoryService service) {
    this.service = service;
  }

  @Override
  public ViewModel process(Request request) {
    List<Category> categories = service.getAll();
    return new ViewModel("/categories.jsp")
            .withAttribute("categories", categories);
  }
}
