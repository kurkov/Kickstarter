package ua.goit.kickstarter.servlet;

import ua.goit.kickstarter.controller.*;
import ua.goit.kickstarter.view.ViewModel;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
