package ua.goit.kickstarter.servlet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

public class FrontServlet extends HttpServlet {
  static DataSource dataSource;

  @Override
  public void init() throws ServletException {
    super.init();
    InitialContext cxt;
    try {
      cxt = new InitialContext();
      dataSource = (DataSource) cxt.lookup("java:/comp/env/jdbc/kickstarter");
    } catch (NamingException e) {
      e.printStackTrace();
    }
  }
}
