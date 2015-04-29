package ua.goit.kickstarter.factory;


import ua.goit.kickstarter.dao.DaoFactory;
import ua.goit.kickstarter.dao.DaoFactoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

  private static ThreadLocal<Connection> connection = new ThreadLocal<Connection>();


  public static DaoFactory getDaoFactory() {
    return new DaoFactoryImpl();
  }


  public static Connection createConnection() {
    Connection connection = null;
    ProjectProperties prop = new ProjectProperties();
    String database = prop.getDatabase();

    try {

      connection = DriverManager.getConnection(database);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return connection;
  }


  public static Connection getConnection() {

    Connection threadConnection = connection.get();
    try {

      if (threadConnection == null || threadConnection.isClosed()) {
        threadConnection = createConnection();
        connection.set(threadConnection);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return threadConnection;
  }


  public static void closeConnection(Connection connection) {
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
