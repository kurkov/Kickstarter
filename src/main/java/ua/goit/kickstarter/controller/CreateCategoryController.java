package ua.goit.kickstarter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.service.CategoryService;

import java.util.List;

@Controller
public class CreateCategoryController {

  private final CategoryService categoryService;

  @Autowired
  public CreateCategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/category/add")
  public ModelAndView addCategory() {
    return new ModelAndView("categoryItemAdd");
  }

  @RequestMapping(method = RequestMethod.POST, value = "/category/add")
  public ModelAndView addCategory(@RequestParam("categoryName") String categoryName ) {
    ModelAndView mv;
    if (categoryName.equals("")) {
      mv = getErrorMessage();
    } else {
      categoryService.add(new Category(categoryName));
      mv = getAllCategories();
    }
    return  mv;
  }

  private ModelAndView getErrorMessage() {
    ModelAndView mv = new ModelAndView("categoryItemAdd");
    mv.addObject("ErrorMessage", "Field 'name' must be filled");
    return mv;
  }

  private ModelAndView getAllCategories() {
    ModelAndView mv = new ModelAndView("categories");
    List<Category> categories = categoryService.getAll();
    mv.addObject("categories", categories);
    return mv;
  }
}
