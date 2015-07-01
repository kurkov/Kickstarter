package ua.goit.kickstarter.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import ua.goit.kickstarter.model.User;

import java.util.List;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

  public UserDaoImpl() {
    super(User.class);
  }

  @Override
  public List<User> getAll() {
    Session session = sessionFactory.getCurrentSession();
    return session.createCriteria(User.class)
        .addOrder(Order.asc("name"))
        .list();
  }

  @Override
  public User getByName(String name) {
    Session session = sessionFactory.getCurrentSession();
    String sql = "from User u where u.name=:name";
    Query query = session.createQuery(sql);
    return (User) query.setParameter("name", name).uniqueResult();
  }
}
