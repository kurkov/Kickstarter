package ua.goit.kickstarter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView login() {
    return new ModelAndView("login");
  }
}
