package ua.goit.kickstarter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.kickstarter.dao.UserDao;
import ua.goit.kickstarter.model.User;
import ua.goit.kickstarter.model.UserRole;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("UserDetailServiceImpl")
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private UserDao userDao;

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    User user = userDao.getByName(name);
    List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
    return buildUserForAuthentication(user, authorities);
  }

  private List<GrantedAuthority> buildUserAuthority(UserRole role) {
    Set<GrantedAuthority> authoritySet = new HashSet<>();
    authoritySet.add(new SimpleGrantedAuthority(role.getRole()));
    return new ArrayList<>(authoritySet);
  }

  private org.springframework.security.core.userdetails.User buildUserForAuthentication
                                        (User user, List<GrantedAuthority> authorities) {

    return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                                        user.isEnable(), true, true, true, authorities);
  }
}
