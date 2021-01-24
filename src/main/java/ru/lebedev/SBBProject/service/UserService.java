package ru.lebedev.SBBProject.service;

import ru.lebedev.SBBProject.model.User;

public interface UserService {
    User getUser(String username);

    void save(User user);

    boolean checkIfUsernameExists(User user);

    boolean isAuthenticated();
}
