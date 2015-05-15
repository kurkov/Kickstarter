package ua.goit.kickstarter.service;

import ua.goit.kickstarter.dao.CategoryDao;
import ua.goit.kickstarter.model.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

  private final CategoryDao categoryDao;

  public CategoryServiceImpl(CategoryDao categoryDao) {
    this.categoryDao = categoryDao;
  }

  @Override
  public List<Category> getAll() {
    return categoryDao.getAll();
  }

  @Override
  public Category getById(Integer id) {
    return categoryDao.getById(id);
  }

  @Override
  public Category add(Category category) {
    return categoryDao.add(category);
  }

  @Override
  public void delete(Category category) {
    categoryDao.delete(category);
  }

  @Override
  public Category update(Category category) {
    return categoryDao.update(category);
  }
}
