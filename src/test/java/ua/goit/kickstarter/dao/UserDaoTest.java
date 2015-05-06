package ua.goit.kickstarter.dao;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.goit.kickstarter.factory.ConnectionPool;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.model.User;

import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDaoTest {
  private static Connection connection;
  private static UserDao userDao;

  @BeforeClass
  public static void createConnection() throws SQLException {
    connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);
    userDao = Factory.getUserDao(connection);
  }

  @Test
  public void add_New_User() throws SQLException {
    User user = new User(0, "user2", "zzz", "User2_fname", "User2_lName",
        "user2@host.com");
    User newUser = userDao.add(user);

    assertEquals(user.getEmail(), newUser.getEmail());
    assertEquals(user.getFirstName(), newUser.getFirstName());
    assertEquals(user.getLogin(), newUser.getLogin());
    assertEquals(user.getPassword(), newUser.getPassword());
    assertTrue(newUser.getId() > 0);

    connection.rollback();
  }

  @AfterClass
  public static void closeConnection() throws SQLException {
    connection.close();
  }
}
