package ua.goit.kickstarter;

import org.junit.Test;
import ua.goit.kickstarter.factory.DBHelper;

public class CreateDB {

  @Test
  public void createDBStructure() {
    DBHelper dbHelper = new DBHelper();
    dbHelper.initDatabase();

  }
}
