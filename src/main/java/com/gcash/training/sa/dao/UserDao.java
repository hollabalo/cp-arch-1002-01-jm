package com.gcash.training.sa.dao;

import com.gcash.training.sa.model.User;

import java.util.List;

/**
 * @author johnmichael.gerona
 * @created 1/3/24
 */
public interface UserDao {

    List<User> selectAllUsers();

    User selectUserByUserId(String id);

    int updateUser(User user);

    int deleteUserByUserId(String id);

    int insertUser(User user);
}
