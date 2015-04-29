package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.service.CategoryService;
import ua.goit.kickstarter.service.CategoryServiceImpl;
import ua.goit.kickstarter.service.ProjectServiceImpl;
import ua.goit.kickstarter.util.Operation;
import ua.goit.kickstarter.util.OperationType;
import ua.goit.kickstarter.util.UrlParser;
import ua.goit.kickstarter.service.ProjectService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryControllerImpl implements CategoryController {
  @Override
  public void proceedRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String url = req.getPathInfo();
    String page;
    Operation operation = new UrlParser().parse(url);

    CategoryService categoryService = new CategoryServiceImpl();
    if (operation.getOperationType() == OperationType.VIEW_ITEM) {
      page = "/WEB-INF/jsp/categoryItem.jsp";
      Category category = categoryService.getById(operation.getObjectId());
      List<Project> projects = null;
      if (category != null) {
        ProjectService projectService = new ProjectServiceImpl();
        projects = projectService.getByCategory(category);
      }
      req.setAttribute("projects", projects);
      req.setAttribute("categoryItem", category);
    } else if (operation.getOperationType() == OperationType.ADD_ITEM) {
      page = "/WEB-INF/jsp/categoryItemAdd.jsp";
    } else if (operation.getOperationType() == OperationType.EDIT_ITEM) {
      Category category = categoryService.getById(operation.getObjectId());
      req.setAttribute("categoryItem", category);
      page = "/WEB-INF/jsp/categoryItemEdit.jsp";
    } else {
      page = "/WEB-INF/jsp/categories.jsp";
      List<Category> categories = categoryService.getAll();
      req.setAttribute("categories", categories);
    }

    RequestDispatcher dispatcher = req.getRequestDispatcher(page);
    dispatcher.forward(req, resp);
  }

  @Override
  public void proceedPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String url = req.getPathInfo();
    Operation operation = new UrlParser().parse(url);
    String categoryName = req.getParameter("categoryName");
    String page = "/WEB-INF/jsp/categories.jsp";
    CategoryService categoryService = new CategoryServiceImpl();

    if (operation.getOperationType() == OperationType.ADD_ITEM) {
      if (categoryName.equals("")) {
        page = "/WEB-INF/jsp/categoryItemAdd.jsp";
        req.setAttribute("ErrorMessage", "Field 'name' must be filled");
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);
      } else {
        categoryService.addNewCategory(categoryName);
        List<Category> categories = categoryService.getAll();
        req.setAttribute("categories", categories);
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);

      }
    } else if (operation.getOperationType() == OperationType.DELETE_ITEM) {
      categoryService.deleteItem(operation.getObjectId());
      page = "/category/";
      resp.sendRedirect(page);
    } else if (operation.getOperationType() == OperationType.EDIT_ITEM) {
      categoryService.editCategory(new Category(operation.getObjectId(), categoryName));
      page = "/category/";
      resp.sendRedirect(page);
    }

  }
}




