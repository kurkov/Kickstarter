package ua.goit.kickstarter.dao;

import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.model.Project;

import java.util.List;

public interface BlogPostDao extends GenericDao<BlogPost> {

  List<BlogPost> getByProjectId(Integer id);
  List<BlogPost> getByProject(Project project);
  BlogPost add(String title, String text, Integer projectId);

}
