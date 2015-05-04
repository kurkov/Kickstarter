package ua.goit.kickstarter.dao;


import ua.goit.kickstarter.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDao<T> implements GenericDao<T> {

  protected final Connection connection;

  protected AbstractDao(Connection connection) {
    this.connection = connection;
  }

  public void deleteById(String strId){
    int id = Integer.parseInt(strId);
    deleteById(id);
  }

  public T getById(String strId){
    int id = Integer.parseInt(strId);
    return getById(id);
  }

  public ResultSet executeQuery(String query){
    Connection connection = ConnectionFactory.getConnection();
    ResultSet rs;
    try {
      Statement statement = connection.createStatement();
      rs = statement.executeQuery(query);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return rs;
  }

  public int executeUpdate(String query){
    Connection connection = ConnectionFactory.getConnection();
    int rs;
    try {
      Statement statement = connection.createStatement();
      rs = statement.executeUpdate(query);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return rs;
  }
}
