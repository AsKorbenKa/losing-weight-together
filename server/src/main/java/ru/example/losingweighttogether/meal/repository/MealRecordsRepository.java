package ru.example.losingweighttogether.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.losingweighttogether.meal.model.MealRecords;

import java.time.LocalDate;
import java.util.List;

public interface MealRecordsRepository extends JpaRepository<MealRecords, Long> {
    List<MealRecords> findAllByUserIdAndMealDate(Long userId, LocalDate mealDate);
}
