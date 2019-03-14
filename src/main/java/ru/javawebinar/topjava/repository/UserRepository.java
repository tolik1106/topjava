package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // throws UnsupportedOperationException
    // use only in DataJpaMealRepositoryImpl
    default User getWithMeals(int id) {
        throw new UnsupportedOperationException();
    }

    // null if not found
    User getByEmail(String email);

    List<User> getAll();
}