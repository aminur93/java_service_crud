package net.aminurdev.user.service;

import net.aminurdev.user.entity.User;

import java.util.List;

public interface UserService {

    List<User> index();

    User store(User user);

    User edit(Long userId);

    User update(Long userId, User user);

    void delete(Long userId);
}
