package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.service.ProjectService;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class ReadAllProjectsController implements Controller {
  private final ProjectService projectService;

  public ReadAllProjectsController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @Override
  public ViewModel process(Request request) throws ServletException, IOException {
    return proceedGet();
  }

  private ViewModel proceedGet() {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projects.jsp");
    List<Project> projects = projectService.getAll();
    viewModel.addAttributes("projects", projects);

    return viewModel;
  }
}
