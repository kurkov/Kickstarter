package ua.goit.kickstarter.controller;

import org.joda.time.DateTime;
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

public class CreateBlogPostController implements Controller {

  private final ProjectService projectService;
  private final BlogPostService blogPostService;
  private final CommentService commentService;

  public CreateBlogPostController(ProjectService projectService, BlogPostService blogPostService, CommentService commentService) {
    this.projectService = projectService;
    this.blogPostService = blogPostService;
    this.commentService = commentService;
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

    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/blogPostAdd.jsp");

    String blogPostTitle = request.getParameter("blogPostTitle");
    String blogPostText = request.getParameter("blogPostText");

    Integer projectId = getIdInteger(request.getParameter("projectId"));
    Integer blogPostId = getIdInteger(request.getParameter("blogPostId"));

    Project project = projectService.getById(projectId);

    BlogPost blogPost = new BlogPost(blogPostId, blogPostTitle, blogPostText, new DateTime(), project);

    if (blogPostTitle.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'title' must be filled");
    } else if (blogPostText.equals("")) {
      viewModel.addAttributes("ErrorMessage", "Field 'description' must be filled");
    } else {
      blogPostService.addPostToProjectBlog(blogPost);
      viewModel = getViewModelForProject(project);
    }

    return viewModel;
  }

  private ViewModel getViewModelForProject(Project project) {
    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItem.jsp");
    viewModel.addAttributes("project", project);

    List<Comment> commentList = commentService.getByProject(project);
    if (commentList.size() > 0) {
      Collections.sort(commentList); //TODO sort in request from database
    }
    viewModel.addAttributes("commentList", commentList);

    List<BlogPost> blogPostList = blogPostService.getByProject(project);
    if (blogPostList.size() > 0) {
      Collections.sort(commentList); // TODO sort
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
