package appl.dao;

import appl.models.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    User getUser(Long id);
    void updateUser(Long id, User updatedUser);
    void deleteUser(Long id);
    List<User> getAllUsers();
}
