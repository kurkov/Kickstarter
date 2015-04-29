package ua.goit.kickstarter.dao;


import ua.goit.kickstarter.factory.Factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDaoImpl <T> implements AbstractDao <T>{

  public void deleteById(String strId){
    int id = Integer.parseInt(strId);
    deleteById(id);

  };

  public T getById(String strId){
    int id = Integer.parseInt(strId);
    return getById(id);
  };

  public ResultSet executeQuery(String query){

    Connection connection = Factory.getConnection();
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

    Connection connection = Factory.getConnection();
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
