package ua.goit.kickstarter.service;


import ua.goit.kickstarter.dao.CategoryDao;
import ua.goit.kickstarter.dao.DaoFactory;
import ua.goit.kickstarter.dao.ProjectDao;
import ua.goit.kickstarter.factory.ConnectionFactory;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;

import java.util.List;


public class ProjectServiceImpl implements ProjectService {

  private final DaoFactory daoFactory = ConnectionFactory.getDaoFactory();
  private final ProjectDao projectDao = daoFactory.getProjectDao();
  private final CategoryDao categoryDao = daoFactory.getCategoryDao();

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
                               String categoryId) {
    Project newProject = new Project();
    newProject.setName(name);
    newProject.setDescription(description);
    Category category = categoryDao.getById(categoryId);
    newProject.setCategory(category);

    return projectDao.add(newProject);
  }

  @Override
  public void editProject(String id, String projectName, String projectDescription) {
    int idProject;
    try {
      idProject = Integer.parseInt(id);
    } catch (NumberFormatException e) {
      /*NE*/
      return;
    }

    Project project = projectDao.getById(idProject);
    if (project == null) return;

    project.setName(projectName);
    project.setDescription(projectDescription);
    projectDao.update(project);
  }

  @Override
  public void deleteProject(String projectId) {
    try {
      projectDao.deleteById(projectId);
    } catch (NumberFormatException e) {
      //NE
      return;
    }
  }
}
