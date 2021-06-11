package ua.training.controller.command.admin;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GetUserListCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetUserListCommand.class);

    private final UserService userService;

    public GetUserListCommand(UserService userService) {
        this.userService = userService;
    }

    private static final String ERROR_MESSAGE = "errorMessage";

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {

        request.getSession().setAttribute("path","/WEB-INF/views/admin/user_list.jsp");
        ResourceBundle rb = CommandUtility.setResourceBundle(request);

        List<User> userList = new ArrayList<>();

        try{
            userList = userService.getUserList();
        } catch (RuntimeException ex){
            request.setAttribute(ERROR_MESSAGE, rb.getString("error.server"));
            logger.error(ex.getMessage());
        }
        request.getSession().setAttribute("userList", userList);

        return "/WEB-INF/views/admin/user_list.jsp";
    }
}
