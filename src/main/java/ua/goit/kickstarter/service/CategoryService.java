package ua.goit.kickstarter.service;

import ua.goit.kickstarter.model.Category;

import java.util.List;

public interface CategoryService {
  List<Category> getAll();

  Category getById(Integer id);

  Category addNewCategory(Category category);

  void deleteItem(Integer objectId);

  Category editCategory(Category category);
}
