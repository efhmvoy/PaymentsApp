package ua.training.model.dao;

import ua.training.model.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDAO extends  GenericDAO<Account>{

    Account findByNumber(Long number);

    Account addFunds(Long accountNumber, BigDecimal amount);

    List<Account> findAllByLogin(String login);

    List<Account> findAllByLoginSorted(String login, String sortBy);

    List<Account> findAllBlocked();
}
