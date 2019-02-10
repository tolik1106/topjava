package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class MealServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MealTo> mealToList = MealsUtil.getFilteredWithExcess(
                MealsUtil.getMeals(),
                LocalTime.MIN,
                LocalTime.MAX,
                2000);

        log.debug("redirect to meals");
        request.setAttribute("mealToList", mealToList);
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
