package ua.goit.kickstarter.util;

import org.junit.Test;


import static junit.framework.Assert.assertEquals;

public class UrlParserTest {

  @Test
  public void TestParserEdit(){

    String testUrl = "/1/edit/";
    UrlParser parser = new UrlParser();
    Operation actual = parser.parse(testUrl);
    Operation expected = new Operation(1, OperationType.EDIT_ITEM);
    assertEquals(expected, actual);

  }

  @Test
  public void TestParserViewItem(){
    String testUrl = "/1/";
    UrlParser parser = new UrlParser();
    Operation actual = parser.parse(testUrl);
    Operation expected = new Operation(1, OperationType.VIEW_ITEM);
    assertEquals(expected, actual);

  }

  @Test
  public void TestParserDeleteItem(){
    String testUrl = "/1/delete";
    UrlParser parser = new UrlParser();
    Operation actual = parser.parse(testUrl);
    Operation expected = new Operation(1, OperationType.DELETE_ITEM);
    assertEquals(expected, actual);

  }
  @Test
     public void TestParserViewAll_On_EmptyUrl(){
    String testUrl = null;
    UrlParser parser = new UrlParser();
    Operation actual = parser.parse(testUrl);
    Operation expected = new Operation(null, OperationType.LIST_ALL);
    assertEquals(expected, actual);

  }

  @Test
  public void TestParserAddItem_without_Slash_onTheEnd(){
    String testUrl = "/add";
    UrlParser parser = new UrlParser();
    Operation actual = parser.parse(testUrl);
    Operation expected = new Operation(null, OperationType.ADD_ITEM);
    assertEquals(expected, actual);

  }

  @Test
  public void TestParserAddItem_with_Slash_onTheEnd(){
    String testUrl = "/add/";
    UrlParser parser = new UrlParser();
    Operation actual = parser.parse(testUrl);
    Operation expected = new Operation(null, OperationType.ADD_ITEM);
    assertEquals(expected, actual);

  }

}
