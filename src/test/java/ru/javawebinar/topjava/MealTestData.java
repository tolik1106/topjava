package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

public class MealTestData {

    public static final int USER_BR_MEAL_ID = ADMIN_ID + 1;
    public static final int USER_LUNCH_MEAL_ID = USER_BR_MEAL_ID + 1;
    public static final int ADMIN_LUNCH_MEAL_ID = USER_LUNCH_MEAL_ID + 1;
    public static final int ADMIN_DINNER_MEAL_ID = ADMIN_LUNCH_MEAL_ID + 1;

    public static final Meal USER_BR_MEAL = new Meal(USER_BR_MEAL_ID, LocalDateTime.of(2015, 05, 30, 10, 00), "Завтрак", 500);
    public static final Meal USER_LUNCH_MEAL = new Meal(USER_LUNCH_MEAL_ID, LocalDateTime.of(2015, 05, 30, 13, 00), "Обед", 1000);
    public static final Meal ADMIN_LUNCH_MEAL = new Meal(ADMIN_LUNCH_MEAL_ID, LocalDateTime.of(2015, 05, 31, 13, 00), "Обед", 500);
    public static final Meal ADMIN_DINNER_MEAL = new Meal(ADMIN_DINNER_MEAL_ID, LocalDateTime.of(2015, 05, 31, 20, 00), "Ужин", 510);


}
