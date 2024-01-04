package service;

import com.gcash.training.sa.dao.FakeDataDao;
import com.gcash.training.sa.model.User;
import com.gcash.training.sa.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author johnmichael.gerona
 * @created 1/4/24
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserService userService = new UserService(new FakeDataDao());

    @Test
    public void shouldUpdateUser() {
        String id = "142b9c88-9993-4283-b506-4a395cbda8d0";
        User data = User.builder()
                .email("test@test.com")
                .firstName("updated first name")
                .lastName("last")
                .gender(User.Gender.Male)
                .age(22)
                .build();
        int result = userService.updateUser(id, data);
        assertEquals(1, result);
    }

    @Test
    public void shouldRemoveUser() {
        String id = "142b9c88-9993-4283-b506-4a395cbda8d0";
        int result = userService.removeUser(id);
        assertEquals(1, result);
    }

    @Test
    public void shouldInsertUser() {
        User data = User.builder()
                .email("test@test.com")
                .firstName("first")
                .lastName("last")
                .gender(User.Gender.Male)
                .age(22)
                .build();
        int result = userService.insertUser(data);
        assertEquals(1, result);
    }

    @Test
    public void shouldListAllUsers() {
        User data = User.builder()
                .email("test@test.com")
                .firstName("first")
                .lastName("last")
                .gender(User.Gender.Male)
                .age(22)
                .build();
        userService.insertUser(data);
        List<User> users = userService.getAllUsers();
        assertTrue(users.size() > 1);
    }

    @Test
    public void getUser() {
        String id = userService.getAllUsers().get(0).getUserId().toString();
        User user = userService.getUser(id);
        assertNotNull(user);
    }

    @Test
    public void shouldUpdateNothingGivenNonExistentUser() {
        String id = "142b9c88-9993-4283-b506-4a395cbda000";
        User data = User.builder()
                .email("test@test.com")
                .firstName("updated first name")
                .lastName("last")
                .gender(User.Gender.Male)
                .age(22)
                .build();
        int result = userService.updateUser(id, data);
        assertEquals(-1, result);
    }

    @Test
    public void shouldDeleteNothingGivenNonExistentUser() {
        String id = "142b9c88-9993-4283-b506-4a395cbda000";
        int result = userService.removeUser(id);
        assertEquals(-1, result);
    }

}
