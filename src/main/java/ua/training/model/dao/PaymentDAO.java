package ua.training.model.dao;

import ua.training.model.entity.Payment;
import ua.training.model.entity.PaymentPage;

import java.util.List;

public interface PaymentDAO extends GenericDAO<Payment>{

    Payment executeByNumber(Long number);

    PaymentPage findAllPreparedByAccountNumberPaginated(Long number, Integer page, Integer pageSize, String sortBy);

    PaymentPage findAllExecutedByAccountNumberPaginated(Long number, Integer page, Integer pageSize, String sortBy);

}
