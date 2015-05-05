package ua.goit.kickstarter.dao;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.Test;
import ua.goit.kickstarter.factory.ConnectionPool;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.model.Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

public class BlogPostDaoTest {
  private Integer getExistingProjectId(Connection connection) {
    Integer res = null;
    ProjectDao projectDao = Factory.getProjectDao(connection);
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
  public void addNewBlogPost() throws SQLException {
    Connection connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);

    ProjectDao projectDao = Factory.getProjectDao(connection);
    BlogPostDao blogPostDao = Factory.getBlogPostDao(connection);
    BlogPost createdBlogPost = new BlogPost();
    createdBlogPost.setTitle("New blogpost test");
    createdBlogPost.setText("This is a new blogpost");
    DateTime currentDate = new DateTime();
    createdBlogPost.setDateOfCreation(currentDate);
    Integer projectId = getExistingProjectId(connection);
    Project project = projectDao.getById(projectId);
    createdBlogPost.setProject(project);
    BlogPost addedBlogPost = blogPostDao.add(createdBlogPost);

    assertEquals(createdBlogPost.getTitle(), addedBlogPost.getTitle());
    assertEquals(createdBlogPost.getText(), addedBlogPost.getText());

    connection.rollback();
    connection.close();
  }

  @Test
  public void getBlogPostById() throws SQLException {
    Connection connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);

    BlogPostDao blogPostDao = Factory.getBlogPostDao(connection);
    blogPostDao.add("Test1", "Test2", 1);
    BlogPost blogPost = blogPostDao.getById(1);

    assertNotNull(blogPost);

    connection.rollback();
    connection.close();
  }

  @Test
  public void getBlogPostsByProjectId() throws SQLException {
    Connection connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);

    BlogPostDao blogPostDao = Factory.getBlogPostDao(connection);
    blogPostDao.add("Test1", "Test2", 1);
    List<BlogPost> blogPosts = blogPostDao.getByProjectId(1);

    Logger logger = Logger.getLogger(this.getClass());
    logger.info(blogPosts);
    logger.info("blogPosts.size()" + blogPosts.size());

    assertNotNull(blogPosts);
    assertTrue(blogPosts.size() > 0);

    connection.rollback();
    connection.close();
  }

  @Test
  public void getBlogPostsByProject() throws SQLException {
    Connection connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);

    BlogPostDao blogPostDao = Factory.getBlogPostDao(connection);
    ProjectDao projectDao = Factory.getProjectDao(connection);
    Project project = projectDao.add("Test project1", "Test description", 1);
    BlogPost blogPost = blogPostDao.add("Test blog post1", "Test2",
            project.getId());
    List<BlogPost> blogPosts = blogPostDao.getByProject(project);

    assertNotNull(blogPosts);
    assertTrue(blogPosts.size() > 0);

    connection.rollback();
    connection.close();
  }

  @Test
  public void getAllBlogPosts() throws SQLException {
    Connection connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);

    BlogPostDao blogPostDao = Factory.getBlogPostDao(connection);
    ProjectDao projectDao = Factory.getProjectDao(connection);
    Project project = projectDao.add("Test project1", "Test description", 1);
    BlogPost blogPost = blogPostDao.add("Test blog post1", "Test2",
            project.getId());
    List<BlogPost> blogPosts = blogPostDao.getAll();

    assertNotNull(blogPosts);
    assertTrue(blogPosts.size() > 0);

    connection.rollback();
    connection.close();
  }
}
