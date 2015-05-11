package ua.goit.kickstarter.dao;

 import java.util.List;

public interface GenericDao<T>{
  T getById(Integer id);

  List<T> getAll();

  T add(T element);

  void deleteById(Integer id);

  T update(T element);
}





