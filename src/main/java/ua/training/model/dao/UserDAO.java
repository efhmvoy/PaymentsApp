package ua.training.model.dao;

import ua.training.model.entity.User;


import java.util.Optional;

public interface UserDAO extends GenericDAO<User>{

    Optional<User> findByLogin(String login);

    void deleteByLogin(String login);
}
