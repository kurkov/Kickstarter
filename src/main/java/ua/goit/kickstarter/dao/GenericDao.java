package ua.goit.kickstarter.dao;

public interface GenericDao<T>{
  T getById(Integer id);

  T add(T element);

  void delete(T element);

  T update(T element);
}





