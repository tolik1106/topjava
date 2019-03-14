package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.UserTestData.USER_WITH_MEAL;
import static ru.javawebinar.topjava.UserTestData.assertMatchWithMeals;

@ActiveProfiles(value = {Profiles.POSTGRES_DB, Profiles.DATAJPA}, inheritProfiles = false)
public class DataJpaUserServiceTest extends JdbcUserServiceTest {

    @Test
    public void getWithMeals() {
        User user = service.getWithMeals(USER_ID);
        assertMatchWithMeals(user, USER_WITH_MEAL);
    }

    @Test
    public void getWithMealsNotFound() {
        thrown.expect(NotFoundException.class);
        User user = service.getWithMeals(1);
    }
}
