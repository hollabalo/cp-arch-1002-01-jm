package com.gcash.training.sa.service;

import com.gcash.training.sa.dao.UserDao;
import com.gcash.training.sa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author johnmichael.gerona
 * @created 1/3/24
 */
@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.selectAllUsers();
    }

    public User getUser(String id) {
        return userDao.selectUserByUserId(id);
    }

    public int updateUser(String id, User user) {
        if (userDao.selectUserByUserId(id) == null) {
            return -1;
        }

        User data = User.builder()
                .userId(UUID.fromString(id))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .email(user.getEmail())
                .gender(user.getGender())
                .build();
        return userDao.updateUser(data);
    }

    public int removeUser(String id) {
        if (userDao.selectUserByUserId(id) == null) {
            return -1;
        }
        return userDao.deleteUserByUserId(id);
    }

    public int insertUser(User user) {
        User data = User.builder()
                .userId(UUID.randomUUID())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .email(user.getEmail())
                .gender(user.getGender())
                .build();
        return userDao.insertUser(data);
    }


}
