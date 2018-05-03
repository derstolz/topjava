package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.javawebinar.topjava.service.MealService;

@Controller
public class JspMealController {

    private final MealService mealService;

    @Autowired
    public JspMealController(MealService mealService) {
        this.mealService = mealService;
    }


}