package ua.goit.kickstarter.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProjectProperties {
  public String getDatabase() {
    Properties prop = new Properties();
    InputStream input = null;
    String database = null;

    try {
      input  = this.getClass().getResourceAsStream("/config.properties");

      if (input == null){
        throw new RuntimeException("Error reading property file");
      }

      prop.load(input);
      database = prop.getProperty("database");
    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return database;
  }
}
