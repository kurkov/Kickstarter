package ua.goit.kickstarter.dao;

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
//    return (User) sessionFactory.getCurrentSession().get(User.class, name);

    return new User("olenenok", "olenenok", "olenenok", true, null);
  }
}
