package ua.goit.kickstarter.service;


import ua.goit.kickstarter.dao.BlogPostDao;
import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.model.Project;

import java.util.List;

public class BlogPostServiceImpl implements BlogPostService {

  private final BlogPostDao blogPostDao;

  public BlogPostServiceImpl(BlogPostDao blogPostDao) {
    this.blogPostDao = blogPostDao;
  }

  @Override
  public List<BlogPost> getAll() {
    return blogPostDao.getAll();
  }

  @Override
  public List<BlogPost> getByProject(Project project) {
    return blogPostDao.getByProject(project);
  }

  @Override
  public List<BlogPost> getByProjectId(Integer projectId) {
    return blogPostDao.getByProjectId(projectId);
  }

  @Override
  public BlogPost getBlogPostById(Integer id) {
    return blogPostDao.getById(id);
  }

  @Override
  public BlogPost addNewBlogPost(String title, String text, Integer projectId) {
    return blogPostDao.add(title, text, projectId);
  }

  @Override
  public void deleteBlogPostById(Integer blogPostId) {
    blogPostDao.deleteById(blogPostId);
  }
}
