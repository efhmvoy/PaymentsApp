package ua.training.controller.command.user;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandUtility;
import ua.training.model.entity.Payment;
import ua.training.model.entity.PaymentPage;
import ua.training.model.service.PaymentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ResourceBundle;

public class ExcecutePaymentCommand implements Command {
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final Logger logger = Logger.getLogger(GetPreparedPaymentsCommand.class);

    private PaymentsService paymentsService;

    public ExcecutePaymentCommand(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {

        ResourceBundle rb = CommandUtility.setResourceBundle(request);
        String paymentNumber = request.getParameter("paymentNumber");

        try{
            Payment payment = paymentsService.executePaymentByNumber(Long.parseLong(paymentNumber));
        } catch (RuntimeException ex){
            request.setAttribute(ERROR_MESSAGE, rb.getString("error.server"));
            logger.error(ex.getMessage());
        }

        String page = request.getParameter("page");
        String accountNumber = (String) request.getSession().getAttribute("accountNumber");
        if (page == null) {
            page = "0";
        }

        String pageSize = request.getParameter("size");
        if (pageSize == null) {
            pageSize = "5";
        }
        String sortBy =request.getParameter("sortBy");
        if(sortBy==null){
            sortBy="number";
        }
        PaymentPage paymentPage = paymentsService.getPreparedPaymentsPaginated(Long.parseLong(accountNumber), Integer.parseInt(page), Integer.parseInt(pageSize), sortBy);
        return "/WEB-INF/views/user/prepared_payments.jsp";
    }
}

