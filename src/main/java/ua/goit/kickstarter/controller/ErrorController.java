package ua.goit.kickstarter.controller;


import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.view.ViewModel;

public class ErrorController implements Controller {
  @Override
  public ViewModel process(Request request) {
    return new ViewModel("/WEB-INF/jsp/error.jsp").withAttribute("error",
            request.getParameter("error"));
  }
}
