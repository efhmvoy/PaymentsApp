package ua.training.model.dao.impl;

import ua.training.controller.command.common.LoginCommand;
import ua.training.model.dao.UserDAO;
import ua.training.model.dao.mapper.RoleMapper;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.dao.mapper.UserStatusMapper;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import org.apache.log4j.Logger;
import ua.training.model.entity.UserStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private final String SELECT_USERS = "SELECT * FROM USERS;";
    private final String SELECT_USER_BY_ID = "SELECT u.*, \n" +
            "r.id as role_id, r.name as role_name,\n" +
            "s.id as user_status_id, s.name as status_name \n" +
            "FROM users u JOIN roles r ON u.role_id = r.id \n" +
            "JOIN user_statuses s ON u.user_status_id = s.id \n" +
            "where u.id = ?;";

    private final String SELECT_USER_BY_LOGIN = "SELECT u.*, \n" +
            "r.id as role_id, r.name as role_name,\n" +
            "s.id as user_status_id, s.name as status_name \n" +
            "FROM users u JOIN roles r ON u.role_id = r.id \n" +
            "JOIN user_statuses s ON u.user_status_id = s.id \n" +
            "where u.login = ?;";

    private final String SELECT_ALL_USERS = "SELECT u.*, \n" +
            "r.id as role_id, r.name as role_name,\n" +
            "s.id as user_status_id, s.name as status_name \n" +
            "FROM users u JOIN roles r ON u.role_id = r.id \n" +
            "JOIN user_statuses s ON u.user_status_id = s.id \n" +
            "where u.role_id = ?;";

    private final String INSERT_USER = "INSERT INTO USERS VALUES(DEFAULT, ?, ?, ?, ?, DEFAULT, DEFAULT) RETURNING *;";

    private final String UPDATE_USER = "UPDATE USERS SET user_status_id = ? WHERE id = ? RETURNING *;;";

    private final String DELETE_USER = "DELETE FROM USERS WHERE id = ?;";

    private final String DELETE_USER_BY_LOGIN = "DELETE FROM USERS WHERE login = ?;";

    private Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User create(User user) {
        User userFromDb = null;
        UserMapper userMapper = new UserMapper();
        try(PreparedStatement ps = connection.prepareStatement(INSERT_USER)){
            ps.setString(1,user.getFirstName());
            ps.setString(2,user.getLastName());
            ps.setString(3,user.getLogin());
            ps.setString(4,user.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                userFromDb = userMapper.extractFromResultSet(rs);
            }
        }
        catch (SQLException ex){
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
        return userFromDb;
    }

    @Override
    public User findById(Long id) {
        User user =null;
        try(PreparedStatement ps = connection.prepareStatement((SELECT_USER_BY_ID))){
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();
            RoleMapper roleMapper = new RoleMapper();
            UserStatusMapper userStatusMapper = new UserStatusMapper();
            if(rs.next()){
                user = userMapper.extractFromResultSet(rs);
                Role role = roleMapper.extractFromResultSet(rs);
                UserStatus userStatus = userStatusMapper.extractFromResultSet(rs);
                user.setRole(role);
                user.setUserStatus(userStatus);
            }
        } catch (SQLException ex){
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        User user = null;
        try(PreparedStatement ps = connection.prepareStatement(SELECT_ALL_USERS)){
            ps.setLong(1,2L);
            UserMapper userMapper = new UserMapper();
            RoleMapper roleMapper = new RoleMapper();
            UserStatusMapper userStatusMapper = new UserStatusMapper();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user = userMapper.extractFromResultSet(rs);
                Role role = roleMapper.extractFromResultSet(rs);
                UserStatus userStatus = userStatusMapper.extractFromResultSet(rs);
                user.setRole(role);
                user.setUserStatus(userStatus);
                userList.add(user);
            }
        } catch (SQLException ex){
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
        return userList;
    }

    @Override
    public User update(User user) {
        User userFromDb=null;
        UserMapper userMapper = new UserMapper();
        try(PreparedStatement ps = connection.prepareStatement(UPDATE_USER)){
            ps.setLong(1, user.getUserStatus().getId());
            ps.setLong(2,user.getId());
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                userFromDb = userMapper.extractFromResultSet(rs);
            }
        } catch (SQLException ex){
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
        return userFromDb;
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement ps = connection.prepareStatement(DELETE_USER)){
            ps.setLong(1,id);
            ps.execute();
        } catch (SQLException ex){
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
    }

    @Override
    public void deleteByLogin(String login) {
        try(PreparedStatement ps = connection.prepareStatement(DELETE_USER_BY_LOGIN)){
            ps.setString(1,login);
            ps.execute();
        } catch (SQLException ex){
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        User user = null;
        try(PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_LOGIN)){
            ps.setString(1,login);
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();
            RoleMapper roleMapper = new RoleMapper();
            UserStatusMapper userStatusMapper = new UserStatusMapper();
            if(rs.next()){
               user = userMapper.extractFromResultSet(rs);
               Role role = roleMapper.extractFromResultSet(rs);
               UserStatus userStatus = userStatusMapper.extractFromResultSet(rs);
               user.setRole(role);
               user.setUserStatus(userStatus);
            }
        } catch (SQLException ex){
            DBCPDataSource.rollbackAndClose(connection);
            throw new RuntimeException(ex);
        }
        DBCPDataSource.commitAndClose(connection);
        return Optional.ofNullable(user);
    }


}
