package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User ref = entityManager.getReference(User.class, userId);
        meal.setUser(ref);
        if (meal.isNew()){
            entityManager.persist(meal);
            return meal;
        }
        else
            return entityManager.merge(meal);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
//        User ref = entityManager.getReference(User.class, userId);
//        Query query = entityManager.createQuery("DELETE FROM Meal m WHERE m.id =: id AND m.user =: ref");
//        return query.setParameter("id", id).executeUpdate() != 0;
//
//        return entityManager
//                .createNamedQuery(Meal.DELETE, Meal.class)
//                .setParameter("id", id)
//                .setParameter("userId", userId)
//                .executeUpdate() != 0;
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = entityManager.find(Meal.class, id);
        if (meal.getUser() == entityManager.getReference(User.class, userId))
            return meal;
        else
            return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
//        return entityManager
//                .createNamedQuery(Meal.BY_USER, Meal.class)
//                .setParameter("userId", userId)
//                .getResultList();
        return entityManager
                .createQuery("SELECT m FROM Meal m WHERE m.user.id =:userId ORDER BY m.dateTime DESC")
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
//        return entityManager
//                .createNamedQuery(Meal.GET_BEETWEEN, Meal.class)
//                .setParameter("startDate", startDate)
//                .setParameter("endDate", endDate)
//                .setParameter("userId", userId)
//                .getResultList();
        return null;
    }
}