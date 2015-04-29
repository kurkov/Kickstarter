package ua.goit.kickstarter.dao;

import ua.goit.kickstarter.data.ApplicationData;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;

import java.util.List;

public class ProjectDaoFakeImpl implements ProjectDao {
  @Override
  public Project getById(Integer id) {
    ApplicationData appData = ApplicationData.getInstance();
    return appData.getProjectById(id);
  }

  @Override
  public Project getById(String strId) {
    return null;
  }

  @Override
  public List<Project> getByCategoryId(Integer categoryId) {
    ApplicationData appData = ApplicationData.getInstance();
    return appData.getProjectsByCategoryId(categoryId);
  }

  @Override
  public List<Project> getByCategory(Category category) {
    ApplicationData appData = ApplicationData.getInstance();
    return appData.getProjectsByCategory(category);
  }

  @Override
  public List<Project> getAll() {
    ApplicationData appData = ApplicationData.getInstance();
    return appData.getAllProjects();
  }

  @Override
  public Project add(String name, String category, String
      description) {
    return null;
  }

  @Override
  public Project update(Project project) {
    ApplicationData appData = ApplicationData.getInstance();
    return appData.updateProject(project);

  }


  @Override
  public void delete(Project project) {

    ApplicationData appData = ApplicationData.getInstance();
    appData.deleteProject(project);
  }


  @Override
  public Project add(Project newProject) {
    ApplicationData appData = ApplicationData.getInstance();
    return appData.addProject(newProject);

  }

  @Override
  public void deleteById(Integer id) {

  }

  @Override
  public void deleteById(String strId) {

  }
}
