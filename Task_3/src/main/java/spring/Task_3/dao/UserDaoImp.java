package spring.Task_3.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import spring.Task_3.model.User;

import java.util.List;


@Repository
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findAll() {
        List<User> list = entityManager.createQuery("from User", User.class).getResultList();
        return list;
    }

    public User findOne(int id) {
        return entityManager.find(User.class, id);
    }


    public void save(User user) {
        entityManager.persist(user);
    }


    public User update(User updatedUser) {
        return entityManager.merge(updatedUser);
    }


    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
}