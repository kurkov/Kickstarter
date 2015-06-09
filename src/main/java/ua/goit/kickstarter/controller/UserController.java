package ua.goit.kickstarter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.kickstarter.model.User;
import ua.goit.kickstarter.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(method = RequestMethod.GET, params = "new")
  public ModelAndView addUser() {
    return new ModelAndView("userAdd");
  }

  @RequestMapping(method = RequestMethod.POST)
  public ModelAndView addUser(@RequestParam("userName") String name,
                              @RequestParam("userPassword") String password,
                              @RequestParam("userEmail") String email) {
    User user = new User(name, password, email);
    userService.add(user);
    return new ModelAndView("userProfile");
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ModelAndView getUser(@PathVariable int id) {
    ModelAndView mv = new ModelAndView("userProfile");
    mv.addObject(userService.getById(id));
    return mv;
  }
}
