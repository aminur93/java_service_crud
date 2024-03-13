package net.aminurdev.user.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.aminurdev.user.entity.User;
import net.aminurdev.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> index(@RequestParam(required = false, defaultValue = "2") Integer page, @RequestParam(required = false, defaultValue = "2") Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userService.index(pageable);

        List<User> users = userPage.getContent();

        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<String> store(@Valid @RequestBody User user)
    {

            User storeUser = userService.store(user);

            // Construct the response body
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("data", storeUser);
            responseBody.put("message", "User store successful");
            responseBody.put("status_code", HttpStatus.CREATED.value()); // Status code

            return ResponseEntity.ok(responseBody.toString());

    }

    @GetMapping("{id}")
    public ResponseEntity<String> edit(@PathVariable("id") Long userId)
    {
        User user = userService.edit(userId);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", user);
        responseBody.put("message", "User fetch successful");
        responseBody.put("status_code", HttpStatus.OK.value()); // Status code

        return ResponseEntity.ok(responseBody.toString());
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long userId, @RequestBody User user)
    {
        User updateUser = userService.update(userId, user);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", updateUser);
        responseBody.put("message", "User update successful");
        responseBody.put("status_code", HttpStatus.OK.value()); // Status code

        return ResponseEntity.ok(responseBody.toString());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long userId)
    {
        // Assuming userService.delete(userId) returns a boolean indicating success
        userService.delete(userId);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", "");
        responseBody.put("message", "User delete successful");
        responseBody.put("status_code", HttpStatus.OK.value()); // Status code

        return ResponseEntity.ok(responseBody.toString());
    }
}
