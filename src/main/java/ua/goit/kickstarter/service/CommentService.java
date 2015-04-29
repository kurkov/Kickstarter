package ua.goit.kickstarter.service;

import ua.goit.kickstarter.model.Comment;
import ua.goit.kickstarter.model.Project;

import java.util.List;

public interface CommentService {
  List<Comment> getAll();
  List<Comment> getByProject(Project project);
  List<Comment> getByProjectId(Integer projectId);
  Comment getCommentById(Integer id);
  Comment addNewComment(String text, Integer projectId);
  void deleteCommentById(Integer commentId);
}
