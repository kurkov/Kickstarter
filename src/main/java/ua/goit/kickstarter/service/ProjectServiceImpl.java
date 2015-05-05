package ua.goit.kickstarter.service;

import ua.goit.kickstarter.dao.CategoryDao;
import ua.goit.kickstarter.dao.ProjectDao;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {
  private final ProjectDao projectDao;
  private final CategoryDao categoryDao;

  public ProjectServiceImpl(ProjectDao projectDao, CategoryDao categoryDao) {
    this.projectDao = projectDao;
    this.categoryDao = categoryDao;
  }

  @Override
  public List<Project> getAll() {
    return projectDao.getAll();
  }

  @Override
  public List<Project> getByCategory(Category category) {
    return projectDao.getByCategory(category);
  }

  @Override
  public Project getProjectById(Integer id) {
    return projectDao.getById(id);
  }

  @Override
  public Project addNewProject(String name, String description,
                               Integer categoryId) {
    Project newProject = new Project();
    newProject.setName(name);
    newProject.setDescription(description);
    Category category = categoryDao.getById(categoryId);
    newProject.setCategory(category);

    return projectDao.add(newProject);
  }

  @Override
  public void editProject(Integer id, String projectName,
                          String projectDescription) {
    Project project = projectDao.getById(id);
    if (project == null) return;

    project.setName(projectName);
    project.setDescription(projectDescription);
    projectDao.update(project);
  }

  @Override
  public void deleteProject(Integer projectId) {
    try {
      projectDao.deleteById(projectId);
    } catch (NumberFormatException e) {
      //NE
      return;
    }
  }
}
