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
  public Category addNewCategory(Category categoryName) {
    return categoryDao.add(categoryName);
  }

  @Override
  public void deleteItem(Integer objectId) {
    categoryDao.deleteById(objectId);
  }

  @Override
  public Category editCategory(Category category) {
    return categoryDao.update(category);
  }
}
