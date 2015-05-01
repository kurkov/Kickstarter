<%@ page contentType="text/html;charset=UTF-8" language="java" import="ua.goit.kickstarter.model.Comment"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>


<html>
<head>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

  <!-- Latest compiled and minified JavaScript-->
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <script>$(document).ready(function() {
        /* Automagically jump on good tab based on anchor; for page reloads or links */
        if(location.hash) {
            $('a[href=' + location.hash + ']').tab('show');
        }})
    </script>
  <title></title>
</head>
<body>

<div class="container" role="navigation">

  <br>
  <div class="jumbotron">

    <p><c:out value="${project.description}"/></p>
    <p><a class="btn btn-primary btn-lg" href="http://google.com" target="_blank" role="button">Learn more</a></p>
  </div>
  <br>

  <div role="tabpanel">
    <ul class="nav nav-tabs" role="tablist" id="tabs">
      <li role="presentation" class="active"><a href="#profile" aria-controls="profile"
                                                role="tab" data-toggle="tab">Profile</a>
      </li>
      <li role="presentation"><a href="#comments" aria-controls="comments" role="tab"
                                 data-toggle="tab">Comments</a></li>
      <li role="presentation"><a href="#blogposts" aria-controls="blogposts" role="tab"
                                 data-toggle="tab">Blog Posts</a></li>
    </ul>

    <div class="tab-content">
      <div role="tabpanel" class="tab-pane active" id="profile">
        <div class="panel panel-success">
          <div class="panel-heading">
          </div>
          <div class="panel-body">
            <c:out value="${project.description}"/>
          </div>
        </div>
      </div>

      <div role="tabpanel" class="tab-pane " id="comments">
        <br>
        <div class="row">
          <div class="col-lg-6">
            <form action = "/comment/add" method="post">
              <div class="input-group">
                <input type="hidden" name="projectId" value="<c:out value="${project.id}"/>">
                <input type="text" class="form-control" name="newComment" placeholder="Add comment">
                <span class="input-group-btn">
                  <button class="btn btn-default" type="submit">Add</button>
                </span>
              </div>
            </form>
            <fmt:setLocale value="en_US" />
            <div class="list-group">
              <ul>
                <c:forEach var="c" items="${commentList}">
                <div class="input-group">
                <form action = "/comment/delete" method="post">
                  <li>
                    (<joda:format value="${c.dateOfCreation}" style="SM" />)

                    <%--(<fmt:formatDate type="both"
                    dateStyle="medium" timeStyle="short"
                    value="${c.dateOfCreation}"/>)--%>
                    <c:out value="${c.text}"/>
                    <input type="hidden" name="projectId" value="<c:out value="${project.id}"/>">
                    <input type="hidden" name="commentId" value="<c:out value="${c.id}"/>">
                    <button class="btn btn-default" type="submit">X</button>
                  </li>
                  </form>
                </div>
                </c:forEach>
              </ul>
            </div>
          </div>
        </div>
      </div>

      <div role="tabpanel" class="tab-pane" id="blogposts">
        <div class="row">
          <div class="col-lg-6">
            <a href="/blogpost/add?projectId=<c:out value='${project.id}'/>" class="glyphicon glyphicon-plus">
              Add new post
            </a>
            <br><br>

            <fmt:setLocale value="en_US" />
            <div class="list-group">
              <ul>
                <c:forEach var="c" items="${blogPostList}">
                  <div class="input-group">

                    <form action="/blogpost/delete" method="post">

                      <li>
                        <%--(<fmt:formatDate type="both" dateStyle="medium" timeStyle="short"
                                     value="${c.dateOfCreation}"/>)--%>
                        (<joda:format value="${c.dateOfCreation}" style="SM" />)
                        <button class="btn btn-default" type="submit">X</button>
                        <a href="/blogpost/edit?projectId=<c:out value="${project.id}"/>" class="glyphicon glyphicon-pencil"></a>
                        <br>
                        <b><c:out value="${c.title}"/></b><br>
                        <c:out value="${c.text}"/>
                        <input type="hidden" name="projectId" value="<c:out value="${project.id}"/>">
                        <input type="hidden" name="blogPostId" value="<c:out value="${c.id}"/>">
                      </li>
                    </form>
                  </div>
                  <br><br>
                </c:forEach>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <br>
</div>
</body>
</html>
