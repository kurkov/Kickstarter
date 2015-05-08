package ua.goit.kickstarter.servlet;

import org.apache.log4j.Logger;
import ua.goit.kickstarter.controller.*;
import ua.goit.kickstarter.factory.ConnectionPool;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.util.UrlParser;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FrontServlet extends HttpServlet {

  private final static Logger logger = Logger.getLogger(FrontServlet.class);
  Map<Request, Controller> controllerMap = new HashMap<>();

  @Override
  public void init() throws ServletException {
    super.init();

    Controller categoryController = Factory.createCategoryController
        (CategoryController.class, ConnectionPool.getConnection());
    controllerMap.put(Request.create("GET", "/category"),
        categoryController);
    controllerMap.put(Request.create("POST", "/category"),
        categoryController);

    Controller projectController = Factory.createProjectController
        (ProjectController.class, ConnectionPool.getConnection());
    controllerMap.put(Request.create("GET", "/project"),
        projectController);
    controllerMap.put(Request.create("POST", "/project"),
        projectController);

    Controller commentController = Factory.createCommentController
        (CommentController.class, ConnectionPool.getConnection());
    controllerMap.put(Request.create("POST", "/comment"),
        commentController);

    Controller blogPostController = Factory.createBlogPostController
        (BlogPostController.class, ConnectionPool.getConnection());
    controllerMap.put(Request.create("GET", "/blogpost"),
        blogPostController);
    controllerMap.put(Request.create("POST", "/blogpost"),
        blogPostController);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    handle(req, resp);
  }

  private void handle(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {

    String simpleUrl = UrlParser.simplifyUrl(req.getRequestURI());
    Request request = new Request(req.getParameterMap(), req.getMethod(),
            req.getRequestURI(), simpleUrl);

    try {
      Controller controller = controllerMap.get(request);
      if (controller == null) {
        throw new RuntimeException("Can not handle " + request);
      }
      ViewModel vm = controller.process(request);
      if ("GET".equals(request.getMethod())) {
        forward(req, resp, vm);
      }else if ("POST".equals(request.getMethod())) {
        String urlForRedirect = vm.getUrlForRedirect();
        System.out.println(urlForRedirect);
        resp.sendRedirect(urlForRedirect);
      }
    } catch (Throwable t) {
      logger.error("error", t);
      ViewModel vm = new ErrorController().process(request);
      vm.addAttributes("error", t.getClass() + " " + t.getMessage());

      forward(req, resp, vm);
    }
  }

  private void forward(HttpServletRequest req, HttpServletResponse resp,
                       ViewModel vm) throws ServletException, IOException {
    RequestDispatcher dispatcher = req.getRequestDispatcher(getView(req, vm));
    setAttributes(req, vm);
    dispatcher.forward(req, resp);
  }

  private void setAttributes(HttpServletRequest req, ViewModel vm) {
    for (String attr : vm.getAttributes().keySet()) {
      req.setAttribute(attr, vm.getAttribute(attr));
    }
  }

  private String getView(HttpServletRequest req, ViewModel vm) {
    return req.getContextPath() + vm.getView();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    handle(req, resp);
  }
}
