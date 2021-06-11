package ua.training.model.dao.mapper;

import ua.training.model.entity.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AccountMapper implements ObjectMapper<Account>{
    @Override
    public Account extractFromResultSet(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getLong("id"));
        account.setNumber(rs.getLong("number"));
        account.setName(rs.getString("name"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }

    @Override
    public Account makeUnique(Map<Long, Account> cache, Account account) {
        cache.putIfAbsent(account.getId(), account);
        return cache.get(account.getId());
    }
}
