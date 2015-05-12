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

public class DeleteBlogPostController implements Controller {

  private final ProjectService projectService;
  private final BlogPostService blogPostService;
  private final CommentService commentService;

  public DeleteBlogPostController(ProjectService projectService, BlogPostService blogPostService, CommentService
          commentService) {
    this.projectService = projectService;
    this.blogPostService = blogPostService;
    this.commentService = commentService;
  }

  @Override
  public ViewModel process(Request request) throws ServletException, IOException {

    ViewModel viewModel;

    Integer blogPostId = getIdInteger(request.getParameter("Id"));
    Integer projectId = getIdInteger(request.getParameter("projectId"));

    Project project = projectService.getById(projectId);
    blogPostService.deleteBlogPostById(blogPostId);

    viewModel = getViewModelForProject(project);

    return viewModel;
  }

  private ViewModel getViewModelForProject(Project project) {

    ViewModel viewModel = new ViewModel("/WEB-INF/jsp/projectItem.jsp");
    viewModel.addAttributes("project", project);

    List<Comment> commentList = commentService.getByProject(project);
    if (commentList.size() > 0) {
      Collections.sort(commentList);
    }
    viewModel.addAttributes("commentList", commentList);

    List<BlogPost> blogPostList = blogPostService.getByProject(project);
    if (blogPostList.size() > 0) {
      Collections.sort(blogPostList);
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
