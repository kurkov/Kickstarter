package ua.goit.kickstarter.service;

import java.util.List;

public interface GenericService<T> {
  T getById(Integer id);

  List<T> getAll();

  T add(T element);

  void delete(T element);

  T update(T element);
}
