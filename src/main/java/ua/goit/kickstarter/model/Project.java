package ua.goit.kickstarter.model;

import javax.persistence.*;

@Entity
@Table(name = "projects")
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String name;
  private String description;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_category")
  private Category category;

  public Project(){
  }

  public Project(String projectName, String description, Category category) {
    this.name = projectName;
    this.description = description;
    this.category = category;
  }

  @Override
  public String toString() {
    return "Project{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", category=" + category +
            '}';
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Project project = (Project) o;

    if (getId() != null ? !getId().equals(project.getId()) : project.getId()
            != null)
      return false;
    if (getName() != null ? !getName().equals(project.getName()) : project
            .getName() != null)
      return false;
    if (getDescription() != null ? !getDescription().equals(project
            .getDescription()) : project.getDescription() != null)
      return false;
    return !(getCategory() != null ? !getCategory().equals(project
            .getCategory()) : project.getCategory() != null);

  }

  @Override
  public int hashCode() {
    int result = getId() != null ? getId().hashCode() : 0;
    result = 31 * result + (getName() != null ? getName().hashCode() : 0);
    result = 31 * result + (getDescription() != null ? getDescription()
            .hashCode() : 0);
    result = 31 * result + (getCategory() != null ? getCategory().hashCode()
            : 0);
    return result;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }
}

