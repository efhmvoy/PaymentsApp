package ua.training.model.service;

import org.apache.log4j.Logger;
import ua.training.model.dao.AccountDAO;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDAO;
import ua.training.model.entity.Account;
import ua.training.model.entity.User;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class AccountService {

    private final Logger logger = Logger.getLogger(AccountService.class);

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Account> getAccountListByUser(String login){
        AccountDAO accountDAO = daoFactory.createAccountDao();
        return accountDAO.findAllByLogin(login);
    }

    public List<Account> getAccountListByUserSorted(String login, String sortBy){

        AccountDAO accountDAO = daoFactory.createAccountDao();
        return accountDAO.findAllByLoginSorted(login, sortBy);
    }

    public Account createAccount(String login, String name){
        UserDAO userDAO = daoFactory.createUserDao();
        Optional<User> user = userDAO.findByLogin(login);
        Account account = new Account();
        account.setUser(user.get());
        account.setName(name);
        AccountDAO accountDAO = daoFactory.createAccountDao();
        Account accountFromDb = accountDAO.create(account);
        logger.info("Account has been created: " + accountFromDb);
        return accountFromDb;
    }

    public  Account addFunds(Long accountNumber, BigDecimal amount){
        AccountDAO accountDAO = daoFactory.createAccountDao();
        return accountDAO.addFunds(accountNumber, amount);
    }

}
