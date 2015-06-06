package ua.goit.kickstarter.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {
  @RequestMapping(value = "/error")
  @ResponseBody
  public ModelAndView error(HttpServletRequest request) {
    ModelAndView mv = new ModelAndView("error");
    mv.addObject("url", request.getRequestURL());
    mv.addObject("ErrorMessage", "404 Page not found");
    return mv;
  }
}
