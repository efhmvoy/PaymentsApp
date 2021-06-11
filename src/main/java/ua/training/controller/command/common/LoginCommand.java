package ua.training.controller.command.common;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginCommand implements Command {

    private static final Logger logger = Logger.getLogger(LoginCommand.class);
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String ERROR_TAG = "Error tag: ";
    private final UserService userService;

    public LoginCommand(UserService userService){
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {

        logger.debug("Command starts");
        ResourceBundle rb = CommandUtility.setResourceBundle(request);

        String login = (request.getParameter("login"));
        String password = request.getParameter("password");

        logger.trace("User with login " + login + " is logging");
        if(login.isEmpty() ||  password.isEmpty()){
            request.setAttribute(ERROR_MESSAGE, rb.getString("fill.fields"));
            logger.trace(ERROR_TAG + rb.getString("fill.fields"));
            return "/login.jsp";
        }

        Optional<User> user = userService.getUserByLogin(login);

        if(!user.isPresent() || !user.get().getPassword().equals(CommandUtility.getHash(password))){
            request.setAttribute(ERROR_MESSAGE, rb.getString("incorrect.login.or.password"));
            logger.trace(ERROR_TAG + rb.getString("incorrect.login.or.password"));
            return "/login.jsp";
        }

        if(user.get().getUserStatus().getName().equals("blocked")) {
            request.setAttribute(ERROR_MESSAGE, rb.getString("user.blocked"));
            logger.trace(ERROR_TAG + rb.getString("user.blocked"));
            return "/login.jsp";
        }

        if (CommandUtility.checkIsUserLogged(request, login)) {
            request.setAttribute(ERROR_MESSAGE, rb.getString("unfinished.session"));
            logger.trace(ERROR_TAG + rb.getString("unfinished.session"));
            return "/login.jsp";
        }

        CommandUtility.setUserRole(request, login, user.get().getRole().getName());
        logger.trace("User with " + login + " is logged in");
        logger.debug("Commands finished");
        return "redirect:/";

    }

    public static String redirect(User user)  {

        switch (user.getRole().getName()) {
            case "ADMIN":
                return "getAdminPage";
            case "USER":
                return "getAUserPage";
            default:
                return "/error.jsp";
        }
    }

}
