package ua.training.model.dao.mapper;

import ua.training.model.entity.PaymentStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class PaymentStatusMapper implements ObjectMapper<PaymentStatus>{
    @Override
    public PaymentStatus extractFromResultSet(ResultSet rs) throws SQLException {
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setId(rs.getLong("id"));
        paymentStatus.setName(rs.getString("name"));
        return paymentStatus;
    }

    @Override
    public PaymentStatus makeUnique(Map<Long, PaymentStatus> cache, PaymentStatus paymentStatus) {
        cache.putIfAbsent(paymentStatus.getId(), paymentStatus);
        return cache.get(paymentStatus.getId());
    }
}
