package ua.training.controller;


import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.admin.*;
import ua.training.controller.command.common.ChangeLocaleCommand;
import ua.training.controller.command.common.LogOutCommand;
import ua.training.controller.command.common.LoginCommand;
import ua.training.controller.command.common.RegisterCommand;
import ua.training.controller.command.user.*;
import ua.training.model.service.AccountService;
import ua.training.model.service.PaymentsService;
import ua.training.model.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {

    private final Map<String, Command> commands = new HashMap<>();

    private static final String URL_PATTERN = ".*/paymentsApp/";

    private static final Logger logger = Logger.getLogger(Servlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        logger.trace("URI: " + uri);

        String commandName = uri.replaceAll(URL_PATTERN, "");
        logger.trace("Command name: " + commandName);

        Command command = commands.getOrDefault(commandName, r -> "/index.jsp");
        logger.trace("Command: " + command.getClass().getSimpleName());

        String page = command.execute(req);
        if (page.contains("redirect:")) {
            page = page.replace("redirect:", "");
            logger.trace("Redirect: " + page);
            resp.sendRedirect(page);
        } else {
            logger.trace("Forward: " + page);
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }

    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext().setAttribute("loggedUsers", new HashSet<String>());
        commands.put("changeLocale", new ChangeLocaleCommand());
        commands.put("login", new LoginCommand(new UserService()));
        commands.put("register", new RegisterCommand(new UserService()));
        commands.put("logout", new LogOutCommand());
        commands.put(("getUserPage"), new GetUserPageCommand());
        commands.put(("getAdminPage"), new GetAdminPageCommand());
        commands.put("getAccountList", new GetUserAccountListCommand(new AccountService()));
        commands.put("getAccountListSorted", new GetUserAccountListSortedCommand(new AccountService()));
        commands.put("createUserAccount", new CreateUserAccountCommand(new AccountService()));
        commands.put("getPaymentsPage", new GetPaymentsPageCommand());
        commands.put("getPreparedPayments", new GetPreparedPaymentsCommand(new PaymentsService()));
        commands.put("getExecutedPayments", new GetExecutedPaymentsCommand(new PaymentsService()));
        commands.put("executePayment", new ExcecutePaymentCommand(new PaymentsService()));
        commands.put("getCreatePaymentPage", new GetCreatePaymentPageCommand());
        commands.put("createPayment", new CreatePaymentCommand(new PaymentsService()));
        commands.put("getUserList",new GetUserListCommand(new UserService()));
        commands.put("updateUserStatus", new UpdateUserStatusCommand(new UserService()));
        commands.put("getAddFundsPage", new GetAddFundsPageCommand());
        commands.put("addFunds", new AddFundsCommand(new AccountService()));
        commands.put("blockAccount", new BlockAccountCommand(new AccountService()));
        commands.put("getBlockedAccountList", new GetBlockedAccountList(new AccountService()));
        commands.put("unblockAccount", new UnblockAccountCommand(new AccountService()));
    }
}
