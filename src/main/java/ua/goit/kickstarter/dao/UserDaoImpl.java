package ua.goit.kickstarter.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.kickstarter.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

  @Autowired
  public UserDaoImpl(Connection connection) {
    super(connection);
  }

  @Override
  public User getById(Integer id) {
    User user;
    String sqlSelect = "SELECT * FROM users WHERE id = " + id + ";";
    try {
      ResultSet rs = executeQuery(sqlSelect);
      if (rs.next()) {

        String login = rs.getString("login");
        String password = rs.getString("password");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        String email = rs.getString("email");
        user = new User(id, login, password, firstName, lastName, email);
      } else {
        user = null;
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return user;
  }

  @Override
  public List<User> getAll() {
    return null;
  }

  @Override
  public User add(User user) {

    int userId;
    User newUser;
    String sqlInsert = "INSERT INTO users (login,password,firstName, lastName, email ) VALUES ( ?,?,?,?,? )";
    try {
      PreparedStatement statement = connection.prepareStatement(sqlInsert);
      statement.setString(1, user.getLogin());
      statement.setString(2, user.getPassword());
      statement.setString(3, user.getFirstName());
      statement.setString(4, user.getLastName());
      statement.setString(5, user.getEmail());

      int affectedRows = statement.executeUpdate();

      if (affectedRows == 0) {
        throw new SQLException("Creating user failed, no rows affected.");
      }
      try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          userId = generatedKeys.getInt(1);
          newUser = getById(userId);
        } else {
          throw new SQLException("Creating user failed, no ID obtained.");
        }
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return newUser;
  }

  @Override
  public void delete(User user) {

  }

  @Override
  public User update(User user) {
    return null;
  }
}
