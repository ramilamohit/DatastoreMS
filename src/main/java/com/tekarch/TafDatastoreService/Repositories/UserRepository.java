package com.tekarch.TafDatastoreService.Repositories;

import com.tekarch.TafDatastoreService.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findById(Long id);
    List<Users> findAll();
}
