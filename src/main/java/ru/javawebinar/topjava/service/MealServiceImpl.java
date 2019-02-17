package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal create(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    @Override
    public void delete(int userId, int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(userId, id), id);
    }

    @Override
    public Meal get(int userId, int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(userId, id), id);
    }

    @Override
    public void update(int userId, Meal meal) throws NotFoundException {
        checkNotFoundWithId(repository.save(userId, meal), meal.getId());
    }

    @Override
    public List<MealTo> getAll(int userId) {
        return MealsUtil.getWithExcess(repository.getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    @Override
    public List<MealTo> getAllByDate(int userId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return MealsUtil.getFilteredWithExcess(repository.getAll(userId),
                MealsUtil.DEFAULT_CALORIES_PER_DAY, startDate, endDate, startTime, endTime);
    }
}