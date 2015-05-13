package ua.goit.kickstarter.model;

import org.joda.time.DateTime;

public class BlogPost extends AbstractModel implements Comparable {
  private String title;
  private String text;
  private DateTime dateOfCreation;
  private Project project;

  public BlogPost() {
  }

  public BlogPost(String title, String text, DateTime dateOfCreation, Project project) {
    this.title = title;
    this.text = text;
    this.dateOfCreation = dateOfCreation;
    this.project = project;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BlogPost blogPost = (BlogPost) o;

    if (id != blogPost.id) return false;
    if (title != null ? !title.equals(blogPost.title) : blogPost.title != null)
      return false;
    if (text != null ? !text.equals(blogPost.text) : blogPost.text != null)
      return false;
    return !(dateOfCreation != null ? !dateOfCreation.equals(blogPost
            .dateOfCreation) : blogPost.dateOfCreation != null);
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (text != null ? text.hashCode() : 0);
    result = 31 * result + (dateOfCreation != null ? dateOfCreation.hashCode():0);
    return result;
  }

  @Override
  public String toString() {
    return "BlogPost{" +
        "title='" + title + '\'' +
        ", text='" + text + '\'' +
        ", dateOfCreation=" + dateOfCreation.toString("dd-MM-yyyy HH:mm:ss") +
        ", project=" + project +
        '}';
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public DateTime getDateOfCreation() {
    return dateOfCreation;
  }

  public void setDateOfCreation(DateTime dateOfCreation) {
    this.dateOfCreation = dateOfCreation;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  @Override
  public int compareTo(Object o) {
    if (o == null) {
      return -1;
    }
    BlogPost entry = (BlogPost) o;
    return -getDateOfCreation().compareTo(entry.getDateOfCreation());
  }
}
