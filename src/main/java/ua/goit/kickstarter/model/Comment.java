package ua.goit.kickstarter.model;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment extends AbstractModel implements Comparable {
  private String text;
  private DateTime dateOfCreation;
  private Project project;

  public Comment(Integer id, String text, DateTime dateOfCreation, Project
      project) {
    this.id = id;
    this.text = text;
    this.dateOfCreation = dateOfCreation;
    this.project = project;
  }

  public Comment(String text, DateTime dateOfCreation, Project project) {
    this.text = text;
    this.dateOfCreation = dateOfCreation;
    this.project = project;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Comment comment = (Comment) o;

      if (id != comment.id) return false;
      if (getText() != null ? !getText().equals(comment.getText()) : comment.getText() != null)
          return false;
      return !(dateOfCreation != null ? !dateOfCreation.equals(comment.dateOfCreation) : comment.dateOfCreation != null);

  }

  @Override
  public int hashCode() {
      int result = (int) (id ^ (id >>> 32));
      result = 31 * result + (getText() != null ? getText().hashCode() : 0);
      result = 31 * result + (dateOfCreation != null ? dateOfCreation.hashCode() : 0);
      return result;
  }

  @Override
  public String toString() {
    return "Comment{" +
        "text='" + text + '\'' +
        ", dateOfCreation=" + dateOfCreation.toString("dd-MM-yyyy HH:mm:ss") +
        ", project=" + project +
        '}';
  }

  public DateTime getDateOfCreation() {
      return dateOfCreation;
  }

  public void setDateOfCreation(DateTime dateOfCreation) {
      this.dateOfCreation = dateOfCreation;
  }

  public String getText() {
      return text;
  }

  public void setText(String text) {
      this.text = text;
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
      Comment entry = (Comment) o;
      return -getDateOfCreation().compareTo(entry.getDateOfCreation());
  }
}
