package ua.goit.kickstarter.data;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import ua.goit.kickstarter.model.*;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Comment;

import java.util.*;

import static junit.framework.Assert.assertEquals;

public class AppDataTest {

  @Test
  public void testProjectMap(){
    final Logger logger = LogManager.getLogger(AppDataTest.class);
    ApplicationData appData = ApplicationData.getInstance();
    logger.info("appData" + appData);
    List<Project> actualProjectSet = new ArrayList<>();
    Collection<List<Project>> projectSets = appData.getProjects();
    logger.info("projectSets:" + projectSets.size());

    for (List<Project> projectSet : projectSets) {
      for (Project project : projectSet) {
         actualProjectSet.add(project);
        logger.info(project);
      }
    }

    assertEquals(6,actualProjectSet.size());
  }

  @Test
  public void testAllCategories(){
    final Logger logger = LogManager.getLogger(AppDataTest.class);
    ApplicationData appData = ApplicationData.getInstance();
    logger.info("appData" + appData);
    List<Category> categories = appData.getAllCategories();

    assertEquals(2, categories.size());
  }

  @Test
  public void testAllComments() {
    ApplicationData appData = ApplicationData.getInstance();
    List<Comment> comments = appData.getAllComments();

    assertEquals(9, comments.size());
  }

  @Test
     public void testGetCommentsByProjectId() {
    ApplicationData appData = ApplicationData.getInstance();
    List<Comment> comments = appData.getCommentsByProjectId(2);

    assertEquals(3, comments.size());
  }

  @Test
  public void testGetCommentsByProject() {
    ApplicationData appData = ApplicationData.getInstance();
    Project project = appData.getProjectById(2);
    List<Comment> comments = appData.getCommentsByProject(project);

    assertEquals(3, comments.size());
  }

  @Test
  public void testAllBlogPosts() {
    ApplicationData appData = ApplicationData.getInstance();
    List<BlogPost> blogPostList = appData.getAllBlogPosts();

    assertEquals(2, blogPostList.size());
  }
}
