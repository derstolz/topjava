package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;
import ru.javawebinar.topjava.web.user.ProfileRestController;

import java.time.LocalDateTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));
            adminUserController.create(new User(null, "loh", "vasya@email.com", "password", Role.ROLE_USER));
            adminUserController.create(new User(null, "pidr", "petya@email.com", "password", Role.ROLE_USER));

            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            ProfileRestController profileRestController = appCtx.getBean(ProfileRestController.class);

            mealRestController.create(new Meal(LocalDateTime.now(), "Обед", 1000));
            mealRestController.create(new Meal(LocalDateTime.now().minusHours(1), "Завтрак", 1000));

            System.out.println(profileRestController.get(2));
        }
    }
}
