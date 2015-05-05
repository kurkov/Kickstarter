package ua.goit.kickstarter.dao;

import org.apache.log4j.Logger;
import org.junit.Test;
import ua.goit.kickstarter.factory.ConnectionPool;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.model.Category;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class CategoryDaoTest {
  private Integer getExistingCategoryId(Connection connection) {
    Integer res = null;
    CategoryDao categoryDao = Factory.getCategoryDao(connection);
    List<Category> categoryList = categoryDao.getAll();
    for (Category category : categoryList) {
      res = category.getId();
    }
    if (res == null) {
      fail("No 'Category' record available to test!");
    }

    return res;
  }

  @Test
  public void addNewCategory() throws SQLException {
    Connection connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);

    CategoryDao categoryDao = Factory.getCategoryDao(connection);
    Category actual = categoryDao.add("New category2");

    assertNotNull(actual);

    connection.rollback();
    connection.close();
  }


  @Test
  public void getById() throws SQLException {
    Connection connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);

    CategoryDao categoryDao = Factory.getCategoryDao(connection);
    List<Category> categoryList = categoryDao.getAll();
    Integer id = getExistingCategoryId(connection);
    for (Category category : categoryList) {
      id = category.getId();
    }
    Category actual = categoryDao.getById(id);

    assertNotNull(actual);

    connection.rollback();
    connection.close();
  }

  @Test
  public void deleteById() throws SQLException {
    Connection connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);

    CategoryDao categoryDao = Factory.getCategoryDao(connection);
    Category newElement = categoryDao.add("New category2");
    int idNewElement = newElement.getId();
    Category newElementFromDB = categoryDao.getById(idNewElement);

    assertNotNull(newElementFromDB);

    categoryDao.deleteById(idNewElement);
    newElementFromDB = categoryDao.getById(idNewElement);

    assertNull(newElementFromDB);

    connection.rollback();
    connection.close();
  }

  @Test
  public void updateById() throws SQLException {
    Connection connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);

    CategoryDao categoryDao = Factory.getCategoryDao(connection);
    int id = getExistingCategoryId(connection);
    Category element = categoryDao.getById(id);
    element.setName("updated element");
    categoryDao.update(element);
    Category elementAfterUpdate = categoryDao.getById(id);

    assertEquals("updated element", elementAfterUpdate.getName());

    connection.rollback();
    connection.close();
  }

  @Test
  public void getAll() throws SQLException {
    Connection connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);

    CategoryDao categoryDao = Factory.getCategoryDao(connection);

    Logger logger = Logger.getLogger(this.getClass());

    List<Category> categoryList = categoryDao.getAll();

    logger.info(categoryList);

    assertNotNull(categoryList);

    connection.rollback();
    connection.close();
  }
}
