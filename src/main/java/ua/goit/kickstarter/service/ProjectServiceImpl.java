package ua.goit.kickstarter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.kickstarter.dao.ProjectDao;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;

import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
  private final ProjectDao projectDao;

  @Autowired
  public ProjectServiceImpl(ProjectDao projectDao) {
    this.projectDao = projectDao;
  }

  @Override
  //@Secured({"ANONYMOUS", "USER", "ADMIN"})
  public List<Project> getByCategory(Category category) {
    return projectDao.getByCategory(category);
  }

  @Override
  //@Secured({"ANONYMOUS", "USER", "ADMIN"})
  public Project getById(Integer id) {
    return projectDao.getById(id);
  }

  @Override
  //@Secured({"USER", "ADMIN"})
  public void add(Project project) {
    projectDao.add(project);
  }

  @Override
  //@Secured({"USER", "ADMIN"})
  public void update(Project project) {
    projectDao.update(project);
  }

  @Override
  //@Secured("ADMIN")
  public void delete(Project project) {
    projectDao.delete(project);
  }
}
