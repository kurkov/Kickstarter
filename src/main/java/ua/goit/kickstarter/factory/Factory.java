package ua.goit.kickstarter.factory;

import ua.goit.kickstarter.controller.Controller;
import ua.goit.kickstarter.dao.CategoryDao;
import ua.goit.kickstarter.dao.CategoryDaoImpl;
import ua.goit.kickstarter.service.CategoryService;
import ua.goit.kickstarter.service.CategoryServiceImpl;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Factory {

  public static Controller createCategoryController(Class<? extends
          Controller> clazz, Connection connection) {

    Controller controller = null;

    try {
      Constructor<? extends Controller> constructor =
              clazz.getConstructor(CategoryService.class);
      CategoryService service = getCategoryService(getCategoryDao(connection));
      controller = constructor.newInstance(service);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }

    return controller;
  }

  protected static CategoryService getCategoryService(CategoryDao dao) {
    return new CategoryServiceImpl(dao);
  }

  protected static CategoryDao getCategoryDao(Connection connection) {
    return new CategoryDaoImpl(getConnection());
  }

  public static Connection getConnection() {

    Connection connection = null;
    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:kickstarter.db");
    } catch (SQLException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    return connection;
  }
}
