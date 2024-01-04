package com.gcash.training.sa.dao;

import com.gcash.training.sa.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author johnmichael.gerona
 * @created 1/3/24
 */
@Repository
public class FakeDataDao implements UserDao {
    private static final Map<UUID, User> database;

    static {
        database = new HashMap<>();
        UUID id = UUID.fromString("142b9c88-9993-4283-b506-4a395cbda8d0");
        database.put(id, new User(id, "juan", "dela cruz", 22,
                "juandelacruz@gmail.com", User.Gender.Male));
    }

    @Override
    public List<User> selectAllUsers() {
        return new ArrayList<>(database.values());
    }

    @Override
    public User selectUserByUserId(String id) {
        return database.get(UUID.fromString(id));
    }

    @Override
    public int updateUser(User user) {
        if (database.get(user.getUserId()) == null) {
            // do nothing
            return -1;
        }
        database.put(user.getUserId(), user);
        return 1;
    }

    @Override
    public int deleteUserByUserId(String id) {
        if (database.get(UUID.fromString(id)) == null) {
            // do nothing
            return -1;
        }
        database.remove(UUID.fromString(id));
        return 1;
    }

    @Override
    public int insertUser(User user) {
        database.put(user.getUserId(), user);
        return 1;
    }
}
