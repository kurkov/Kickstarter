package ua.goit.kickstarter.factory;

import ua.goit.kickstarter.servlet.InitListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
  public static final String CREATE_TABLES_FILENAME = "/sql/createTables.sql";
  public static final String DROP_TABLES_FILENAME = "/sql/dropTables.sql";
  public static final String INSERT_DATA_FILENAME = "/sql/insertData.sql";

  public void initDatabase() {
    executeQueryFromFile(DROP_TABLES_FILENAME);
    executeQueryFromFile(CREATE_TABLES_FILENAME);
    executeQueryFromFile(INSERT_DATA_FILENAME);
  }

  private void executeQueryFromFile(String filename) {
    InputStream input = this.getClass().getResourceAsStream(filename);

    if (input == null) {
      throw new RuntimeException("Cannot read file: " + filename);
    }

    String query = getStringFromInputStream(input);
    String[] arrayOfQueries = query.split(";");

    for (String arrayOfQuery : arrayOfQueries) {
      // we ensure that there is no spaces before or after the request string
      // in order to not execute empty statements
      if (!arrayOfQuery.trim().equals("")) {
        executeUpdate(arrayOfQuery);
      }
    }

    try {
      input.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static String getStringFromInputStream(InputStream is) {
    BufferedReader br = null;
    StringBuilder sb = new StringBuilder();
    String line;
    try {
      br = new BufferedReader(new InputStreamReader(is));
      while ((line = br.readLine()) != null) {
        if (sb.length() > 0) {
          sb.append(" ");
        }
        sb.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return sb.toString();
  }

  private int executeUpdate(String query) {
    Connection connection = InitListener.getConnection();
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
