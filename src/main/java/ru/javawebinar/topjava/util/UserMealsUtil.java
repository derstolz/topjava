package ru.javawebinar.topjava.util;

import com.sun.tracing.dtrace.FunctionAttributes;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * GKislin
 * 31.05.2015.
 */

public class UserMealsUtil {

    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime,
                                                                   LocalTime endTime, int caloriesPerDay) {

        //подсчет общего количества калорий в день
        Map<LocalDate, Integer> map = mealList
                .stream()
                .collect(Collectors.groupingBy(s -> s.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories))
                );

        return mealList
                .stream()
                .filter(s -> s.getDateTime().toLocalTime().isAfter(startTime))
                .filter(s -> s.getDateTime().toLocalTime().isBefore(endTime))
                .map(s -> new UserMealWithExceed(s, compareCalories(map, s, caloriesPerDay)))
                .collect(Collectors.toList());
    }

    private static boolean compareCalories(Map<LocalDate, Integer> map, UserMeal meal, int caloriesLimit) {
        //метод проверяет - не превышено ли количество калорий в течении дня
        LocalDate date = meal.getDateTime().toLocalDate();
        if (map.get(date) > caloriesLimit)
            return true;
        else
            return false;
    }
}