package ua.goit.kickstarter.service;

import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;

import java.util.List;

public interface ProjectService extends GenericService<Project>{
  List<Project> getByCategory(Category category);
}
