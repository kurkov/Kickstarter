package ua.goit.kickstarter.dao;

import ua.goit.kickstarter.data.ApplicationData;
import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.model.Project;

import java.util.List;

public class BlogPostDaoFakeImpl  extends AbstractDaoImpl<BlogPost> implements BlogPostDao {
    @Override
    public BlogPost getById(Integer id) {
        ApplicationData appData = ApplicationData.getInstance();
        return appData.getBlogPostById(id);
    }

    @Override
    public List<BlogPost> getByProjectId(Integer id) {
        ApplicationData appData = ApplicationData.getInstance();
        return appData.getBlogPostsByProjectId(id);
    }

    @Override
    public List<BlogPost> getByProject(Project project) {
        ApplicationData appData = ApplicationData.getInstance();
        return appData.getBlogPostsByProject(project);
    }

    @Override
    public List<BlogPost> getAll() {
        ApplicationData appData = ApplicationData.getInstance();
        return appData.getAllBlogPosts();
    }

  @Override
  public BlogPost add(BlogPost element) {
    return null;
  }

  @Override
    public BlogPost add(String title, String text, Integer projectId) {
        ApplicationData appData = ApplicationData.getInstance();
        return appData.addBlogPost(title, text, projectId);
    }

    @Override
    public void deleteById(Integer id) {
        ApplicationData appData = ApplicationData.getInstance();
        appData.deleteBlogPostById(id);
    }

  @Override
  public BlogPost update(BlogPost element) {
    return null;
  }


}
