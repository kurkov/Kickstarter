package ua.goit.kickstarter.controller;

import org.joda.time.DateTime;
import ua.goit.kickstarter.model.Comment;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.service.CommentService;
import ua.goit.kickstarter.service.ProjectService;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class CreateCommentController implements Controller {
  private final CommentService commentService;
  private final ProjectService projectService;

  public CreateCommentController(CommentService commentService, ProjectService projectService) {
    this.commentService = commentService;
    this.projectService = projectService;
  }

  @Override
  public ViewModel process(Request request) throws ServletException, IOException {
    String newComment = request.getParameter("newComment");
    Integer projectId = getIdInteger(request.getParameter("projectId"));
    Project project = projectService.getById(projectId);
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItem.jsp");

    if (newComment.equals("")) {
      viewModel.addAttributes("errorMessage", "Cant add empty comment!!!");
    } else {
      Comment comment = commentService.addNewComment(new Comment(newComment, new DateTime(),project));
      List<Comment> commentList = commentService.getByProject(project);
      viewModel.addAttributes("commentList", commentList);
      viewModel.addAttributes("project", project);
    }
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
