package ru.javawebinar.topjava.service.jdbc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {

    @Autowired
    private UserService service;

    @Override
    public void setUp() throws Exception {
    }

    @Override
    @Test
    public void create() throws Exception {
        User user = UserTestData.USER;
        service.create(user);
    }

    @Override
    @Test
    public void duplicateMailCreate() throws Exception {
        thrown.expect(DuplicateKeyException.class);
        service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER));
    }

    @Override
    @Test
    public void delete() throws Exception {
        service.delete(UserTestData.USER_ID);
    }

    @Override
    @Test
    public void notFoundDelete() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(1);
    }

    @Override
    @Test
    public void get() throws Exception {
        service.get(UserTestData.USER_ID);
    }

    @Override
    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(1);
    }

    @Override
    @Test
    public void getByEmail() throws Exception {
        service.getByEmail(UserTestData.USER.getEmail());
    }

    @Override
    @Test
    public void update() throws Exception {
        User user = UserTestData.USER;
        user.setName("updatedName");
        user.setCaloriesPerDay(330);
        service.update(user);
    }

    @Override
    @Test
    public void getAll() throws Exception {
        service.getAll();
    }

    @Override
    @Test
    public void testValidation() throws Exception {
    }
}