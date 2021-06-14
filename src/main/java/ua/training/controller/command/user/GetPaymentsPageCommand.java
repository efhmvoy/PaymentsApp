package ua.training.controller.command.user;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.controller.command.common.RegisterCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ResourceBundle;

public class GetPaymentsPageCommand implements Command {
    private static final Logger logger = Logger.getLogger(RegisterCommand.class);

    private static final String ERROR_MESSAGE = "errorMessage";

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {

        ResourceBundle rb = CommandUtility.setResourceBundle(request);

        request.getSession().setAttribute("path","/WEB-INF/views/user/payments.jsp");
        String accountNumber = request.getParameter("accountNumber");
        request.getSession().setAttribute("accountNumber",accountNumber);
        String accountstatus = request.getParameter("accountStatus");

        if(accountstatus.equals("blocked")){
            request.setAttribute(ERROR_MESSAGE, rb.getString("account.blocked"));
            return "/WEB-INF/views/user/user_account_list.jsp";
        }
        return "/WEB-INF/views/user/payments.jsp";
    }
}
