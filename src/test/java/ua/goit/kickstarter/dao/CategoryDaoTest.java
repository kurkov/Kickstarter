package ua.goit.kickstarter.dao;


import org.apache.log4j.Logger;
import org.junit.Test;
import ua.goit.kickstarter.factory.ConnectionFactory;
import ua.goit.kickstarter.model.Category;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class CategoryDaoTest {

  private Integer getExistingCategoryId() {

    Integer res = null;
    DaoFactory daoFactory = new DaoFactoryImpl();
    CategoryDao categoryDao = daoFactory.getCategoryDao();
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
  public void add_New_Category() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    DaoFactory daoFactory = new DaoFactoryImpl();
    CategoryDao categoryDao = daoFactory.getCategoryDao();

    Category actual = categoryDao.add("New category2");
    assertNotNull(actual);
    ConnectionFactory.closeConnection(connection);
  }


  @Test
  public void get_By_id() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    DaoFactory daoFactory = new DaoFactoryImpl();
    CategoryDao categoryDao = daoFactory.getCategoryDao();

    List<Category> categoryList = categoryDao.getAll();
    Integer id = getExistingCategoryId();


    for (Category category : categoryList) {
      id = category.getId();
    }
    Category actual = categoryDao.getById(id);

    assertNotNull(actual);
    ConnectionFactory.closeConnection(connection);
  }

  @Test
  public void delete_By_id() throws SQLException {


    DaoFactory daoFactory = new DaoFactoryImpl();
    CategoryDao categoryDao = daoFactory.getCategoryDao();
    Connection connection = ConnectionFactory.getConnection();

    connection.setAutoCommit(false);

    Category newElement = categoryDao.add("New category2");
    int idNewElemet = newElement.getId();
    Category newElementFromDB = categoryDao.getById(idNewElemet);
    assertNotNull(newElementFromDB);
    categoryDao.deleteById(idNewElemet);
    newElementFromDB = categoryDao.getById(idNewElemet);
    assertNull(newElementFromDB);

    connection.rollback();
    ConnectionFactory.closeConnection(connection);
  }

  @Test
  public void update_By_id() throws SQLException {

    DaoFactory daoFactory = new DaoFactoryImpl();
    CategoryDao categoryDao = daoFactory.getCategoryDao();

    Connection connection = ConnectionFactory.getConnection();

    connection.setAutoCommit(false);

    int id = getExistingCategoryId();

    Category element = categoryDao.getById(id);
    element.setName("updated element");
    categoryDao.update(element);
    Category elementAfterUpdate = categoryDao.getById(id);
    assertEquals("updated element", elementAfterUpdate.getName());
    connection.rollback();

    ConnectionFactory.closeConnection(connection);
  }


  @Test
  public void getAll_test() throws SQLException {

    DaoFactory daoFactory = new DaoFactoryImpl();
    CategoryDao categoryDao = daoFactory.getCategoryDao();

    Connection connection = ConnectionFactory.getConnection();
    connection.setAutoCommit(false);

    Logger logger = Logger.getLogger(this.getClass());
    List<Category> categoryList = categoryDao.getAll();
    logger.info(categoryList);
    assertNotNull(categoryList);
    ConnectionFactory.closeConnection(connection);
  }
}
