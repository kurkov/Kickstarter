package ua.goit.kickstarter.dao;

import java.util.List;

public interface GenericDao<T>{
  T getById(Integer id);

  List<T> getAll();

  T add(T element);

  void delete(T element);

  T update(T element);
}





