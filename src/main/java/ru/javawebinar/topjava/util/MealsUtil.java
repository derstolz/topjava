package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MealsUtil {
    public static void main(String[] args) {
        List<Meal> mealList = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
    }

    public static List<MealWithExceed> getFilteredWithExceeded(List<Meal> mealList, LocalTime startTime,
                                                                   LocalTime endTime, int caloriesPerDay) {
        //метод возвращает список приемов пищи с фильтрацией по полю - превышено ли суточное количество калорий или нет

        //подсчет общего количества калорий в день
        Map<LocalDate, Integer> map = mealList
                .stream()
                .collect(Collectors.groupingBy(s -> s.getDateTime().toLocalDate(),
                        Collectors.summingInt(Meal::getCalories))
                );

        return mealList
                .stream()
                .filter(s -> s.getDateTime().toLocalTime().isAfter(startTime))
                .filter(s -> s.getDateTime().toLocalTime().isBefore(endTime))
                .map(s -> new MealWithExceed(s, compareCalories(map, s, caloriesPerDay)))
                .collect(Collectors.toList());
    }

    private static boolean compareCalories(Map<LocalDate, Integer> map, Meal meal, int caloriesLimit) {
        //метод проверяет - превышено количество калорий в течении дня
        LocalDate date = meal.getDateTime().toLocalDate();
        return map.get(date) > caloriesLimit;
    }
}