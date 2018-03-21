package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;

@Controller
public class MealRestController {
    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal create(Meal meal) {
        return service.create(meal);
    }

    public Meal get(int id) {
        return service.get(id);
    }

    public void update(Meal meal, int id) {
        assureIdConsistent(meal, id);
        service.update(meal);
    }

    public void delete(int id) {
        service.delete(id);
    }

    public List<Meal> getAll() {
        return service.getAll();
    }
}