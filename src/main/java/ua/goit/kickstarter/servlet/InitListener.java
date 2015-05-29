package ua.goit.kickstarter.servlet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class InitListener implements ServletContextListener {
  static DataSource dataSource;

  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {
    InitialContext cxt;
    try {
      cxt = new InitialContext();
      dataSource = (DataSource) cxt.lookup("java:/comp/env/jdbc/kickstarter");
    } catch (NamingException e) {
      e.printStackTrace();
    }
  }

  public static Connection getConnection() {
    Connection connection = null;
    try {
      if (dataSource != null) {
        connection = dataSource.getConnection();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent) {
  }
}
