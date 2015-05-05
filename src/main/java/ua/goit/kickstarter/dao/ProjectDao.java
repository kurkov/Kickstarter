package ua.goit.kickstarter.dao;

import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;

import java.util.List;


public interface ProjectDao extends GenericDao<Project> {

  List<Project> getByCategoryId(Integer categoryId);

  List<Project> getByCategory(Category category);

  Project add(String name, Integer category, String description);

  Project update(Project project);

  void delete(Project project);

  Project add(Project newProject);

}
