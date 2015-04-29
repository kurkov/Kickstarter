package ua.goit.kickstarter.dao;

import org.junit.Test;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Comment;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CommentDaoTest {
  @Test
  public void addNewComment() throws SQLException {
    Connection con = Factory.getConnection();
    con.setAutoCommit(false);

    DaoFactory daoFactory = new DaoFactoryImpl();
    CategoryDao categoryDao = daoFactory.getCategoryDao();
    Category category = categoryDao.add("New category 88");

    ProjectDao projectDao = daoFactory.getProjectDao();
    Project project = projectDao.add("New project 11", category.getId().toString(), "Something new invented");

    CommentDao commentDao = daoFactory.getCommentDao();
    Comment newComment = new Comment(2, "New Comment", new Date(), project);
    Comment actual = commentDao.add(newComment);

    assertNotNull(actual);

    con.rollback();
    Factory.closeConnection(con);
  }

  @Test
  public void getById() throws SQLException {
    Connection con = Factory.getConnection();
    con.setAutoCommit(false);

    DaoFactory daoFactory = new DaoFactoryImpl();
    CategoryDao categoryDao = daoFactory.getCategoryDao();
    Category category = categoryDao.add("New category 88");

    ProjectDao projectDao = daoFactory.getProjectDao();
    Project project = projectDao.add("New project 11", category.getId().toString(), "Something new invented");

    CommentDao commentDao = daoFactory.getCommentDao();
    Comment newComment = new Comment(1, "New Comment", new Date(), project);
    Comment comment = commentDao.add(newComment);
    Comment actual = commentDao.getById(1);

    assertEquals(comment, actual);

    con.rollback();
    Factory.closeConnection(con);
    //TODO need adding Joda-Time
  }

}
