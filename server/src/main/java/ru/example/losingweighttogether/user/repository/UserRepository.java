package ru.example.losingweighttogether.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.losingweighttogether.user.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
