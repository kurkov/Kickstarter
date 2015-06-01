package ua.goit.kickstarter.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import ua.goit.kickstarter.model.Category;

import java.util.List;

@Repository
public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {

  public CategoryDaoImpl() {
    super(Category.class);
  }

  @Override
  public List<Category> getAll() {
    Session session = sessionFactory.getCurrentSession();
    return session.createCriteria(Category.class)
        .addOrder(Order.asc("name"))
        .list();
  }

}
