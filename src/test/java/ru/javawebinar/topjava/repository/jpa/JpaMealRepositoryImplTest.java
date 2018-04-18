package ru.javawebinar.topjava.repository.jpa;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

//@Repository
//@Transactional(readOnly = true)
//@ContextConfiguration("classpath:spring/spring-db.xml")
//@RunWith(SpringJUnit4ClassRunner.class)
public class JpaMealRepositoryImplTest {
//    private static final Logger logger = LoggerFactory.getLogger(JpaMealRepositoryImplTest.class);

//    @Autowired
    private EntityManager entityManager;

//    @Autowired
    private JpaMealRepositoryImpl jpaRepository;

//    @Before
    public void setUp() throws Exception {
        System.out.println("test");
    }

//    @Test
//    @Transactional
    public void save() {
    }

//    @Test
//    @Transactional
    public void delete() {
    }

//    @Test
//    @Transactional
    public void get() {
    }

//    @Test
//    @Transactional
    public void getAll() {
    }

//    @Test
//    @Transactional
    public void getBetween() {
    }
}