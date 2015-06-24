package ua.goit.kickstarter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.kickstarter.model.User;
import ua.goit.kickstarter.model.UserRole;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("UserDetailServiceImpl")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    User user = userService.getByName(name);
//    List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
//    return buildUserForAuthentication(user, authorities);
    return buildUserForAuthentication(user, null);
  }

  private List<GrantedAuthority> buildUserAuthority(Set<UserRole> roles) {
    Set<GrantedAuthority> authoritySet = new HashSet<>();

    for (UserRole role : roles) {
      authoritySet.add(new SimpleGrantedAuthority(role.getRole()));
    }

    return new ArrayList<>(authoritySet);
  }

  private org.springframework.security.core.userdetails.User buildUserForAuthentication
                                        (User user, List<GrantedAuthority> authorities) {

    Set<GrantedAuthority> authoritiesGrand = new HashSet<>();
    authoritiesGrand.add(new SimpleGrantedAuthority("ADMIN"));
    return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                                        user.isEnable(), true, true, true, authoritiesGrand);
  }
}
