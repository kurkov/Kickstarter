package ua.goit.kickstarter.dao;

public class DaoFactoryImpl implements DaoFactory {
  @Override
  public CategoryDao getCategoryDao(){
    return new CategoryDaoImpl();
  }

  @Override
  public ProjectDao getProjectDao(){
    return new ProjectDaoImpl();
  }

  @Override
  public CommentDao getCommentDao(){
    return new CommentDaoImpl();
  }

  @Override
  public BlogPostDao getBlogPostDao(){
    return new BlogPostDaoImpl();
  }

  @Override
  public UserDao getUserDao() {
    return new UserDaoImpl();
  }
}
