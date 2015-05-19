package ua.goit.kickstarter.servlet;

import ua.goit.kickstarter.controller.*;
import ua.goit.kickstarter.view.ViewModel;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class FrontServlet extends HttpServlet {
  static DataSource dataSource;

  @Override
  public void init() throws ServletException {
    super.init();
    InitialContext cxt;
    try {
      cxt = new InitialContext();
      dataSource = (DataSource) cxt.lookup("java:/comp/env/jdbc/kickstarter");
    } catch (NamingException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    handle(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    handle(req, resp);
  }

  private void handle(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    Request request = new Request(req.getParameterMap(), req.getMethod(),
            req.getRequestURI());

    Connection connection = getConnection();

    try {
      FrontController frontController = new FrontController(connection);
      ViewModel vm = frontController.dispatchRequest(request);
      forward(req, resp, vm);
    } catch (Throwable t) {
      ViewModel vm = new ErrorController().process(request);
      vm.addAttributes("error", t.getClass() + " " + t.getMessage());
      forward(req, resp, vm);
    }
  }

  public static Connection getConnection() {
    Connection connection = null;
    try {
      connection = dataSource.getConnection();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  private void forward(HttpServletRequest req, HttpServletResponse resp, ViewModel vm)
                      throws ServletException, IOException {
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
}
