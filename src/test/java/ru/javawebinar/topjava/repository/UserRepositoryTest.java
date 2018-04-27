package ru.javawebinar.topjava.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static ru.javawebinar.topjava.UserTestData.*;

public class UserRepositoryTest extends RepositoryTest {
    
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void save(User user) {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", 1555, false, new Date(), Collections.singleton(Role.ROLE_USER));
        User created = userRepository.save(newUser);
        newUser.setId(created.getId());
        assertMatch(userRepository.getAll(), ADMIN, newUser, USER);
    }

    @Test
    public void delete(int id) {
        userRepository.delete(USER_ID);
        assertMatch(userRepository.getAll(), ADMIN);
    }

    @Test
    public void get(int id) {
        User user = userRepository.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test
    public void getByEmail(String email) {
        User user = userRepository.getByEmail("user@yandex.ru");
        assertMatch(user, USER);
    }

    @Test
    public void getAll() {
        List<User> all = userRepository.getAll();
        assertMatch(all, ADMIN, USER);
    }
}
