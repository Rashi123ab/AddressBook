package com.rashi.AddressBook.Address.repository;

import com.rashi.AddressBook.Address.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
}

