package ua.goit.kickstarter.dao;

public interface GenericDao<T>{
  T getById(Integer id);

  void add(T element);

  void delete(T element);

  void update(T element);
}





