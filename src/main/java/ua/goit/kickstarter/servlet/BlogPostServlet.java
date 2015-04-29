package ua.goit.kickstarter.servlet;

import ua.goit.kickstarter.controller.BlogPostController;
import ua.goit.kickstarter.controller.ControllerFactory;
import ua.goit.kickstarter.controller.ControllerFactoryImpl;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.util.Operation;
import ua.goit.kickstarter.util.OperationType;
import ua.goit.kickstarter.util.UrlParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class BlogPostServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ControllerFactory factory = new ControllerFactoryImpl();
    BlogPostController blogPostController = factory.getBlogPostController();
    blogPostController.proceedRequest(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    Connection con = Factory.getConnection();

    String url = req.getPathInfo();
    Operation operation = new UrlParser().parse(url);
    ControllerFactory factory = new ControllerFactoryImpl();
    BlogPostController blogPostController = factory.getBlogPostController();
    if (operation.getOperationType() == OperationType.ADD_ITEM) {
      blogPostController.addNewBlogPost(req, resp);
    } else if (operation.getOperationType() == OperationType.DELETE_ITEM) {
      blogPostController.deleteBlogPost(req, resp);
    }
    Factory.closeConnection(con);
  }
}
