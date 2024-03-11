package net.aminurdev.user.controllers;

import lombok.AllArgsConstructor;
import net.aminurdev.user.entity.User;
import net.aminurdev.user.service.UserService;
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
    public ResponseEntity<List<User>> index()
    {
        List<User> users = userService.index();

        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> store(@RequestBody User user)
    {
        User storeUser = userService.store(user);

        // Construct the response body
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", storeUser);
        responseBody.put("message", "User store successful");
        responseBody.put("status_code", HttpStatus.CREATED.value()); // Status code

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> edit(@PathVariable("id") Long userId)
    {
        User user = userService.edit(userId);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", user);
        responseBody.put("message", "User fetch successful");
        responseBody.put("status_code", HttpStatus.OK.value()); // Status code

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PutMapping("{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable("id") Long userId, @RequestBody User user)
    {
        User updateUser = userService.update(userId, user);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", updateUser);
        responseBody.put("message", "User update successful");
        responseBody.put("status_code", HttpStatus.OK.value()); // Status code

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") Long userId)
    {
        // Assuming userService.delete(userId) returns a boolean indicating success
        userService.delete(userId);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", "");
        responseBody.put("message", "User delete successful");
        responseBody.put("status_code", HttpStatus.OK.value()); // Status code

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
