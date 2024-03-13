package net.aminurdev.user.service;

import net.aminurdev.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    Page<User> index(Pageable pageable);

    User store(User user);

    User edit(Long userId);

    User update(Long userId, User user);

    void delete(Long userId);
}
