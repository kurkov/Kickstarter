package ua.goit.kickstarter.model;

import org.joda.time.DateTime;

import java.util.Date;

public class BlogPost extends AbstractModel {

  String title;
  String text;
  DateTime dateOfCreation;
  Project project;

  public BlogPost() {
  }

  public BlogPost(Integer id, String title, String text, DateTime dateOfCreation, Project project) {
    this.id = id;
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
}
