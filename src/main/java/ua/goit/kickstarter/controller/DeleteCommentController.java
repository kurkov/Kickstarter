package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.Comment;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.service.CommentService;
import ua.goit.kickstarter.service.ProjectService;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class DeleteCommentController implements Controller {
  private final CommentService commentService;
  private final ProjectService projectService;

  public DeleteCommentController(CommentService commentService, ProjectService projectService) {
    this.commentService = commentService;
    this.projectService = projectService;
  }

  @Override
  public ViewModel process(Request request) throws ServletException, IOException {
    Integer commentId = getIdInteger(request.getParameter("commentId"));
    Integer projectId = getIdInteger(request.getParameter("projectId"));
    Project project = projectService.getById(projectId);
    if (commentId > 0) {
      commentService.deleteCommentById(commentId);
    }
    List<Comment> commentList = commentService.getByProject(project);
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItem.jsp");
    viewModel.addAttributes("commentList", commentList);
    viewModel.addAttributes("project", project);

    return viewModel;
  }

  private Integer getIdInteger(String IdStr) {
    Integer id = null;
    try {
      id = Integer.parseInt(IdStr);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return id;
  }
}
