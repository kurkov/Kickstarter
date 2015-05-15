package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.service.ProjectService;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.util.UrlParser;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Calendar;

public class ReadProjectController implements Controller {

  private final ProjectService projectService;

  public ReadProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @Override
  public ViewModel process(Request request) throws ServletException, IOException {
    return proceedGet(request);
  }

  private ViewModel proceedGet(Request request) {
    Integer projectId = UrlParser.getObjectId(request.getUrl());
    Project project = projectService.getById(projectId);

    return getViewModelForProjectView(project);
  }

  private ViewModel getViewModelForProjectView(Project project) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItem.jsp");
    viewModel.addAttributes("project", project);

    return viewModel;
  }
}
