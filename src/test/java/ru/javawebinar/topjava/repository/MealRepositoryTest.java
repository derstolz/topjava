package ru.javawebinar.topjava.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

public class MealRepositoryTest extends RepositoryTest{
    
    @Autowired
    private MealRepository mealRepository;


    @Test
    public void delete() throws Exception {
        mealRepository.delete(MEAL1_ID, USER_ID);
        assertMatch(mealRepository.getAll(USER_ID), MEAL6, MEAL5, MEAL4, MEAL3, MEAL2);
    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        mealRepository.delete(MEAL1_ID, 1);
    }

    @Test
    @Transactional
    public void create() throws Exception {
        Meal created = getCreated();
        mealRepository.save(created, USER_ID);
        assertMatch(mealRepository.getAll(USER_ID), created, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    }

    @Test
    public void get() throws Exception {
        Meal actual = mealRepository.get(ADMIN_MEAL_ID, ADMIN_ID);
        assertMatch(actual, ADMIN_MEAL1);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        mealRepository.get(MEAL1_ID, ADMIN_ID);
    }

    @Test
    @Transactional
    public void update() throws Exception {
        Meal updated = getUpdated();
        mealRepository.save(updated, USER_ID);
        assertMatch(mealRepository.get(MEAL1_ID, USER_ID), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + MEAL1_ID);
        mealRepository.save(MEAL1, ADMIN_ID);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(mealRepository.getAll(USER_ID), MEALS);
    }

    @Test
    public void getBetween() throws Exception {
        assertMatch(mealRepository.getBetween(
                LocalDate.of(2015, Month.MAY, 30).atStartOfDay(),
                LocalDate.of(2015, Month.MAY, 30).atStartOfDay(), USER_ID), MEAL3, MEAL2, MEAL1);
    }
}
