package spring.Task_3.service;



import spring.Task_3.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findOne(int id);

    void save(User user);

    User update(User updatedUser);

    void delete(int id);



}
