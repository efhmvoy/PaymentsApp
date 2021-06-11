package ua.training.model.dao.impl;

import org.apache.log4j.Logger;
import ua.training.controller.command.common.LoginCommand;
import ua.training.model.dao.CreditCardDAO;
import ua.training.model.entity.CreditCard;

import java.sql.Connection;
import java.util.List;

public class CreditCardDAOImpl implements CreditCardDAO {

    private final Logger logger = Logger.getLogger(LoginCommand.class);

    private Connection connection;

    public CreditCardDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public CreditCard create(CreditCard entity) {
        return null;
    }

    @Override
    public CreditCard findById(Long id) {
        return null;
    }

    @Override
    public List<CreditCard> findAll() {
        return null;
    }

    @Override
    public CreditCard update(CreditCard entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
