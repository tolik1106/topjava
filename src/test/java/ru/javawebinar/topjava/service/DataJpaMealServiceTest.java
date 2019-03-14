package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.MealTestData.MEAL1;
import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ActiveProfiles(value = {Profiles.POSTGRES_DB, Profiles.DATAJPA}, inheritProfiles = false)
public class DataJpaMealServiceTest extends JdbcMealServiceTest {

    @Test
    public void getWithUser() {
        Meal actual = service.getWithUser(MEAL1_ID, USER_ID);
        assertMatch(actual, MEAL1);
        UserTestData.assertMatch(actual.getUser(), USER);
    }

    @Test
    public void getWithUserNotFound() {
        thrown.expect(NotFoundException.class);
        Meal actual = service.getWithUser(MEAL1_ID, ADMIN_ID);
    }
}
