package ua.goit.kickstarter.controller;

import org.apache.log4j.Logger;
import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.model.Comment;
import ua.goit.kickstarter.model.Project;
import ua.goit.kickstarter.util.Operation;
import ua.goit.kickstarter.util.OperationType;
import ua.goit.kickstarter.util.UrlParser;
import ua.goit.kickstarter.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ProjectController implements ProjectController {
  @Override
  public void proceedRequest(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    String url = req.getPathInfo();
    String page;
    Operation operation = new UrlParser().parse(url);
    ProjectService projectService = new ProjectServiceImpl();
    CommentService commentService = new CommentServiceImpl();
    BlogPostService blogPostService = new BlogPostServiceImpl();
    String categoryId = req.getParameter("categoryId");

    if (operation.getOperationType() == OperationType.VIEW_ITEM) {
      page = "/WEB-INF/jsp/projectItem.jsp";
      Project project = projectService.getProjectById(operation.getObjectId());
      List<Comment> commentList = commentService.getByProject(project);
      if (commentList.size() > 0) {
        Collections.sort(commentList);
      }

      req.setAttribute("commentList", commentList);
      List<BlogPost> blogPostList = blogPostService.getByProject(project);
      req.setAttribute("blogPostList", blogPostList);
      req.setAttribute("project", project);

    } else if (operation.getOperationType() == OperationType.ADD_ITEM) {
      page = "/WEB-INF/jsp/projectItemAdd.jsp";
      req.setAttribute("categoryId", categoryId);

    } else if (operation.getOperationType() == OperationType.EDIT_ITEM) {
      page = "/WEB-INF/jsp/projectItemEdit.jsp";
      Project project = projectService.getProjectById(operation.getObjectId());
      req.setAttribute("category", project.getCategory());
      req.setAttribute("projectItem", project);
    } else {
      page = "/WEB-INF/jsp/projects.jsp";
      List<Project> projects = projectService.getAll();
      req.setAttribute("projects", projects);
    }
    RequestDispatcher dispatcher = req.getRequestDispatcher(page);
    dispatcher.forward(req, resp);
  }

  @Override
  public void proceedPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    String url = req.getPathInfo();
    String page;
    Operation operation = new UrlParser().parse(url);
    ProjectService projectService = new ProjectServiceImpl();

    Logger logger = Logger.getLogger(this.getClass());
    logger.info(operation);

    String categoryId = req.getParameter("categoryId");
    String projectId = req.getParameter("projectId");
    String projectName = req.getParameter("projectName");
    String projectDescription = req.getParameter("projectDescription");

    if (operation.getOperationType() == OperationType.ADD_ITEM) {
      if (projectName.equals("")) {
        req.setAttribute("ErrorMessage", "Field 'name' must be filled");
      } else {
        projectService.addNewProject(projectName, projectDescription, categoryId);
      }
    } else if (operation.getOperationType() == OperationType.DELETE_ITEM) {
      projectService.deleteProject(operation.getObjectId().toString());
    } else if (operation.getOperationType() == OperationType.EDIT_ITEM) {
      projectService.editProject(operation.getObjectId().toString(), projectName, projectDescription);
    }

    //TODO make correct redirect
    String urlObject = url.trim();
    if (urlObject.equals("project")) {
      page = "/category/" + categoryId;
    } else {
      page = "/WEB-INF/jsp/error.jsp";
    }

    resp.sendRedirect(page);
  }
}
