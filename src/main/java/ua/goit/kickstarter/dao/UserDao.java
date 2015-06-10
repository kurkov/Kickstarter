package ua.goit.kickstarter.dao;

import ua.goit.kickstarter.model.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
  List<User> getAll();
  User getByName(String name);
}
