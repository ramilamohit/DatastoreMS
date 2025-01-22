package com.tekarch.TafDatastoreService.Service.Interface;

import com.tekarch.TafDatastoreService.Model.Users;

import java.util.List;

public interface UserService {
    List<Users> getAllUsers();
    Users getUserById(Long id);
    Users getUserByUsername(String username);
}
