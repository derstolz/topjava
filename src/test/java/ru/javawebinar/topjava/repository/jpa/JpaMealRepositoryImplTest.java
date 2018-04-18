package ru.javawebinar.topjava.repository.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.Assert.*;

@ContextConfiguration({"classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaMealRepositoryImplTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MealRepository jpaMealRepository;

    @Test
    @Transactional
    public void save() {
        Meal meal = new Meal(LocalDateTime.now(), "hooita", 500);
        jpaMealRepository.save(meal, 100000);
    }

    @Test
    @Transactional
    public void delete() {
        jpaMealRepository.delete(100005, 100000);
    }

    @Test
    @Transactional
    public void get() {
        jpaMealRepository.get(100003, 100000);
    }

    @Test
    @Transactional
    public void getAll() {
        System.out.println(jpaMealRepository.getAll(100000));
    }

    @Test
    @Transactional
    public void getBetween() {
        jpaMealRepository.getBetween(LocalDateTime.of(2015, Month.MAY, 30, 7,30),
                LocalDateTime.of(2015, Month.MAY, 31, 12, 00), 100000);
    }
}