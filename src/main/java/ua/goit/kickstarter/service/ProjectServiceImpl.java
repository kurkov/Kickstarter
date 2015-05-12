package ua.goit.kickstarter.service;

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
  public Project getById(Integer id) {
    return projectDao.getById(id);
  }

  @Override
  public Project addNewProject(Project project) {
    return projectDao.add(project);
  }

  @Override
  public void editProject(Project project) {
    if (project != null) {
      projectDao.update(project);
    }
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
