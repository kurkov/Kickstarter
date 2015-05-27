package ua.goit.kickstarter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.service.CategoryService;
import ua.goit.kickstarter.service.ProjectService;

import java.util.List;

@Controller
public class ProjectController {

  private final CategoryService categoryService;
  private final ProjectService projectService;

  @Autowired
  public ProjectController(CategoryService categoryService, ProjectService projectService) {
    this.categoryService = categoryService;
    this.projectService = projectService;
  }

  @RequestMapping(method = RequestMethod.GET    )
  private ModelAndView getProjectAddFromCategory(Category category) {
    ModelAndView modelAndView = new ViewModel("/WEB-INF/jsp/projectItemAdd.jsp");
    List<Category> categories = categoryService.getAll();
    modelAndView.addAttributes("categories", categories);
    modelAndView.addAttributes("category", category);
    return viewModel;
  }


}
