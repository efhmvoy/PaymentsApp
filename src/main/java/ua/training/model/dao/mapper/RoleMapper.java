package ua.training.model.dao.mapper;

import ua.training.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RoleMapper implements ObjectMapper<Role>{
    @Override
    public Role extractFromResultSet(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getLong("role_id"));
        role.setName(rs.getString("role_name"));
        return role;
    }

    @Override
    public Role makeUnique(Map<Long, Role> cache, Role role) {
        cache.putIfAbsent(role.getId(), role);
        return cache.get(role.getId());
    }
}
