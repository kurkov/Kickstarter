package ua.goit.kickstarter.servlet;

import ua.goit.kickstarter.controller.CommentController;
import ua.goit.kickstarter.controller.ControllerFactory;
import ua.goit.kickstarter.controller.ControllerFactoryImpl;
import ua.goit.kickstarter.factory.ConnectionFactory;
import ua.goit.kickstarter.util.Operation;
import ua.goit.kickstarter.util.OperationType;
import ua.goit.kickstarter.util.UrlParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class CommentServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Connection con = ConnectionFactory.getConnection();

    ControllerFactory factory = new ControllerFactoryImpl();
    CommentController commentController = factory.getCommentController();
    commentController.proceedRequest(req, resp);

    ConnectionFactory.closeConnection(con);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Connection con = ConnectionFactory.getConnection();

    String url = req.getPathInfo();
    Operation operation = new UrlParser().parse(url);
    ControllerFactory factory = new ControllerFactoryImpl();
    CommentController commentController = factory.getCommentController();
    if (operation.getOperationType() == OperationType.ADD_ITEM) {
      commentController.addNewComment(req, resp);
    } else if (operation.getOperationType() == OperationType.DELETE_ITEM) {
      commentController.deleteComment(req, resp);
    }

    ConnectionFactory.closeConnection(con);
  }
}
