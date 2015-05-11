package ua.goit.kickstarter.util;


public class UrlParser {
  public static Operation parse(String url){
    Operation result = new Operation();
    result.setOperationType(OperationType.LIST_ALL);

    if (url != null) {
      String[] urlParts = url.split("/");
      if (urlParts.length > 3) {

        if (urlParts[3].equals("add")) {
          result.setOperationType(OperationType.ADD_ITEM);
        } else if (urlParts[3].equals("delete")) {
          result.setOperationType(OperationType.DELETE_ITEM);
        } else if (urlParts[3].equals("edit")) {
          result.setOperationType(OperationType.EDIT_ITEM);
        } else {
          try {
            Integer id = Integer.parseInt(urlParts[3]);
            result.setObjectId(id);
            result.setOperationType(OperationType.VIEW_ITEM);
          } catch (NumberFormatException e) {
          /*NoN*/
          }
        }

        if (urlParts.length >= 5) {
          if (urlParts[4].equals("edit")){
            result.setOperationType(OperationType.EDIT_ITEM);
          }else if (urlParts[4].equals("delete")){
            result.setOperationType(OperationType.DELETE_ITEM);
          }
        }
      }
    }
    return result;
  }

  public static String simplifyUrl(String url) {
    String result = null;
    if (url != null) {
      String[] urlParts = url.split("/");
      if (urlParts.length >= 2) {
        result = "/" + urlParts[2];
      } else {
        result = url;
      }
    }
    return result;
  }
}
