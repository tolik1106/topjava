package ru.javawebinar.topjava.service;


import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    default User getWithMeals(int id) throws NotFoundException {
        throw new UnsupportedOperationException();
    }

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    List<User> getAll();
}