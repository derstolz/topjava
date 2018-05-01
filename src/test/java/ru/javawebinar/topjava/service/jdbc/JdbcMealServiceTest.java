package ru.javawebinar.topjava.service.jdbc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.jdbc.JdbcMealRepositoryImpl;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.Profiles.JDBC;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ActiveProfiles(JDBC)
public class JdbcMealServiceTest extends AbstractMealServiceTest {

    public JdbcMealServiceTest() {
    }

    @Autowired
    private MealService service;

    @Override
    @Test
    public void delete() throws Exception {
        System.out.println(service.getAll(USER_ID));
        service.delete(MEAL1_ID, USER_ID);
    }
    

    @Override
    @Test
    public void create() throws Exception {
        Meal meal = getCreated();
        service.create(meal, USER_ID);
    }

    @Override
    @Test
    public void get() throws Exception {
        Meal actual = service.get(MEAL1_ID, USER_ID);
    }

    
    @Override
    @Test
    public void update() throws Exception {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
    }

    @Override
    @Test
    public void getAll() throws Exception {
        service.getAll(USER_ID);
    }

    @Override
    @Test
    public void getBetween() throws Exception {
        service.getBetweenDates(
                LocalDate.of(2018, Month.MAY, 1),
                LocalDate.of(2018, Month.MAY, 1), USER_ID);
    }

    @Override
    @Test
    public void testValidation() throws Exception {
    }
}