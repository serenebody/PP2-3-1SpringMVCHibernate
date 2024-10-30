package web.DAO;

import web.model.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    List<User> getListUsers();
    User showUser(int id);
}
