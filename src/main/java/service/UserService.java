package service;

import model.User;
import util.factory.UserDAOFactory;

import java.util.List;

public class UserService {
    private static UserService instance;
    private UserDAOFactory daoFactory;

    public static UserService getInstance() {
        if (instance == null)
            instance = new UserService();
        return instance;
    }

    private UserService() {
        this.daoFactory = UserDAOFactory.createFactoryByProperties();
    }

    public boolean addUser(User user) {
        if (daoFactory.createDAO().validateUser(user.getLogin(), user.getPassword()))
            return false;
        daoFactory.createDAO().addUser(user);
        return true;
    }

    public void deleteUserById(Long id) {
        daoFactory.createDAO().deleteUserById(id);
    }

    public void deleteAllUsers() {
        daoFactory.createDAO().deleteAllUsers();
    }

    public void updateUser(User user) {
        daoFactory.createDAO().updateUser(user);
    }

    public List<User> getAllUsers() {
        return daoFactory.createDAO().getAllUsers();
    }
}
