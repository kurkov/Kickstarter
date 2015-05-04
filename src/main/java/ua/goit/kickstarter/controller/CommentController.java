package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.Comment;
import ua.goit.kickstarter.service.CommentService;
import ua.goit.kickstarter.service.CommentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentController implements CommentController {
  @Override
  public void proceedRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //TODO
  }
  @Override
  public void addNewComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Integer projectId = 0;
    try {
      String newComment = req.getParameter("newComment");
      projectId = Integer.parseInt(req.getParameter("projectId"));
      if (!newComment.equals("")) {
        if (projectId > 0) {
          CommentService commentService = new CommentServiceImpl();
          Comment comment = commentService.addNewComment(newComment, projectId);
        }
      } else {
        req.setAttribute("errorMessage", "Cant add empty comment!!!");
      }
    } catch (NumberFormatException e) {
      resp.sendRedirect("/project");
    }

    resp.sendRedirect("/project/" + projectId + "#comments");
  }

  @Override
  public void deleteComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Integer commentId = 0;
    Integer projectId = 0;
    try {
      commentId = Integer.parseInt(req.getParameter("commentId"));
      projectId = Integer.parseInt(req.getParameter("projectId"));
      if (commentId > 0) {
        CommentService commentService = new CommentServiceImpl();
        commentService.deleteCommentById(commentId);
      }
    } catch (NumberFormatException e) {
      resp.sendRedirect("/project");
    }

    resp.sendRedirect("/project/" + projectId + "#comments");
  }
}




