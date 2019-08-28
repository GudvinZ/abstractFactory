package dao;

import model.User;

import java.util.List;

public interface DAO {
     void addUser(User user);

     void deleteUserById(Long id);

     void deleteAllUsers();

    void updateUser(User user);

    boolean validateUser(String login, String password);

    List<User> getAllUsers();
}
