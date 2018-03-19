package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MealsUtil {
    private static List<Meal> meals;
    final static Object lock = new Object();


    public static void main(String[] args) {
//        List<MealWithExceed> mealsWithExceeded = getFilteredWithExceed(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        mealsWithExceeded.forEach(System.out::println);
    }

    public static List<MealWithExceed> getFilteredWithExceed(List<Meal> meals, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = countCalories(meals, caloriesPerDay);

        return meals.stream()
                .map(meal ->
                        new MealWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<MealWithExceed> getFilteredWithExceed(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = countCalories(meals, caloriesPerDay);

        return meals.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .map(meal ->
                        new MealWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static Map<LocalDate, Integer> countCalories(List<Meal> meals, int caloriesPerDay) {
        return meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
                );
    }

    public static List<Meal> initializeMeals() {
        meals = new ArrayList<>(Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        ));
        return meals;
    }

    public static List<Meal> listMeals() {
        synchronized (lock) {
            if (meals == null)
                meals = initializeMeals();

            for (int i = 0; i < meals.size(); i++)
                meals.get(i).setId(i);
            return meals;
        }
    }

    public static void add(LocalDateTime dateTime, String description, int calories) {
        synchronized (lock) {
            meals.add(new Meal(dateTime, description, calories));
        }
    }

    public static void edit(int id, LocalDateTime dateTime, String description, int calories) {
        synchronized (lock) {
            Meal meal = new Meal(dateTime, description, calories);
            meal.setId(id);
            meals.remove(id);
            meals.add(meal);
        }
    }

    public static void delete(int id) {
        synchronized (lock) {
            meals.remove(id);
        }
    }
}