package ua.goit.kickstarter.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authorities")
public class UserRole {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "user_authority", joinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
  private Set<User> userRoles;

  @Column(name = "authority")
  private String role;

  public UserRole() {
  }

  public UserRole(Roles roles, Set<User> userRoles) {
    role = roles.toString();
    this.userRoles = userRoles;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Set<User> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(Set<User> userRoles) {
    this.userRoles = userRoles;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
