package ua.goit.kickstarter.model;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class UserRole {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "username", nullable = false)
  private User user;

  @Column(name = "authority")
  private String role;

  public UserRole() {
  }

  public UserRole(Roles roles, User user) {
    role = roles.toString();
    this.user = user;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
