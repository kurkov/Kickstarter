package ua.goit.kickstarter.dao;

public class DaoFactoryImpl implements DaoFactory {
  public DaoFactoryImpl(){};

  public CategoryDao getCategoryDao(){
    return new CategoryDaoImpl();
  }

  public ProjectDao getProjectDao(){
    return new ProjectDaoImpl();
  }

  public CommentDao getCommentDao(){
    return new CommentDaoImpl();
  }

  public BlogPostDao getBlogpostDao(){
    return new BlogPostDaoImpl();
  }

  @Override
  public UserDao getUserDao() {
    return new UserDaoImpl();
  }
}
