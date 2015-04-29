package ua.goit.kickstarter.dao;


import ua.goit.kickstarter.data.ApplicationData;
import ua.goit.kickstarter.model.Category;

import java.util.List;


public class CategoryDaoFakeImpl extends AbstractDaoImpl<Category> implements CategoryDao {
  @Override
  public Category getById(Integer id) {
    ApplicationData appData = ApplicationData.getInstance();
    return appData.getCategoryById(id);
  }

  @Override
  public List<Category> getAll() {
    ApplicationData appData = ApplicationData.getInstance();
    return appData.getAllCategories();
  }

  @Override
  public Category add(Category element) {
    return null;
  }

  @Override
  public void deleteById(Integer id) {

  }

  @Override
  public Category update(Category element) {
    return null;
  }

  @Override
  public Category add(String newCategory) {
    return null;
  }

  @Override
  public Category getById(String categoryId) {
    int id;
    try {
      id = Integer.parseInt(categoryId);
    }catch(NumberFormatException e){
      return null;
    }

    return  getById(id);
  }

}
