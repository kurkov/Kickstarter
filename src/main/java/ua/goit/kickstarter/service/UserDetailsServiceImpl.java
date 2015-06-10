package ua.goit.kickstarter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.goit.kickstarter.model.User;

public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    User user = userService.getByName(name);
    return user;
  }
}
