package ua.training.model.service;

import org.apache.log4j.Logger;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDAO;
import ua.training.model.entity.User;
import ua.training.model.entity.UserStatus;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final Logger logger = Logger.getLogger(AccountService.class);
    DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<User> getUserByLogin(String login) {
        UserDAO userDAO = daoFactory.createUserDao();
        return userDAO.findByLogin(login);
    }

    public User createUser(String login, String password, String firstname, String lastname){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        UserDAO userDAO = daoFactory.createUserDao();
        User userFromDb = userDAO.create(user);
        logger.info("User has been created: " + userFromDb);
        return  userFromDb;
    }

    public User updateUser(Long id, Long userStatusId){
        UserStatus userStatus = new UserStatus();
        userStatus.setId(userStatusId);
        User user = new User.Builder()
                .withId(id)
                .withUserStatus(userStatus)
                .build();
        UserDAO userDAO = daoFactory.createUserDao();
        return userDAO.update(user);
    }

    public List<User> getUserList(){
        UserDAO userDAO = daoFactory.createUserDao();
        return  userDAO.findAll();
    }
}
