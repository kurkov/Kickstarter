package ua.goit.kickstarter.dao;

 import java.util.List;

public interface AbstractDao <T>{

  public T getById(Integer id);
  public T getById(String strId);

  public List<T> getAll();
  public T add(T element);

  public void deleteById(Integer id);
  public void deleteById(String strId);

  T update(T element);
}





