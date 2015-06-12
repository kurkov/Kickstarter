package ua.goit.kickstarter.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.goit.kickstarter.model.User;

public class UserDetailsServiceImpl implements UserDetailsService {
  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

    return new User();
  }
}
