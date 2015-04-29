package ua.goit.kickstarter;

import org.junit.Ignore;
import ua.goit.kickstarter.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.Statement;

public class SQLiteTest {

  //@Test
  @Ignore
  public void createTables() {
    Connection con = ConnectionFactory.getConnection();
    Statement stmt;
    String sql;

    try {
      stmt = con.createStatement();
      sql = "CREATE TABLE categories " +
              "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
              " name TEXT)";
      stmt.executeUpdate(sql);
      stmt.close();
      ConnectionFactory.closeConnection(con);
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }

    con = ConnectionFactory.getConnection();
    try {
      stmt = con.createStatement();
      sql = "CREATE TABLE projects " +
              "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
              " name TEXT," +
              "title TEXT," +
              "description TEXT," +
              "id_category INTEGER)";
      stmt.executeUpdate(sql);
      stmt.close();
      ConnectionFactory.closeConnection(con);
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }

    con = ConnectionFactory.getConnection();
    try {
      stmt = con.createStatement();
      sql = "CREATE TABLE blogs " +
              "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
              "title TEXT," +
              "text TEXT," +
              "dateOfCreation LONG," +
              "id_project INTEGER)";
      stmt.executeUpdate(sql);
      stmt.close();
      ConnectionFactory.closeConnection(con);
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }

    con = ConnectionFactory.getConnection();
    try {
      stmt = con.createStatement();
      sql = "CREATE TABLE comments " +
              "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
              "text TEXT," +
              "dateOfCreation LONG," +
              "id_project INTEGER)";
      stmt.executeUpdate(sql);
      stmt.close();
      ConnectionFactory.closeConnection(con);
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
  }
}
