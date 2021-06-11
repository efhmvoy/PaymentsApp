package ua.training.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public interface Command {
    String execute(HttpServletRequest request) throws ServletException, IOException;
}
