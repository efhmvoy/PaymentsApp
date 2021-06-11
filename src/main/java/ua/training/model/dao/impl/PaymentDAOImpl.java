package ua.training.model.dao.impl;

import org.apache.log4j.Logger;
import ua.training.controller.command.common.LoginCommand;
import ua.training.model.dao.PaymentDAO;
import ua.training.model.dao.mapper.PaymentMapper;
import ua.training.model.entity.Payment;
import ua.training.model.entity.PaymentPage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PaymentDAOImpl implements PaymentDAO {

    private final String INSERT_PAYMENT = "INSERT INTO payments VALUES(DEFAULT, DEFAULT, DEFAULT, DEFAULT, \n " +
            "(SELECT a.id  FROM accounts a where a.number = ?),?,?, DEFAULT) RETURNING *;";

    private final String UPDATE_PAYMENT ="UPDATE PAYMENTS SET payment_status_id = 2, \n " +
            "execute_time = current_timestamp  WHERE number =? RETURNING *;";

    private final String UPDATE_ACCOUNT ="UPDATE accounts set balance=balance-? where id=? RETURNING *;";

    private final String SELECT_ALL_PAYMENTS_PAGINATED_ORDER_BY_CREATE_TIME = "SELECT * ,count(*) over() \n " +
            "FROM PAYMENTS WHERE payment_status_id = ? AND account_id =(SELECT id FROM accounts \n" +
            "WHERE accounts.number = ?) ORDER BY create_time OFFSET ? LIMIT ?";

    private final String SELECT_ALL_PAYMENTS_PAGINATED_ORDER_BY_CREATE_TIME_DESC =" SELECT * ,count(*) over() \n" +
            "FROM PAYMENTS WHERE payment_status_id = ? AND account_id =(SELECT id FROM accounts \n" +
            "WHERE accounts.number = ?) ORDER BY create_time DESC OFFSET ? LIMIT ?";

    private final String SELECT_ALL_PAYMENTS_PAGINATED_ORDER_BY_NUMBER = "SELECT * ,count(*) over() \n" +
            " FROM PAYMENTS WHERE payment_status_id = ? AND account_id =(SELECT id FROM accounts \n" +
            "WHERE accounts.number = ?) ORDER BY id  OFFSET ? LIMIT ?";

    private Connection connection;

    public PaymentDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public PaymentPage findAllPreparedByAccountNumberPaginated(Long number , Integer page, Integer pageSize, String sortBy) {
        PaymentPage paymentPage = new PaymentPage();
        List<Payment> payments = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(getSorting(sortBy))){
             ps.setLong(1,1L);
            ps.setLong(2,number);
            ps.setInt(3,page*pageSize);
            ps.setInt(4,pageSize);

            ResultSet rs = ps.executeQuery();
            PaymentMapper paymentMapper = new PaymentMapper();
            while (rs.next()){
                Payment payment = paymentMapper.extractFromResultSet(rs);
                payments.add(payment);
                paymentPage.setTotal(rs.getInt("count"));
            }
            paymentPage.setPaymentList(payments);
            paymentPage.setPage(page);
            paymentPage.setPageSize(pageSize);
        } catch (SQLException ex){
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
        return paymentPage;
    }

    @Override
    public PaymentPage findAllExecutedByAccountNumberPaginated(Long number , Integer page, Integer pageSize, String sortBy) {
        PaymentPage paymentPage = new PaymentPage();
        List<Payment> payments = new ArrayList<>();

        try(PreparedStatement ps = connection.prepareStatement(getSorting(sortBy))){
            ps.setLong(1,2L);
            ps.setLong(2,number);
            ps.setInt(3,page*pageSize);
            ps.setInt(4,pageSize);

            ResultSet rs = ps.executeQuery();
            PaymentMapper paymentMapper = new PaymentMapper();
            while (rs.next()){
                Payment payment = paymentMapper.extractFromResultSet(rs);
                payments.add(payment);
                paymentPage.setTotal(rs.getInt("count"));
            }
            paymentPage.setPaymentList(payments);
            paymentPage.setPage(page);
            paymentPage.setPageSize(pageSize);
        } catch (SQLException ex){
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
        return paymentPage;
    }






    @Override
    public Payment create(Payment payment) {
        Payment paymentFromDb =null;
        PaymentMapper paymentMapper = new PaymentMapper();
        try(PreparedStatement ps = connection.prepareStatement(INSERT_PAYMENT)){
            ps.setLong(1,payment.getAccount().getNumber());
            ps.setBigDecimal(2,payment.getAmount());
            ps.setInt(3,payment.getRecieverAccount());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                paymentFromDb = paymentMapper.extractFromResultSet(rs);
            }
        }catch (SQLException ex){
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
        return paymentFromDb;
    }

    @Override
    public Payment executeByNumber(Long number) {
        Payment paymentFromDB = null;
        Long accountId = null;
        PaymentMapper paymentMapper = new PaymentMapper();
        try(PreparedStatement ps = connection.prepareStatement(UPDATE_PAYMENT);
            PreparedStatement ps1 =connection.prepareStatement(UPDATE_ACCOUNT)){
            ps.setLong(1,number);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                paymentFromDB = paymentMapper.extractFromResultSet(rs);
                accountId = rs.getLong("account_id");
            }
            ps1.setBigDecimal(1,paymentFromDB.getAmount());
            ps1.setLong(2,accountId);

            ResultSet rs1 =ps1.executeQuery();
        } catch (SQLException ex){
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
        return paymentFromDB;
    }

    @Override
    public Payment findById(Long id) {
        return null;
    }

    @Override
    public List<Payment> findAll() {
        return null;
    }

    @Override
    public Payment update(Payment entity) {

        return null;
    }

    @Override
    public void delete(Long id) {

    }

    String getSorting(String sortBy) {
        String statement = SELECT_ALL_PAYMENTS_PAGINATED_ORDER_BY_NUMBER;
        switch (sortBy) {
            case "createTime":
                statement = SELECT_ALL_PAYMENTS_PAGINATED_ORDER_BY_CREATE_TIME;
                break;
            case "createTimeDesc":
                statement = SELECT_ALL_PAYMENTS_PAGINATED_ORDER_BY_CREATE_TIME_DESC;
                break;
            case "number":
                statement = SELECT_ALL_PAYMENTS_PAGINATED_ORDER_BY_NUMBER;
                break;

        }
        return statement;
    }
}
