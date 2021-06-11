package ua.training.model.dao.mapper;

import ua.training.model.entity.AccountStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AccountStatusMapper implements ObjectMapper<AccountStatus>{
    @Override
    public AccountStatus extractFromResultSet(ResultSet rs) throws SQLException {
        AccountStatus accountStatus = new AccountStatus();
        accountStatus.setId(rs.getLong("account_status_id"));
        accountStatus.setName(rs.getString("status_name"));
        return accountStatus;
    }

    @Override
    public AccountStatus makeUnique(Map<Long, AccountStatus> cache, AccountStatus accountStatus) {
        cache.putIfAbsent(accountStatus.getId(), accountStatus);
        return cache.get(accountStatus.getId());
    }
}
