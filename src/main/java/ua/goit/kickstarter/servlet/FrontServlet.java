package ua.goit.kickstarter.servlet;

import org.apache.log4j.Logger;
import ua.goit.kickstarter.controller.Controller;

import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

public class FrontServlet extends HttpServlet {

  private final static Logger logger = Logger.getLogger(FrontServlet.class);
  Map<Request, Controller> controllerMap = new HashMap<>();
}
