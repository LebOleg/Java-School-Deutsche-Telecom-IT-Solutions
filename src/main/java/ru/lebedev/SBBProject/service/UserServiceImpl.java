package ru.lebedev.SBBProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.SBBProject.dao.UserDAO;
import ru.lebedev.SBBProject.model.Role;
import ru.lebedev.SBBProject.model.User;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @Override
    @Transactional
    public User getUser(String username) {
        User user = userDAO.getUserByUsername(username);

        return user;
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        user.setPassword(bCryptEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userDAO.save(user);
    }
}
