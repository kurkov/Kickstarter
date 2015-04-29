package ua.goit.kickstarter.dao;

import ua.goit.kickstarter.data.ApplicationData;
import ua.goit.kickstarter.model.Comment;
import ua.goit.kickstarter.model.Project;

import java.util.List;

public class CommentDaoFakeImpl extends AbstractDaoImpl<Comment> implements CommentDao {
  @Override
  public Comment getById(Integer id) {
    ApplicationData appData = ApplicationData.getInstance();
    return appData.getCommentById(id);
  }

  @Override
  public List<Comment> getByProjectId(Integer id) {
    ApplicationData appData = ApplicationData.getInstance();
    return appData.getCommentsByProjectId(id);
  }

  @Override
  public List<Comment> getByProject(Project project) {
    ApplicationData appData = ApplicationData.getInstance();
    return appData.getCommentsByProject(project);
  }

  @Override
  public List<Comment> getAll() {
    ApplicationData appData = ApplicationData.getInstance();
    return appData.getAllComments();
  }

  @Override
  public Comment add(Comment element) {
    return null;
  }

  @Override
  public void deleteById(Integer id) {

  }

  @Override
  public Comment update(Comment element) {
    return null;
  }

  @Override
  public Comment add(String text, Integer projectId) {
    ApplicationData appData = ApplicationData.getInstance();
    return appData.addComment(text, projectId);
  }

}
