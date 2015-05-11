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

import static org.junit.Assert.*;

public class CommentDaoTest {
  private static Connection connection;
  private static CategoryDao categoryDao;
  private static ProjectDao projectDao;
  private static CommentDao commentDao;

  @BeforeClass
  public static void init() throws SQLException {
    connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);
    categoryDao = Factory.getCategoryDao(connection);
    projectDao = Factory.getProjectDao(connection);
    commentDao = Factory.getCommentDao(connection);
  }

  private Comment createCommentInDB() {
    Category category = categoryDao.add("New category 88");
    Project project = projectDao.add("New project 11", "Something new invented",
        category.getId());
    Comment newComment = new Comment(1, "New Comment", new DateTime(), project);
    return commentDao.add(newComment);
  }

  @Test
  public void addNewComment() throws SQLException {
    Comment actual = createCommentInDB();

    assertNotNull(actual);

    connection.rollback();
  }

  @Test
  public void getById() throws SQLException {
    Comment comment = createCommentInDB();
    List<Comment> commentList = commentDao.getAll();
    int commentId = 0;
    for (Comment com : commentList) {
      commentId = com.getId();
    }
    Comment actual = commentDao.getById(commentId);

    assertEquals(comment, actual);

    connection.rollback();
  }

  @Test
  public void getByProject() throws SQLException {
    Comment comment = createCommentInDB();
    Project project = comment.getProject();
    List<Comment> commentList = commentDao.getByProject(project);

    assertTrue(commentList.size() > 0);

    connection.rollback();
  }

  @Test
  public void getAllComments() throws SQLException {
    List<Comment> commentList = commentDao.getAll();
    assertNotNull(commentList);

    connection.rollback();
  }

  @Test
  public void deleteCommentById() throws SQLException {
    Comment comment = createCommentInDB();
    Integer commentId = comment.getId();
    commentDao.deleteById(commentId);
    Comment deletedComment = commentDao.getById(commentId);

    assertNull(deletedComment);

    connection.rollback();
  }

  @AfterClass
  public static void closeConnection() throws SQLException {
    connection.close();
  }
}
