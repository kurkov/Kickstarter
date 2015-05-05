package ua.goit.kickstarter.factory;

import ua.goit.kickstarter.controller.Controller;
import ua.goit.kickstarter.dao.*;
import ua.goit.kickstarter.service.*;

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

  public static Controller createProjectController(Class<? extends
          Controller> clazz, Connection connection) {
    Controller controller = null;
    try {
      Constructor<? extends Controller> constructor =
              clazz.getConstructor(ProjectService.class);
      ProjectService service = getProjectService(getProjectDao(connection));
      controller = constructor.newInstance(service);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static Controller createCommentController(Class<? extends
          Controller> clazz, Connection connection) {
    Controller controller = null;
    try {
      Constructor<? extends Controller> constructor =
              clazz.getConstructor(CommentService.class);
      CommentService service = getCommentService(getCommentDao(connection));
      controller = constructor.newInstance(service);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static Controller createBlogPostController(Class<? extends
          Controller> clazz, Connection connection) {
    Controller controller = null;
    try {
      Constructor<? extends Controller> constructor =
              clazz.getConstructor(BlogPostService.class);
      BlogPostService service = getBlogPostService(getBlogPostDao(connection));
      controller = constructor.newInstance(service);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return controller;
  }

  public static CategoryDao getCategoryDao(Connection connection) {
    return new CategoryDaoImpl(getConnection());
  }

  public static ProjectDao getProjectDao(Connection connection) {
    return new ProjectDaoImpl(getConnection());
  }

  public static CommentDao getCommentDao(Connection connection) {
    return new CommentDaoImpl(getConnection());
  }

  public static BlogPostDao getBlogPostDao(Connection connection) {
    return new BlogPostDaoImpl(getConnection());
  }

  public static UserDao getUserDao(Connection connection) {
    return new UserDaoImpl(getConnection());
  }

  public static CategoryService getCategoryService(CategoryDao dao) {
    return new CategoryServiceImpl(dao);
  }

  public static ProjectService getProjectService(ProjectDao dao) {
    return new ProjectServiceImpl(dao);
  }

  public static CommentService getCommentService(CommentDao dao) {
    return new CommentServiceImpl(dao);
  }

  public static BlogPostService getBlogPostService(BlogPostDao dao) {
    return new BlogPostServiceImpl(dao);
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
