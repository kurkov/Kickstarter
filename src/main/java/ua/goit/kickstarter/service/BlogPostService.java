package ua.goit.kickstarter.service;

import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.model.Project;

import java.util.List;

public interface BlogPostService {
  List<BlogPost> getAll();
  List<BlogPost> getByProject(Project project);
  List<BlogPost> getByProjectId(Integer projectId);
  BlogPost getBlogPostById(Integer id);
  BlogPost addNewBlogPost(String title, String text, Integer projectId);
  void deleteBlogPostById(Integer blogPostId);
}
