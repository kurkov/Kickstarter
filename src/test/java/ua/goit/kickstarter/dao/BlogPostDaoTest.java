package ua.goit.kickstarter.dao;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.*;
import ua.goit.kickstarter.factory.ConnectionPool;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.model.Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.*;

public class BlogPostDaoTest {
  private static Connection connection;
  private static ProjectDao projectDao;
  private static BlogPostDao blogPostDao;

  @BeforeClass
  public static void createConnection() throws SQLException {
    connection = ConnectionPool.getConnection();
    connection.setAutoCommit(false);
    projectDao = Factory.getProjectDao(connection);
    blogPostDao = Factory.getBlogPostDao(connection);
  }

  private BlogPost createBlogPost() {
    BlogPost createdBlogPost = new BlogPost();
    createdBlogPost.setTitle("New blogpost test");
    createdBlogPost.setText("This is a new blogpost");
    DateTime currentDate = new DateTime();
    createdBlogPost.setDateOfCreation(currentDate);
    Integer projectId = getExistingProjectId();
    Project project = projectDao.getById(projectId);
    createdBlogPost.setProject(project);

    return createdBlogPost;
  }

  private Integer getExistingProjectId() {
    Integer res = null;
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
    BlogPost createdBlogPost = createBlogPost();
    BlogPost addedBlogPost = blogPostDao.add(createdBlogPost);

    assertEquals(createdBlogPost.getTitle(), addedBlogPost.getTitle());
    assertEquals(createdBlogPost.getText(), addedBlogPost.getText());

    connection.rollback();
  }

  @Test
  public void getBlogPostById() throws SQLException {
    BlogPost createdBlogPost = createBlogPost();
    blogPostDao.add(createdBlogPost);
    BlogPost blogPost = blogPostDao.getById(1);

    assertNotNull(blogPost);

    connection.rollback();
  }

  @Test
  public void getBlogPostsByProjectId() throws SQLException {
    BlogPost blogPost = createBlogPost();
    blogPostDao.add(blogPost);
    List<BlogPost> blogPosts = blogPostDao.getByProjectId(getExistingProjectId());

    Logger logger = Logger.getLogger(this.getClass());
    logger.info(blogPosts);
    logger.info("blogPosts.size()" + blogPosts.size());

    assertNotNull(blogPosts);
    assertTrue(blogPosts.size() > 0);

    connection.rollback();
  }

  @Test
  public void getAllBlogPosts() throws SQLException {
    BlogPost blogPost = createBlogPost();
    blogPostDao.add(blogPost);
    List<BlogPost> blogPosts = blogPostDao.getAll();

    assertNotNull(blogPosts);
    assertTrue(blogPosts.size() > 0);

    connection.rollback();
  }

  @Test
  public void deleteBlogPostById() throws SQLException {
    BlogPost blogPost = createBlogPost();
    blogPost = blogPostDao.add(blogPost);
    Integer blogPostId = blogPost.getId();
    blogPostDao.deleteById(blogPostId);
    BlogPost deletedBlogPost = blogPostDao.getById(blogPostId);

    assertNull(deletedBlogPost);

    connection.rollback();
  }

  @Test
  public void updateBlogPost() throws SQLException {
    BlogPost blogPost = createBlogPost();
    blogPost = blogPostDao.add(blogPost);
    String newTitle = "BrandNewTitle";
    blogPost.setTitle(newTitle);
    blogPostDao.update(blogPost);

    assertEquals(newTitle, blogPost.getTitle());

    connection.rollback();
  }

  @AfterClass
  public static void closeConnection() throws SQLException {
    connection.close();
  }
}
