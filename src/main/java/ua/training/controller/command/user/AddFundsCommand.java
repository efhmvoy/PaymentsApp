package ua.training.controller.command.user;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.entity.Account;
import ua.training.model.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddFundsCommand implements Command {

    private static final Logger logger = Logger.getLogger(AddFundsCommand.class);

    private static final String ERROR_MESSAGE = "errorMessage";

    private AccountService accountService;

    public AddFundsCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {

        ResourceBundle rb = CommandUtility.setResourceBundle(request);
        String accountNumber = (String) request.getSession().getAttribute("accountNumber");
        String amount = request.getParameter("amount");

        try{
            new BigDecimal(amount);

        } catch (NumberFormatException ex){
            request.setAttribute(ERROR_MESSAGE, rb.getString("incorrect.input"));
            return "/WEB-INF/views/user/add_funds.jsp";
        }

        try {
           accountService.addFunds(Long.parseLong(accountNumber), new BigDecimal(amount));
        } catch (RuntimeException ex){
            logger.error(ex.getMessage());
            request.setAttribute(ERROR_MESSAGE, rb.getString("error.server"));
            return "/WEB-INF/views/user/user_account_list.jsp";
        }

        List<Account> accountList = new ArrayList<>();
        String userLogin = (String) request.getSession().getAttribute("login");

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
