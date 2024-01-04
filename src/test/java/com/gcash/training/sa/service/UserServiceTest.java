package com.gcash.training.sa.service;

import com.gcash.training.sa.dao.FakeDataDao;
import com.gcash.training.sa.model.User;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author johnmichael.gerona
 * @created 1/4/24
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private FakeDataDao fakeDataDao;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(fakeDataDao);
    }

    @Test
    public void shouldGetAllUsers() {
        // given
        User data = User.builder()
                .userId(UUID.randomUUID())
                .email("test@test.com")
                .firstName("first")
                .lastName("last")
                .gender(User.Gender.Male)
                .age(22)
                .build();

        ImmutableList<User> users = new ImmutableList.Builder<User>()
                .add(data)
                .build();

        // when
        when(fakeDataDao.selectAllUsers()).thenReturn(users);

        // then
        List<User> result = userService.getAllUsers();
        assertThat(result).hasSize(1);

        User user = result.get(0);
        assertUserFields(user);
    }

    @Test
    public void shouldGetUser() {
        // given
        User data = User.builder()
                .userId(UUID.randomUUID())
                .email("test@test.com")
                .firstName("first")
                .lastName("last")
                .gender(User.Gender.Male)
                .age(22)
                .build();

        // when
        when(fakeDataDao.selectUserByUserId(anyString())).thenReturn(data);

        // then
        User user = userService.getUser(data.getUserId().toString());
        assertUserFields(user);
    }

    @Test
    public void shouldUpdateUser() {
        // given
        String id = "142b9c88-9993-4283-b506-4a395cbda8d0";
        User data = User.builder()
                .userId(UUID.fromString(id))
                .email("test@test.com")
                .firstName("updated first name")
                .lastName("last")
                .gender(User.Gender.Male)
                .age(22)
                .build();

        // when
        when(fakeDataDao.selectUserByUserId(id)).thenReturn(data);
        when(fakeDataDao.updateUser(any(User.class))).thenReturn(1);

        // then
        int result = userService.updateUser(id, data);
        verify(fakeDataDao).selectUserByUserId(id);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void shouldRemoveUser() {
        // given
        String id = "142b9c88-9993-4283-b506-4a395cbda8d0";
        User data = User.builder()
                .userId(UUID.fromString(id))
                .email("test@test.com")
                .firstName("updated first name")
                .lastName("last")
                .gender(User.Gender.Male)
                .age(22)
                .build();

        // when
        when(fakeDataDao.selectUserByUserId(id)).thenReturn(data);
        when(fakeDataDao.deleteUserByUserId(id)).thenReturn(1);

        // then
        int result = userService.removeUser(id);
        verify(fakeDataDao).selectUserByUserId(id);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void shouldInsertUser() {
        // given
        String id = "142b9c88-9993-4283-b506-4a395cbda8d0";
        User data = User.builder()
                .userId(UUID.fromString(id))
                .email("test@test.com")
                .firstName("updated first name")
                .lastName("last")
                .gender(User.Gender.Male)
                .age(22)
                .build();

        // when
        when(fakeDataDao.insertUser(any(User.class))).thenReturn(1);

        // then
        int result = userService.insertUser(data);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void shouldUpdateNothingGivenNonExistentUser() {
        // given
        String id = "142b9c88-9993-4283-b506-4a395cbda8d0";
        User data = User.builder()
                .userId(UUID.fromString(id))
                .email("test@test.com")
                .firstName("updated first name")
                .lastName("last")
                .gender(User.Gender.Male)
                .age(22)
                .build();

        // when
        when(fakeDataDao.selectUserByUserId(id)).thenReturn(null);

        // then
        int result = userService.updateUser(id, data);
        verify(fakeDataDao).selectUserByUserId(id);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void shouldDeleteNothingGivenNonExistentUser() {
        // given
        String id = "142b9c88-9993-4283-b506-4a395cbda8d0";

        // when
        when(fakeDataDao.selectUserByUserId(id)).thenReturn(null);

        // then
        int result = userService.removeUser(id);
        verify(fakeDataDao).selectUserByUserId(id);
        assertThat(result).isEqualTo(-1);
    }

    private void assertUserFields(User user) {
        assertThat(user).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("first");
        assertThat(user.getLastName()).isEqualTo("last");
        assertThat(user.getGender()).isEqualTo(User.Gender.Male);
        assertThat(user.getAge()).isEqualTo(22);
    }

}
