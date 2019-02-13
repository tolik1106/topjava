package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.CrudDAO;
import ru.javawebinar.topjava.dao.crudImpl.SimpleMealDAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MealServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private static final String INSERT_OR_EDIT = "/editMeal.jsp";
    private static final String MEAL_SERVLET = "meals";
    private static final String MEALS_JSP = "/meals.jsp";
    private static final int CALORIES_PER_DAY = 2000;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    private CrudDAO<Meal, Integer> dao = new SimpleMealDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        String forward = "";
        String action = request.getParameter("action");
        if (action == null) {
            action = "mealList";
        }

        if (action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("mealToId"));
            dao.delete(id);
            forward = MEAL_SERVLET;
            response.sendRedirect(forward);
            return;
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int id = Integer.parseInt(request.getParameter("mealToId"));
            Meal meal = dao.read(id);
            request.setAttribute("mealTo", meal);
        } else if (action.equalsIgnoreCase("mealList")) {
            forward = MEALS_JSP;
            request.setAttribute("mealToList", getMealToListFromMealCollection(dao.getAll()));
        } else {
            forward = INSERT_OR_EDIT;
        }
        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("meals post");

        Meal meal = new Meal();

        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("dateTime"), dateTimeFormatter);
        meal.setDateTime(localDateTime);
        meal.setDescription(req.getParameter("description"));
        meal.setCalories(Integer.parseInt(req.getParameter("calories")));
        String id = req.getParameter("mealToId");
        if (id == null || id.isEmpty()) {
            dao.create(meal);
        } else {
            meal.setId(Integer.parseInt(id));
            dao.update(meal);
        }
        resp.sendRedirect(MEAL_SERVLET);
    }

    private List<MealTo> getMealToListFromMealCollection(Collection<Meal> mealCollection) {
        ArrayList<Meal> mealArrayList = new ArrayList<>(mealCollection);
        return MealsUtil.getFilteredWithExcess(mealArrayList, LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
    }
}
