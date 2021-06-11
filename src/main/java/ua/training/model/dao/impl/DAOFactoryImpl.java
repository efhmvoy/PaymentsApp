package ua.training.model.dao.impl;

import ua.training.model.dao.*;
import java.sql.Connection;
import java.sql.SQLException;

public class DAOFactoryImpl extends DaoFactory {

    private DBCPDataSource dbcpDataSource = DBCPDataSource.getInstance();

    @Override
    public UserDAO createUserDao() {
        return new UserDAOImpl(getConnection());
    }

    @Override
    public AccountDAO createAccountDao() {
        return new AccountDAOImpl(getConnection());
    }

    @Override
    public CreditCardDAO createCreditCardDao() {
        return new CreditCardDAOImpl(getConnection());
    }

    @Override
    public PaymentDAO createPaymentDao() {

        return new PaymentDAOImpl(getConnection());
    }

    private Connection getConnection() {
        try {
            return dbcpDataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
