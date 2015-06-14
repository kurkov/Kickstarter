<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title></title>
</head>
<body>
  <h1>LOGIN</h1>
  <form name="form_login" action="<c:url value='/servlet/login'/>" method="post">
    <table>
      <tr>
        <td>User:</td>
        <td><input type="text" name="username"></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type="password" name="password"></td>
      </tr>
      <tr>
        <td>Remember me <input type="checkbox" name="_spring_security_remember_me"></td>
      </tr>
      <tr>
        <td><input type="submit" name="submit" value="submit"></td>
      </tr>
    </table>
  </form>

</body>
</html>
