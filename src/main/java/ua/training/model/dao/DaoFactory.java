package ua.training.model.dao;

import ua.training.model.dao.impl.DAOFactoryImpl;

public abstract class DaoFactory {

    private static volatile DaoFactory daoFactory;

    public abstract UserDAO createUserDao();

    public abstract AccountDAO createAccountDao();

    public abstract CreditCardDAO createCreditCardDao();

    public abstract PaymentDAO createPaymentDao();

    public static  DaoFactory getInstance(){

        if (daoFactory==null){
            synchronized (DaoFactory.class){
                if (daoFactory==null){
                    daoFactory = new DAOFactoryImpl();
                    }
                }
            }
        return daoFactory;
        }

    }

