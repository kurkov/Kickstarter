package ua.goit.kickstarter.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Project;

import java.util.List;

@Repository
public class ProjectDaoImpl extends AbstractDao<Project> implements ProjectDao {

  public ProjectDaoImpl() {
    super(Project.class);
  }

  @Override
  public List<Project> getByCategory(Category category) {
    Session session = sessionFactory.getCurrentSession();
    return session.createCriteria(Project.class)
        .add(Restrictions.eq("category.id", category.getId()))
        .addOrder(Order.asc("name"))
        .list();
  }
}
