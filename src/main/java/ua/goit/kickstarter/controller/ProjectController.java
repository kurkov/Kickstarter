package ua.goit.kickstarter.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ProjectController {

  void proceedRequest(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException;

  void proceedPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException;
}
