package ua.goit.kickstarter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.service.CategoryService;
import ua.goit.kickstarter.service.ProjectService;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
  private final CategoryService categoryService;
  private final ProjectService projectService;

  @Autowired
  public ProjectController(CategoryService categoryService, ProjectService projectService) {
    this.categoryService = categoryService;
    this.projectService = projectService;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/add")
  public ModelAndView addProject(@RequestParam("categoryId") int categoryId) {
    Category category = categoryService.getById(categoryId);
    ModelAndView mv = new ModelAndView("projectItemAdd");
    List<Category> categories = categoryService.getAll();
    mv.addObject("categories", categories);
    mv.addObject("category", category);
    return mv;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/add")
  public ModelAndView addProject(@RequestParam("projectName") String projectName,
                                 @RequestParam("projectDescription") String projectDescription,
                                 @RequestParam("categoryId") int categoryId) {
    ModelAndView mv = new ModelAndView("projectItemAdd");
    Category category = categoryService.getById(categoryId);
    List<Category> categories = categoryService.getAll();

    if (projectName.equals("")) {
      mv.addObject("ErrorMessage", "Field 'name' must be filled");
      mv.addObject("category", category);
      mv.addObject("categories", categories);
    } else if (projectDescription.equals("")) {
      mv.addObject("ErrorMessage", "Field 'description' must be filled");
      mv.addObject("category", category);
      mv.addObject("categories", categories);
    } else {
      Project newProject = new Project(projectName, projectDescription, category);
      Project project = projectService.add(newProject);
      mv = getProject(project);
    }
    return mv;
  }

  private ModelAndView getProject(Project project) {
    ModelAndView mv = new ModelAndView("projectItem");
    mv.addObject("project", project);
    return mv;
  }

  @RequestMapping("/{id}/delete")
  public ModelAndView deleteProject(@PathVariable("id") int projectId,
                                    @RequestParam("categoryId") int categoryId) {
    Project project = projectService.getById(projectId);
    projectService.delete(project);
    ModelAndView mv = new ModelAndView("categoryItem");
    Category category = categoryService.getById(categoryId);
    List<Project> projects = null;

    if (category != null) {
      projects = projectService.getByCategory(category);
    }

    mv.addObject("projects", projects);
    mv.addObject("category", category);
    return mv;
  }

  @RequestMapping("/{id}")
  public ModelAndView readProject(@PathVariable("id") int projectId) {
    Project project = projectService.getById(projectId);
    return getProject(project);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}/edit")
  public ModelAndView updateProject(@PathVariable("id") int projectId) {
    ModelAndView mv = new ModelAndView("projectItemEdit");
    Project project = projectService.getById(projectId);
    mv.addObject("categoryId", project.getCategory().getId());
    mv.addObject("project", project);
    return mv;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/{id}/edit")
  public ModelAndView updateProject(@PathVariable("id") int projectId,
                                    @RequestParam("id") int categoryId,
                                    @RequestParam("projectName") String projectName,
                                    @RequestParam("projectDescription") String projectDescription) {
    Category category = categoryService.getById(categoryId);
    Project project = projectService.getById(projectId);
    project.setName(projectName);
    project.setDescription(projectDescription);
    project.setCategory(category);
    ModelAndView mv = new ModelAndView("projectItemEdit");

    if (projectName.equals("")) {
      mv.addObject("ErrorMessage", "Field 'name' must be filled");
    } else if (projectDescription.equals("")) {
      mv.addObject("ErrorMessage", "Field 'description' must be filled");
      mv.addObject("project", project);
    } else {
      projectService.update(project);
      mv = new ModelAndView("categoryItem");
      List<Project> projects = null;
      if (category != null) {
        projects = projectService.getByCategory(category);
      }
      mv.addObject("projects", projects);
      mv.addObject("category", category);
    }
    return mv;
  }

}
