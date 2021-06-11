package ua.training.controller.command.user;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.controller.command.common.RegisterCommand;
import ua.training.model.entity.Account;
import ua.training.model.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateUserAccountCommand implements Command {

    private static final Logger logger = Logger.getLogger(RegisterCommand.class);

    private static final String ERROR_MESSAGE = "errorMessage";

    private  AccountService accountService;

    public CreateUserAccountCommand(AccountService accountService) {
        this.accountService = accountService;
    }


    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        ResourceBundle rb = CommandUtility.setResourceBundle(request);
        String name = request.getParameter("name");
        String userLogin = (String) request.getSession().getAttribute("login");

        if (name.isEmpty()) {
            request.setAttribute(ERROR_MESSAGE, rb.getString("fill.fields"));
            return "/WEB-INF/views/user/user_account_list.jsp";
        }
        try {
            accountService.createAccount(userLogin, name);
        } catch (RuntimeException ex){
            logger.error(ex.getMessage());
            request.setAttribute(ERROR_MESSAGE, rb.getString("error.server"));
            return "/WEB-INF/views/user/user_account_list.jsp";
        }

        List<Account> accountList = new ArrayList<>();

        try{
            accountList = accountService.getAccountListByUser(userLogin);
        } catch (RuntimeException ex){
            request.setAttribute(ERROR_MESSAGE, rb.getString("error.server"));
            logger.error(ex.getMessage());
        }

        request.getSession().setAttribute("accountList", accountList);
        logger.trace("Payment list: " + accountList);
        return "/WEB-INF/views/user/user_account_list.jsp";
    }
}
