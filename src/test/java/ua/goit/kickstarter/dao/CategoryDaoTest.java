package ua.goit.kickstarter.dao;

import static org.junit.Assert.assertEquals;

import com.mockrunner.jdbc.BasicJDBCTestCaseAdapter;
import com.mockrunner.jdbc.StatementResultSetHandler;
import com.mockrunner.mock.jdbc.JDBCMockObjectFactory;
import com.mockrunner.mock.jdbc.MockDataSource;
import com.mockrunner.mock.jdbc.MockResultSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockejb.jndi.MockContextFactory;
import ua.goit.kickstarter.factory.Factory;
import ua.goit.kickstarter.model.Category;

import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.SQLException;

public class CategoryDaoTest extends BasicJDBCTestCaseAdapter {
  private CategoryDao categoryDao;
  private Connection connection;

  @Before
  public void setUp() throws Exception {
    super.setUp();
    JDBCMockObjectFactory factory = getJDBCMockObjectFactory();
    MockDataSource dataSource = factory.getMockDataSource();
    connection = dataSource.getConnection();
    categoryDao = Factory.getCategoryDao(connection);
    MockContextFactory.setAsInitial();
    InitialContext context = new InitialContext();
    context.rebind("jdbc/kickstarter", dataSource);
  }

  @After
  public void tearDown() throws Exception {
    super.tearDown();
    MockContextFactory.revertSetAsInitial();
  }

  @Test
  public void testGetById() {
    createResultSet();
    Category category = categoryDao.getById(1);
    String sql = "SELECT * FROM categories WHERE id = 1;";
    closeConnection();

    verifySQLStatementExecuted(sql);
    verifyAllResultSetsClosed();
    verifyAllStatementsClosed();
    verifyConnectionClosed();

    int id = category.getId();
    assertEquals(1, id);
    assertEquals("Game", category.getName());
  }

  private void closeConnection() {
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void createResultSet() {
    StatementResultSetHandler handler = getStatementResultSetHandler();
    MockResultSet resultSet = handler.createResultSet();
    resultSet.addColumn("id", new Object[]{"1"});
    resultSet.addColumn("name", new Object[]{"Game"});
    handler.prepareGlobalResultSet(resultSet);
  }
}
