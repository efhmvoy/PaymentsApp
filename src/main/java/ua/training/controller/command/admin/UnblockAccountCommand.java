package ua.training.controller.command.admin;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.entity.Account;
import ua.training.model.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UnblockAccountCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetUserListCommand.class);

    private static final String ERROR_MESSAGE = "errorMessage";

    private final AccountService accountService;

    public UnblockAccountCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        ResourceBundle rb = CommandUtility.setResourceBundle(request);
        String accountNumber = request.getParameter("accountNumber");

        try{
            accountService.updateAccount(Long.parseLong(accountNumber), 1L);
        } catch (RuntimeException ex){
            request.setAttribute(ERROR_MESSAGE, rb.getString("error.server"));
            logger.error(ex);
        }

        List<Account> blockedAccountList = new ArrayList<>();

        try{
            blockedAccountList = accountService.getBlockedAccountList();
        } catch (RuntimeException ex){
            request.setAttribute(ERROR_MESSAGE, rb.getString("error.server"));
            logger.error(ex.getMessage());
        }
        request.getSession().setAttribute("blockedAccountList", blockedAccountList);

        return "/WEB-INF/views/admin/blocked_account_list.jsp";

    }
}
