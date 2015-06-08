<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
  <h1>LOGIN</h1>
  <form name="form_login" action="/j_spring_security_check" method="post">
    <table>
      <tr>
        <td>User:</td>
        <td><input type="text" name="j_username"></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type="password" name="j_password"></td>
      </tr>
      <tr>
        <td>Remember me <input type="checkbox" name="remember_me"></td>
      </tr>
      <tr>
        <td><input type="submit" name="submit" value="submit"></td>
      </tr>
    </table>
  </form>

</body>
</html>
