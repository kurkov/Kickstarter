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

public class ReadProjectController implements Controller {

  private final ProjectService projectService;
  private final CommentService commentService;
  private final BlogPostService blogPostService;

  public ReadProjectController(ProjectService projectService, CommentService commentService,
                               BlogPostService blogPostService) {
    this.projectService = projectService;
    this.commentService = commentService;
    this.blogPostService = blogPostService;
  }

  @Override
  public ViewModel process(Request request) throws ServletException, IOException {
    return proceedGet(request);
  }

  private ViewModel proceedGet(Request request) {

    ViewModel viewModel;
    Integer projectId;

    projectId = getIdInteger(request.getParameter("projectId"));
    Project project = projectService.getById(projectId);
    viewModel = getViewModelForProjectView(project);

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
