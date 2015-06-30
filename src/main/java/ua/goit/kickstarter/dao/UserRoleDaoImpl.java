package ua.goit.kickstarter.dao;

import org.springframework.stereotype.Repository;
import ua.goit.kickstarter.model.UserRole;

@Repository
public class UserRoleDaoImpl extends AbstractDao<UserRole> implements UserRoleDao {

  public UserRoleDaoImpl() {
    super(UserRole.class);
  }
}
