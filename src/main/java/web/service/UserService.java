package web.service;

import org.springframework.stereotype.Service;
import web.model.User;

import java.util.List;

@Service
public interface UserService {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    List<User> getListUsers();
    User showUser(int id);
}
