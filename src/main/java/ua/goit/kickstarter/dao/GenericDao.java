package ua.goit.kickstarter.dao;

 import java.util.List;

public interface GenericDao<T>{
  T getById(Integer id);

  T getById(String strId);

  List<T> getAll();

  T add(T element);

  void deleteById(Integer id);

  void deleteById(String strId);

  T update(T element);
}





