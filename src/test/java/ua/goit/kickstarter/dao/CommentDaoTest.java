package ua.goit.kickstarter.dao;

import org.joda.time.DateTime;
import org.junit.Test;
import ua.goit.kickstarter.factory.ConnectionFactory;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Comment;
import ua.goit.kickstarter.model.Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CommentDaoTest {
  @Test
  public void addNewComment() throws SQLException {
    Connection con = ConnectionFactory.getConnection();
    con.setAutoCommit(false);

    DaoFactory daoFactory = new DaoFactoryImpl();
    CategoryDao categoryDao = daoFactory.getCategoryDao();
    Category category = categoryDao.add("New category 88");

    ProjectDao projectDao = daoFactory.getProjectDao();
    Project project = projectDao.add("New project 11", category.getId().toString(), "Something new invented");

    CommentDao commentDao = daoFactory.getCommentDao();
    Comment newComment = new Comment(2, "New Comment", new DateTime(), project);
    Comment actual = commentDao.add(newComment);

    assertNotNull(actual);

    con.rollback();
    ConnectionFactory.closeConnection(con);
  }

  @Test
  public void getById() throws SQLException {
    Connection con = ConnectionFactory.getConnection();
    con.setAutoCommit(false);

    DaoFactory daoFactory = new DaoFactoryImpl();
    CategoryDao categoryDao = daoFactory.getCategoryDao();
    Category category = categoryDao.add("New category 88");

    ProjectDao projectDao = daoFactory.getProjectDao();
    Project project = projectDao.add("New project 11", category.getId().toString(), "Something new invented");

    CommentDao commentDao = daoFactory.getCommentDao();
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
    ConnectionFactory.closeConnection(con);
  }

}
