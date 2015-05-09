package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.util.Operation;
import ua.goit.kickstarter.util.OperationType;
import ua.goit.kickstarter.util.UrlParser;
import ua.goit.kickstarter.service.BlogPostService;
import ua.goit.kickstarter.service.ProjectService;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;

public class BlogPostController implements Controller {
  private final ProjectService projectService;
  private final BlogPostService blogPostService;

  public BlogPostController(ProjectService projectService,
                            BlogPostService blogPostService) {
    this.projectService = projectService;
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

  public ViewModel proceedRequest(Request request) throws ServletException,
      IOException {
    String url = request.getUrl();
    Operation operation = UrlParser.parse(url);
    String projectIdStr = request.getParameter("projectId");
    Integer projectId = getInteger(projectIdStr);
    String blogPostIdStr = request.getParameter("blogPostId");
    Integer blogPostId = getInteger(blogPostIdStr);
    ViewModel viewModel;

    Project project = projectService.getById(projectId);

    if (operation.getOperationType() == OperationType.ADD_ITEM) {
      viewModel = getViewModelForBlogPostAdd(project, null, null);
    } else if (operation.getOperationType() == OperationType.EDIT_ITEM) {
      viewModel = getViewModelForBlogPostEdit(blogPostId, project, null, null);
    } else {
      viewModel = getViewModelForProject(projectId);
    }
    return viewModel;
  }

  private ViewModel getViewModelForProject(Integer projectId) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItem.jsp");
    viewModel.setUrlForRedirect("/servlet/project/" + projectId + "#blogposts");

    return viewModel;
  }

  private ViewModel getViewModelForBlogPostEdit(Integer blogPostId,
          Project project, String blogPostTitle, String blogPostText) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/blogPostEdit.jsp");
    BlogPost blogPost = blogPostService.getBlogPostById(blogPostId);
    viewModel.addAttributes("blogPost", blogPost);
    viewModel.addAttributes("project", project);

    if (blogPostTitle.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'blog post title' must be filled");
    } else if (blogPostText.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'blog post text' must be filled");
    }

    return viewModel;
  }

  private ViewModel getViewModelForBlogPostAdd(Project project,
           String blogPostTitle, String blogPostText) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/blogPostAdd.jsp");
    viewModel.addAttributes("project", project);

    if (blogPostTitle.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'blog post title' must be filled");
    } else if (blogPostText.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'blog post text' must be filled");
    }

    return viewModel;
  }

  private Integer getInteger(String idStr) {
    Integer id = null;
    try {
      id = Integer.parseInt(idStr);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return id;
  }

  public ViewModel proceedPost(Request request) throws ServletException, IOException {
    Operation operation= UrlParser.parse(request.getUrl());

    String blogPostTitle = request.getParameter("blogPostTitle");
    String blogPostText = request.getParameter("blogPostText");
    String projectIdStr = request.getParameter("projectId");
    Integer projectId = getInteger(projectIdStr);
    String blogPostIdStr = request.getParameter("blogPostId");
    Integer blogPostId = getInteger(blogPostIdStr);
    ViewModel viewModel = null;
    Project project = projectService.getById(projectId);

    if (operation.getOperationType() == OperationType.ADD_ITEM) {
      if (blogPostTitle.equals("") || blogPostText.equals("")) {
        viewModel = getViewModelForBlogPostAdd(project, blogPostTitle,
            blogPostText);
      } else {
        BlogPost blogPost = blogPostService.addNewBlogPost(blogPostTitle,
            blogPostText,
            projectId);
        viewModel = getViewModelForProject(projectId);
      }
    } else if (operation.getOperationType() == OperationType.DELETE_ITEM) {
      if (blogPostId > 0) {
        blogPostService.deleteBlogPostById(blogPostId);
        viewModel = getViewModelForProject(projectId);
      }
    } else if (operation.getOperationType() == OperationType.EDIT_ITEM) {
      if (blogPostTitle.equals("") || blogPostText.equals("")) {
        viewModel = getViewModelForBlogPostEdit(blogPostId, project,
            blogPostTitle, blogPostText);
      } else {
        blogPostService.editBlogPost(blogPostId, blogPostTitle, blogPostText);
        viewModel = getViewModelForProject(projectId);
      }
    }

    return viewModel;
  }
}




