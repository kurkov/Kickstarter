package ua.goit.kickstarter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.goit.kickstarter.model.Roles;
import ua.goit.kickstarter.model.User;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    User user = userService.getByName(name);
    Set<GrantedAuthority> roles = new HashSet<>();
    roles.add(new SimpleGrantedAuthority(Roles.USER.name()));
    UserDetails userDetails = new org.springframework.security.core.userdetails.User
                              (user.getName(), user.getPassword(), roles);

    return userDetails;
  }
}
