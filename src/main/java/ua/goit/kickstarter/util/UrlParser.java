package ua.goit.kickstarter.util;


public class UrlParser {
  public static Operation parse(String url){
    Operation result = new Operation();
    result.setOperationType(OperationType.LIST_ALL);

    if (url != null) {
      String[] urlParts = url.split("/");
      if (urlParts.length >= 2) {

        if (urlParts[2].equals("add")) {
          result.setOperationType(OperationType.ADD_ITEM);
        } else if (urlParts[2].equals("delete")) {
          result.setOperationType(OperationType.DELETE_ITEM);
        } else if (urlParts[2].equals("edit")) {
          result.setOperationType(OperationType.EDIT_ITEM);
        } else {
          try {
            Integer id = Integer.parseInt(urlParts[2]);
            result.setObjectId(id);
            result.setOperationType(OperationType.VIEW_ITEM);
          } catch (NumberFormatException e) {
          /*NoN*/
          }
        }

        if (urlParts.length >= 4) {
          if (urlParts[3].equals("edit")){
            result.setOperationType(OperationType.EDIT_ITEM);
          }else if (urlParts[3].equals("delete")){
            result.setOperationType(OperationType.DELETE_ITEM);
          }
        }
      }
    }
    return result;
  }
}
