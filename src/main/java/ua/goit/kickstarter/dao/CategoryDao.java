package ua.goit.kickstarter.dao;

import ua.goit.kickstarter.model.Category;

public interface CategoryDao extends GenericDao<Category> {

  Category add(String name);
}
