package ua.training.controller.command.user;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.entity.PaymentPage;
import ua.training.model.service.PaymentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class GetExecutedPaymentsCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetPreparedPaymentsCommand.class);

    private PaymentsService paymentsService;

    public GetExecutedPaymentsCommand(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {


        logger.debug("Command starts");

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
        PaymentPage paymentPage = paymentsService.getExecutedPaymentsPaginated(Long.parseLong(accountNumber), Integer.parseInt(page), Integer.parseInt(pageSize), sortBy);
        request.getSession().setAttribute("executedPaymentsPage", paymentPage);
        request.getSession().setAttribute("path","/WEB-INF/views/user/executed_payments.jsp");
        return "/WEB-INF/views/user/executed_payments.jsp";
    }
}
