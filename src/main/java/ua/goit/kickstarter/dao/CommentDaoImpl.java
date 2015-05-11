package ua.goit.kickstarter.dao;

import org.joda.time.DateTime;
import ua.goit.kickstarter.factory.ConnectionPool;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.model.Comment;
import ua.goit.kickstarter.model.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl extends AbstractDao<Comment>
        implements CommentDao {

  public CommentDaoImpl(Connection connection) {
    super(connection);
  }

  @Override
  public Comment getById(Integer id) {
    Comment comment = null;
    ResultSet rs = null;
    Project project;
    ProjectDao projectDao = Factory.getProjectDao(connection);
    String sqlSelect = "SELECT * FROM comments WHERE id = " + id;
    try {
      rs = executeQuery(sqlSelect);
      if (rs.next()) {
        String text = rs.getString("text");
        Long date = rs.getLong("dateOfCreation");
        DateTime dateOfCreation = new DateTime(date);
        Integer projectId = rs.getInt("id_project");
        project = projectDao.getById(projectId);
        comment = new Comment(id, text, dateOfCreation, project);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    return comment;
  }

  @Override
  public List<Comment> getByProjectId(Integer projectID) {
    List<Comment> commentList = new ArrayList<>();
    Comment comment;
    Project project;
    ProjectDao projectDao = Factory.getProjectDao(connection);
    String sqlSelect = "SELECT * FROM comments WHERE id_project = " +
            projectID;
    try {
      ResultSet rs = executeQuery(sqlSelect);
      while (rs.next()) {
        Integer id = rs.getInt("id");
        String text = rs.getString("text");
        Long date = rs.getLong("dateOfCreation");
        DateTime dateOfCreation = new DateTime(date);
        Integer id_project = rs.getInt("id_project");
        project = projectDao.getById(id_project);
        comment = new Comment(id, text, dateOfCreation, project);
        commentList.add(comment);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return commentList;
  }

  @Override
  public List<Comment> getByProject(Project project) {
    Integer projectID = project.getId();
    return getByProjectId(projectID);
  }

  @Override
  public List<Comment> getAll() {
    Project project;
    Comment comment;
    ProjectDao projectDao = Factory.getProjectDao(connection);
    List<Comment> commentList = new ArrayList<>();
    String sqlSelect = "SELECT * FROM comments";
    try {
      ResultSet rs = executeQuery(sqlSelect);
      while (rs.next()) {
        Integer id = rs.getInt("id");
        String text = rs.getString("text");
        Long date = rs.getLong("dateOfCreation");
        DateTime dateOfCreation = new DateTime(date);
        Integer id_project = rs.getInt("id_project");
        project = projectDao.getById(id_project);
        comment = new Comment(id, text, dateOfCreation, project);
        commentList.add(comment);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return commentList;
  }

  @Override
  public Comment add(Comment comment) {
    String query = "INSERT INTO comments (text, dateOfCreation, id_project) VALUES (?, ?, ?);";

    try {
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, comment.getText());
      DateTime dateOfCreation = comment.getDateOfCreation();
      statement.setLong(2, dateOfCreation.getMillis());
      statement.setInt(3, comment.getProject().getId());

      int affectedRows = statement.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException("Creating comment failed, no rows affected.");
      }

      ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next()) {
        comment.setId(generatedKeys.getInt(1));
      } else {
        throw new SQLException("Creating project failed, no ID obtained.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return comment;
  }

  @Override
  public void deleteById(Integer id) {
    String query = "DELETE FROM comments WHERE id = " + id;
    executeUpdate(query);
  }

  @Override
  public Comment update(Comment element) {
    return null;
  }

  @Override
  public Comment add(String text, Integer projectId) {
    ProjectDao projectDao = Factory.getProjectDao(connection);
    Project project = projectDao.getById(projectId);
    if (project == null) {
      throw new RuntimeException("Incorrect project id");
    }
    return add(new Comment(0, text, new DateTime(), project));
  }
}
