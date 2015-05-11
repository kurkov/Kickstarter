package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Comment;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.service.BlogPostService;
import ua.goit.kickstarter.service.CategoryService;
import ua.goit.kickstarter.service.CommentService;
import ua.goit.kickstarter.service.ProjectService;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CreateProjectController implements Controller {

  private final CategoryService categoryService;
  private final ProjectService projectService;
  private final CommentService commentService;
  private final BlogPostService blogPostService;

  public CreateProjectController(CategoryService categoryService, ProjectService projectService,
                                 CommentService commentService, BlogPostService blogPostService) {
    this.categoryService = categoryService;
    this.projectService = projectService;
    this.commentService = commentService;
    this.blogPostService = blogPostService;
  }

  @Override
  public ViewModel process(Request request) throws ServletException, IOException {

    ViewModel viewModel = null;

    if ("GET".equals(request.getMethod())) {
      viewModel = proceedRequest(request);
    } else if ("POST".equals(request.getMethod())) {
      viewModel = proceedPost(request);
    }

    return viewModel;
  }

  private ViewModel proceedRequest(Request request) {

    ViewModel viewModel;
    Integer categoryId = getIdInteger(request.getParameter("categoryId"));

    if (categoryId == null) {
      viewModel = getViewModel();
    } else {
      viewModel = getViewModel(categoryId);
    }

    return viewModel;
  }

  private ViewModel proceedPost(Request request) {

    ViewModel viewModel;
    Integer categoryId;
    String projectName;
    String projectDescription;

    categoryId = getIdInteger(request.getParameter("categoryId"));

    projectName = request.getParameter("projectName");
    projectDescription = request.getParameter("projectDescription");

    if (projectName.equals("") || projectDescription.equals("")) {
      viewModel = getViewModel();
    } else {
      Project project = projectService.addNewProject(new Project(projectName, projectDescription, categoryId));
      viewModel = getViewModel(project);
    }

    return viewModel;
  }

  private ViewModel getViewModel() {

    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItemAdd.jsp");
    List<Category> categories = categoryService.getAll();

    viewModel.addAttributes("categories", categories);
    viewModel.setUrlForRedirect("/servlet/project/add");

    return viewModel;
  }

  private ViewModel getViewModel(Integer categoryId) {

    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItemAdd.jsp");
    List<Category> categories = categoryService.getAll();

    viewModel.addAttributes("categories", categories);
    viewModel.addAttributes("categoryId", categoryId);

    if (viewModel.getUrlCameFrom().equals("category")) {
      viewModel.setUrlForRedirect("/servlet/category/" + categoryId);
    } else if (viewModel.getUrlCameFrom().equals("projects"))
      viewModel.setUrlForRedirect("/servlet/project");

    return viewModel;
  }

  private ViewModel getViewModel(Project project) {

    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItem.jsp");
    viewModel.addAttributes("project", project);

    List<Comment> commentList = commentService.getByProject(project);
    if (commentList.size() > 0) {
      Collections.sort(commentList);
    }
    viewModel.addAttributes("commentList", commentList);

    List<BlogPost> blogPostList = blogPostService.getByProject(project);
    if (blogPostList.size() > 0) {
      Collections.sort(commentList);
    }
    viewModel.addAttributes("blogPostList", blogPostList);

    return viewModel;
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
