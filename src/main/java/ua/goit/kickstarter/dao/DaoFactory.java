package ua.goit.kickstarter.dao;

public interface DaoFactory {


  public CategoryDao getCategoryDao();

  public ProjectDao getProjectDao();

  public CommentDao getCommentDao();

  public BlogPostDao getBlogpostDao();

  public UserDao getUserDao();
}
