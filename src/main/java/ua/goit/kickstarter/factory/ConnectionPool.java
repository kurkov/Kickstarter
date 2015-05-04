package ua.goit.kickstarter.factory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

  private static List<Connection> connections = new ArrayList<>();

  public ConnectionPool(int size) {
  }
  static {
    connections.add(Factory.getConnection());
  }


  public static Connection getConnection() {
    return connections.get(0);
  }

  public static void release(Connection con) {

  }

}
