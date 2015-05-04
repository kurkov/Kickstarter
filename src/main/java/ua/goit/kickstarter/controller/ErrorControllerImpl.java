package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.service.ProjectService;
import ua.goit.kickstarter.service.ProjectServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ErrorControllerImpl {

  public void proceedRequest(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    String page;
    ProjectService projectService = new ProjectServiceImpl();
    page = "/WEB-INF/jsp/error.jsp";
    List<Project> projects = projectService.getAll();
    req.setAttribute("projects", projects);
    RequestDispatcher dispatcher = req.getRequestDispatcher(page);
    dispatcher.forward(req, resp);
  }
}
