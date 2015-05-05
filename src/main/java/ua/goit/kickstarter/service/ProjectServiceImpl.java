package ua.goit.kickstarter.service;

import ua.goit.kickstarter.dao.CategoryDao;
import ua.goit.kickstarter.dao.ProjectDao;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {
  private final ProjectDao projectDao;

  public ProjectServiceImpl(ProjectDao projectDao) {
    this.projectDao = projectDao;
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
    return projectDao.add(name, description, categoryId);
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
