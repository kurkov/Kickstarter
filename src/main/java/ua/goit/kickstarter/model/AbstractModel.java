package ua.goit.kickstarter.model;

public abstract class AbstractModel {
  int id;

  public Integer getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
