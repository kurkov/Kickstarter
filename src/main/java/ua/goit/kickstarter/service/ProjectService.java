package ua.goit.kickstarter.service;

import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;

import java.util.List;

public interface ProjectService {
  List<Project> getAll();

  List<Project> getByCategory(Category category);

  Project getProjectById(Integer id);

  Project addNewProject(String name, String description, Integer categoryId);

  void editProject(Integer id,String projectName, String projectDescription);


  void deleteProject(Integer projectId);
}
