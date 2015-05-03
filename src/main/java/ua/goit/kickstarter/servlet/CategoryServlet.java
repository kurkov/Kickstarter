package ua.goit.kickstarter.servlet;


import ua.goit.kickstarter.controller.Controller;
import ua.goit.kickstarter.controller.ControllerFactory;
import ua.goit.kickstarter.controller.ControllerFactoryImpl;
import ua.goit.kickstarter.factory.ConnectionFactory;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

public class CategoryServlet extends HttpServlet {

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
    Connection con = ConnectionFactory.getConnection();

    ControllerFactory factory = new ControllerFactoryImpl();
    Controller categoryController = factory.getCategoryController();
    ViewModel viewModel = categoryController.process(new Request(req.getParameterMap(),
        req.getMethod(), req.getRequestURI()));
    forward(req, resp, viewModel);
    ConnectionFactory.closeConnection(con);
  }

  private void forward(HttpServletRequest req, HttpServletResponse resp,
                       ViewModel viewModel) throws ServletException, IOException {
    String view = req.getContextPath() + viewModel.getView();
    RequestDispatcher dispatcher = req.getRequestDispatcher(view);
    setAttributes(req, viewModel);
    dispatcher.forward(req, resp);
  }

  private void setAttributes(HttpServletRequest req, ViewModel viewModel) {
    Map<String, Object> attributes = viewModel.getAttributes();
    for (String attr : attributes.keySet()) {
      req.setAttribute(attr, attributes.get(attr));
    }
  }
}
