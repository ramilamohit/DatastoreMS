package com.tekarch.TafDatastoreService.Controller;

import com.tekarch.TafDatastoreService.Model.Users;
import com.tekarch.TafDatastoreService.Repositories.UserRepository;
import com.tekarch.TafDatastoreService.Service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userserviceImpl;
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    public UserController(UserServiceImpl userserviceImpl) {
        this.userserviceImpl = userserviceImpl;
    }

   @PostMapping
    public ResponseEntity<Users> registerUser(@RequestBody Users user) {
        return ResponseEntity.ok(userserviceImpl.saveUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users updatedUser) {
        return ResponseEntity.ok(userserviceImpl.updateUser(id, updatedUser));
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userserviceImpl.getUserById(id);
    }

    // Get all users
    @GetMapping
    public List<Users> getAllUsers() {
        return userserviceImpl.getAllUsers();
    }

    // Get user by username
    @GetMapping("/username/{username}")
    public Users getUserByUsername(@PathVariable String username) {
        return userserviceImpl.getUserByUsername(username);
    }


}
