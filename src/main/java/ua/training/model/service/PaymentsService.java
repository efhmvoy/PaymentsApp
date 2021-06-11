package ua.training.model.service;

import org.apache.log4j.Logger;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.PaymentDAO;
import ua.training.model.entity.Account;
import ua.training.model.entity.Payment;
import ua.training.model.entity.PaymentPage;

import java.math.BigDecimal;

public class PaymentsService {

    private static final Logger logger = Logger.getLogger(PaymentsService.class);

    DaoFactory daoFactory = DaoFactory.getInstance();


    public PaymentPage getPreparedPaymentsPaginated(Long number, Integer page, Integer pageSize, String sortBy) {
        PaymentDAO paymentDAO = daoFactory.createPaymentDao();
        return paymentDAO.findAllPreparedByAccountNumberPaginated(number, page, pageSize, sortBy);
    }

    public PaymentPage getExecutedPaymentsPaginated(Long number, Integer page, Integer pageSize, String sortBy) {
        PaymentDAO paymentDAO = daoFactory.createPaymentDao();
        return paymentDAO.findAllExecutedByAccountNumberPaginated(number, page, pageSize, sortBy);
    }

    public Payment executePaymentByNumber(Long number) {
        PaymentDAO paymentDAO = daoFactory.createPaymentDao();
        return paymentDAO.executeByNumber(number);
    }

    public Payment createPayment(Long accountNumber, BigDecimal amount, Integer receiverAccount) {
        Account account = new Account();
        account.setNumber(accountNumber);
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setRecieverAccount(receiverAccount);
        payment.setAccount(account);

        PaymentDAO paymentDAO = daoFactory.createPaymentDao();
        Payment paymentFromDb = paymentDAO.create(payment);
        logger.info("Payment has been created: " + paymentFromDb);
        return paymentFromDb;
    }

}

