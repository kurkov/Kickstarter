package ua.goit.kickstarter.controller;

public interface ControllerFactory {

  ProjectController getProjectController();

  Controller getCategoryController();

  CommentController getCommentController();

  BlogPostController getBlogPostController();
}
