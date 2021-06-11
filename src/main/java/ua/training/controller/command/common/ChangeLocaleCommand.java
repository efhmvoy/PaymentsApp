package ua.training.controller.command.common;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChangeLocaleCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        logger.debug("Commands starts");
        String lang = request.getParameter("lang");
        Config.set(request.getSession(), "javax.servlet.jsp.jstl.fmt.locale", lang);
        logger.trace("Set locale " + lang + " for " + request.getSession().getAttribute("login"));
        logger.debug("Commands finished");
        request.getSession().setAttribute("sessionLocale", lang);
        String path = (String) request.getSession().getAttribute("path");
        if(path==null){
            path="redirect:/";
        }
        return path;
    }
}
