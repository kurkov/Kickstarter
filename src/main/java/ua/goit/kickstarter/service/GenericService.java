package ua.goit.kickstarter.service;

public interface GenericService<T> {
  T getById(Integer id);

  T add(T element);

  void delete(T element);

  T update(T element);
}
