package ua.goit.kickstarter.view;

import java.util.HashMap;
import java.util.Map;

public class ViewModel {
  private final String view;
  private final Map<String, Object> attributes = new HashMap<>();

  public ViewModel(String view) {
    this.view = view;
  }

  public ViewModel addAttributes(String attr, Object value) {
    attributes.put(attr, value);
    return this;
  }

  public String getView() {
    return view;
  }

  public Map<String, Object> getAttributes() {
    return attributes;
  }

  public Object getAttribute(String attr) {
    return attributes.get(attr);
  }
}
