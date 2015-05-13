package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.model.Comment;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.service.BlogPostService;
import ua.goit.kickstarter.service.CommentService;
import ua.goit.kickstarter.service.ProjectService;
import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class UpdateBlogPostController implements Controller {

  private final ProjectService projectService;
  private final CommentService commentService;
  private final BlogPostService blogPostService;

  public UpdateBlogPostController(ProjectService projectService, CommentService commentService,
                                  BlogPostService blogPostService) {
    this.projectService = projectService;
    this.commentService = commentService;
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

  private ViewModel proceedGet(Request request) throws ServletException, IOException {

    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/blogPostEdit.jsp");

    Integer projectId = getIdInteger(request.getParameter("projectId"));
    Project project = projectService.getById(projectId);

    Integer blogPostId = getIdInteger(request.getParameter("blogPostId"));
    BlogPost blogPost = blogPostService.getBlogPostById(blogPostId);

    viewModel.addAttributes("project", project);
    viewModel.addAttributes("blogPost", blogPost);

    return viewModel;
  }

  private ViewModel proceedPost(Request request) throws ServletException, IOException {

    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/blogPostEdit.jsp");

    String blogPostTitle = request.getParameter("blogPostTitle");
    String blogPostText = request.getParameter("blogPostText");

    Integer projectId = getIdInteger(request.getParameter("projectId"));
    Project project = projectService.getById(projectId);

    Integer blogPostId = getIdInteger(request.getParameter("blogPostId"));
    BlogPost blogPost = blogPostService.getBlogPostById(blogPostId);

    if (blogPostTitle.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'blogPostTitle' must be filled");
    } else if (blogPostText.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'blogPostText' must be filled");
    } else {
      blogPostService.editBlogPost(blogPost);
      viewModel = getProjectView(project);
    }
    return viewModel;
  }

  private ViewModel getProjectView(Project project) {

    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItem.jsp");
    viewModel.addAttributes("project", project);

    List<Comment> commentList = commentService.getByProject(project);
    if (commentList.size() > 0) {
      Collections.sort(commentList); //TODO sort in request from database
    }
    viewModel.addAttributes("commentList", commentList);

    List<BlogPost> blogPostList = blogPostService.getByProject(project);
    if (blogPostList.size() > 0) {
      Collections.sort(blogPostList); //TODO sort in request from database
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
