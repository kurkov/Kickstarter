package ua.goit.kickstarter.dao;

import ua.goit.kickstarter.model.Category;

public interface CategoryDao extends AbstractDao<Category> {

  Category add(String name);
}
