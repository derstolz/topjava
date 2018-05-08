package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
public class JspMealController {
    private final static Logger log = LoggerFactory.getLogger(JspMealController.class);

    private final MealService service;

    @Autowired
    public JspMealController(MealService service) {
        this.service = service;
    }

    @GetMapping("/meals")
    public String meals(Model model, HttpServletRequest request) throws ServletException, IOException {
        List<MealWithExceed> meals;

        if (request.getParameter("filter") == null) {
            meals = MealsUtil
                    .getWithExceeded(service.getAll(MealsUtil.getUserId()), AuthorizedUser.getCaloriesPerDay());
        } else
            meals = MealsUtil.getFiltered(service, request);

        model.addAttribute("meals", meals);
        return "meals";
    }

    @PostMapping("/meals/add")
    public String create (Model model, HttpServletRequest request, HttpServletResponse response) {
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (request.getParameter("id").isEmpty()){
            service.create(meal, AuthorizedUser.id());
        }
        else
            service.update(meal, AuthorizedUser.id());
        return "redirect:/meals";
    }

    @GetMapping("/meals/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        service.delete(id, MealsUtil.getUserId());
        return "redirect:/meals";
    }

    @GetMapping("/meals/update/{id}")
    public String update(@PathVariable("id") int id, Model model) {

        final Meal meal = service.get(id, MealsUtil.getUserId());
        model.addAttribute(meal);
        return "mealForm";
    }

    @GetMapping("/meals/create")
    public String mealForm() {
        return "mealForm";
    }
    
}