package ua.training.controller.command.user;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.common.RegisterCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class GetPaymentsPageCommand implements Command {
    private static final Logger logger = Logger.getLogger(RegisterCommand.class);

    private static final String ERROR_MESSAGE = "Error message: ";

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        request.getSession().setAttribute("path","/WEB-INF/views/user/payments.jsp");
        String accountNumber = request.getParameter("accountNumber");
        request.getSession().setAttribute("accountNumber",accountNumber);
        return "/WEB-INF/views/user/payments.jsp";
    }
}
