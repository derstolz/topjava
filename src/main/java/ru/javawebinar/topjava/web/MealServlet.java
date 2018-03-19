package ru.javawebinar.topjava.web;

import com.sun.xml.internal.bind.v2.TODO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MealWithExceed> meals = MealsUtil.getFilteredWithExceed(MealsUtil.listMeals(), 2000);
        if (request.getParameter("action") != null)
            processRequest(request, response);
        else {
            request.setAttribute("meals", meals);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "delete":{
                int id = Integer.parseInt(request.getParameter("mealId"));
                MealsUtil.delete(id);
            }
            case "edit":{
                /*String description = request.getParameter("description");
                LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"), formatter);
                int calories = Integer.parseInt(request.getParameter("calories"));
                MealsUtil.add(dateTime, description, calories);*/
                //TODO
            }
            case "add": {

                String description = request.getParameter("description");
                LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"), formatter);
                int calories = Integer.parseInt(request.getParameter("calories"));
                MealsUtil.add(dateTime, description, calories);
            }
        }
        List<MealWithExceed> meals = MealsUtil.getFilteredWithExceed(MealsUtil.listMeals(), 2000);
        request.setAttribute("meals", meals);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }


}
