package ru.example.losingweighttogether.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.losingweighttogether.meal.model.Meal;

import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {
    Optional<Meal> findByName(String name);
}
