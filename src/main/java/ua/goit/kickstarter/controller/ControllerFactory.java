package ua.goit.kickstarter.controller;

public interface ControllerFactory {

  public ProjectController getProjectController();

  public CategoryController getCategoryController();

  public CommentController getCommentController();

  public BlogPostController getBlogPostController();
}
