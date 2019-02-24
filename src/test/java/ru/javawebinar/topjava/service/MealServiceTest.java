package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(USER_BR_MEAL_ID, USER_ID);
        assertThat(meal).isEqualToComparingFieldByField(USER_BR_MEAL);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getAlienMeal() {
        service.get(ADMIN_LUNCH_MEAL_ID, USER_ID);
    }

    @Test
    public void delete() {
        service.delete(USER_BR_MEAL_ID, USER_ID);
        assertThat(service.getAll(USER_ID)).containsExactly(USER_LUNCH_MEAL);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteAlienMeal() {
        service.delete(ADMIN_LUNCH_MEAL_ID, USER_ID);
    }

    @Test
    public void getBetweenNullDates() {
        List<Meal> meals = service.getBetweenDates(null, null, USER_ID);
        assertThat(meals).isSortedAccordingTo(Comparator.comparing(Meal::getDateTime, Comparator.reverseOrder()))
                .isEqualTo(Arrays.asList(USER_LUNCH_MEAL, USER_BR_MEAL));
    }

    @Test
    public void getBetweenNullDown() {
        List<Meal> meals = service.getBetweenDates(null, LocalDate.of(2015, 5, 31), USER_ID);
        assertThat(meals).isSortedAccordingTo(Comparator.comparing(Meal::getDateTime, Comparator.reverseOrder()))
                .isEqualTo(Arrays.asList(USER_LUNCH_MEAL, USER_BR_MEAL));
    }

    @Test
    public void getBetweenNullUp() {
        List<Meal> meals = service.getBetweenDates(LocalDate.of(2015, 5, 30), null, USER_ID);
        assertThat(meals).isSortedAccordingTo(Comparator.comparing(Meal::getDateTime, Comparator.reverseOrder()))
                .isEqualTo(Arrays.asList(USER_LUNCH_MEAL, USER_BR_MEAL));
    }

    @Test
    public void getBetweenDates() {
        List<Meal> meals = service.getBetweenDates(LocalDate.of(2015, 5, 30), LocalDate.of(2015, 5, 31), USER_ID);
        assertThat(meals).isSortedAccordingTo(Comparator.comparing(Meal::getDateTime, Comparator.reverseOrder()))
                .isEqualTo(Arrays.asList(USER_LUNCH_MEAL, USER_BR_MEAL));
    }

    @Test
    public void getAllForUser() {
        List<Meal> meals = service.getAll(USER_ID);
        assertThat(meals).isSortedAccordingTo(Comparator.comparing(Meal::getDateTime, Comparator.reverseOrder()))
                .isEqualTo(Arrays.asList(USER_LUNCH_MEAL, USER_BR_MEAL));
    }

    @Test
    public void getAllForAdmin() {
        List<Meal> meals = service.getAll(ADMIN_ID);
        assertThat(meals).isSortedAccordingTo(Comparator.comparing(Meal::getDateTime, Comparator.reverseOrder()))
                .isEqualTo(Arrays.asList(ADMIN_DINNER_MEAL, ADMIN_LUNCH_MEAL));
    }

    @Test
    public void update() {
        Meal updated = new Meal(USER_BR_MEAL);
        updated.setDescription("Dinner");
        updated.setCalories(1000);
        service.update(updated, USER_ID);
        assertThat(service.get(USER_BR_MEAL_ID, USER_ID))
                .isEqualToComparingFieldByField(updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateAlien() {
        Meal updated = new Meal(USER_BR_MEAL);
        updated.setDescription("Dinner");
        updated.setCalories(2000);
        service.update(updated, ADMIN_ID);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(LocalDateTime.now(), "Breakfast", 1000);
        Meal created = service.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
        assertThat(newMeal).isEqualToComparingFieldByField(created);
    }
}