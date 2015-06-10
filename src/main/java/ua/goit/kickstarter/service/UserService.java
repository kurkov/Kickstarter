package ua.goit.kickstarter.service;

import ua.goit.kickstarter.model.User;

import java.util.List;

public interface UserService extends GenericService<User> {
  List<User> getAll();
  User getByName(String name);
}
