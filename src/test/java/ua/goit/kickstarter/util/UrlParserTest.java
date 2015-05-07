package ua.goit.kickstarter.util;

import org.junit.Test;

import static junit.framework.Assert.*;

public class UrlParserTest {

  @Test
  public void TestParserEdit(){
    String testUrl = "/servlet/category/1/edit/";
    Operation actual = UrlParser.parse(testUrl);
    Operation expected = new Operation(1, OperationType.EDIT_ITEM);
    assertEquals(expected, actual);
  }

  @Test
  public void TestParserViewItem(){
    String testUrl = "/servlet/project/1/";
    Operation actual = UrlParser.parse(testUrl);
    Operation expected = new Operation(1, OperationType.VIEW_ITEM);
    assertEquals(expected, actual);
  }

  @Test
  public void TestParserDeleteItem(){
    String testUrl = "/servlet/project/1/delete";
    Operation actual = UrlParser.parse(testUrl);
    Operation expected = new Operation(1, OperationType.DELETE_ITEM);
    assertEquals(expected, actual);
  }

  @Test
  public void TestParserViewAll_On_EmptyUrl(){
    String testUrl = null;
    Operation actual = UrlParser.parse(testUrl);
    Operation expected = new Operation(null, OperationType.LIST_ALL);
    assertEquals(expected, actual);
  }

  @Test
  public void TestParserAddItem_without_Slash_onTheEnd(){
    String testUrl = "/servlet/category/add";
    Operation actual = UrlParser.parse(testUrl);
    Operation expected = new Operation(null, OperationType.ADD_ITEM);
    assertEquals(expected, actual);
  }

  @Test
  public void TestParserAddItem_with_Slash_onTheEnd(){
    String testUrl = "/servlet/project/add/";
    Operation actual = UrlParser.parse(testUrl);
    Operation expected = new Operation(null, OperationType.ADD_ITEM);
    assertEquals(expected, actual);
  }

  @Test
  public void simplifyUrl() {
    String url = "/servlet/category";
    String expected = "/category";
    String actual = UrlParser.simplifyUrl(url);
    assertEquals(expected, actual);

    url = "/servlet/category/1/edit";
    actual = UrlParser.simplifyUrl(url);
    assertEquals(expected, actual);

    expected = "/project";
    url = "/servlet/category/add";
    actual = UrlParser.simplifyUrl(url);
    assertFalse(expected.equals(actual));

    url = null;
    actual = UrlParser.simplifyUrl(url);
    assertNull(actual);
  }
}
