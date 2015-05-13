package ua.goit.kickstarter.dao;

import org.joda.time.DateTime;
import ua.goit.kickstarter.factory.ConnectionPool;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogPostDaoImpl extends AbstractDao<BlogPost>
        implements BlogPostDao {

  public BlogPostDaoImpl(Connection connection) {
    super(connection);
  }

  @Override
  public BlogPost getById(Integer id) {
    BlogPost blogPost;
    Project project;
    ProjectDao projectDao = Factory.getProjectDao(connection);
    String sqlSelect = "SELECT * FROM blogs WHERE id = " + id + ";";
    ResultSet rs;
    try {
      rs = executeQuery(sqlSelect);
      if (rs.next()) {
        String title = rs.getString("title");
        String text = rs.getString("text");
        long date = rs.getLong("dateOfCreation");
        DateTime dateOfCreation = new DateTime(date);
        Integer id_project = rs.getInt("id_project");
        project = projectDao.getById(id_project);
        blogPost = new BlogPost(title, text, dateOfCreation, project);
        blogPost.setId(id);
      } else {
        blogPost = null;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return blogPost;
  }

  @Override
  public List<BlogPost> getByProjectId(Integer projectID) {
    List<BlogPost> blogPostList = new ArrayList<>();
    BlogPost blogPost;
    Project project;
    ProjectDao projectDao = Factory.getProjectDao(connection);
    String sqlSelect = "SELECT * FROM blogs WHERE id_project = "
        + projectID + ";";
    try {
      ResultSet rs = executeQuery(sqlSelect);
      while (rs.next()) {
        Integer id = rs.getInt("id");
        String title = rs.getString("title");
        String text = rs.getString("text");
        Long date = rs.getLong("dateOfCreation");
        DateTime dateOfCreation = new DateTime(date);
        Integer id_project = rs.getInt("id_project");
        project = projectDao.getById(id_project);
        blogPost = new BlogPost(title, text, dateOfCreation, project);
        blogPost.setId(id);
        blogPostList.add(blogPost);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return blogPostList;
  }

  @Override
  public List<BlogPost> getByProject(Project project) {
    Integer projectID = project.getId();
    return getByProjectId(projectID);
  }

  @Override
  public List<BlogPost> getAll() {
    Project project;
    BlogPost blogPost;
    ProjectDao projectDao = Factory.getProjectDao(connection);
    List<BlogPost> blogPostList = new ArrayList<>();
    String sqlQuery = "SELECT * FROM blogs;";
    ResultSet rs;
    try {
      rs = executeQuery(sqlQuery);
      while (rs.next()) {
        Integer id = rs.getInt("id");
        String title = rs.getString("title");
        String text = rs.getString("text");
        Long date = rs.getLong("dateOfCreation");
        DateTime dateOfCreation = new DateTime(date);
        Integer id_project = rs.getInt("id_project");
        project = projectDao.getById(id_project);
        blogPost = new BlogPost(title, text, dateOfCreation, project);
        blogPost.setId(id);
        blogPostList.add(blogPost);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return blogPostList;
  }

  @Override
  public BlogPost add(String title, String text, Integer projectID) {
    String sqlInsert = "INSERT INTO blogs (title, text, " +
            "dateOfCreation, id_project) VALUES ( ?,?,?,? );";
    Integer id;
    Project project;
    ProjectDao projectDao = Factory.getProjectDao(connection);
    DateTime dateOfCreation = new DateTime();
    try {
      PreparedStatement statement = connection.prepareStatement(sqlInsert);
      statement.setString(1, title);
      statement.setString(2, text);
      statement.setLong(3, dateOfCreation.getMillis());
      statement.setInt(4, projectID);
      int affectedRows = statement.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException("Creating user failed, no rows affected.");
      }
      try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          id = generatedKeys.getInt(1);
        } else {
          throw new SQLException("Creating user failed, no ID obtained.");
        }
      }
      project = projectDao.getById(projectID);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    BlogPost blogPost = new BlogPost(title, text, dateOfCreation, project);
    blogPost.setId(id);
    return blogPost;
  }

  @Override
  public BlogPost add(BlogPost element) {
    return add(element.getTitle(), element.getText(),
            element.getProject().getId());
  }

  @Override
  public void deleteById(Integer id) {
    String query = "DELETE FROM blogs WHERE id = " + id + ";";
    executeUpdate(query);
  }

  @Override
  public BlogPost update(BlogPost blogPost) {
    String query = "UPDATE blogs " +
        " SET title = '" + blogPost.getTitle() + "'," +
        " text = '" + blogPost.getText() + "'" +
        " WHERE id = " + blogPost.getId() + ";";
    executeUpdate(query);
    return blogPost;
  }
}
