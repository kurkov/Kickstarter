package ua.goit.kickstarter.dao;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.Test;
import ua.goit.kickstarter.factory.ConnectionFactory;
import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.model.Project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

public class BlogPostDaoTest {
  private Integer getExistingProjectId() {

    Integer res = null;
    DaoFactory daoFactory = ConnectionFactory.getDaoFactory();
    ProjectDao projectDao = daoFactory.getProjectDao();
    List<Project> projectList = projectDao.getAll();

    for (Project project : projectList) {
      res = project.getId();
    }

    if (res == null) {
      fail("No 'Project' record available to test!");
    }

    return res;
  }

  @Test
  public void add_New_Blogpost() throws SQLException {
    DaoFactory daoFactory = ConnectionFactory.getDaoFactory();
    ProjectDao projectDao = daoFactory.getProjectDao();
    BlogPostDao blogPostDao = daoFactory.getBlogpostDao();
    Connection connection = ConnectionFactory.getConnection();
    connection.setAutoCommit(false);

    BlogPost createdBlogpost = new BlogPost();
    createdBlogpost.setTitle("New blogpost test");
    createdBlogpost.setText("This is a new blogpost");
    DateTime currentDate = new DateTime();
    createdBlogpost.setDateOfCreation(currentDate);
    Integer projectId = 1;
    Project project = projectDao.getById(projectId);
    createdBlogpost.setProject(project);
    BlogPost addedBlogPost = blogPostDao.add(createdBlogpost);

    assertEquals(createdBlogpost.getTitle(), addedBlogPost.getTitle());
    assertEquals(createdBlogpost.getText(), addedBlogPost.getText());

    connection.rollback();
    ConnectionFactory.closeConnection(connection);
  }


  @Test
  public void getBlogPost__byId() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    connection.setAutoCommit(false);
    DaoFactory daoFactory = ConnectionFactory.getDaoFactory();
    BlogPostDao blogPostDao = daoFactory.getBlogpostDao();
    blogPostDao.add("Test1", "Test2", 1);
    BlogPost blogPost = blogPostDao.getById(1);
    assertNotNull(blogPost);
    connection.rollback();
    ConnectionFactory.closeConnection(connection);
  }

  @Test
  public void getBlogPosts__byProjectId() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    connection.setAutoCommit(false);
    DaoFactory daoFactory = ConnectionFactory.getDaoFactory();
    BlogPostDao blogPostDao = daoFactory.getBlogpostDao();
    blogPostDao.add("Test1", "Test2", 1);
    List<BlogPost> blogPosts = blogPostDao.getByProjectId(1);
    Logger logger = Logger.getLogger(this.getClass());
    logger.info(blogPosts);
    logger.info("blogPosts.size()" + blogPosts.size());

    assertNotNull(blogPosts);
    assertTrue(blogPosts.size() > 0);

    connection.rollback();
    ConnectionFactory.closeConnection(connection);
  }

  @Test
  public void getBlogPosts__byProject() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    connection.setAutoCommit(false);
    DaoFactory daoFactory = ConnectionFactory.getDaoFactory();
    BlogPostDao blogPostDao = daoFactory.getBlogpostDao();
    ProjectDao projectDao = daoFactory.getProjectDao();
    Project project = projectDao.add("Test project1", "1", "Test description");
    BlogPost blogPost = blogPostDao.add("Test blog post1", "Test2",
            project.getId());
    List<BlogPost> blogPosts = blogPostDao.getByProject(project);

    assertNotNull(blogPosts);
    assertTrue(blogPosts.size() > 0);

    connection.rollback();
    ConnectionFactory.closeConnection(connection);
  }

  @Test
  public void getBlogPosts__all() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    connection.setAutoCommit(false);
    DaoFactory daoFactory = ConnectionFactory.getDaoFactory();
    BlogPostDao blogPostDao = daoFactory.getBlogpostDao();
    ProjectDao projectDao = daoFactory.getProjectDao();
    Project project = projectDao.add("Test project1", "1", "Test description");
    BlogPost blogPost = blogPostDao.add("Test blog post1", "Test2",
            project.getId());
    List<BlogPost> blogPosts = blogPostDao.getAll();

    assertNotNull(blogPosts);
    assertTrue(blogPosts.size() > 0);

    connection.rollback();
    ConnectionFactory.closeConnection(connection);
  }
}
