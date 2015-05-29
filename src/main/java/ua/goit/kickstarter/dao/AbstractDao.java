package ua.goit.kickstarter.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T> implements GenericDao<T> {
  @Autowired
  SessionFactory sessionFactory;

  Class<T> type;

  protected AbstractDao(Class<T> type) {
    this.type = type;
  }

  @Override
  public T getById(Integer id) {
    return (T) sessionFactory.getCurrentSession().get(type, id);
  }

  @Override
  public void add(T element) {
    sessionFactory.getCurrentSession().save(element);
  }

  @Override
  public void delete(T element) {
    sessionFactory.getCurrentSession().delete(element);
  }

  @Override
  public void update(T element) {
    sessionFactory.getCurrentSession().update(element);
  }
}
