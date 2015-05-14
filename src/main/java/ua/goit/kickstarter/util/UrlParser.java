package ua.goit.kickstarter.util;

public class UrlParser {
  public static int getObjectId(String url) {
    String[] urlParts = url.split("/");
    int objectId = 0;
    try {
      objectId = Integer.parseInt(urlParts[3]);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return objectId;
  }

}
