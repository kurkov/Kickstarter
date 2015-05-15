package ua.goit.kickstarter.dao;

import ua.goit.kickstarter.model.Category;

import java.util.List;

public interface CategoryDao extends GenericDao<Category> {
  List<Category> getAll();
}
