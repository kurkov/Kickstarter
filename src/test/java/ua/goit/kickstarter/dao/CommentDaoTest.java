package ua.goit.kickstarter.dao;

import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.goit.kickstarter.factory.ConnectionPool;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Comment;
import ua.goit.kickstarter.model.Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CommentDaoTest {
  private static Connection connection;

  @BeforeClass
  public static void createConnection() throws SQLException {
    connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);
  }

  @Test
  public void addNewComment() throws SQLException {
    CategoryDao categoryDao = Factory.getCategoryDao(connection);
    Category category = categoryDao.add("New category 88");
    ProjectDao projectDao = Factory.getProjectDao(connection);
    Project project = projectDao.add("New project 11", "Something new invented",
        category.getId());
    CommentDao commentDao = Factory.getCommentDao(connection);
    Comment newComment = new Comment(2, "New Comment", new DateTime(), project);
    Comment actual = commentDao.add(newComment);

    assertNotNull(actual);

    connection.rollback();
  }

  @Test
  public void getById() throws SQLException {
    CategoryDao categoryDao = Factory.getCategoryDao(connection);
    Category category = categoryDao.add("New category 88");
    ProjectDao projectDao = Factory.getProjectDao(connection);
    Project project = projectDao.add("New project 11", "Something new invented",
        category.getId());
    CommentDao commentDao = Factory.getCommentDao(connection);
    Comment newComment = new Comment("New Comment", new DateTime(), project);
    Comment comment = commentDao.add(newComment);
    List<Comment> commentList = commentDao.getAll();
    int commentId = 0;
    for (Comment com : commentList) {
      commentId = com.getId();
    }
    Comment actual = commentDao.getById(commentId);

    assertEquals(comment, actual);

    connection.rollback();
  }

  @AfterClass
  public static void closeConnection() throws SQLException {
    connection.close();
  }
}
