package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "Tolik1106", "email@mail.ru", "password", Role.ROLE_ADMIN));
            MealRestController mealRestController = (MealRestController) appCtx.getBean("mealRestController");

            System.out.println(mealRestController.getAll());
            LocalDate startDate = LocalDate.of(2015, 5, 31);
            LocalTime startTime = LocalTime.of(11, 00);
            System.out.println("___________________________________________");
            System.out.println(mealRestController.getAllByDate(startDate, startTime, null, null));
        }
    }
}
