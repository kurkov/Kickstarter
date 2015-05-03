package ua.goit.kickstarter.controller;

import ua.goit.kickstarter.servlet.Request;
import ua.goit.kickstarter.view.ViewModel;

import javax.servlet.ServletException;
import java.io.IOException;

public interface CategoryController {

  ViewModel proceedRequest(Request req) throws ServletException, IOException;

  ViewModel proceedPost(Request req) throws ServletException, IOException;
}
