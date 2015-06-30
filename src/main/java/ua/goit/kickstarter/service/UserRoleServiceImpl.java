package ua.goit.kickstarter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.kickstarter.dao.UserRoleDao;
import ua.goit.kickstarter.model.UserRole;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {
  private final UserRoleDao userRoleDao;

  @Autowired
  public UserRoleServiceImpl(UserRoleDao userRoleDao) {
    this.userRoleDao = userRoleDao;
  }

  @Override
  public UserRole getById(Integer id) {
    return userRoleDao.getById(id);
  }

  @Override
  public void add(UserRole userRole) {
    userRoleDao.add(userRole);
  }

  @Override
  public void delete(UserRole userRole) {
    userRoleDao.delete(userRole);
  }

  @Override
  public void update(UserRole userRole) {
    userRoleDao.update(userRole);
  }
}
