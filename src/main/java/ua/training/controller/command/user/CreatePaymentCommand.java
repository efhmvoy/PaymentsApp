package ua.training.controller.command.user;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.service.PaymentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ResourceBundle;

public class CreatePaymentCommand implements Command {

    private static final Logger logger = Logger.getLogger(CreatePaymentCommand.class);

    private static final String ERROR_MESSAGE = "errorMessage";

    private PaymentsService paymentsService;

    public CreatePaymentCommand(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }


    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        ResourceBundle rb = CommandUtility.setResourceBundle(request);
        String accountNumber = (String) request.getSession().getAttribute("accountNumber");
        String amount = request.getParameter("amount");
        String receiverAccount = request.getParameter("receiverAccount");

        try{
            new BigDecimal(amount);
            Integer.parseInt(receiverAccount);
        } catch (NumberFormatException ex){
            request.setAttribute(ERROR_MESSAGE, rb.getString("incorrect.input"));
            return "/WEB-INF/views/user/create_payment.jsp";
        }

        try {
           paymentsService.createPayment(Long.parseLong(accountNumber),new BigDecimal(amount),Integer.parseInt(receiverAccount));

        } catch (RuntimeException ex){
            logger.error(ex.getMessage());
            request.setAttribute(ERROR_MESSAGE, rb.getString("error.server"));
            return "/WEB-INF/views/user/payments.jsp";
        }

        return "/WEB-INF/views/user/payments.jsp";
    }
}
