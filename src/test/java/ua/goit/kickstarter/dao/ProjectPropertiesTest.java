package ua.goit.kickstarter.dao;

import org.junit.Test;
import ua.goit.kickstarter.factory.ProjectProperties;

import java.io.*;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class ProjectPropertiesTest {
  @Test
  public void getDatabaseProperty(){
    ProjectProperties projectProperties = new ProjectProperties();
    String str = projectProperties.getDatabase();
    assertTrue(!str.isEmpty());
  }

  @Test
  public void getProperty(){
    InputStream in = this.getClass().getResourceAsStream("/config.properties");

    if (in == null){
      System.out.println("null");
    }

   assertNotNull(in);

    try {
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
