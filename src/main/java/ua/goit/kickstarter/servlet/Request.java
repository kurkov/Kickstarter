package ua.goit.kickstarter.servlet;

import java.util.HashMap;
import java.util.Map;

public class Request {
  private final Map<String, String> parameters = new HashMap<>();
  private final String method;
  private final String url;
  private final String simpleUrl;

  public Request(Map<String, String[]> parameters, String method, String url,
                 String simpleUrl) {
    this.method = method.toUpperCase();
    this.url = url;
    this.simpleUrl = simpleUrl;

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

  public String getSimpleUrl() {
    return simpleUrl;
  }

  public String getParameter(String param) {
    return parameters.get(param);
  }

  public static Request create(String method, String simpleUrl) {
    return new Request(null, method, null, simpleUrl);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Request request = (Request) o;

    if (method != null ? !method.equals(request.method) : request.method != null)
      return false;
    return !(simpleUrl != null ? !simpleUrl.equals(request.simpleUrl) : request.simpleUrl != null);

  }

  @Override
  public int hashCode() {
    int result = method != null ? method.hashCode() : 0;
    result = 31 * result + (simpleUrl != null ? simpleUrl.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Request{" +
        "method='" + method + '\'' +
        ", url='" + url + '\'' +
        ", simpleUrl='" + simpleUrl + '\'' +
        '}';
  }
}
