package ua.goit.kickstarter.service;

import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.model.Project;

import java.util.List;

public interface BlogPostService {
  List<BlogPost> getAll();
  List<BlogPost> getByProject(Project project);
  List<BlogPost> getByProjectId(Integer projectId);
  BlogPost getBlogPostById(Integer id);
  void addPostToProjectBlog(BlogPost blogPost);
  void deleteBlogPostById(Integer blogPostId);
  void editBlogPost(BlogPost blogPost);
}
