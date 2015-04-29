package ua.goit.kickstarter.data;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import ua.goit.kickstarter.dao.CategoryDao;
import ua.goit.kickstarter.dao.CategoryDaoFakeImpl;
import ua.goit.kickstarter.model.Category;

import java.util.List;

public class ProjectCategoriesDaoTest {

  @Test
  public void testAllCategories(){
    CategoryDao categoryDao = new CategoryDaoFakeImpl();
    List<Category> categoryAll = categoryDao.getAll();
    final Logger logger = LogManager.getLogger(AppDataTest.class);

    for (Category category : categoryAll) {
      logger.info(category);
    }
  }
}
