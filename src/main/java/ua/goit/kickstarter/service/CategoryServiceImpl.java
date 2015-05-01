package ua.goit.kickstarter.service;

import ua.goit.kickstarter.dao.CategoryDao;
import ua.goit.kickstarter.dao.DaoFactory;
import ua.goit.kickstarter.factory.ConnectionFactory;
import ua.goit.kickstarter.model.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

  private final DaoFactory daoFactory = ConnectionFactory.getDaoFactory();
  private final CategoryDao categoryDao = daoFactory.getCategoryDao();

  @Override
  public List<Category> getAll() {
    return categoryDao.getAll();
  }

  @Override
  public Category getById(Integer id) {
    return categoryDao.getById(id);
  }

  @Override
  public Category addNewCategory(String name) {
    return categoryDao.add(name);
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
