package ua.goit.kickstarter.servlet;

import ua.goit.kickstarter.controller.*;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    handle(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    handle(req, resp);
  }

  private void handle(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {

    Request request = new Request(req.getParameterMap(), req.getMethod(),
            req.getRequestURI());

    try {
      FrontController frontController = new FrontController();
      ViewModel vm = frontController.dispatchRequest(request);
      forward(req, resp, vm);
    } catch (Throwable t) {
      ViewModel vm = new ErrorController().process(request);
      vm.addAttributes("error", t.getClass() + " " + t.getMessage());
      forward(req, resp, vm);
    }
  }

  private void forward(HttpServletRequest req, HttpServletResponse resp, ViewModel vm)
                      throws ServletException, IOException {
    RequestDispatcher dispatcher = req.getRequestDispatcher(getView(req, vm));
    setAttributes(req, vm);
    dispatcher.forward(req, resp);
  }

  private void setAttributes(HttpServletRequest req, ViewModel vm) {
    for (String attr : vm.getAttributes().keySet()) {
      req.setAttribute(attr, vm.getAttribute(attr));
    }
  }

  private String getView(HttpServletRequest req, ViewModel vm) {
    return req.getContextPath() + vm.getView();
  }
}
