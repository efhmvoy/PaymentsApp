package ua.training.model.dao.mapper;

import ua.training.model.entity.UserStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserStatusMapper implements ObjectMapper<UserStatus>{
    @Override
    public UserStatus extractFromResultSet(ResultSet rs) throws SQLException {
        UserStatus userStatus = new UserStatus();
        userStatus.setId(rs.getLong("user_status_id"));
        userStatus.setName(rs.getString("status_name"));
        return userStatus;
    }

    @Override
    public UserStatus makeUnique(Map<Long, UserStatus> cache, UserStatus userStatus) {
        cache.putIfAbsent(userStatus.getId(), userStatus);
        return cache.get(userStatus.getId());
    }
}
