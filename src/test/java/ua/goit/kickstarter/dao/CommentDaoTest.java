package ua.goit.kickstarter.dao;

import org.joda.time.DateTime;
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
  @Test
  public void addNewComment() throws SQLException {
    Connection con = ConnectionPool.getConnection();
    con.setAutoCommit(false);

    CategoryDao categoryDao = Factory.getCategoryDao(con);
    Category category = categoryDao.add("New category 88");
    ProjectDao projectDao = Factory.getProjectDao(con);
    Project project = projectDao.add("New project 11", category.getId(),
        "Something new invented");
    CommentDao commentDao = Factory.getCommentDao(con);
    Comment newComment = new Comment(2, "New Comment", new DateTime(), project);
    Comment actual = commentDao.add(newComment);

    assertNotNull(actual);

    con.rollback();
    con.close();
  }

  @Test
  public void getById() throws SQLException {
    Connection con = ConnectionPool.getConnection();
    con.setAutoCommit(false);

    CategoryDao categoryDao = Factory.getCategoryDao(con);
    Category category = categoryDao.add("New category 88");
    ProjectDao projectDao = Factory.getProjectDao(con);
    Project project = projectDao.add("New project 11", category.getId(),
        "Something new invented");
    CommentDao commentDao = Factory.getCommentDao(con);
    Comment newComment = new Comment("New Comment", new DateTime(), project);
    Comment comment = commentDao.add(newComment);
    List<Comment> commentList = commentDao.getAll();
    int commentId = 0;
    for (Comment com : commentList) {
      commentId = com.getId();
    }
    Comment actual = commentDao.getById(commentId);

    assertEquals(comment, actual);

    con.rollback();
    con.close();
  }
}
