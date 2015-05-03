package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;

public interface Controller {
  ViewModel process(Request request) throws ServletException, IOException;
}
