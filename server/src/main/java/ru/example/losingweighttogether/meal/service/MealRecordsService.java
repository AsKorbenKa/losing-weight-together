package ru.example.losingweighttogether.meal.service;

import ru.example.losingweighttogether.meal.dto.MealDto;
import ru.example.losingweighttogether.meal.dto.MealRecordsDto;

import java.util.List;

public interface MealRecordsService {
    MealRecordsDto createMealRecords(Long userId, List<MealDto> eatenMeal);
}
