package com.gcash.training.sa.dao;

import com.gcash.training.sa.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author johnmichael.gerona
 * @created 1/4/24
 */
class FakeDataDaoTest {

    private FakeDataDao fakeDataDao;

    @BeforeEach
    void setUp() {
        fakeDataDao = new FakeDataDao();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldSelectAllUsers() {
        // ensure at least a record is existing
        User data = User.builder()
                .userId(UUID.randomUUID())
                .email("test@test.com")
                .firstName("first")
                .lastName("last")
                .gender(User.Gender.Male)
                .age(22)
                .build();
        fakeDataDao.insertUser(data);

        List<User> users = fakeDataDao.selectAllUsers();
        assertTrue(users.size() >= 1);
        User user = users.get(0);
        assertEquals("first", user.getFirstName());
        assertEquals("last", user.getLastName());
        assertEquals("test@test.com", user.getEmail());
        assertEquals(User.Gender.Male, user.getGender());
        assertEquals(22, user.getAge());

    }

    @Test
    void selectUserByUserId() {
        // ensure at least a record is existing
        User data = User.builder()
                .userId(UUID.randomUUID())
                .email("test@test.com")
                .firstName("first")
                .lastName("last")
                .gender(User.Gender.Male)
                .age(22)
                .build();
        fakeDataDao.insertUser(data);

        User user = fakeDataDao.selectUserByUserId(data.getUserId().toString());
        assertNotNull(user);
    }

    @Test
    void updateUser() {
        User data = User.builder()
                .userId(UUID.fromString("142b9c88-9993-4283-b506-4a395cbda8d0"))
                .email("test@test.com")
                .firstName("updated first name")
                .lastName("last")
                .gender(User.Gender.Male)
                .age(22)
                .build();
        int result = fakeDataDao.updateUser(data);
        assertEquals(1, result);
    }

    @Test
    void deleteUserByUserId() {
        User data = User.builder()
                .userId(UUID.randomUUID())
                .email("test@test.com")
                .firstName("first")
                .lastName("last")
                .gender(User.Gender.Male)
                .age(22)
                .build();
        fakeDataDao.insertUser(data);
        int result = fakeDataDao.deleteUserByUserId(data.getUserId().toString());
        assertEquals(1, result);
    }

    @Test
    void insertUser() {
        User data = User.builder()
                .email("test@test.com")
                .firstName("first")
                .lastName("last")
                .gender(User.Gender.Male)
                .age(22)
                .build();
        int result = fakeDataDao.insertUser(data);
        assertEquals(1, result);
    }

    @Test
    public void shouldUpdateNothingGivenNonExistentUser() {
        User data = User.builder()
                .userId(UUID.fromString("142b9c88-9993-4283-b506-4a395cbda000"))
                .email("test@test.com")
                .firstName("updated first name")
                .lastName("last")
                .gender(User.Gender.Male)
                .age(22)
                .build();
        int result = fakeDataDao.updateUser(data);
        assertEquals(-1, result);
    }

    @Test
    public void shouldDeleteNothingGivenNonExistentUser() {
        String id = "142b9c88-9993-4283-b506-4a395cbda000";
        int result = fakeDataDao.deleteUserByUserId(id);
        assertEquals(-1, result);
    }
}