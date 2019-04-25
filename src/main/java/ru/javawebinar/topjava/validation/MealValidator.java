package ru.javawebinar.topjava.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MealValidator implements Validator {

    @Autowired
    private MealService mealService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Meal.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Meal meal = (Meal) target;

        LocalDateTime dateTime = meal.getDateTime();

        if (dateTime != null) {
            int id = SecurityUtil.authUserId();

            List<Meal> meals = mealService.getBetweenDateTimes(dateTime, dateTime, id);

            if (meals.size() != 0) {
                if (meal.isNew() || !meal.getId().equals(meals.get(0).getId())) {
                    errors.rejectValue("dateTime", "dateTime","Meal with this dateTime already exists");
                }
            }
        }
    }
}
