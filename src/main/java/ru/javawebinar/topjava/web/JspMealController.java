package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

@Controller
public class JspMealController {
    private final static Logger log = LoggerFactory.getLogger(JspMealController.class);

    private final MealService service;

    @Autowired
    public JspMealController(MealService service) {
        this.service = service;
    }

    @GetMapping("/meals")
    public String meals(Model model) {
        List<MealWithExceed> meals = MealsUtil
                .getWithExceeded(service.getAll(getUserId()), AuthorizedUser.getCaloriesPerDay());

        model.addAttribute("meals", meals);
        return "meals";
    }

    @GetMapping("/meals/delete/{id}")
    public String delete(@PathVariable("id") int id) {

        service.delete(id, getUserId());
        return "redirect:/meals";
    }

    @GetMapping("/meals/update/{id}")
    public String update(@PathVariable("id") int id, Model model) {

        final Meal meal = service.get(id, getUserId());
        model.addAttribute(meal);
        return "mealForm";
    }

    @GetMapping("/meals/create")
    public String mealForm () {
        return "mealForm";
    }

    private int getUserId() {
        return AuthorizedUser.id();
    }
}