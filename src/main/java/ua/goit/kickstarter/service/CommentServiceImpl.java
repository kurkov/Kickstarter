package ua.goit.kickstarter.service;


import ua.goit.kickstarter.dao.CommentDao;
import ua.goit.kickstarter.dao.DaoFactory;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.model.Comment;
import ua.goit.kickstarter.model.Project;

import java.util.List;

public class CommentServiceImpl implements CommentService {

  private final DaoFactory daoFactory = Factory.getDaoFactory();
  private final CommentDao commentDao = daoFactory.getCommentDao();

  @Override
  public List<Comment> getAll() {
    return commentDao.getAll();
  }

  @Override
  public List<Comment> getByProject(Project project) {
    return commentDao.getByProject(project);
  }

  @Override
  public List<Comment> getByProjectId(Integer projectId) {
    return commentDao.getByProjectId(projectId);
  }

  @Override
  public Comment getCommentById(Integer id) {
    return commentDao.getById(id);
  }

  @Override
  public Comment addNewComment(String text, Integer projectId) {
    return commentDao.add(text, projectId);
  }

  @Override
  public void deleteCommentById(Integer commentId) {
    commentDao.deleteById(commentId);
  }
}
