package ua.training.controller.command.admin;

import ua.training.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetAdminPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        request.getSession().setAttribute("path","/WEB-INF/views/admin/admin_page.jsp");
        return "/WEB-INF/views/admin/admin_page.jsp";
    }

}
