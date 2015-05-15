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
  public List<Project> getByCategory(Category category) {
    return projectDao.getByCategory(category);
  }

  @Override
  public Project getById(Integer id) {
    return projectDao.getById(id);
  }

  @Override
  public Project add(Project project) {
    return projectDao.add(project);
  }

  @Override
  public Project update(Project project) {
    return projectDao.update(project);
  }

  @Override
  public void delete(Project project) {
    projectDao.delete(project);
  }
}
