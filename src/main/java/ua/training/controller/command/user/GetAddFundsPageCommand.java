package ua.training.controller.command.user;

import ua.training.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class GetAddFundsPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        request.getSession().setAttribute("path","/WEB-INF/views/user/add_funds.jsp");
        return "/WEB-INF/views/user/add_funds.jsp";
    }
}
