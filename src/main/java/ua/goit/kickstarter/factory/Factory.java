package ua.goit.kickstarter.factory;

import ua.goit.kickstarter.controller.Controller;
import ua.goit.kickstarter.dao.*;
import ua.goit.kickstarter.service.*;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Factory {

  public static Controller createReadCategoryController(Class<? extends Controller> clazz, Connection connection) {
    Controller controller;
    try {
      Constructor<? extends Controller> constructor = clazz.getConstructor(CategoryService.class, ProjectService.class);
      CategoryService categoryService = getCategoryService(getCategoryDao(connection));
      ProjectService projectService = getProjectService(getProjectDao(connection));
      controller = constructor.newInstance(categoryService, projectService);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static Controller createCategoryController(Class<? extends Controller> clazz, Connection connection) {
    Controller controller;
    try {
      Constructor<? extends Controller> constructor = clazz.getConstructor(CategoryService.class);
      CategoryService categoryService = getCategoryService(getCategoryDao(connection));
      controller = constructor.newInstance(categoryService);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static Controller createCreateProjectController(Class<? extends Controller> clazz, Connection connection) {
    Controller controller;
    try {
      Constructor<? extends Controller> constructor = clazz.getConstructor(CategoryService.class, ProjectService.class);
      ProjectService projectService = getProjectService(getProjectDao(connection));
      CategoryService categoryService = getCategoryService(getCategoryDao(connection));
      controller = constructor.newInstance(categoryService, projectService);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static Controller createReadProjectController(Class<? extends Controller> clazz, Connection connection) {
    Controller controller;
    try {
      Constructor<? extends Controller> constructor = clazz.getConstructor(ProjectService.class);
      ProjectService projectService = getProjectService(getProjectDao(connection));
      controller = constructor.newInstance(projectService);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static Controller createProjectController(Class<? extends Controller> clazz, Connection connection) {
    Controller controller;
    try {
      Constructor<? extends Controller> constructor = clazz.getConstructor(CategoryService.class, ProjectService.class);
      CategoryService categoryService = getCategoryService(getCategoryDao(connection));
      ProjectService projectService = getProjectService(getProjectDao(connection));
      controller = constructor.newInstance(categoryService, projectService);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static Controller createReadAllProjectsController(Class<? extends Controller> clazz, Connection connection) {
    Controller controller;
    try {
      Constructor<? extends Controller> constructor = clazz.getConstructor(ProjectService.class);
      ProjectService projectService = getProjectService(getProjectDao(connection));
      controller = constructor.newInstance(projectService);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static Controller createErrorController(Class<? extends
      Controller> clazz, Connection connection) {
    Controller controller;
    try {
      Constructor<? extends Controller> constructor =
          clazz.getConstructor();
      controller = constructor.newInstance();
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static CategoryDao getCategoryDao(Connection connection) {
    return new CategoryDaoImpl(connection);
  }

  public static ProjectDao getProjectDao(Connection connection) {
    return new ProjectDaoImpl(connection);
  }

  public static UserDao getUserDao(Connection connection) {
    return new UserDaoImpl(connection);
  }

  public static CategoryService getCategoryService(CategoryDao dao) {
    return new CategoryServiceImpl(dao);
  }

  public static ProjectService getProjectService(ProjectDao dao) {
    return new ProjectServiceImpl(dao);
  }

  public static Connection getConnection() {
    Connection connection;
    ProjectProperties prop = new ProjectProperties();
    String database = prop.getDatabase();

    try {
      connection = DriverManager.getConnection(database);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return connection;
  }
}
