package ua.goit.kickstarter.dao;

import ua.goit.kickstarter.model.Comment;
import ua.goit.kickstarter.model.Project;

import java.util.List;

public interface CommentDao  extends GenericDao<Comment> {
  Comment add(Comment comment);
  List<Comment> getByProjectId(Integer id);
  List<Comment> getByProject(Project project);
}
