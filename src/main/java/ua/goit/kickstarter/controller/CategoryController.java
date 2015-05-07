package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.service.CategoryService;
import ua.goit.kickstarter.service.ProjectService;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.util.Operation;
import ua.goit.kickstarter.util.OperationType;
import ua.goit.kickstarter.util.UrlParser;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class CategoryController implements Controller {

  private final CategoryService categoryService;
  private final ProjectService projectService;

  public CategoryController(CategoryService categoryService,
                            ProjectService projectService) {
    this.categoryService = categoryService;
    this.projectService = projectService;
  }

  @Override
  public ViewModel process(Request request) throws ServletException, IOException {
    ViewModel viewModel = null;
    if ("GET".equals(request.getMethod())) {
      viewModel = proceedRequest(request);
    } else if ("POST".equals(request.getMethod())) {
      viewModel = proceedPost(request);
    }
    return viewModel;
  }

  private ViewModel proceedRequest(Request req)
      throws ServletException, IOException {
    String url = req.getUrl();
    Operation operation = UrlParser.parse(url);
    ViewModel viewModel;

    if (operation.getOperationType() == OperationType.VIEW_ITEM) {
      viewModel = getViewModelForCategoryView(operation);
    } else if (operation.getOperationType() == OperationType.ADD_ITEM) {
      viewModel = getViewModelForCategoryAdd();
    } else if (operation.getOperationType() == OperationType.EDIT_ITEM) {
      viewModel = getViewModelForCategoryEdit(operation, null);
    } else {
      viewModel = getViewModelForAllCategories(categoryService);
    }

    return viewModel;
  }

  private ViewModel getViewModelForCategoryEdit(Operation operation,
                                                String categoryName) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/categoryItemEdit.jsp");
    Category category = categoryService.getById(operation.getObjectId());
    viewModel.addAttributes("category", category);
    if (categoryName.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'name' must be filled");
    }
    return viewModel;
  }

  private ViewModel getViewModelForCategoryAdd() {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/categoryItemAdd.jsp");
    viewModel.addAttributes("ErrorMessage", "Field 'name' must be filled");
    return viewModel;
  }

  private ViewModel getViewModelForCategoryView(Operation operation) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/categoryItem.jsp");
    Category category = categoryService.getById(operation.getObjectId());
    List<Project> projects = null;
    if (category != null) {
      projects = projectService.getByCategory(category);
    }
    viewModel.addAttributes("projects", projects);
    viewModel.addAttributes("category", category);
    return viewModel;
  }

  private ViewModel proceedPost(Request req)
      throws ServletException, IOException {
    String url = req.getUrl();
    Operation operation = UrlParser.parse(url);

    String categoryName = req.getParameter("categoryName");
    ViewModel viewModel = null;

    if (operation.getOperationType() == OperationType.ADD_ITEM) {
      if (categoryName.equals("")) {
        viewModel = getViewModelForCategoryAdd();
      } else {
        categoryService.addNewCategory(new Category(categoryName));
        viewModel = getViewModelForAllCategories(categoryService);
      }
    } else if (operation.getOperationType() == OperationType.DELETE_ITEM) {
      categoryService.deleteItem(operation.getObjectId());
      viewModel = getViewModelForAllCategories(categoryService);

    } else if (operation.getOperationType() == OperationType.EDIT_ITEM) {
      if (categoryName.equals("")) {
        viewModel = getViewModelForCategoryEdit(operation, categoryName);
      } else {
        categoryService.editCategory(new Category(operation.getObjectId(),
            categoryName));
        viewModel = getViewModelForAllCategories(categoryService);
      }
    }

    return viewModel;
  }

  private ViewModel getViewModelForAllCategories(CategoryService categoryService) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/categories.jsp");
    List<Category> categories = categoryService.getAll();
    viewModel.addAttributes("categories", categories);
    viewModel.setUrlForRedirect("/servlet/category");
    return viewModel;
  }
}




