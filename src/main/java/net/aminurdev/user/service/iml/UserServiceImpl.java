package net.aminurdev.user.service.iml;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net.aminurdev.user.entity.User;
import net.aminurdev.user.exception.ResourceNotFoundException;
import net.aminurdev.user.repository.UserRepository;
import net.aminurdev.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public Page<User> index(Pageable pageable) {

        return userRepository.findAll(pageable);
    }

    @Override
    public User store(User user) {

       return userRepository.save(user);

    }

    @Override
    public User edit(Long userId) {

        User editUser = new  User();

              editUser  = userRepository.findById(userId)
                            .orElseThrow(() -> new ResourceNotFoundException("User Not Found By Id"));

        return editUser;
    }

    @Override
    @Transactional
    public User update(Long userId, User user) {
        try {
            // Retrieve the user by userId or throw an exception if not found
            User updateUser = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User Not Found By Id"));

            // Update user attributes
            updateUser.setName(user.getName());
            updateUser.setEmail(user.getEmail());
            updateUser.setPhone(user.getPhone());

            // Save the updated user
            updateUser = userRepository.save(updateUser);

            return updateUser;
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to update user: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long userId) {

        try{

          userRepository.deleteById(userId);

        } catch (Exception e){

            throw new RuntimeException("Delete failed");
        }
    }
}
