package ru.lebedev.SBBProject.dao;

import ru.lebedev.SBBProject.model.User;

public interface UserDAO {
    User getUserByUsername(String username);

    void save(User user);
}
