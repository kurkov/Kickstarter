package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.service.BlogPostService;
import ua.goit.kickstarter.service.ProjectService;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.util.OperationType;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;

public class CreateBlogPostController implements Controller {

  private final ProjectService projectService;
  private final BlogPostService blogPostService;

  public CreateBlogPostController(ProjectService projectService, BlogPostService blogPostService) {
    this.projectService = projectService;
    this.blogPostService = blogPostService;
  }

  @Override
  public ViewModel process(Request request) throws ServletException, IOException {
    ViewModel viewModel = null;
    if ("GET".equals(request.getMethod())) {
      viewModel = proceedGet(request);
    } else if ("POST".equals(request.getMethod())) {
      viewModel = proceedPost(request);
    }
    return viewModel;
  }

  private ViewModel proceedGet(Request request) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/blogPostAdd.jsp");
    Integer projectId = getIdInteger(request.getParameter("projectId"));
    Project project = projectService.getById(projectId);
    viewModel.addAttributes("project", project);
    return viewModel;
  }

  private ViewModel proceedPost(Request request) {
    String blogPostTitle = request.getParameter("blogPostTitle");
    String blogPostText = request.getParameter("blogPostText");
    String projectIdStr = request.getParameter("projectId");
    Integer projectId = getIdInteger(projectIdStr);
    String blogPostIdStr = request.getParameter("blogPostId");
    Integer blogPostId = getIdInteger(blogPostIdStr);
    ViewModel viewModel;
    Project project = projectService.getById(projectId);

    if (blogPostTitle.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'title' must be filled");
    } else if (blogPostText.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'description' must be filled");
    } else {
      BlogPost blogPost = blogPostService.addNewBlogPost(blogPostTitle, blogPostText, projectId);
      viewModel = getViewModelForProject(projectId);
    }
    return viewModel;
  }

  private ViewModel getViewModelForProject(Integer projectId) {
    return null;
  }

  private Integer getIdInteger(String idStr) {

    Integer id = null;

    try {
      id = Integer.parseInt(idStr);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }

    return id;
  }
}
