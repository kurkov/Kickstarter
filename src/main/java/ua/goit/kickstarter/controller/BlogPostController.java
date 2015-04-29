package ua.goit.kickstarter.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface BlogPostController {
  void proceedRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

  void addNewBlogPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

  void deleteBlogPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
