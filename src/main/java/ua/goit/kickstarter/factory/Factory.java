package ua.goit.kickstarter.factory;

import ua.goit.kickstarter.controller.Controller;
import ua.goit.kickstarter.dao.*;
import ua.goit.kickstarter.service.CategoryService;
import ua.goit.kickstarter.service.CategoryServiceImpl;
import ua.goit.kickstarter.service.ProjectService;
import ua.goit.kickstarter.service.ProjectServiceImpl;

import java.lang.reflect.Constructor;
import java.sql.Connection;

public class Factory {

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

}
