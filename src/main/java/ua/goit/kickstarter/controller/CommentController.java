package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.Comment;
import ua.goit.kickstarter.service.CommentService;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.util.Operation;
import ua.goit.kickstarter.util.OperationType;
import ua.goit.kickstarter.util.UrlParser;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;

public class CommentController implements Controller {
  private final CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @Override
  public ViewModel process(Request request) throws ServletException,
      IOException {

    return proceedPost(request);
  }

  private ViewModel proceedPost(Request request) throws ServletException,
      IOException {
    Operation operation = UrlParser.parse(request.getUrl());
    String newComment = request.getParameter("newComment");
    String commentIdStr = request.getParameter("commentId");
    Integer commentId = getIdInteger(commentIdStr);
    String projectIdStr = request.getParameter("projectId");
    Integer projectId = getIdInteger(projectIdStr);
    ViewModel viewModel;

    if (operation.getOperationType() == OperationType.ADD_ITEM) {
      if (! newComment.equals("")) {
        if (projectId > 0) {
          Comment comment = commentService.addNewComment(newComment, projectId);
        }
      }
    } else if (operation.getOperationType() == OperationType.DELETE_ITEM) {
      if (commentId > 0) {
        commentService.deleteCommentById(commentId);
      }
    }
    viewModel = getViewModelForComment(newComment, projectId);

    return viewModel;
  }

  private ViewModel getViewModelForComment(String newComment, Integer projectId) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItem.jsp");
    if (newComment.equals("")) {
      viewModel.addAttributes("errorMessage", "Cant add empty comment!!!");
      viewModel.setUrlForRedirect("/project" + projectId + "#comments");
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




