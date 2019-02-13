package ru.javawebinar.topjava.dao.crudImpl;

import ru.javawebinar.topjava.dao.CrudDAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleMealDAO implements CrudDAO<Meal, Integer> {

    private static final AtomicInteger key = new AtomicInteger(7);
    private static final Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();
    static {
        for (Meal meal : MealsUtil.getMeals()) {
            mealMap.put(meal.getId(), meal);
        }
    }

    @Override
    public void create(Meal meal) {
        meal.setId(key.get());
        mealMap.put(key.getAndIncrement(), meal);
    }

    @Override
    public Meal read(Integer id) {
        return mealMap.get(id);
    }

    @Override
    public void update(Meal meal) {
        mealMap.put(meal.getId(), meal);

    }

    @Override
    public void delete(Integer id) {
        mealMap.remove(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return mealMap.values();
    }
}
