package ua.goit.kickstarter.model;

public class User {
  private int id;
  private String login;
  private String email;
  private String name;
  private String password;

  public User() {
  }

  public User(String login, String password, String name, String email) {
    this.login = login;
    this.email = email;
    this.name = name;
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{" +
        "login='" + login + '\'' +
        ", email='" + email + '\'' +
        ", name='" + name + '\'' +
        ", password='" + password + '\'' +
        '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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
}
