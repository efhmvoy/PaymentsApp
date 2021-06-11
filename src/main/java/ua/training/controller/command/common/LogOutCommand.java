package ua.training.controller.command.common;

import ua.training.controller.command.Command;
import org.apache.log4j.Logger;
import ua.training.controller.command.CommandUtility;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {

    private static final Logger logger = Logger.getLogger(LogOutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("Command starts");
        String login = (String) request.getSession().getAttribute("login");

        CommandUtility.deleteUserFromContext(request, login);
        CommandUtility.deleteUserFromSession(request);

        logger.trace("User with " + login + " is logged out");

        logger.debug("Commands finished");
        request.getSession().setAttribute("path","redirect:/");
        return "redirect:/";
    }
}
