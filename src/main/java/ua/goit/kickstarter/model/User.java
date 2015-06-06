package ua.goit.kickstarter.model;

public class User {
  private int id;
  private String name;
  private String password;
  private String email;

  public User() {
  }

  public User(String name, String password, String email) {
    this.name = name;
    this.password = password;
    this.email = email;
  }

  @Override
  public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", password='" + password + '\'' +
        ", email='" + email + '\'' +
        '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
