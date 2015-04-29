package ua.goit.kickstarter.dao;

import ua.goit.kickstarter.model.Comment;
import ua.goit.kickstarter.model.Project;

import java.util.List;

public interface CommentDao  extends AbstractDao<Comment>{
  Comment add(String text, Integer projectId);
  List<Comment> getByProjectId(Integer id);
  List<Comment> getByProject(Project project);
}
