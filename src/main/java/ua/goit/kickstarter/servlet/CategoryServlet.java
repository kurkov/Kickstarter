package ua.goit.kickstarter.servlet;


import ua.goit.kickstarter.controller.Controller;
import ua.goit.kickstarter.controller.ControllerFactory;
import ua.goit.kickstarter.controller.ControllerFactoryImpl;
import ua.goit.kickstarter.factory.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class CategoryServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Connection con = ConnectionFactory.getConnection();

    ControllerFactory factory = new ControllerFactoryImpl();
    Controller categoryController = factory.getCategoryController();
    categoryController.process(new Request(req.getParameterMap(), req.getMethod(),
        req.getRequestURI()));

    ConnectionFactory.closeConnection(con);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Connection con = ConnectionFactory.getConnection();

    ControllerFactory factory = new ControllerFactoryImpl();
    Controller categoryController = factory.getCategoryController();
    categoryController.process(new Request(req.getParameterMap(), req.getMethod(),
                              req.getRequestURI()));

    ConnectionFactory.closeConnection(con);
  }
}
