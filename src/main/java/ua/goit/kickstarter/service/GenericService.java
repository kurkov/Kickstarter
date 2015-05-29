package ua.goit.kickstarter.service;

public interface GenericService<T> {
  T getById(Integer id);

  void add(T element);

  void delete(T element);

  void update(T element);
}
