package ua.training.controller.command.common;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterCommand implements Command {

    private static final Logger logger = Logger.getLogger(RegisterCommand.class);

    private static final String ERROR_TAG = "Error tag: ";

    private static final String ERROR_MESSAGE = "errorMessage";

    UserService userService;

    public RegisterCommand(UserService userService){

        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request)  {
        logger.debug("Command starts");
        request.getSession().setAttribute("path","register");
        ResourceBundle rb = CommandUtility.setResourceBundle(request);

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");

        if(login.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()){
            request.setAttribute(ERROR_MESSAGE, rb.getString("fill.fields"));
            logger.trace(ERROR_TAG + rb.getString("fill.fields"));
            return "/register.jsp";
        }

        try {
            userService.createUser(login, CommandUtility.getHash(password),firstName,lastName);
        } catch (RuntimeException ex){
            logger.error(ex.getMessage());
            request.setAttribute(ERROR_MESSAGE, rb.getString("login.exists"));
            return "/register.jsp";
        }

        request.setAttribute("successMessage", rb.getString("login.register.success"));
        return "/login.jsp";
    }
}
