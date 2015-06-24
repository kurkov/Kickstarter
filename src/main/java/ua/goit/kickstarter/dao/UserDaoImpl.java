package ua.goit.kickstarter.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import ua.goit.kickstarter.model.Roles;
import ua.goit.kickstarter.model.User;
import ua.goit.kickstarter.model.UserRole;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    User user = new User("olenenok", "olenenok", "masyakot@gmail.com", true);
    Set<UserRole> roles = new HashSet<>();
    roles.add(new UserRole(Roles.ADMIN, user));
    user.setRoles(roles);
    return user;
  }
}
