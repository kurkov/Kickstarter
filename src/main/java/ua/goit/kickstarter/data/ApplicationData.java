package ua.goit.kickstarter.data;


import ua.goit.kickstarter.model.BlogPost;
import ua.goit.kickstarter.model.Category;
import ua.goit.kickstarter.model.Comment;
import ua.goit.kickstarter.model.Project;

import java.util.*;

public class ApplicationData {
    Map<Integer, Project> projects = new HashMap<>();
    Map<Integer, Category> categories = new HashMap<>();
    Map<Integer, Comment> comments = new HashMap<>();
    Map<Integer, BlogPost> blogPosts = new HashMap<>();
    Map<Category, List<Project>> projectsByCategoriesMap = new HashMap<>();
    Map<Project, List<Comment>> commentsByProjectsMap = new HashMap<>();
    Map<Project, List<BlogPost>> blogPostsByProjectsMap = new HashMap<>();
    Integer projectId = 0;
    Integer commentId = 0;
    Integer blogPostId = 0;
    private static ApplicationData instance;

    private ApplicationData() {
        Project newProject;
        Comment newComment;
        BlogPost newBlogPost;

        Category cat1 = new Category(++projectId, "Technology");
        categories.put(cat1.getId(), cat1);

        newProject = new Project(++projectId,  "Developing new OS", cat1,  "MorphOS is a lightweight, highly efficient and flexible media-centric operating system. If you are new to MorphOS and would like to learn more about it, please visit this page.");
        projects.put(newProject.getId(), newProject);
        newComment = new Comment(++commentId, "Good project", new GregorianCalendar(2014, 11, 25).getTime(), newProject);
        comments.put(newComment.getId(), newComment);
        newComment = new Comment(++commentId, "I think it's interesting!", new GregorianCalendar(2013, 6, 10).getTime(), newProject);
        comments.put(newComment.getId(), newComment);
        newComment = new Comment(++commentId, "Windows 99 or riot!", new GregorianCalendar(2015, 1, 20).getTime(), newProject);
        comments.put(newComment.getId(), newComment);
        newBlogPost = new BlogPost(++blogPostId, "Our first post", "We have created our project. Yohooo!", new Date(), newProject);
        blogPosts.put(newBlogPost.getId(), newBlogPost);
        newBlogPost = new BlogPost(++blogPostId, "Our second post", "We want to win, so send a lot of money to us!", new Date(), newProject);
        blogPosts.put(newBlogPost.getId(), newBlogPost);

        newProject = new Project(++projectId,"Beautifully Simple, Professional App Creation", cat1, "Building an app yourself is hard work. Normally you need a lot of technical skills that most people don't have. Glide makes it easy to create beautiful apps that look professional right from the start.\n" +
                "\n" +
                "You simply put text, images and movies into folders, and Glide will build the app for you. When you change the content, the app updates automatically. \n" +
                "\n" +
                "We have created a number of award winning apps using Glide, but we believe that everyone should be able to afford their own app. This Kickstarter is our way of making this happen. ");
        projects.put(newProject.getId(), newProject);
        newComment = new Comment(++commentId, "Bad project", new Date(), newProject);
        comments.put(newComment.getId(), newComment);
        newComment = new Comment(++commentId, "What is this project about?", new Date(), newProject);
        comments.put(newComment.getId(), newComment);

        newProject = new Project(++projectId, "ENVELOP - 3D Sound", cat1,  "ENVELOP is a 3D sound platform that is creating a space in San Francisco for live electronic music, DJ sets and educational workshops.");
        projects.put(newProject.getId(), newProject);
        newComment = new Comment(++commentId, "Good project", new Date(), newProject);
        comments.put(newComment.getId(), newComment);

        Category cat2 = new Category(++projectId, "Food");
        categories.put(cat2.getId(), cat2);
        newProject = new Project(++projectId, "Lowcountry Street Grocery : Mobile Farmers' Market", cat2, "Charleston's first mission-driven mobile farmers' market committed to improving healthy food access & bolstering our local food economy");
        projects.put(newProject.getId(), newProject);
        newComment = new Comment(++commentId, "Something interesting", new Date(), newProject);
        comments.put(newComment.getId(), newComment);
        newComment = new Comment(++commentId, "Good project", new Date(), newProject);
        comments.put(newComment.getId(), newComment);

        newProject = new Project(++projectId, "Breakfast in a Box", cat2, "The minimal effort, optimised breakfast. Superfoods and mindfulness. A breakfast that nourishes your body & calms your mind.");
        projects.put(newProject.getId(), newProject);
        newComment = new Comment(++commentId, "Mniam, mniam", new Date(), newProject);
        comments.put(newComment.getId(), newComment);

        newProject = new Project(++projectId, "Modern Monk Brewery", cat2, "Creating a space to craft great beer, relationships and be a blessing to our neighborhood and city!");
        projects.put(newProject.getId(), newProject);

        updateCategoriesProjectsMap();
        updateProjectsCommentsMap();
        updateProjectsBlogPostsMap();
    }

    public static ApplicationData getInstance() {
        if (instance == null)
            instance = new ApplicationData();
        return instance;
    }

    void updateCategoriesProjectsMap() {
        Collection<Project> projectsCollection = projects.values();
        projectsByCategoriesMap.clear();
        for (Project project : projectsCollection) {
            Category cat = project.getCategory();
            List<Project> projectList = projectsByCategoriesMap.get(cat);
            if (projectList == null) {
                projectList = new ArrayList<>();
            }
            projectList.add(project);
            projectsByCategoriesMap.put(cat, projectList);
        }
    }

    void updateProjectsCommentsMap() {
        Collection<Comment> commentsCollection = comments.values();
        commentsByProjectsMap.clear();
        for (Comment comment : commentsCollection) {
            Project project = comment.getProject();
            List<Comment> commentList = commentsByProjectsMap.get(project);
            if (commentList == null) {
                commentList = new ArrayList<>();
            }
            commentList.add(comment);
            commentsByProjectsMap.put(project, commentList);
        }
    }


    void updateProjectsBlogPostsMap() {
        Collection<BlogPost> blogPostsCollection = blogPosts.values();
        blogPostsByProjectsMap.clear();
        for (BlogPost blogPost : blogPostsCollection) {
            Project project = blogPost.getProject();
            List<BlogPost> blogPostList = blogPostsByProjectsMap.get(project);
            if (blogPostList == null) {
                blogPostList = new ArrayList<>();
            }
            blogPostList.add(blogPost);
            blogPostsByProjectsMap.put(project, blogPostList);
        }
    }

    public Category getCategoryById(Integer id) {
        return categories.get(id);
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(projectsByCategoriesMap.keySet());
    }

    public Project getProjectById(Integer id) {
        return projects.get(id);
    }

    public Comment getCommentById(Integer id) {
        return comments.get(id);
    }

    public List<Project> getProjectsByCategoryId(Integer categoryId) {
        Category category = categories.get(categoryId);
        if (category == null) {
            return null;
        }
        return getProjectsByCategory(category);
    }

    public List<Comment> getCommentsByProjectId(Integer projectId) {
        Project project = projects.get(projectId);
        if (project == null) {
            return null;
        }
        return getCommentsByProject(project);
    }

    public List<Project> getProjectsByCategory(Category category) {
        return projectsByCategoriesMap.get(category);
    }

    public List<Comment> getCommentsByProject(Project project) {
        if (commentsByProjectsMap.get(project) == null) {
            return new ArrayList<Comment>();
        } else {
            return commentsByProjectsMap.get(project);
        }
    }

    public List<Project> getAllProjects() {
        return new ArrayList<>(projects.values());
    }

    public Category addCategory(String newCategoryName) {
        Category newCategory = new Category(++projectId, newCategoryName);
        categories.put(newCategory.getId(),newCategory);
        projectsByCategoriesMap.put(newCategory, null);

        return newCategory;
    }

    public Comment addComment(String text, Integer projectId) {
        Project project = projects.get(projectId);
        Comment newComment = new Comment(++commentId, text, new Date(), project);
        comments.put(newComment.getId(), newComment);
        List<Comment> commentList = commentsByProjectsMap.get(project);
        if (commentList == null) {
            commentList = new ArrayList<>();
        }
        commentList.add(newComment);
        commentsByProjectsMap.put(project, commentList);
        return newComment;
    }

    public BlogPost addBlogPost(String title, String text, Integer projectId) {
        Project project = projects.get(projectId);
        BlogPost newBlogPost = new BlogPost(++blogPostId, title, text, new Date(), project);
        blogPosts.put(newBlogPost.getId(), newBlogPost);
        List<BlogPost> blogPostList = blogPostsByProjectsMap.get(project);
        if(blogPostList == null) {
            blogPostList = new ArrayList<>();
        }
        blogPostList.add(newBlogPost);
        blogPostsByProjectsMap.put(project, blogPostList);
        return newBlogPost;
    }

    public List<Comment> getAllComments() {
        return new ArrayList<>(comments.values());
    }

    public Collection<List<Project>> getProjects() {
        return projectsByCategoriesMap.values();
    }

    public BlogPost getBlogPostById(Integer id) {
        return blogPosts.get(id);
    }

    public List<BlogPost> getBlogPostsByProjectId(Integer id) {
        Project project = projects.get(projectId);
        if (project == null) {
            return null;
        }
        return getBlogPostsByProject(project);
    }

    public List<BlogPost> getBlogPostsByProject(Project project) {
        return blogPostsByProjectsMap.get(project);
    }

    public List<BlogPost> getAllBlogPosts() {
        return new ArrayList<>(blogPosts.values());
    }

    public Project addProject(String newProjectName) {
        return null; //TODO
    }

    public void deleteCommentById(Integer commentId) {
        comments.remove(commentId);
        updateProjectsCommentsMap();
    }

    public void deleteBlogPostById(Integer blogPostId) {
        blogPosts.remove(blogPostId);
        updateProjectsBlogPostsMap();
    }

  public Project updateProject(Project project) {


    Project oldProject = projects.get(project.getId());
    oldProject = project;
    return oldProject;


  }

  public void deleteProject(Project project) {
    projects.remove(project.getId());
    updateCategoriesProjectsMap();
  }

  public Project addProject(Project newProject) {
    projects.put(newProject.getId(),newProject);
    updateCategoriesProjectsMap();
    return newProject;
  }
}
