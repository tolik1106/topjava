package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal.getUserId(), meal));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        if (meal.isNew()) {
            meal.setUserId(userId);
            meal.setId(counter.incrementAndGet());
            log.info("save {}", meal);

            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userId, int mealId) {
        log.info("delete {}", mealId);
        if (checkMealForUser(userId, mealId))
            return repository.remove(mealId) != null;
        return false;
    }

    @Override
    public Meal get(int userId, int mealId) {
        log.info("get {}", mealId);
        if (checkMealForUser(userId, mealId))
            return repository.get(mealId);
        return null;
    }



    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll");
        return repository.values()
                .stream()
                .filter(meal -> meal.getUserId().equals(userId))
                .sorted(Comparator.comparing(Meal::getDateTime, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    private boolean checkMealForUser(int userId, int mealId) {
        Meal mealById = repository.get(mealId);
        return (mealById != null && mealById.getUserId().equals(userId));
    }
}

