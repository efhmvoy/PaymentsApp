package ua.training.controller.command.user;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.entity.PaymentPage;
import ua.training.model.service.PaymentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class GetPreparedPaymentsCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetPreparedPaymentsCommand.class);

    private PaymentsService paymentsService;

    public GetPreparedPaymentsCommand(PaymentsService paymentsService) {
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
        request.getSession().setAttribute("path","/WEB-INF/views/user/prepared_payments.jsp");
        PaymentPage paymentPage = paymentsService.getPreparedPaymentsPaginated(Long.parseLong(accountNumber), Integer.parseInt(page), Integer.parseInt(pageSize), sortBy);
        request.getSession().setAttribute("preparedPaymentsPage", paymentPage);
        return "/WEB-INF/views/user/prepared_payments.jsp";
    }
}
