package ua.goit.kickstarter.servlet;

import java.util.HashMap;
import java.util.Map;

public class Request {
  private final Map<String, String> parameters = new HashMap<>();
  private final String method;
  private final String url;

  public Request(Map<String, String[]> parameters, String method, String url) {
    this.method = method.toUpperCase();
    this.url = url;

    if (parameters != null) {
      for (String param : parameters.keySet()) {
        Object value = parameters.get(param);

        if (value != null) {
          if (value.getClass() == String.class) {
            this.parameters.put(param, (String) value);
          } else if (value.getClass() == String[].class) {
            String[] valueArray = (String[]) value;
            this.parameters.put(param, valueArray[0]);
          }
        }
      }
    }
  }

  public String getMethod() {
    return method;
  }

  public String getUrl() {
    return url;
  }

  public String getParameter(String param) {
    return parameters.get(param);
  }

  public static Request create(String method, String url) {
    return new Request(null, method, url);
  }
}
