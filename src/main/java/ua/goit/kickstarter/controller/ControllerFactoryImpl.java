package ua.goit.kickstarter.controller;

public class ControllerFactoryImpl implements ControllerFactory {

  @Override
  public ProjectController getProjectController() {
    return new ProjectControllerImpl();
  }

  @Override
  public CategoryController getCategoryController() {
    return new CategoryControllerImpl() {
    };
  }

  @Override
  public CommentController getCommentController() {
    return new CommentControllerImpl();
  }

  @Override
  public BlogPostController getBlogPostController() {
    return new BlogPostControllerImpl();
  }
}
