package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        getFilteredWithExceedNoStream(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> sumCaloriesPerDayMap =
                mealList.stream()
                        .collect(Collectors.groupingBy(
                                mList -> mList.getDateTime().toLocalDate(),
                                Collectors.summingInt(UserMeal::getCalories)));

        return mealList
                .stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
                .map((userMeal) -> {
                    return new UserMealWithExceed(
                            userMeal.getDateTime(),
                            userMeal.getDescription(),
                            userMeal.getCalories(),
                            caloriesPerDay < sumCaloriesPerDayMap.get(
                                    userMeal.getDateTime().toLocalDate())
                    );
                })
                .collect(Collectors.toList());
    }

    public static List<UserMealWithExceed> getFilteredWithExceedNoStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> userMealWithExceeds = new ArrayList<>();
        Map<LocalDate, Integer> sumCaloriesPerDayMap = new HashMap<>();
        for (UserMeal userMeal : mealList) {
            LocalDate date = userMeal.getDateTime().toLocalDate();
            Integer calories = sumCaloriesPerDayMap.getOrDefault(date, userMeal.getCalories());
            sumCaloriesPerDayMap.merge(date, calories, (oldValue, newValue) -> oldValue + newValue);
        }

        for (UserMeal userMeal : mealList) {
            LocalTime localTime = userMeal.getDateTime().toLocalTime();
            if (TimeUtil.isBetween(localTime, startTime, endTime)) {
                userMealWithExceeds.add(new UserMealWithExceed(userMeal.getDateTime(),
                                                userMeal.getDescription(),
                                                userMeal.getCalories(),
                                                sumCaloriesPerDayMap.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }
        return userMealWithExceeds;
    }
}
