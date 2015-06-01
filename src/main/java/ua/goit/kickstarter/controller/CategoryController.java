package ua.goit.kickstarter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.service.CategoryService;
import ua.goit.kickstarter.service.ProjectService;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
  private final CategoryService categoryService;
  private final ProjectService projectService;

  @Autowired
  public CategoryController(CategoryService categoryService, ProjectService projectService) {
    this.categoryService = categoryService;
    this.projectService = projectService;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/add")
  public ModelAndView addCategory() {
    return new ModelAndView("categoryItemAdd");
  }

  @RequestMapping(method = RequestMethod.POST, value = "/add")
  public ModelAndView addCategory(@RequestParam("categoryName") String categoryName ) {
    ModelAndView mv;
    if (categoryName.equals("")) {
      mv = new ModelAndView("categoryItemAdd");
      mv.addObject("ErrorMessage", "Field 'name' must be filled");
    } else {
      categoryService.add(new Category(categoryName));
      mv = getAllCategories();
    }
    return  mv;
  }

  private ModelAndView getAllCategories() {
    ModelAndView mv = new ModelAndView("categories");
    List<Category> categories = categoryService.getAll();
    mv.addObject("categories", categories);
    return mv;
  }

  @RequestMapping("/{id}/delete")
  public ModelAndView deleteCategory(@PathVariable("id") int categoryId) {
    Category category = categoryService.getById(categoryId);
    categoryService.delete(category);
    return getAllCategories();
  }

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView readAllCategories() {
    return getAllCategories();
  }

  @RequestMapping("/{id}")
  public ModelAndView readCategory(@PathVariable("id") int categoryId) {
    Category category = categoryService.getById(categoryId);
    ModelAndView mv = new ModelAndView("categoryItem");
    List<Project> projects = null;
    if (category != null) {
      projects = projectService.getByCategory(category);
    }
    mv.addObject("projects", projects);
    mv.addObject("category", category);
    return mv;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}/edit")
  public ModelAndView updateCategory(@PathVariable("id") int categoryId) {
    ModelAndView mv = new ModelAndView("categoryItemEdit");
    Category category = categoryService.getById(categoryId);
    mv.addObject("category", category);
    return mv;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/{id}/edit")
  public ModelAndView updateCategory(@PathVariable("id") int categoryId,
                                     @RequestParam("categoryName") String categoryName) {
    Category category = categoryService.getById(categoryId);
    ModelAndView mv;
    if (categoryName.equals("")) {
      mv = new ModelAndView("categoryItemEdit");
      mv.addObject("ErrorMessage", "Field 'name' must be filled");
      mv.addObject("category", category);
    } else {
      category.setName(categoryName);
      categoryService.update(category);
      mv = getAllCategories();
    }
    return mv;
  }

  @ExceptionHandler(Exception.class)
  public ModelAndView exceptionHandler(Exception ex) {
    return new ModelAndView("error", "ErrorMessage", ex.getMessage());
  }
}
