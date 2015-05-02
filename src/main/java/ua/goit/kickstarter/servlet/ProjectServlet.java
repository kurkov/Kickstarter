package ua.goit.kickstarter.servlet;

import org.apache.log4j.Logger;
import ua.goit.kickstarter.controller.ControllerFactory;
import ua.goit.kickstarter.controller.ControllerFactoryImpl;
import ua.goit.kickstarter.controller.ProjectController;
import ua.goit.kickstarter.factory.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class ProjectServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Logger logger = Logger.getLogger(this.getClass());
    logger.info("ProjectServlet GET:" + req.getPathInfo());

    Connection con = ConnectionFactory.getConnection();

    ControllerFactory factory = new ControllerFactoryImpl();
    ProjectController projectController = factory.getProjectController();
    projectController.proceedRequest(req, resp);

    ConnectionFactory.closeConnection(con);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Connection con = ConnectionFactory.getConnection();

    ControllerFactory factory = new ControllerFactoryImpl();
    Logger logger = Logger.getLogger(this.getClass());
    logger.info("ProjectServlet POST:" + req.getPathInfo());
    ProjectController projectController = factory.getProjectController();
    projectController.proceedPost(req, resp);

    ConnectionFactory.closeConnection(con);
  }
}
