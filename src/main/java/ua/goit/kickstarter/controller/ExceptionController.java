package ua.goit.kickstarter.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {
  @ExceptionHandler(Exception.class)
  public ModelAndView exceptionHandler(HttpServletRequest request, Exception ex) {
    ModelAndView mv = new ModelAndView("error");
    mv.addObject("url", request.getRequestURL());
    mv.addObject("ErrorMessage", ex.getMessage());
    return mv;
  }
}

