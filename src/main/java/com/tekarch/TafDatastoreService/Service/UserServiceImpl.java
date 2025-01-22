package com.tekarch.TafDatastoreService.Service;

import com.tekarch.TafDatastoreService.Model.Users;
import com.tekarch.TafDatastoreService.Repositories.UserRepository;
import com.tekarch.TafDatastoreService.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public Users getUserById(Long id) {
        Optional<Users> user = userRepository.findById(id);
        return user.orElse(null); // Return null if user is not found
    }

    // Get all users
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by username
    public Users getUserByUsername(String username) {
        Optional<Users> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }
    public Users saveUser(Users user) {
        return userRepository.save(user);
    }
    public Users updateUser(Long id, Users updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhone(updatedUser.getPhone());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

}
