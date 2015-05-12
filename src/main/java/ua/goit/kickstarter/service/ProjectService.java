package ua.goit.kickstarter.service;

import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;

import java.util.List;

public interface ProjectService {
  List<Project> getAll();

  List<Project> getByCategory(Category category);

  Project getById(Integer id);

  Project addNewProject(Project project);

  void editProject(Project project);

  void deleteProject(Integer projectId);
}
