package ua.goit.kickstarter.controller;

import org.apache.log4j.Logger;
import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Comment;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.util.Operation;
import ua.goit.kickstarter.util.OperationType;
import ua.goit.kickstarter.util.UrlParser;
import ua.goit.kickstarter.service.*;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ProjectController implements Controller {
  private final CategoryService categoryService;
  private final ProjectService projectService;
  private final CommentService commentService;
  private final BlogPostService blogPostService;

  public ProjectController(CategoryService categoryService,
                           ProjectService projectService,
                           CommentService commentService,
                           BlogPostService blogPostService) {
    this.categoryService = categoryService;
    this.projectService = projectService;
    this.commentService = commentService;
    this.blogPostService = blogPostService;
  }

  @Override
  public ViewModel process(Request request)
      throws ServletException, IOException {
    ViewModel viewModel = null;
    if ("GET".equals(request.getMethod())) {
      viewModel = proceedRequest(request);
    } else if ("POST".equals(request.getMethod())) {
      viewModel = proceedPost(request);
    }
    return viewModel;
  }

  private ViewModel proceedRequest(Request request)
          throws ServletException, IOException {
    String url = request.getUrl();
    Operation operation = UrlParser.parse(url);
    ViewModel viewModel;
    String categoryIdStr = request.getParameter("categoryId");
    Integer categoryId = getIdInteger(categoryIdStr);

    if (operation.getOperationType() == OperationType.VIEW_ITEM) {
      Project project = projectService.getProjectById(operation.getObjectId());
      viewModel = getViewModelForProjectView(project);
    } else if (operation.getOperationType() == OperationType.ADD_ITEM) {
      viewModel = getViewModelForProjectAdd(categoryId, null, null);
    } else if (operation.getOperationType() == OperationType.EDIT_ITEM) {
      viewModel = getViewModelForProjectEdit(operation, categoryId, null, null);
    } else {
      viewModel = new ViewModel("/WEB-INF/jsp/projects.jsp");
      List<Project> projects = projectService.getAll();
      viewModel.addAttributes("projects", projects);
    }

    return viewModel;
  }

  private ViewModel getViewModelForProjectEdit(Operation operation,
           Integer categoryId, String projectName, String projectDescription) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItemEdit.jsp");
    Project project = projectService.getProjectById(operation.getObjectId());
    viewModel.addAttributes("categoryId", categoryId);
    viewModel.addAttributes("project", project);
    if (projectName.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'name' must be filled");
    } else if (projectDescription.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'description' must be " +
          "filled");
    }
    return viewModel;
  }

  private ViewModel getViewModelForProjectAdd(Integer categoryId, String
      projectName, String projectDescription) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItemAdd.jsp");
    viewModel.addAttributes("categoryId", categoryId);
    if (projectName.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'name' must be filled");
    } else if (projectDescription.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'description' must be " +
          "filled");
    }
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

  private ViewModel proceedPost(Request request)
      throws ServletException, IOException {
    String url = request.getUrl();
    Operation operation = UrlParser.parse(url);
    ViewModel viewModel = null;

    Logger logger = Logger.getLogger(this.getClass());
    logger.info(operation);

    String categoryIdStr = request.getParameter("categoryId");
    Integer categoryId = getIdInteger(categoryIdStr);
    String projectName = request.getParameter("projectName");
    String projectDescription = request.getParameter("projectDescription");

    if (operation.getOperationType() == OperationType.ADD_ITEM) {
      if (projectName.equals("") || projectDescription.equals("")) {
        viewModel = getViewModelForProjectAdd(categoryId, projectName,
            projectDescription);
      } else {
        Project project = projectService.addNewProject(projectName,
            projectDescription, categoryId);
        viewModel = getViewModelForProjectView(project);
      }
    } else if (operation.getOperationType() == OperationType.DELETE_ITEM) {
      projectService.deleteProject(operation.getObjectId());
      viewModel = getViewModelForProjectsViewInCategory(categoryId);
    } else if (operation.getOperationType() == OperationType.EDIT_ITEM) {
      if (projectName.equals("") || projectDescription.equals("")) {
        viewModel = getViewModelForProjectEdit(operation, categoryId,
            projectName, projectDescription);
      } else {
        projectService.editProject(operation.getObjectId(), projectName,
            projectDescription);
        viewModel = getViewModelForProjectsViewInCategory(categoryId);
      }
    }
    return viewModel;
  }

  private ViewModel getViewModelForProjectsViewInCategory(Integer categoryId) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/categoryItem.jsp");
    Category category= categoryService.getById(categoryId);
    List<Project> projects = null;
    if (category != null) {
      projects = projectService.getByCategory(category);
    }
    viewModel.addAttributes("projects", projects);
    viewModel.addAttributes("category", category);
    viewModel.setUrlForRedirect("/category/" + categoryId);

    return viewModel;
  }

  private ViewModel getViewModelForProjectView(Project project) {
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
}
