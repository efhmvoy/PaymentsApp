package ua.training.model.dao.impl;

import ua.training.model.dao.AccountDAO;
import ua.training.model.dao.mapper.AccountMapper;
import ua.training.model.dao.mapper.AccountStatusMapper;
import ua.training.model.dao.mapper.CreditCardMapper;
import ua.training.model.entity.Account;
import ua.training.model.entity.AccountStatus;
import ua.training.model.entity.CreditCard;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {

    private final String INSERT_ACCOUNT = "INSERT INTO ACCOUNTS VALUES(DEFAULT, DEFAULT, DEFAULT, ?, ?, \n" +
            "(SELECT u.id FROM users u WHERE u.login=?), DEFAULT) RETURNING *;";

    private final String INSERT_CREDIT_CARD = "INSERT INTO CREDIT_CARDS VALUES(DEFAULT, ?, DEFAULT, DEFAULT) RETURNING *; ";

    private final String SELECT_ACCOUNTS_BY_USER_LOGIN = "SELECT a.*, s.id as account_status_id, s.name as status_name \n" +
            "FROM accounts a  JOIN account_statuses s ON a.account_status_id = s.id WHERE \n " +
            "(SELECT u.id  FROM users u where u.login = ?) = a.user_id";

    private final String SELECT_BLOCKED_ACCOUNTS = "SELECT a.*, s.id as account_status_id, s.name as status_name \n" +
            "FROM accounts a JOIN account_statuses s ON a.account_status_id = s.id WHERE a.account_status_id = ?";

    private final String SELECT_ACCOUNTS_BY_USER_LOGIN_ORDER_BY_NUMBER = "SELECT a.*, s.id as account_status_id, \n" +
            "s.name as status_name FROM accounts a  JOIN account_statuses s ON a.account_status_id = s.id \n" +
            "WHERE (SELECT u.id  FROM users u where u.login = ?) = a.user_id ORDER BY number;";

    private final String SELECT_ACCOUNTS_BY_USER_LOGIN_ORDER_BY_NAME = "SELECT a.*, s.id as account_status_id,\n" +
            " s.name as status_name FROM accounts a  JOIN account_statuses s ON a.account_status_id = s.id WHERE \n" +
            "(SELECT u.id  FROM users u where u.login = ?) = a.user_id ORDER BY name;";

    private final String SELECT_ACCOUNTS_BY_USER_LOGIN_ORDER_BY_BALANCE = "SELECT a.*, s.id as account_status_id, \n" +
            "s.name as status_name FROM accounts a  JOIN account_statuses s ON a.account_status_id = s.id \n" +
            " WHERE (SELECT u.id  FROM users u where u.login = ?) = a.user_id ORDER BY balance;";

    private final String SELECT_ACCOUNT_BY_NUMBER = "SELECT a.*, s.id as account_status_id, s.name as status_name \n" +
            " FROM accounts a  JOIN account_statuses s ON a.account_status_id = s.id WHERE number = ? ";

    private final String UPDATE_ACCOUNT = "UPDATE ACCOUNTS SET account_status_id = ? WHERE number = ? RETURNING *;";

    private final String UPDATE_ACCOUNT_BALANCE = "UPDATE ACCOUNTS SET balance = balance + ? WHERE number = ? RETURNING *;";

    private Connection connection;

    public AccountDAOImpl(Connection connection) {

        this.connection = connection;
    }

    private Integer generateCVV() {
        int max= 999;
        int min =100;
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(max-min)+min;
    }

    @Override
    public Account create(Account account) {
        Account accountFromDb = null;
        AccountMapper accountMapper = new AccountMapper();
        CreditCard creditCard = null;
        try (PreparedStatement ps = connection.prepareStatement(INSERT_CREDIT_CARD);
             PreparedStatement ps1 = connection.prepareStatement(INSERT_ACCOUNT)) {
            ps.setInt(1, generateCVV());
            ResultSet rs = ps.executeQuery();
            CreditCardMapper creditCardMapper = new CreditCardMapper();
            if (rs.next()) {
                creditCard = creditCardMapper.extractFromResultSet(rs);
            }
            ps1.setString(1, account.getName());
            ps1.setLong(2, creditCard.getId());
            ps1.setString(3, account.getUser().getLogin());
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                accountFromDb = accountMapper.extractFromResultSet(rs1);
            }
        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
        return accountFromDb;
    }

    @Override
    public Account findByNumber(Long number) {
        Account accountFromDb = null;
        AccountStatus accountStatus = null;
        AccountMapper accountMapper = new AccountMapper();
        AccountStatusMapper accountStatusMapper = new AccountStatusMapper();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ACCOUNT_BY_NUMBER)) {
            ps.setLong(1, number);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                accountFromDb = accountMapper.extractFromResultSet(rs);
                accountStatus = accountStatusMapper.extractFromResultSet(rs);
                accountFromDb.setAccountStatus(accountStatus);
            }
        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
        return accountFromDb;
    }

    @Override
    public Account findById(Long id) {
        return null;
    }

    @Override
    public List<Account> findAll() {

        return null;
    }

    @Override
    public List<Account> findAllBlocked() {
        List<Account> accountList = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(SELECT_BLOCKED_ACCOUNTS)) {
            ps.setLong(1, 2L);
            ResultSet rs = ps.executeQuery();
            AccountMapper accountMapper = new AccountMapper();
            AccountStatusMapper accountStatusMapper = new AccountStatusMapper();
            while (rs.next()) {
                Account account = accountMapper.extractFromResultSet(rs);
                AccountStatus accountStatus = accountStatusMapper.extractFromResultSet(rs);
                account.setAccountStatus(accountStatus);
                accountList.add(account);
            }
         } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
             DBCPDataSource.commitAndClose(connection);
            return accountList;
        }

    @Override
    public Account update(Account account) {
        Account accountFromDb = null;
        AccountMapper accountMapper = new AccountMapper();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_ACCOUNT)) {
            ps.setLong(1, account.getAccountStatus().getId());
            ps.setLong(2, account.getNumber());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                accountFromDb = accountMapper.extractFromResultSet(rs);
            }
        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }

        DBCPDataSource.commitAndClose(connection);
        return accountFromDb;
    }
    @Override
    public Account addFunds(Long accountNumber, BigDecimal amount) {
        Account accountFromDb = null;
        AccountMapper accountMapper = new AccountMapper();
        try(PreparedStatement ps = connection.prepareStatement(UPDATE_ACCOUNT_BALANCE)){
            ps.setBigDecimal(1,amount);
            ps.setLong(2,accountNumber);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                accountFromDb =accountMapper.extractFromResultSet(rs);
            }
        }catch (SQLException ex){
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
        return accountFromDb;
    }


    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Account> findAllByLogin(String login) {
        List<Account> accountList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ACCOUNTS_BY_USER_LOGIN)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            AccountMapper accountMapper = new AccountMapper();
            AccountStatusMapper accountStatusMapper = new AccountStatusMapper();
            while (rs.next()) {
                Account account = accountMapper.extractFromResultSet(rs);
                AccountStatus accountStatus = accountStatusMapper.extractFromResultSet(rs);
                account.setAccountStatus(accountStatus);
                accountList.add(account);
            }
        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
        return accountList;
    }


    @Override
    public List<Account> findAllByLoginSorted(String login, String sortBy) {

        List<Account> accountList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(getSorting(sortBy))) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            AccountMapper accountMapper = new AccountMapper();
            AccountStatusMapper accountStatusMapper = new AccountStatusMapper();
            while (rs.next()) {
                Account account = accountMapper.extractFromResultSet(rs);
                AccountStatus accountStatus = accountStatusMapper.extractFromResultSet(rs);
                account.setAccountStatus(accountStatus);
                accountList.add(account);
            }
        } catch (SQLException ex) {
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
        return accountList;
    }

    String getSorting(String sortBy) {
        String statement;
        switch (sortBy) {
            case "name":
                statement = SELECT_ACCOUNTS_BY_USER_LOGIN_ORDER_BY_NAME;
                break;
            case "balance":
                statement = SELECT_ACCOUNTS_BY_USER_LOGIN_ORDER_BY_BALANCE;
                break;
            case "number":
                statement = SELECT_ACCOUNTS_BY_USER_LOGIN_ORDER_BY_NUMBER;
                break;
            default:
                statement = SELECT_ACCOUNTS_BY_USER_LOGIN;
            }
        return statement;
        }
}
