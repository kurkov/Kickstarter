package ua.goit.kickstarter.dao;

public interface DaoFactory {
  CategoryDao getCategoryDao();

  ProjectDao getProjectDao();

  CommentDao getCommentDao();

  BlogPostDao getBlogPostDao();

  UserDao getUserDao();
}
