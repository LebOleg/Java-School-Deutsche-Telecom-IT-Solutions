package ru.lebedev.SBBProject.dao;

import ru.lebedev.SBBProject.model.User;

import java.util.Optional;

public interface UserDAO {
    Optional<User> getUserByUsername(String username);

    void save(User user);
}
