package ua.goit.kickstarter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.kickstarter.dao.CategoryDao;
import ua.goit.kickstarter.model.Category;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

  private final CategoryDao categoryDao;

  @Autowired
  public CategoryServiceImpl(CategoryDao categoryDao) {
    this.categoryDao = categoryDao;
  }

  @Override
  @Secured("ROLE_ANONYMOUS, ROLE_USER, ROLE_ADMIN")
  public List<Category> getAll() {
    return categoryDao.getAll();
  }

  @Override
  @Secured("ROLE_ANONYMOUS, ROLE_USER, ROLE_ADMIN")
  public Category getById(Integer id) {
    return categoryDao.getById(id);
  }

  @Override
  @Secured("ROLE_ADMIN")
  public void add(Category category) {
    categoryDao.add(category);
  }

  @Override
  @Secured("ROLE_ADMIN")
  public void delete(Category category) {
    categoryDao.delete(category);
  }

  @Override
  @Secured("ROLE_ADMIN")
  public void update(Category category) {
    categoryDao.update(category);
  }
}
