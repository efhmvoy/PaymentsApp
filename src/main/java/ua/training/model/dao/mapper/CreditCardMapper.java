package ua.training.model.dao.mapper;

import ua.training.model.entity.CreditCard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

public class CreditCardMapper implements ObjectMapper<CreditCard>{
    @Override
    public CreditCard extractFromResultSet(ResultSet rs) throws SQLException {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(rs.getLong("id"));
        creditCard.setNumber(rs.getInt("number"));
        creditCard.setCVV(rs.getInt("cvv"));
        creditCard.setExpireDate(rs.getObject("expire_date", LocalDate.class));
        return creditCard;
    }

    @Override
    public CreditCard makeUnique(Map<Long, CreditCard> cache, CreditCard creditCard) {
        cache.putIfAbsent(creditCard.getId(), creditCard);
        return cache.get(creditCard.getId());
    }
}
