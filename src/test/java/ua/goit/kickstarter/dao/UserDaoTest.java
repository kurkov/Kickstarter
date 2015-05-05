package ua.goit.kickstarter.dao;

import org.junit.Test;
import ua.goit.kickstarter.factory.ConnectionPool;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.model.User;

import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDaoTest {

  @Test
  public void add_New_User() throws SQLException {
    Connection connection = ConnectionPool.getConnection();
    UserDao userDao = Factory.getUserDao(connection);
    connection.setAutoCommit(false);

    User user = new User(0, "user2", "zzz", "User2_fname", "User2_lName", "user2@host.com");
    User newUser = userDao.add(user);
    assertEquals(newUser.getEmail(), user.getEmail());
    assertEquals(newUser.getFirstName(), user.getFirstName());
    assertEquals(newUser.getLogin(), user.getLogin());
    assertEquals(newUser.getPassword(), user.getPassword());
    assertTrue(newUser.getId() > 0);

    connection.rollback();
    connection.close();
  }
}
