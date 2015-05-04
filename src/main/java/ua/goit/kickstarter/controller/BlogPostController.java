package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.util.Operation;
import ua.goit.kickstarter.util.OperationType;
import ua.goit.kickstarter.util.UrlParser;
import ua.goit.kickstarter.service.BlogPostService;
import ua.goit.kickstarter.service.BlogPostServiceImpl;
import ua.goit.kickstarter.service.ProjectService;
import ua.goit.kickstarter.service.ProjectServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlogPostController implements BlogPostController {
  @Override
  public void proceedRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String url = req.getPathInfo();
    String page;
    Operation operation = new UrlParser().parse(url);
    Integer projectId = 0;
    Integer blogPostId = 0;
    try {
      projectId = Integer.parseInt(req.getParameter("projectId"));
    } catch (NumberFormatException e) {
      throw new RuntimeException("Incorrect projectId");
    }
    ProjectService projectService = new ProjectServiceImpl();
    Project project = projectService.getProjectById(projectId);
    req.setAttribute("project", project);

    if (operation.getOperationType() == OperationType.ADD_ITEM) {
      page = "/WEB-INF/jsp/blogPostAdd.jsp";
    } else if (operation.getOperationType() == OperationType.EDIT_ITEM) {
      try {
        blogPostId = Integer.parseInt(req.getParameter("blogPostId"));
      } catch (NumberFormatException e) {
        throw new RuntimeException("Incorrect blogPostId");
      }
      page = "/WEB-INF/jsp/blogPostEdit.jsp";
      BlogPostService blogPostService = new BlogPostServiceImpl();
      BlogPost blogPost = blogPostService.getBlogPostById(blogPostId);
      req.setAttribute("blogPost", blogPost);
    } else {
      page = "/WEB-INF/jsp/projectItem.jsp";
    }
    RequestDispatcher dispatcher = req.getRequestDispatcher(page);
    dispatcher.forward(req, resp);
  }

  @Override
  public void addNewBlogPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String blogPostTitle = req.getParameter("blogPostTitle");
    String blogPostText = req.getParameter("blogPostText");
    String page = "/WEB-INF/jsp/blogPostAdd.jsp";
    RequestDispatcher dispatcher = req.getRequestDispatcher(page);
    Integer projectId = 0;
    try {
      projectId = Integer.parseInt(req.getParameter("projectId"));
    } catch (NumberFormatException e) {
      resp.sendRedirect("/project");
    }
    ProjectService projectService = new ProjectServiceImpl();
    Project project = projectService.getProjectById(projectId);
    req.setAttribute("project", project);

    if (blogPostTitle.equals("") || blogPostTitle == null) {
      req.setAttribute("ErrorMessage", "Field 'blog post title' must be filled");
      dispatcher.forward(req, resp);
    } else if (blogPostText.equals("") || blogPostText == null) {
      req.setAttribute("ErrorMessage", "Field 'blog post text' must be filled");
      dispatcher.forward(req, resp);
    } else {
      BlogPostService blogPostService = new BlogPostServiceImpl();
      blogPostService.addNewBlogPost(blogPostTitle, blogPostText, projectId);
      resp.sendRedirect("/project/" + projectId + "#blogposts");
    }
  }

  @Override
  public void deleteBlogPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Integer blogPostId = 0;
    Integer projectId = 0;
    try {
      blogPostId = Integer.parseInt(req.getParameter("blogPostId"));
      projectId = Integer.parseInt(req.getParameter("projectId"));
      if (blogPostId > 0) {
        BlogPostService blogPostService = new BlogPostServiceImpl();
        blogPostService.deleteBlogPostById(blogPostId);
      }
    } catch (NumberFormatException e) {
      resp.sendRedirect("/project");
    }

    resp.sendRedirect("/project/" + projectId + "#blogposts");
  }
}




