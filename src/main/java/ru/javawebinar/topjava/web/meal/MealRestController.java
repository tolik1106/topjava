package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {

    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public List<MealTo> getAll() {
        log.info("getAll");
        return service.getAll(authUserId());
    }

    public List<MealTo> getAllByDate(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return service.getAllByDate(authUserId(), startDate, startTime, endDate, endTime);
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(authUserId(), id);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(authUserId(), id);
    }

    public void save(Meal meal) {
        log.info("save {}", meal);
        checkNew(meal);
        service.create(authUserId(), meal);
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(authUserId(), meal);
    }


}