package ua.goit.kickstarter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.kickstarter.dao.UserDao;
import ua.goit.kickstarter.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
  private final UserDao userDao;

  @Autowired
  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public List<User> getAll() {
    return userDao.getAll();
  }

  @Override
  public User getById(Integer id) {
    return userDao.getById(id);
  }

  @Override
  public void add(User user) {
    userDao.add(user);
  }

  @Override
  public void delete(User user) {
    userDao.delete(user);
  }

  @Override
  public void update(User user) {
    userDao.update(user);
  }

  @Override
  public User getByName(String name) {
    return userDao.getByName(name);
  }

}
