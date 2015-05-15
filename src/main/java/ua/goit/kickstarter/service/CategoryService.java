package ua.goit.kickstarter.service;

import ua.goit.kickstarter.model.Category;

import java.util.List;

public interface CategoryService extends GenericService<Category> {
  List<Category> getAll();
}
