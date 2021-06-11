package ua.training.model.dao.mapper;

import ua.training.model.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

public class PaymentMapper implements ObjectMapper<Payment>{
    @Override
    public Payment extractFromResultSet(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setId(rs.getLong("id"));
        payment.setNumber(rs.getLong("number"));
        payment.setCreateTime(rs.getObject("create_time", LocalDateTime.class));
        payment.setExecuteTime(rs.getObject("execute_time",LocalDateTime.class));
        payment.setAmount(rs.getBigDecimal("amount"));
        payment.setRecieverAccount(rs.getInt("reciever_account"));
        return payment;
    }

    @Override
    public Payment makeUnique(Map<Long, Payment> cache, Payment payment) {
        cache.putIfAbsent(payment.getId(), payment);
        return cache.get(payment.getId());
    }
}
