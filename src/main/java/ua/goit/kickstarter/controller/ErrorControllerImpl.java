package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.view.ViewModel;

public class ErrorControllerImpl implements Controller {

  @Override
  public ViewModel process(Request request) {
    return new ViewModel("/error.jsp").withAttribute("error",
            request.getParameter("error"));
  }
}
