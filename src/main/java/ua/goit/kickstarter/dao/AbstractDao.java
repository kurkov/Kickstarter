package ua.goit.kickstarter.dao;

import java.sql.*;

public abstract class AbstractDao<T> implements GenericDao<T> {
  protected final Connection connection;
  protected PreparedStatement statement = null;

  protected AbstractDao(Connection connection) {
    this.connection = connection;
  }

  public ResultSet executeQuery(String query){
    ResultSet rs;
    try {
      statement = connection.prepareStatement(query);
      rs = statement.executeQuery(query);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return rs;
  }

  public int executeUpdate(String query){
    int rs;
    try {
      statement = connection.prepareStatement(query);
      rs = statement.executeUpdate(query);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return rs;
  }
}
