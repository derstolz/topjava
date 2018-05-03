package ru.javawebinar.topjava.web.meal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.service.MealService;

@Controller
public abstract class AbstractMealController {

    private final MealService service;

    @Autowired
    public AbstractMealController(MealService service) {
        this.service = service;
    }


}
